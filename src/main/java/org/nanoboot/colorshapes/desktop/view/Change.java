
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

package org.nanoboot.colorshapes.desktop.view;

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.colorshapes.desktop.Task;
import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.view.windows.GameWindow;
import org.nanoboot.colorshapes.desktop.view.windows.SigningWindow;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Ball;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Bomb;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Field;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Rollable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.nanoboot.powerframework.collections.Stack;
import org.nanoboot.powerframework.simplicity.Simplicity;
import org.nanoboot.powerframework.simplicity.window.WindowColourSkin;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Change extends Thread {

    private final View view;
    private final ChangeReader changeReader;

    private static Stack<Field> highlightedFields = new Stack<>();

    private static int i = 0;

    /**
     * Constructor
     *
     * @param change command instructions
     * @param view goal
     */
    public Change(String change, View view) {
        this.view = view;
        this.changeReader = new ChangeReader(change);
        i++;
        this.setName(i + " " + change);
        start();
    }

    private void setLoginWindow(SigningWindow loginWindow) {
        this.view.setLoginWindow(loginWindow);
    }

    private SigningWindow getLoginWindow() {
        return this.view.getLoginWindow();
    }

    private void setGameWindow(GameWindow gameWindow) {
        this.view.setGameWindow(gameWindow);
    }

    private GameWindow getGameWindow() {
        return this.view.getGameWindow();
    }

    /**
     * Runs changes.
     */
    @Override
    public void run() {
        executeChange();
    }

    /*    
----------------------------------------------------------------
    MODE LOGIN|GAME
    GAME NEW 9 9 3 HOLES
    NICK|SCORE PLAYER|RECORD-HOLDER robert|120
    FIELD 4 9 GRID ON|OFF
    FIELD 4 9 WALL TOP|RIGHT|BOTTOM|LEFT ON|OFF
    FIELD 4 9|NEXT 2 ROLLABLE NEW BALL 1-16|0 VALUE-2 UNMOVEABLE|MOVEABLE UNBREAKABLE|BREAKABLE
    FIELD 4 9|NEXT 2 ROLLABLE NEW BOMB AUTOMATIC|MANUAL
    FIELD 4 9 ROLLABLE INFLATE
    FIELD 4 9 ROLLABLE JUMPING ON|OFF
    FIELD 4 9 ROLLABLE EXPLODE
    FIELD 4 9 ROLLABLE MOVE TO 4 8 TO 4 7 TO 4 6 TO 4 5 TO 4 4
    NEXT 2 CLEAR
    WAIT 4000
----------------------------------------------------------------
     */
    /**
     * Run one change. Splits command by spaces to parameters.<br/>
     * For example: BALL MOVE 5 7 9 6 will be split into 6 parameters.<br/>
     * This method choose right command set by switch statements
     */
    private void executeChange() {

        switch (changeReader.getNextWord()) {
            case "MODE":
                runMode();
                break;
            case "GAME":
                runGame();
                break;
            case "NICK":
                runNick();
                break;
            case "SCORE":
                runScore();
                break;
            case "FIELD":
                runField();
                break;
            case "NEXT":
                runNext();
                break;
            case "WAIT":
                runWait();
                break;
            case "":
                break;
            default:
                runError();
        }

    }

    /**
     *
     */
    private void runMode() {
        switch (changeReader.getNextWord()) {
            case "LOGIN":
                runModeLogin();
                break;
            case "GAME":
                runModeGame();
                break;
            default:
                runError();
        }
    }

    private void runModeLogin() {
        if (ColorShapes.getServices().getApplicationService().isRunning()) {

            Simplicity.setDefaultWindowColourSkin(new WindowColourSkin(ColorShapes.getServices().getCurrentLookService().getColourSkin()));
            Runnable runnable = ()
                    -> Platform.runLater(() -> {

                        SigningWindow loginWindow = new SigningWindow();
                        setLoginWindow(loginWindow);
                        if (view.getGameWindow() != null) {
                            view.getGameWindow().close();
                            view.getGameWindow().setClosed();
                        }
                        setGameWindow(null);
                        loginWindow.show();
                    });
            Task task = new Task("LOGIN", runnable);//NOSONAR
        }
    }

    private void runModeGame() {
        Simplicity.setDefaultWindowColourSkin(new WindowColourSkin(ColorShapes.getServices().getCurrentLookService().getColourSkin()));
        Runnable runnableGame = ()
                -> Platform.runLater(() -> {
                    if (getLoginWindow() != null) {
                        getLoginWindow().close();
                        setLoginWindow(null);
                    }
                    GameWindow gameWindow = new GameWindow();
                    setGameWindow(gameWindow);
                    gameWindow.show();
                });
        Task taskGame = new Task("GAME", runnableGame);//NOSONAR

    }

    private void runGame() {
        switch (changeReader.getNextWord()) {
            case "NEW":
                runGameNew();
                break;
            case "END":
                runGameEnd();
                break;
            default:
                runError();
        }
    }

    private void runGameNew() {
        int height = changeReader.getNextWordAsInt();
        int width = changeReader.getNextWordAsInt();
        int next = changeReader.getNextWordAsInt();
        Runnable runnableGame = ()
                -> Platform.runLater(() -> {
                    getGameWindow().updateBoard(height, width);
                    getGameWindow().updateNextBoard(next);

                });
        Task taskGame = new Task("GAME NEW", runnableGame);//NOSONAR

        if (changeReader.hasNextWord() && "HOLES".equals(changeReader.getNextWord())) {
            runGameNewHoles();
        }
        while (this.getGameWindow() == null || !this.getGameWindow().isInited()) {
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void runGameEnd() {
        int score = changeReader.getNextWordAsInt();

        Runnable runnableGame = ()
                -> Platform.runLater(()
                        -> getGameWindow().gameEnd()
                );
        Task task = new Task("GAME END", runnableGame);//NOSONAR

    }

    private void runGameNewHoles() {

        while ((changeReader.hasNextWord()) && ("TO".equals(changeReader.getNextWord()))) {

            int row = changeReader.getNextWordAsInt();
            int column = changeReader.getNextWordAsInt();

            Platform.runLater(()
                    -> setHole(row, column)
            );

        }
    }

    private void setHole(int row, int column) {
        //Not yet implemented.
    }

    private void runNick() {
        switch (changeReader.getNextWord()) {
            case "PLAYER":
                getGameWindow().updatePlayerNick(changeReader.getNextWord());
                break;
            case "RECORD-HOLDER":
                getGameWindow().updateRecordNick(changeReader.getNextWord());
                break;
            default:
                runError();
        }
    }

    private void runScore() {
        switch (changeReader.getNextWord()) {
            case "PLAYER":
                Platform.runLater(()
                        -> getGameWindow().updatePlayerScore(changeReader.getNextWord())
                );

                break;
            case "RECORD-HOLDER":
                Platform.runLater(()
                        -> getGameWindow().updateRecordScore(changeReader.getNextWord())
                );
                break;
            default:
                runError();
        }
    }

    private void runField() {
        int row = changeReader.getNextWordAsInt();
        int column = changeReader.getNextWordAsInt();
        Field field = getGameWindow().getField(row, column);
        switch (changeReader.getNextWord()) {
            case "GRID":
                runFieldGrid(field);
                break;
            case "WALL":
                runFieldWall(field);
                break;
            case "ROLLABLE":
                runFieldRollable(field);
                break;

            case "EXPLODE":
                runFieldRollableExplode(field);
                break;
            case "MOVE":
                runFieldRollableMove(field);
                break;

            default:
                runError();
        }
    }

    private void runFieldGrid(Field field) {

        switch (changeReader.getNextWord()) {
            case "ON":
                Platform.runLater(()
                        -> field.setGrid(true)
                );
                break;
            case "OFF":
                Platform.runLater(()
                        -> field.setGrid(false)
                );
                break;
            default:
                runError();
        }
    }

    private void runFieldWall(Field field) {
        String direction = changeReader.getNextWord();
        boolean value = "ON".equals(changeReader.getNextWord()) ? true : false;
        Platform.runLater(() -> {
            switch (direction) {
                case "TOP":
                    field.setTopWall(value);
                    break;
                case "RIGHT":
                    field.setRightWall(value);
                    break;
                case "BOTTOM":
                    field.setBottomWall(value);
                    break;
                case "LEFT":
                    field.setLeftWall(value);
                    break;
                default:
                    runError();
            }
        });
    }

    private void runFieldRollable(Field field) {
        switch (changeReader.getNextWord()) {
            case "NEW":
                runFieldRollableNew(field);
                break;
            case "INFLATE":
                runFieldRollableInflate(field);
                break;
            case "JUMPING":
                runFieldRollableJumping(field);
                break;
            case "EXPLODE":
                runFieldRollableExplode(field);
                break;
            case "MOVE":
                runFieldRollableMove(field);
                break;
            default:
                runError();
        }
    }

    private void runFieldRollableNew(Field field) {
        Platform.runLater(() -> {
            switch (changeReader.getNextWord()) {
                case "BALL":
                    runFieldRollableNewBall(field);
                    break;
                case "BOMB":
                    runFieldRollableNewBomb(field);
                    break;
                default:
                    runError();
            }
        });
        while (field.isEmpty()) {
            sleepToWait();
        }
    }

    private void runFieldRollableNewBall(Field field) {
        field.insertRollable(this.createBall(field.getParentForRollable()));
    }

    private void runFieldRollableNewBomb(Field field) {
        String bombType = changeReader.getNextWord();
        field.insertRollable(new Bomb(field.getParentForRollable(), "AUTOMATIC".equals(bombType)));
    }

    private void runFieldRollableInflate(Field field) {
        Platform.runLater(()
                -> field.getRollable().inflate()
        );
    }

    private void runFieldRollableJumping(Field field) {
        final boolean value = "ON".equals(changeReader.getNextWord()) ? true : false;
        Runnable runnableGame = () -> {
            if (value) {
                for (Field element : highlightedFields) {
                    element.highlightOff();
                }
            }
            Platform.runLater(() -> {
                if (field.hasBall() || (field.hasBomb() && !((Bomb) field.getRollable()).isAutomatic())) {

                    field.getRollable().jump(value);
                }
            });
        };
        Task taskGame = new Task("FIELD " + field.getRow() + " " + field.getColumn() + " ROLLABLE JUMPING ?", runnableGame);//NOSONAR

        sleepToWait(100);

    }

    private void runFieldRollableExplode(Field field) {
        Runnable runnableGame = () -> {
            Task task = new Task("inner FIELD " + field.getRow() + " " + field.getColumn() + " ROLLABLE EXPLODE", () -> {//NOSONAR
                Platform.runLater(() -> {
                    field.getRollable().explode();
                    field.highlightExplosion();
                });

                highlightedFields.push(field);

                while (((Node) field.getRollable()).getOpacity() > 0.2) {
                    sleepToWait(10);
                }
                Platform.runLater(() -> {
                    field.removeRollable();
                });
            });
        };
        Task taskGame = new Task("outside FIELD " + field.getRow() + " " + field.getColumn() + " ROLLABLE EXPLODE", runnableGame);//NOSONAR

    }

    private void runFieldRollableMove(Field field) {
        int endRow = Integer.parseInt(changeReader.getWord(changeReader.getCountOfWords() - 2));
        int endColumn = Integer.parseInt(changeReader.getWord(changeReader.getCountOfWords() - 1));
        final Field endField = getGameWindow().getField(endRow, endColumn);
        final Rollable startRollable = field.getRollable();
        Runnable task = () -> {
            Field tempField = field;
            final boolean highlightFields = "highlight".equals(Look.getBallMoveEffect());
            if (highlightFields) {
                tempField.highlightMovement();
                highlightedFields.push(tempField);
            }

            while (changeReader.hasNextWord() && "TO".equals(changeReader.getNextWord())) {
                sleepToWait(50);
                int row = changeReader.getNextWordAsInt();
                int column = changeReader.getNextWordAsInt();
                final Field currentField = tempField;
                final Field nextField = getGameWindow().getField(row, column);

                Platform.runLater(() -> {

                    if (highlightFields) {
                        nextField.highlightMovement();
                        highlightedFields.push(nextField);
                    }

                    getGameWindow().moveFromTo(currentField.getRow(), currentField.getColumn(), row, column);

                });
                tempField = nextField;

            }

        };
//task
// Run the task in a background thread
        Thread backgroundThread = new Thread(task, changeReader.toStringAndMarkWordWithCurrentIndex());
// Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
// Start the thread
        backgroundThread.start();

        while (endField.isEmpty() || !endField.getRollable().equals(startRollable)) {
            sleepToWait();

        }
    }

    private void runNext() {
        int column = changeReader.getNextWordAsInt();
        Field field = getGameWindow().getField(column);
        switch (changeReader.getNextWord()) {
            case "NEW":
                runNextNew(field);
                break;
            case "CLEAR":
                runNextClear(field);
                break;
            default:
                runError();
        }
    }

    private void runNextNew(Field field) {
        Platform.runLater(() -> {
            field.getParentForRollable().getChildren().clear();
            switch (changeReader.getNextWord()) {
                case "BALL":
                    field.insertRollable(this.createBall(field.getParentForRollable()));
                    break;
                case "BOMB":
                    String bombType = changeReader.getNextWord();
                    field.insertRollable(new Bomb(field.getParentForRollable(), "AUTOMATIC".equals(bombType)));
                    break;
                default:
                    runError();
            }
        });

    }

    private void runNextClear(Field field) {
        Platform.runLater(()
                -> field.getParentForRollable().getChildren().clear()
        );

    }

    private Ball createBall(StackPane parent) {
        int colour = changeReader.getNextWordAsInt();
        int value = changeReader.getNextWordAsInt();
        boolean unmoveable = "UNMOVEABLE".equals(changeReader.getNextWord());
        boolean unbreakable = "UNBREAKABLE".equals(changeReader.getNextWord());
        return new Ball(parent, colour, value, unmoveable, unbreakable);

    }

    private void runWait() {
        if (!changeReader.hasNextWord()) {
            runError();
        } else {
            int countOfMilliseconds = changeReader.getNextWordAsInt();
            sleepToWait(countOfMilliseconds);
        }

    }

    private void runError() {
        throw new ColorShapesRuntimeException(
                "Error while parsing drawing command: "
                + changeReader.toStringAndMarkWordWithCurrentIndex()
        );
    }

    private void sleepToWait() {
        sleepToWait(25);
    }

    private void sleepToWait(int countOfMilliseconds) {
        try {
            Thread.sleep(countOfMilliseconds);

        } catch (InterruptedException ex) {
            Logger.getLogger(Change.class
                    .getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }
}
