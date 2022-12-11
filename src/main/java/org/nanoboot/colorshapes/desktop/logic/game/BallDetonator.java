
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
import org.nanoboot.colorshapes.desktop.logic.composition.BallDetonatorComposition;
import org.nanoboot.colorshapes.desktop.logic.game.board.Ball;
import org.nanoboot.colorshapes.desktop.logic.game.board.Field;
import org.nanoboot.colorshapes.desktop.logic.game.board.Rollable;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.colorshapes.desktop.logic.game.board.Bomb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BallDetonator {

    private final BallDetonatorComposition ballDetonatorComposition;
    private final Game game;
    private Field fieldToCheck;
    private Field currentField;
    private boolean shapeWasFound;
    private ArrayList<Field> fieldWithExplodedBallsList = new ArrayList<>();
    private int colourToFind;

    private static final List<Coordination> coordinationsToExplodeByBombsList;

    static {
        coordinationsToExplodeByBombsList = new ArrayList<>();
        coordinationsToExplodeByBombsList.add(new Coordination(0, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(0, 1));
        coordinationsToExplodeByBombsList.add(new Coordination(0, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(1, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(1, 1));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(1, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, 1));

        coordinationsToExplodeByBombsList.add(new Coordination(0, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(0, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(2, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, 0));

        coordinationsToExplodeByBombsList.add(new Coordination(2, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(2, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, 2));

        coordinationsToExplodeByBombsList.add(new Coordination(1, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(2, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, 1));

        coordinationsToExplodeByBombsList.add(new Coordination(-1, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(1, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(2, 1));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, -1));

        coordinationsToExplodeByBombsList.add(new Coordination(0, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(0, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, 0));
        coordinationsToExplodeByBombsList.add(new Coordination(3, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(2, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, -2));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(1, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, 1));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(1, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(-1, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, -1));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, 1));
        coordinationsToExplodeByBombsList.add(new Coordination(-2, 3));
        coordinationsToExplodeByBombsList.add(new Coordination(2, -3));
        coordinationsToExplodeByBombsList.add(new Coordination(3, 2));
        coordinationsToExplodeByBombsList.add(new Coordination(-3, -2));

    }

    BallDetonator(BallDetonatorComposition ballDetonatorComposition, Game game) {
        this.ballDetonatorComposition = ballDetonatorComposition;
        this.game = game;
    }

    private void setToDefault(Field field) {
        if (field.isEmpty() || field.isLocked()) {
            throw new ColorShapesRuntimeException("Field is empty or locked");
        }
        this.fieldToCheck = field;
        this.currentField = this.fieldToCheck;
        this.shapeWasFound = false;
        this.fieldWithExplodedBallsList.clear();
        this.colourToFind = fieldToCheck.getRollable().isBall() ? (((Ball) fieldToCheck.getRollable()).getColour()) : -1;
    }

    /**
     *
     * @param field
     * @return
     */
    public List<Rollable> checkField(Field field) {
        setToDefault(field);
        List<Rollable> ballsList = new ArrayList<>();
        if (field.getRollable() instanceof Bomb) {
            Bomb bomb = (Bomb) field.getRollable();
            if (bomb.isExploded()) {
                int boardHeight = this.game.getBoard().getHeight();
                int boardWidth = this.game.getBoard().getWidth();
                int boardDimensionAverage = (int) Math.floor((boardHeight + boardWidth) / 2);
                if(boardDimensionAverage<5)boardDimensionAverage=5;
                int i = 0;
                int explodedBallsCount = 0;
                for (Coordination element : BallDetonator.coordinationsToExplodeByBombsList) {
                    i++;
                    if (i > ((int) Math.floor((boardDimensionAverage * 1.0 ))) || explodedBallsCount > boardDimensionAverage) {
                        break;
                    }
                    Field fieldToCheck = game.getBoard().getField(field.getRow() + element.getX(), field.getColumn() + element.getY());
                    if (fieldToCheck != null
                            && !fieldToCheck.isEmpty()
                            && ((fieldToCheck.getRollable() instanceof Ball)
                            ? !(((Ball) fieldToCheck.getRollable()).isUnbreakable())
                            : true)
                            && !fieldToCheck.isLocked()) {
                        if (fieldToCheck.getRollable() instanceof Bomb) {
                            Bomb bombInstance = (Bomb) fieldToCheck.getRollable();
                            if (!bombInstance.isExploded()) {
                                bombInstance.executeExplosion();
                            }

                        }
                        this.fieldWithExplodedBallsList.add(fieldToCheck);
                        explodedBallsCount++;
                    }
                }
                this.shapeWasFound = true;
            }

        } else {
            switch (this.ballDetonatorComposition.getExplodingShapeType()) {
                case LINE:
                    findLine();
                    break;
                case BLOCK:
                    findBlock();
                    break;
                case RING:
                    findRing();
                    break;
                case SQUARE:
                    findRectangle();
                    break;
                case CUSTOM:
                    findCustom();
                    break;
                default:
            }
        }

        if (shapeWasFound) {

            for (Field element : this.fieldWithExplodedBallsList) {

                ColorShapes.getLogic().addChange("FIELD " + element.getRow() + " " + element.getColumn() + " EXPLODE");

                Rollable removedBall = element.removeRollable();
                ballsList.add(removedBall);
                game.getBoard().addEmptyFieldToEmptyFields(element);

            }
            ColorShapes.getLogic().addChange("WAIT 1500");

            return ballsList;
        } else {
            return null;
        }
    }

    /**
     * Tries to find line shape of balls
     */
    public void findLine() {
        Direction[] directions = new Direction[]{Direction.TOP, Direction.RIGHT, Direction.TOPLEFT, Direction.TOPRIGHT};
        for (Direction direction : directions) {
            if (this.shapeWasFound) {
                break;
            } else {
                findLineInPosition(direction);
            }
        }
    }

    private void findLineInPosition(Direction direction) {
        this.currentField = this.fieldToCheck;
        while ((this.currentField != null)
                && !(this.currentField.isEmpty())
                && (this.currentField.getRollable().isBall())
                && (this.colourToFind == ((Ball) currentField.getRollable()).getColour()
                || ((Ball) currentField.getRollable()).isRainbow())
                && (!((Ball) currentField.getRollable()).isUnbreakable())
                && (!this.currentField.isLocked())
                && (direction.isSlant() ? true : !this.currentField.getWalls().isWallAtDirection(direction.overTurn()))
                && ((this.currentField.equals(this.fieldToCheck) || direction.isSlant()) ? true : !currentField.getWalls().isWallAtDirection(direction))) {
            this.fieldWithExplodedBallsList.add(currentField);
            this.currentField = this.currentField.getFieldAtDirection(direction);
        }

        this.currentField = this.fieldToCheck.getFieldAtDirection(direction.overTurn());

        while ((this.currentField != null)
                && !(this.currentField.isEmpty())
                && (this.currentField.getRollable().isBall())
                && (this.colourToFind == ((Ball) currentField.getRollable()).getColour()
                || ((Ball) currentField.getRollable()).isRainbow())
                && (!((Ball) currentField.getRollable()).isUnbreakable())
                && (!this.currentField.isLocked())
                && (direction.isSlant() ? true : !this.currentField.getWalls().isWallAtDirection(direction))
                && ((this.currentField.equals(this.fieldToCheck) || direction.isSlant()) ? true : (!currentField.getWalls().isWallAtDirection(direction.overTurn())))) {
            this.fieldWithExplodedBallsList.add(currentField);
            this.currentField = this.currentField.getFieldAtDirection(direction.overTurn());
        }
        if (this.fieldWithExplodedBallsList.size() >= this.ballDetonatorComposition.getMinimumLineLenght()) {
            this.shapeWasFound = true;
        } else {
            this.fieldWithExplodedBallsList.clear();
        }
    }

    private void findBlock() {
    }

    private void findRectangle() {
        String[] ballPositions = {"row1column1", "row1column2", "row2column1", "row2column2"};
        for (String ballPosition : ballPositions) {
            if (this.shapeWasFound) {
                break;
            } else {
                findRectangleInPosition(ballPosition);
            }

        }
    }

    private void findRectangleInPosition(String string) {
        this.currentField = this.fieldToCheck;
        Field field1 = null;
        Field field2 = null;
        Field field3 = null;
        Field fieldRow1Column1 = null;
        switch (string) {
            case "row1column1": {
                field1 = currentField.getFieldAtDirection(Direction.RIGHT);
                field2 = currentField.getFieldAtDirection(Direction.BOTTOM);
                field3 = currentField.getFieldAtDirection(Direction.BOTTOMRIGHT);
                fieldRow1Column1 = currentField;
            }
            break;
            case "row1column2": {
                field1 = currentField.getFieldAtDirection(Direction.LEFT);
                field2 = currentField.getFieldAtDirection(Direction.BOTTOMLEFT);
                field3 = currentField.getFieldAtDirection(Direction.BOTTOM);
                fieldRow1Column1 = field1;

            }
            break;
            case "row2column1": {
                field1 = currentField.getFieldAtDirection(Direction.TOP);
                field2 = currentField.getFieldAtDirection(Direction.TOPRIGHT);
                field3 = currentField.getFieldAtDirection(Direction.RIGHT);
                fieldRow1Column1 = field1;
            }
            break;
            case "row2column2": {
                field1 = currentField.getFieldAtDirection(Direction.TOPLEFT);
                field2 = currentField.getFieldAtDirection(Direction.TOP);
                field3 = currentField.getFieldAtDirection(Direction.LEFT);
                fieldRow1Column1 = field1;
            }
            break;
        }

        Field[] fields = {currentField, field1, field2, field3};
        for (Field field : fields) {
            if (!((field != null)
                    && !(field.isEmpty())
                    && (field.getRollable().isBall())
                    && (this.colourToFind == ((Ball) field.getRollable()).getColour()
                    || ((Ball) field.getRollable()).isRainbow())
                    && (!((Ball) field.getRollable()).isUnbreakable())
                    && (!field.isLocked()))) {
                return;
            }
        }
        if (fieldRow1Column1.getWalls().isRight()
                || fieldRow1Column1.getWalls().isBottom()
                || fieldRow1Column1.getFieldAtDirection(Direction.RIGHT).getWalls().isLeft()
                || fieldRow1Column1.getFieldAtDirection(Direction.RIGHT).getWalls().isBottom()
                || fieldRow1Column1.getFieldAtDirection(Direction.BOTTOM).getWalls().isTop()
                || fieldRow1Column1.getFieldAtDirection(Direction.BOTTOM).getWalls().isRight()
                || fieldRow1Column1.getFieldAtDirection(Direction.BOTTOMRIGHT).getWalls().isTop()
                || fieldRow1Column1.getFieldAtDirection(Direction.BOTTOMRIGHT).getWalls().isLeft()) {
            return;
        }
        for (Field field : fields) {
            this.fieldWithExplodedBallsList.add(field);
        }
        this.shapeWasFound = true;
    }

    private void findRing() {
        String[] ballPositions = {"row1column2", "row2column1", "row2column3", "row3column2"};
        for (String ballPosition : ballPositions) {
            if (this.shapeWasFound) {
                break;
            } else {
                findRingInPosition(ballPosition);
            }

        }
    }

    private void findRingInPosition(String string) {
        this.currentField = this.fieldToCheck;
        Field fieldRow2Column2 = null;
        switch (string) {
            case "row1column2": {
                fieldRow2Column2 = currentField.getFieldAtDirection(Direction.BOTTOM);
            }
            break;
            case "row2column1": {
                fieldRow2Column2 = currentField.getFieldAtDirection(Direction.RIGHT);
            }
            break;
            case "row2column3": {
                fieldRow2Column2 = currentField.getFieldAtDirection(Direction.LEFT);
            }
            break;
            case "row3column2": {
                fieldRow2Column2 = currentField.getFieldAtDirection(Direction.TOP);
            }
            break;
        }
        if (fieldRow2Column2 == null) {
            return;
        }
        Field field12 = fieldRow2Column2.getFieldAtDirection(Direction.TOP);
        Field field21 = fieldRow2Column2.getFieldAtDirection(Direction.LEFT);
        Field field23 = fieldRow2Column2.getFieldAtDirection(Direction.RIGHT);
        Field field32 = fieldRow2Column2.getFieldAtDirection(Direction.BOTTOM);
        Field[] fields = {field12, field21, field23, field32};
        for (Field field : fields) {
            if (!((field != null)
                    && !(field.isEmpty())
                    && (field.getRollable().isBall())
                    && (this.colourToFind == ((Ball) field.getRollable()).getColour()
                    || ((Ball) field.getRollable()).isRainbow())
                    && (!((Ball) field.getRollable()).isUnbreakable())
                    && (!field.isLocked()))) {
                return;
            }
        }

        for (Field field : fields) {
            this.fieldWithExplodedBallsList.add(field);
        }
        this.shapeWasFound = true;
    }

    /**
     * Tries to find custom shape.
     */
    public void findCustom() {
        throw new ColorShapesRuntimeException("Not yet implemented");
    }
}

class Coordination {

    private final int x;
    private final int y;

    Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
