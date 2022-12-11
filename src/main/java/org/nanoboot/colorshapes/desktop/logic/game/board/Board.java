
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

package org.nanoboot.colorshapes.desktop.logic.game.board;

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.logic.composition.BoardComposition;
import org.nanoboot.colorshapes.desktop.logic.game.BallThrower;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import java.util.ArrayList;
import org.nanoboot.powerframework.collections.Stack;
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Board {

    private final BoardComposition boardComposition;
    private final PseudoRandomGenerator pseudoRandomGenerator;
    private final BallThrower ballThrower;

    private final Field[][] fields;
    private final ArrayList<Field> emptyFields = new ArrayList<>();
    private final ArrayList<Field> lockFields = new ArrayList<>();
    private final ArrayList<Field> fieldsWithWalls = new ArrayList<>();

    private Field activatedField = null;

    private final int gridProbability;
    private final int gridCount;

    private final int wallProbability;
    private final int wallCount;

    private static final String FIELD = "FIELD ";

    /**
     *
     * @param boardModule
     * @param pseudoRandomGenerator
     * @param ballThrower
     */
    public Board(BoardComposition boardModule, PseudoRandomGenerator pseudoRandomGenerator, BallThrower ballThrower) {
        this.boardComposition = boardModule;
        this.pseudoRandomGenerator = pseudoRandomGenerator;
        this.fields = new Field[this.boardComposition.getShape().getHeight() + 1][this.boardComposition.getShape().getWidth() + 1];
        for (int row = 1; row <= this.getHeight(); row++) {
            for (int column = 1; column <= this.getWidth(); column++) {
                this.fields[row][column] = new Field(row, column, this);
                this.emptyFields.add(fields[row][column]);
            }
        }

        this.ballThrower = ballThrower;

        gridProbability = boardComposition.getGridProbability();
        gridCount = boardComposition.getGridCount();

        wallProbability = boardComposition.getWallProbability();
        wallCount = boardComposition.getWallCount();
    }

    /**
     * Starts board
     */
    public void start() {
        this.ballThrower.throwBallsAtTheBeginningOfTheGame();
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Field getField(int row, int column) {
        if (row <= 0 || column <= 0 || row > this.getHeight() || column > this.getWidth()) {
            return null;
        }
        return this.fields[row][column];
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return this.boardComposition.getShape().getHeight();
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return this.boardComposition.getShape().getWidth();
    }

    /**
     * Creates grids and walls
     */
    public void waitForPlayerAction() {
        for (Field element : lockFields) {
            element.setLocked(false);
            if (element.isEmpty() && !this.emptyFields.contains(element)) {
                this.emptyFields.add(element);
            }
        }
        lockFields.clear();
        for (Field element : fieldsWithWalls) {
            element.getWalls().destroyAll();
            ColorShapes.getLogic().addChange(FIELD + element.getRow() + " " + element.getColumn() + " WALL TOP OFF");
            ColorShapes.getLogic().addChange(FIELD + element.getRow() + " " + element.getColumn() + " WALL RIGHT OFF");
            ColorShapes.getLogic().addChange(FIELD + element.getRow() + " " + element.getColumn() + " WALL BOTTOM OFF");
            ColorShapes.getLogic().addChange(FIELD + element.getRow() + " " + element.getColumn() + " WALL LEFT OFF");
        }
        fieldsWithWalls.clear();

        if (this.gridProbability != 0 && this.gridProbability >= this.pseudoRandomGenerator.getInt(0, 100)) {
            for (int i = 1; i <= this.gridCount; i++) {
                int row = this.pseudoRandomGenerator.getInt(1, this.getHeight());
                int column = this.pseudoRandomGenerator.getInt(1, this.getWidth());
                Field field = this.getField(row, column);
                if (lockFields.contains(field)) {
                    continue;
                }
                field.setLocked(true);
                this.lockFields.add(field);
                if (this.emptyFields.contains(field)) {
                    this.emptyFields.remove(field);
                }

            }
        }
        if (this.wallProbability != 0 && this.wallProbability >= this.pseudoRandomGenerator.getInt(0, 100)) {

            for (int i = 1; i <= this.wallCount; i++) {
                int row = this.pseudoRandomGenerator.getInt(1, this.getHeight());
                int column = this.pseudoRandomGenerator.getInt(1, this.getWidth());
                int direction = this.pseudoRandomGenerator.getInt(1, 4);

                Field field = this.getField(row, column);
                Walls walls = field.getWalls();
                switch (direction) {
                    case 1:
                        walls.setTop(true);
                        ColorShapes.getLogic().addChange(FIELD + field.getRow() + " " + field.getColumn() + " WALL TOP ON");
                        break;
                    case 2:
                        walls.setRight(true);
                        ColorShapes.getLogic().addChange(FIELD + field.getRow() + " " + field.getColumn() + " WALL RIGHT ON");
                        break;

                    case 3:
                        walls.setBottom(true);
                        ColorShapes.getLogic().addChange(FIELD + field.getRow() + " " + field.getColumn() + " WALL BOTTOM ON");
                        break;
                    case 4:
                        walls.setLeft(true);
                        ColorShapes.getLogic().addChange(FIELD + field.getRow() + " " + field.getColumn() + " WALL LEFT ON");
                        break;
                }
                fieldsWithWalls.add(field);

            }
        }

    }

    /**
     *
     * @return
     */
    public Field getRandomEmptyField() {
        if (this.emptyFields.isEmpty()) {
            return null;
        }
        Field field = this.emptyFields.get(this.pseudoRandomGenerator.getInt(0, emptyFields.size() - 1));
        this.emptyFields.remove(field);
        return field;
    }

    /**
     *
     * @param field
     */
    public void addEmptyFieldToEmptyFields(Field field) {
        this.emptyFields.add(field);
    }

    /**
     *
     * @return
     */
    public boolean hasActivatedField() {
        return activatedField != null;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Field activateField(int row, int column) {

        if (this.activatedField != null) {
            throw new ColorShapesRuntimeException("Another field is activated. This field can't be activated");
        }
        this.activatedField = this.getField(row, column);

        if (!(activatedField.isEmpty()
                || activatedField.isLocked()
                || (activatedField.getRollable().isBall() && ((Ball) activatedField.getRollable()).isUnmoveable())
                || (activatedField.getRollable().isBomb() && ((Bomb) activatedField.getRollable()).isAutomatic()))) {

            ColorShapes.getLogic().addChange(FIELD + activatedField.getRow() + " " + activatedField.getColumn() + " ROLLABLE JUMPING ON");
        }

        return this.activatedField;
    }

    /**
     *
     * @return
     */
    public Field deactivateActivatedField() {
        Field tempField = activatedField;
        activatedField = null;
        if (!(tempField.isEmpty()
                || tempField.isLocked()
                || (tempField.getRollable().isBall() && ((Ball) tempField.getRollable()).isUnmoveable())
                || (tempField.getRollable().isBomb() && ((Bomb) tempField.getRollable()).isAutomatic()))) {
            ColorShapes.getLogic().addChange(FIELD + tempField.getRow() + " " + tempField.getColumn() + " ROLLABLE JUMPING OFF");
        }
        return tempField;
    }

    /**
     *
     * @param fieldFrom
     * @param fieldTo
     * @param pathStack
     */
    public void moveRollableFromTo(Field fieldFrom, Field fieldTo, Stack<Field> pathStack) {
        Rollable rollable = fieldFrom.removeRollable();
        fieldTo.insertRollable(rollable);
        emptyFields.add(fieldFrom);
        emptyFields.remove(fieldTo);

        StringBuilder commandStringBuilder = new StringBuilder(FIELD + fieldFrom.getRow() + " " + fieldFrom.getColumn() + " ROLLABLE MOVE ");
        for (Field element : pathStack) {
            commandStringBuilder.append("TO ");

            commandStringBuilder.append(element.getRow());
            commandStringBuilder.append(" ");
            commandStringBuilder.append(element.getColumn());
            commandStringBuilder.append(" ");
        }
        ColorShapes.getLogic().addChange(commandStringBuilder.toString());
    }

    public boolean isThereAnEmptyField() {
        return !this.emptyFields.isEmpty();
    }

    /**
     * Converts board to json object
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addInt("height", getHeight());
        jsonObject.addInt("width", getWidth());
        JsonArray jsonArray = new JsonArray();
        for (int tempRow = 1; tempRow <= getHeight(); tempRow++) {
            for (int tempColumn = 1; tempColumn <= getWidth(); tempColumn++) {
                Field element = this.fields[tempRow][tempColumn];
                JsonObject fieldJsonObject = element.toJsonObject();

                jsonArray.addObject(fieldJsonObject);
            }
        }
        JsonArray emptyFieldsJsonArray = new JsonArray();
        for (Field element : emptyFields) {
            emptyFieldsJsonArray.addObject(element.toJsonObject());
        }
        jsonObject.addArray("fields", jsonArray);
        jsonObject.addArray("empty fields", emptyFieldsJsonArray);
        return jsonObject;
    }
}
