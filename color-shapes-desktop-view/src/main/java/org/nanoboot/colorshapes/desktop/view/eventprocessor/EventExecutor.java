
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: The desktop UI for on Color Lines Engine.
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

package org.nanoboot.colorshapes.desktop.view.eventprocessor;

import org.nanoboot.colorshapes.engine.flow.event.core.Event;
import org.nanoboot.colorshapes.engine.flow.event.types.EventType;
import javafx.application.Platform;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Cell;
import org.nanoboot.powerframework.collections.PowerStack;
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class EventExecutor extends Thread {
    private final Event event;

    /**
     * Constructor.
     * @param eventIn change to execute
     */
    public EventExecutor(final Event eventIn) {
        this.event = eventIn;
    }

    /**
     * Runs changes.
     */
    @Override
    public void run() {
        executeChange();
    }

    /**
     * Run one change. Splits command by spaces to parameters.<br/>
     * For example: BALL MOVE 5 7 9 6 will be split into 6 parameters.<br/>
     * This method choose right command set by switch statements
     */
    private void executeChange() {
        if (event.getEventType() == EventType.NOTHING_TO_DO) {
            return;
        }
        addChange();

//        switch (changeReader.next()) {
//            case "MODE":
//                runMode();
//                break;
//            case "GAME":
//                runGame();
//                break;
//            case "NICK":
//                runNick();
//                break;
//            case "SCORE":
//                runScore();
//                break;
//            case "Cell":
//                runCell();
//                break;
//            case "NEXT":
//                runNext();
//                break;
//            case "WAIT":
//                runWait();
//                break;
//            case "":
//                break;
//            default:
//                runError();
//        }

    }









    //////
    @Deprecated
    private static PowerStack<Cell> highlightedCells = new PowerStack<>();

    /**
     * Adds new change to the canvas.
     */
    private void addChange() {
        Platform.runLater(()
                -> processChange()
        );
    }
    /**
     * Change processing.
     */
    private void processChange() {
//        switch (event.getEventType()) {
//            case CLEAR: canvas.clear(); break;
//            case NEW_GAME: canvas.newGame(
//                    event.getInteger(0),
//                    event.getInteger(1),
//                    event.getInteger(2));
//            case MOVE: canvas.move(event.getInteger(0),
//                    event.getInteger(1),
//                    event.getIntegerArray(3)
//            );
//            default :
//                String msg = "Unsupported change: " + event.getEventType();
//                throw new UnsupportedOperationException(msg);
//        }
    }



    /*
----------------------------------------------------------------
    MODE LOGIN|GAME
    GAME NEW 9 9 3 HOLES
    NICK|SCORE PLAYER|RECORD-HOLDER robert|120
    Cell 4 9 GRID ON|OFF
    Cell 4 9 WALL TOP|RIGHT|BOTTOM|LEFT ON|OFF
    Cell 4 9|NEXT 2 ROLLABLE NEW BALL 1-16|0 VALUE-2 UNMOVEABLE|MOVEABLE UNBREAKABLE|BREAKABLE

    Cell 4 9|NEXT 2 ROLLABLE NEW BOMB AUTOMATIC|MANUAL
    Cell 4 9 ROLLABLE INFLATE
    Cell 4 9 ROLLABLE JUMPING ON|OFF
    Cell 4 9 ROLLABLE EXPLODE
    Cell 4 9 ROLLABLE MOVE TO 4 8 TO 4 7 TO 4 6 TO 4 5 TO 4 4
    NEXT 2 CLEAR
    WAIT 4000
----------------------------------------------------------------
     */



    /**
     *
     */
    private void runMode() {
//        switch (changeReader.next()) {
//            case "LOGIN":
//                runModeLogin();
//                break;
//            case "GAME":
//                runModeGame();
//                break;
//            default:
//                runError();
//        }
    }

//    private void runModeLogin() {
//        if (applicationService.isRunning()) {
//
//            View.setDefaultWindowColourSkin(new WindowColourSkin(currentLookService.getColourSkin()));
//            Runnable runnable = ()
//                    -> Platform.runLater(() -> {
//
//                SigningWindow loginWindow = new SigningWindow();
//                setLoginWindow(loginWindow);
//                if (view.getGameWindow() != null) {
//                    view.getGameWindow().close();
//                    view.getGameWindow().setClosed();
//                }
//                setGameWindow(null);
//                loginWindow.show();
//            });
//            Task task = new Task("LOGIN", runnable);//NOSONAR
//        }
//    }

    private void runModeGame() {
//        View.setDefaultWindowColourSkin(new WindowColourSkin(currentLookService.getColourSkin()));
//        Runnable runnableGame = ()
//                -> Platform.runLater(() -> {
//            if (getLoginWindow() != null) {
//                getLoginWindow().close();
//                setLoginWindow(null);
//            }
//            GameWindow gameWindow = new GameWindow();
//            setGameWindow(gameWindow);
//            gameWindow.show();
//        });
//        Task taskGame = new Task("GAME", runnableGame);//NOSONAR

    }












//
//    private void runGame() {
//        switch (changeReader.next()) {
//            case "NEW":
//                runGameNew();
//                break;
//            case "END":
//                runGameEnd();
//                break;
////            default:
////                runError();
//        }
//    }
//
//    private void runGameNew() {
//        int height = changeReader.nextAsInt();
//        int width = changeReader.nextAsInt();
//        int next = changeReader.nextAsInt();
//        Runnable runnableGame = ()
//                -> Platform.runLater(() -> {
//            getGameWindow().updateBoard(height, width);
//            getGameWindow().updateNextBoard(next);
//
//        });
//        Task taskGame = new Task("GAME NEW", runnableGame);//NOSONAR
//
//        if (changeReader.hasNext() && "HOLES".equals(changeReader.next())) {
//            runGameNewHoles();
//        }
//        while (this.getGameWindow() == null || !this.getGameWindow().isInited()) {
//            try {
//                sleep(50);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private void runGameEnd() {
//        int score = changeReader.nextAsInt();
//
//        Runnable runnableGame = ()
//                -> Platform.runLater(()
//                -> getGameWindow().gameEnd()
//        );
//        Task task = new Task("GAME END", runnableGame);//NOSONAR
//
//    }
//
//    private void runGameNewHoles() {
//
//        while ((changeReader.hasNext()) && ("TO".equals(changeReader.next()))) {
//
//            int row = changeReader.nextAsInt();
//            int column = changeReader.nextAsInt();
//
//            Platform.runLater(()
//                    -> setHole(row, column)
//            );
//
//        }
//    }
//
//    private void setHole(int row, int column) {
//        //Not yet implemented.
//    }
//
//    private void runNick() {
//        switch (changeReader.next()) {
//            case "PLAYER":
//                getGameWindow().updatePlayerNick(changeReader.next());
//                break;
//            case "RECORD-HOLDER":
//                getGameWindow().updateRecordNick(changeReader.next());
//                break;
//            default:
//                runError();
//        }
//    }
//
//    private void runScore() {
//        switch (changeReader.next()) {
//            case "PLAYER":
//                Platform.runLater(()
//                        -> getGameWindow().updatePlayerScore(changeReader.next())
//                );
//
//                break;
//            case "RECORD-HOLDER":
//                Platform.runLater(()
//                        -> getGameWindow().updateRecordScore(changeReader.next())
//                );
//                break;
//            default:
//                runError();
//        }
//    }
//
//    private void runCell() {
//        int row = changeReader.nextAsInt();
//        int column = changeReader.nextAsInt();
//        Cell cell = getGameWindow().getCell(row, column);
//        switch (changeReader.next()) {
//            case "GRID":
//                runCellGrid(cell);
//                break;
//            case "WALL":
//                runCellWall(cell);
//                break;
//            case "ROLLABLE":
//                runCellRollable(cell);
//                break;
//
//            case "EXPLODE":
//                runCellRollableExplode(cell);
//                break;
//            case "MOVE":
//                runCellRollableMove(cell);
//                break;
//
//            default:
//                runError();
//        }
//    }
//
//    private void runCellGrid(Cell cell) {
//
//        switch (changeReader.next()) {
//            case "ON":
//                Platform.runLater(()
//                        -> cell.setGrid(true)
//                );
//                break;
//            case "OFF":
//                Platform.runLater(()
//                        -> cell.setGrid(false)
//                );
//                break;
//            default:
//                runError();
//        }
//    }
//
//    private void runCellWall(Cell cell) {
//        String direction = changeReader.next();
//        boolean value = "ON".equals(changeReader.next());
//        Platform.runLater(() -> {
//            switch (direction) {
//                case "TOP":
//                    cell.setTopWall(value);
//                    break;
//                case "RIGHT":
//                    cell.setRightWall(value);
//                    break;
//                case "BOTTOM":
//                    cell.setBottomWall(value);
//                    break;
//                case "LEFT":
//                    cell.setLeftWall(value);
//                    break;
//                default:
//                    runError();
//            }
//        });
//    }
//
//    private void runCellRollable(Cell cell) {
//        switch (changeReader.next()) {
//            case "NEW":
//                runCellRollableNew(cell);
//                break;
//            case "INFLATE":
//                runCellRollableInflate(cell);
//                break;
//            case "JUMPING":
//                runCellRollableJumping(cell);
//                break;
//            case "EXPLODE":
//                runCellRollableExplode(cell);
//                break;
//            case "MOVE":
//                runCellRollableMove(cell);
//                break;
//            default:
//                runError();
//        }
//    }
//
//
//
//    private void runCellRollableNew(Cell cell) {
//        Platform.runLater(() -> {
//            switch (changeReader.next()) {
//                case "BALL":
//                    runCellRollableNewBall(cell);
//                    break;
//                case "BOMB":
//                    runCellRollableNewBomb(cell);
//                    break;
//                default:
//                    runError();
//            }
//        });
//        while (cell.isEmpty()) {
//            sleepToWait();
//        }
//    }
//
//    private void runCellRollableNewBall(Cell cell) {
//        cell.insertRollable(this.createBall(cell.getParentForRollable()));
//    }
//
//    private void runCellRollableNewBomb(Cell cell) {
//        String bombType = changeReader.next();
//        cell.insertRollable(new Bomb(cell.getParentForRollable(), "AUTOMATIC".equals(bombType)));
//    }
//
//    private void runCellRollableInflate(Cell cell) {
//        Platform.runLater(()
//                -> cell.getRollable().inflate()
//        );
//    }
//
//    private void runCellRollableJumping(Cell cell) {
//        final boolean value = "ON".equals(changeReader.next());
//        Runnable runnableGame = () -> {
//            if (value) {
//                for (Cell element : highlightedCells) {
//                    element.highlightOff();
//                }
//            }
//            Platform.runLater(() -> {
//                if (cell.hasBall() || (cell.hasBomb() && !((Bomb) cell.getRollable()).isAutomatic())) {
//
//                    cell.getRollable().jump(value);
//                }
//            });
//        };
//        Task taskGame = new Task("Cell " + cell.getRow() + " " + cell.getColumn() + " ROLLABLE JUMPING ?", runnableGame);//NOSONAR
//
//        sleepToWait(100);
//
//    }
//
//    private void runCellRollableExplode(Cell cell) {
//        Runnable runnableGame = () -> {
//            Task task = new Task("inner Cell " + cell.getRow() + " " + cell.getColumn() + " ROLLABLE EXPLODE", () -> {//NOSONAR
//                Platform.runLater(() -> {
//                    cell.getRollable().explode();
//                    cell.highlightExplosion();
//                });
//
//                highlightedCells.push(cell);
//
//                while (((Node) cell.getRollable()).getOpacity() > 0.2) {
//                    sleepToWait(10);
//                }
//                Platform.runLater(() -> {
//                    cell.removeRollable();
//                });
//            });
//        };
//        Task taskGame = new Task("outside Cell " + cell.getRow() + " " + cell.getColumn() + " ROLLABLE EXPLODE", runnableGame);//NOSONAR
//
//    }
//
//    private void runCellRollableMove(Cell cell) {
//        int endRow = Integer.parseInt(changeReader.getCommandByNumber(changeReader.size() - 2));
//        int endColumn = Integer.parseInt(changeReader.getCommandByNumber(changeReader.size() - 1));
//        final Cell endCell = getGameWindow().getCell(endRow, endColumn);
//        final Rollable startRollable = cell.getRollable();
//        Runnable task = () -> {
//            Cell tempCell = cell;
//            final boolean highlightCells = "highlight".equals(Look.getBallMoveEffect());
//            if (highlightCells) {
//                tempCell.highlightMovement();
//                highlightedCells.push(tempCell);
//            }
//
//            while (changeReader.hasNext() && "TO".equals(changeReader.next())) {
//                sleepToWait(50);
//                int row = changeReader.nextAsInt();
//                int column = changeReader.nextAsInt();
//                final Cell currentCell = tempCell;
//                final Cell nextCell = getGameWindow().getCell(row, column);
//
//                Platform.runLater(() -> {
//
//                    if (highlightCells) {
//                        nextCell.highlightMovement();
//                        highlightedCells.push(nextCell);
//                    }
//
//                    getGameWindow().moveFromTo(currentCell.getRow(), currentCell.getColumn(), row, column);
//
//                });
//                tempCell = nextCell;
//
//            }
//
//        };
////task
//// Run the task in a background thread
//        Thread backgroundThread = new Thread(task, changeReader.toStringForDebuggingPurposes());
//// Terminate the running thread if the application exits
//        backgroundThread.setDaemon(true);
//// Start the thread
//        backgroundThread.start();
//
//        while (endCell.isEmpty() || !endCell.getRollable().equals(startRollable)) {
//            sleepToWait();
//
//        }
//    }
//
//    private void runNext() {
//        int column = changeReader.nextAsInt();
//        Cell cell = getGameWindow().getCell(column);
//        switch (changeReader.next()) {
//            case "NEW":
//                runNextNew(cell);
//                break;
//            case "CLEAR":
//                runNextClear(cell);
//                break;
//            default:
//                runError();
//        }
//    }
//
//    private void runNextNew(Cell cell) {
//        Platform.runLater(() -> {
//            cell.getParentForRollable().getChildren().clear();
//            switch (changeReader.next()) {
//                case "BALL":
//                    cell.insertRollable(this.createBall(cell.getParentForRollable()));
//                    break;
//                case "BOMB":
//                    String bombType = changeReader.next();
//                    cell.insertRollable(new Bomb(cell.getParentForRollable(), "AUTOMATIC".equals(bombType)));
//                    break;
//                default:
//                    runError();
//            }
//        });
//
//    }
//
//    private void runNextClear(Cell cell) {
//        Platform.runLater(()
//                -> cell.getParentForRollable().getChildren().clear()
//        );
//
//    }
//
//    private Ball createBall(StackPane parent) {
//        int colour = changeReader.nextAsInt();
//        int value = changeReader.nextAsInt();
//        boolean unmoveable = "UNMOVEABLE".equals(changeReader.next());
//        boolean unbreakable = "UNBREAKABLE".equals(changeReader.next());
//        return new Ball(parent, colour, value, unmoveable, unbreakable);
//
//    }
//
//    private void runWait() {
//        if (!changeReader.hasNext()) {
//            runError();
//        } else {
//            int countOfMilliseconds = changeReader.nextAsInt();
//            sleepToWait(countOfMilliseconds);
//        }
//
//    }
//
//    private void runError() {
//        throw new ColorShapesDesktopException(
//                "Error while parsing drawing command: "
//                        + changeReader.toStringForDebuggingPurposes()
//        );
//    }
//
//    private void sleepToWait() {
//        sleepToWait(25);
//    }
//
//    private void sleepToWait(int countOfMilliseconds) {
//        try {
//            Thread.sleep(countOfMilliseconds);
//
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Change.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            Thread.currentThread().interrupt();
//        }
//    }
}
