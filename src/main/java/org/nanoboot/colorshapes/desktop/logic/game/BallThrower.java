
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

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.logic.composition.BallThrowerComposition;
import org.nanoboot.colorshapes.desktop.logic.game.board.Ball;
import org.nanoboot.colorshapes.desktop.logic.game.board.Bomb;
import org.nanoboot.colorshapes.desktop.logic.game.board.Field;
import org.nanoboot.colorshapes.desktop.logic.game.board.Rollable;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import java.util.List;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class BallThrower {

    private final BallThrowerComposition ballThrowerComposition;
    private final PseudoRandomGenerator pseudoRandomGenerator;
    private final BallFactory ballFactory;
    private final BallDetonator ballDetonator;
    private final TotalScore totalScore;

    private final Game game;
    private boolean started = false;
    private final int ballFrequencyMax;
    private final int automaticFrequencyMax;
    private final int manualBombFrequecyMax;

    private final Rollable[] rollableArray;

    BallThrower(BallThrowerComposition ballThrowerComposition, PseudoRandomGenerator pseudoRandomGenerator, BallFactory ballFactory, BallDetonator ballDetonator, TotalScore totalScore, Game game) {
        this.ballThrowerComposition = ballThrowerComposition;
        this.ballDetonator = ballDetonator;
        this.totalScore = totalScore;
        this.pseudoRandomGenerator = pseudoRandomGenerator;
        this.ballFactory = ballFactory;
        this.game = game;
        ballFrequencyMax = this.ballThrowerComposition.getBallFrequency();
        automaticFrequencyMax = ballFrequencyMax + this.ballThrowerComposition.getAutomaticBombFrequency();
        manualBombFrequecyMax = automaticFrequencyMax + this.ballThrowerComposition.getManualBombFrequency();
        this.rollableArray = new Rollable[this.ballThrowerComposition.getCountOfBallsThrownDuringTheGame()];
    }

    private void fillRollable() {
        for (int i = 0; i < this.rollableArray.length; i++) {
            Rollable rollable = this.getRandomRollable();
            this.rollableArray[i] = rollable;
            if (rollable instanceof Ball) {
                Ball ball = (Ball) rollable;
                ColorShapes.getLogic().addChange("NEXT " + (i + 1) + " NEW BALL " + ball.getColour() + " " + ball.getValue() + " " + (ball.isUnmoveable() ? "UNMOVEABLE" : "MOVEABLE") + " " + (ball.isUnbreakable() ? "UNBREAKABLE" : "BREAKABLE"));

            } else {
                Bomb bomb = (Bomb) rollable;
                ColorShapes.getLogic().addChange("NEXT " + (i + 1) + " NEW BOMB " + (bomb.isAutomatic() ? "AUTOMATIC" : "MANUAL"));

            }
        }
    }

    private Rollable getRandomRollable() {
        int number = this.pseudoRandomGenerator.getInt(1, this.manualBombFrequecyMax);
        if (number <= ballFrequencyMax) {
            return this.ballFactory.getNextBall();
        }
        if (number <= automaticFrequencyMax) {
            return new Bomb(true);
        }
        if (number <= manualBombFrequecyMax) {
            return new Bomb(false);
        }
        throw new ColorShapesRuntimeException("Fatal error");
    }

    /**
     * Throws ball at the beginning of the game
     */
    public void throwBallsAtTheBeginningOfTheGame() {
        if (started) {
            throw new ColorShapesRuntimeException("Game has started.");
        }
        this.started = true;

        fillRollable();

        for (int i = 1; i <= this.ballThrowerComposition.getCountOfBallsThrownAtTheBeginningOfTheGame(); i++) {

            insertRandomRollableIntoRandomEmptyField(getRandomRollable());

        }
        game.waitForPlayerAction();

    }

    private void insertRandomRollableIntoRandomEmptyField(Rollable rollable) {

        Field field = this.game.getRandomEmptyField();

        field.insertRollable(rollable);
        if (rollable.isBall()) {
            Ball ball = (Ball) rollable;
            ColorShapes.getLogic().addChange(
                    ChangeCommands.FIELD
                    + ChangeCommands.number(field.getRow())
                    + ChangeCommands.number(field.getColumn())
                    + ChangeCommands.ROLLABLE
                    + ChangeCommands.NEW
                    + ChangeCommands.BALL
                    + ChangeCommands.number(ball.getColour())
                    + ChangeCommands.number(ball.getValue())
                    + (ball.isUnmoveable() ? ChangeCommands.UNMOVEABLE : ChangeCommands.MOVEABLE)
                    + (ball.isUnbreakable() ? ChangeCommands.UNBREAKABLE : ChangeCommands.BREAKABLE));
            ColorShapes.getLogic().addChange(
                    ChangeCommands.FIELD
                    + ChangeCommands.number(field.getRow())
                    + ChangeCommands.number(field.getColumn())
                    + ChangeCommands.ROLLABLE
                    + ChangeCommands.INFLATE);
        } else if (rollable.isBomb()) {
            Bomb bomb = (Bomb) rollable;
            ColorShapes.getLogic().addChange(
                    ChangeCommands.FIELD
                    + ChangeCommands.number(field.getRow())
                    + ChangeCommands.number(field.getColumn())
                    + ChangeCommands.ROLLABLE
                    + ChangeCommands.NEW
                    + ChangeCommands.BOMB
                    + (bomb.isAutomatic() ? ChangeCommands.AUTOMATIC : ChangeCommands.MANUAL)
            );
        } else {
            throw new ColorShapesRuntimeException("Rollable is not ball and is not bomb. This is illegal state.");
        }
        List<Rollable> listOfExplodedRollable = ballDetonator.checkField(field);

        if (rollable.isBall()) {
            int addedScore = totalScore.addListOfExplodedRollablesToCheck(listOfExplodedRollable);
        }

    }

    /**
     * Throw balls during the game
     */
    public void throwBallsDuringTheGame() {
        for (Rollable rollable : this.rollableArray) {
            if (!game.getBoard().isThereAnEmptyField()) {
                game.end(false);
                return;
            }
            this.insertRandomRollableIntoRandomEmptyField(rollable);

        }
        fillRollable();
        game.waitForPlayerAction();
    }
}
