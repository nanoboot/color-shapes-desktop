
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: A logic game based on Color linez game.
// Copyright (C) 2016-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.colorshapes.desktop.logic.game;

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.colorshapes.desktop.domain.GameActionTable;
import org.nanoboot.colorshapes.desktop.domain.GameTable;
import org.nanoboot.colorshapes.desktop.domain.PseudoRandomGeneratorTable;
import org.nanoboot.colorshapes.desktop.domain.SessionTable;
import org.nanoboot.colorshapes.desktop.domain.UniversalDateTimeTable;
import org.nanoboot.colorshapes.desktop.logic.game.board.Board;
import org.nanoboot.colorshapes.desktop.logic.game.board.Field;
import org.nanoboot.colorshapes.desktop.logic.composition.GameComposition;
import org.nanoboot.colorshapes.desktop.logic.game.board.Rollable;
import org.nanoboot.colorshapes.desktop.Task;
import org.nanoboot.colorshapes.desktop.logic.Logic;
import org.nanoboot.colorshapes.desktop.logic.game.board.Ball;
import org.nanoboot.colorshapes.desktop.logic.game.board.Bomb;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nanoboot.powerframework.collections.Stack;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Game {

    private boolean hasBeenStarted = false;
    private final GameComposition gameComposition;
    private final Board board;
    private final BallFactory ballFactory;
    private final BallThrower ballThrower;
    private final BallDetonator ballDetonator;

    private final PseudoRandomGenerator pseudoRandomGenerator;
    private final int gameId;
    private final int sessionId;
    private final TotalScore totalScore;

    private ArrayList<GameAction> gameActionList = new ArrayList<GameAction>();
    private volatile boolean ended = false;

    private final Queue<GameAction> activatedFieldsQueue;

    Task checkingForFieldActivationStoppableTask;

    /**
     *
     * @param gameComposition
     */
    public Game(GameComposition gameComposition) {

//        if (!gameCompilation.isThisObjectLocked()) {
//            throw new IllegalArgumentException();
//        }
        int magicNumber = PseudoRandomGenerator.getInstance().getInt(0, 1000000000);
        int universalDateTimeId = UniversalDateTimeTable.saveCurrentUniversalDateTime();
        int pseudoRandomGeneratorId = PseudoRandomGeneratorTable.savePseudoRandomGenerator(magicNumber, universalDateTimeId);
        pseudoRandomGenerator = PseudoRandomGeneratorTable.getPseudoRandomGenerator(pseudoRandomGeneratorId);

        int startUniversalDateTimeId = UniversalDateTimeTable.saveCurrentUniversalDateTime();
        sessionId = SessionTable.saveSession(startUniversalDateTimeId);
        gameId = GameTable.saveGameTable(sessionId, ColorShapes.getLogic().getPlayer().getPlayerId(), ColorShapes.getLogic().getGameManager().getGameCompositionId(), pseudoRandomGeneratorId);
        //GameCompositionTable.getId(gameComposition)

        this.gameComposition = gameComposition;

        this.ballFactory = new BallFactory(gameComposition.getBallFactoryComposition(), pseudoRandomGenerator);
        this.ballDetonator = new BallDetonator(gameComposition.getBallDetonatorComposition(), this);
        this.totalScore = new TotalScore(this.gameComposition.getBallDetonatorComposition().getMinimumCountOfBalls());
        this.ballThrower = new BallThrower(gameComposition.getBallThrowerComposition(), pseudoRandomGenerator, ballFactory, ballDetonator, totalScore, this);

        this.board = new Board(gameComposition.getBoardComposition(), pseudoRandomGenerator, ballThrower);
        this.activatedFieldsQueue = new ConcurrentLinkedQueue<>();
        hasBeenStarted = false;

        checkingForFieldActivationStoppableTask = new Task("Checking for field activation", () -> {
            while (!this.ended) {
                if (!this.activatedFieldsQueue.isEmpty()) {
                    GameAction gameAction = this.activatedFieldsQueue.poll();

                    this.activateGameAction(gameAction);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
            }
        });

        this.start();

    }

    /**
     * Starts the game
     */
    public void start() {
        if (hasBeenStarted) {
            throw new IllegalStateException();
        }
        hasBeenStarted = true;
        ColorShapes.getLogic().addChange(
                "GAME NEW "
                + board.getHeight()
                + " "
                + board.getWidth()
                + " "
                + this.gameComposition.getBallThrowerComposition().getCountOfBallsThrownDuringTheGame()
        );
        ColorShapes.getLogic().addChange("WAIT 1000");
        ColorShapes.getLogic().addChange("SCORE PLAYER 0");
        this.board.start();
    }

    /**
     * Saves game state
     */
    public void save() {
        throw new UnsupportedOperationException();
    }

    /**
     * Ends the game
     */
    public void end(boolean aborting) {
        if (ended) {
            return;
        }
        this.ended = true;

        int currentUniversalDateTimeId = UniversalDateTimeTable.saveCurrentUniversalDateTime();
        SessionTable.setEndUniversalDateTimeId(sessionId, currentUniversalDateTimeId);

        GameTable.setScore(gameId, totalScore.getCurrentTotalScore());
        this.totalScore.lockForChanges();

        if (aborting) {
            return;
        }

        Task task = new Task("Game end(",
                () -> {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                    ColorShapes.getLogic().addChange("GAME END " + this.totalScore.getCurrentTotalScore());
                }
        );
    }

    /**
     * Activates field
     *
     * @param row
     * @param column
     */
    public void activateField(int row, int column) {
        this.activatedFieldsQueue.add(new GameAction(row, column));
    }

    /**
     *
     * @param row
     * @param column
     */
    private void activateGameAction(GameAction gameAction) {
        int row = gameAction.getRow();
        int column = gameAction.getColumn();

        Field deactivatedField = board.hasActivatedField() ? board.deactivateActivatedField() : null;
        Field activatedField = board.activateField(row, column);
        if (activatedField.equals(deactivatedField)) {
            Rollable rollable = activatedField.getRollable();
            if (rollable instanceof Bomb) {
                Bomb bomb = (Bomb) rollable;
                if (bomb.isManual()) {
                    bomb.executeExplosion();
                }
            }
        }

        if (!activatedField.isEmpty() && activatedField.getRollable() instanceof Bomb && ((Bomb) activatedField.getRollable()).isExploded()) {
            ballDetonator.checkField(activatedField);
            board.deactivateActivatedField();
        } else {
            if (activatedField.isEmpty()) {
                if (deactivatedField != null) {
                    checkEmptyActivatedFieldAndNotEmptyDeactivatedField(deactivatedField, activatedField);
                }
                if (ended()) {
                    return;
                }
                board.deactivateActivatedField();
            }
        }
        int universalDateTimeId = UniversalDateTimeTable.saveUniversalDateTime(gameAction.getUniversalDateTime());
        GameActionTable.saveGameActionTable(gameId, universalDateTimeId, row, column);

        if (!board.isThereAnEmptyField()) {
            end(false);
            return;
        }
    }

    private void checkEmptyActivatedFieldAndNotEmptyDeactivatedField(Field deactivatedField, Field activatedField) {
        final Field fieldFrom = deactivatedField;
        final Field fieldTo = activatedField;
        Rollable rollable = fieldFrom.getRollable();
        if (rollable instanceof Ball) {
            Ball ball = (Ball) rollable;
            if (ball.isUnmoveable()) {
                return;
            }
        }
        if (rollable instanceof Bomb && (((Bomb) rollable)).isAutomatic()) {
            return;
        }
        PathFinder pathFinder = new PathFinder(fieldFrom, fieldTo);
        Stack<Field> pathStack = pathFinder.findPath();

        boolean wasPathFound = pathStack != null;
        if (wasPathFound) {

            board.moveRollableFromTo(fieldFrom, fieldTo, pathStack);

            List<Rollable> listOfExplodedRollable = ballDetonator.checkField(fieldTo);

            int addedScore = totalScore.addListOfExplodedRollablesToCheck(listOfExplodedRollable);

            if (addedScore == 0) {
                this.ballThrower.throwBallsDuringTheGame();
            }

        }
    }

    /**
     * Waits for player action
     */
    public void waitForPlayerAction() {
        this.board.waitForPlayerAction();
    }

    /**
     *
     * @return
     */
    public Field getRandomEmptyField() {
        return this.board.getRandomEmptyField();
    }

    Board getBoard() {
        return board;
    }

    /**
     * Adds the ended flag to the game
     *
     * @return
     */
    public boolean ended() {
        return this.ended;
    }
}
