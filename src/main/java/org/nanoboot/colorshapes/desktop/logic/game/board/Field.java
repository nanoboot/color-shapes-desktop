
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
import org.nanoboot.colorshapes.desktop.logic.game.Direction;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Field {

    private final Board board;
    private final int row;
    private final int column;
    private boolean lockedByGrid = false;
    private final Walls walls = new Walls();
    private Rollable rollable;

    /**
     *
     * @param row
     * @param column
     * @param board
     */
    public Field(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
        this.rollable = null;
    }

    Board getBoard() {
        return board;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return this.row;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return this.column;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return this.rollable == null;
    }

    /**
     * Lock the field.
     */
    public void setLocked(boolean value) {
        this.lockedByGrid = value;
        ColorShapes.getLogic().addChange("FIELD " + getRow() + " " + getColumn() + " GRID " + (value ? "ON" : "OFF"));
    }

    /**
     *
     * @return
     */
    public boolean isLocked() {
        return this.lockedByGrid;
    }

    /**
     *
     * @param insertedRollable
     */
    public void insertRollable(Rollable insertedRollable) {
        if (this.isLocked()) {
            throw new UnsupportedOperationException("No Rollable can be inserted. The field is locked.");
        }
        if (!this.isEmpty()) {
            throw new IllegalArgumentException("No ball can be inserted. The field " + this.toString() + "is not empty.");
        }
        if (insertedRollable == null) {
            throw new NullPointerException("Inserted rollable is null and can't be inserted.");
        }
        this.rollable = insertedRollable;
    }

    /**
     *
     * @return
     */
    public Rollable getRollable() {
        if (this.rollable == null) {
            throw new UnsupportedOperationException("There is no rollable in the field.");
        }
        return this.rollable;
    }

    /**
     *
     * @return
     */
    public Rollable removeRollable() {
        if (this.isLocked()) {
            throw new ColorShapesRuntimeException("Field is locked by the bars. No ball can be removed.");
        }
        if (this.rollable == null) {
            throw new ColorShapesRuntimeException("There is no ball to remove.");
        }
        Rollable temporaryRollable = this.rollable;
        this.rollable = null;
        return temporaryRollable;
    }

    /**
     *
     * @param walls
     */
    public Walls getWalls() {
        return walls;
    }

    /**
     * It examines the position of the field on the board <br />
     * <pre>
     *                  11
     *               --------
     *               |1 2 3 |
     *            14 |4 5 6 | 12
     *               |7 8 9 |
     *               --------
     *                  13
     * </pre> For example: If the field is in the position 6, the field has
     * following attributes:<br />
     * <ul>
     * <li>The column is the last column.</li>
     * <li>The row is between the first and the last.</li>
     * </ul>
     *
     * @param field<br />Field
     * @param code<br />Position code for field on board.<br />
     * @return boolean
     */
    public boolean hasPositionCode(int code) {//NOSONAR
        if (!((code >= 1) && (code <= 14) && (code != 10))) {
            throw new IllegalArgumentException("Illegal position code.");
        }

        int boardHeight = board.getHeight();
        int boardWidth = board.getWidth();
        int fieldRow = this.getRow();
        int fieldColumn = this.getColumn();
        switch (code) {

            case 1:
                return (fieldRow == 1) && (fieldColumn == 1);
            case 2:
                return (fieldRow == 1) && (fieldColumn > 1) && (fieldColumn < boardWidth);
            case 3:
                return (fieldRow == 1) && (fieldColumn == boardWidth);
            case 4:
                return (fieldRow > 1) && (fieldRow < boardHeight) && (fieldColumn == 1);
            case 5:
                return (fieldRow > 1) && (fieldRow < boardHeight) && (fieldColumn > 1) && (fieldColumn < boardWidth);
            case 6:
                return (fieldRow > 1) && (fieldRow < boardHeight) && (fieldColumn == boardWidth);
            case 7:
                return (fieldRow == boardHeight) && (fieldColumn == 1);
            case 8:
                return (fieldRow == boardHeight) && (fieldColumn > 1) && (fieldColumn < boardWidth);
            case 9:
                return (fieldRow == boardHeight) && (fieldColumn == boardWidth);
            case 11:
                return fieldRow == 1;
            case 12:
                return fieldColumn == boardWidth;
            case 13:
                return fieldRow == boardHeight;
            case 14:
                return fieldColumn == 1;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param code1
     * @param code2
     * @return
     */
    public boolean hasPositionCode1OrCode2(int code1, int code2) {
        return (this.hasPositionCode(code1)) || (this.hasPositionCode(code2));
    }

    /**
     *
     * @param direction
     * @return Field
     */
    public Field getFieldAtDirection(Direction direction) {//NOSONAR
        int fieldRow = getRow();
        int fieldColumn = getColumn();

        switch (direction) {
            case TOP:
                if (!hasPositionCode(11)) {
                    return board.getField(fieldRow - 1, fieldColumn);
                } else {
                    return null;
                }
            case RIGHT:
                if (!hasPositionCode(12)) {
                    return board.getField(fieldRow, fieldColumn + 1);
                } else {
                    return null;
                }
            case BOTTOM:
                if (!hasPositionCode(13)) {
                    return board.getField(fieldRow + 1, fieldColumn);
                } else {
                    return null;
                }
            case LEFT:
                if (!hasPositionCode(14)) {
                    return board.getField(fieldRow, fieldColumn - 1);
                } else {
                    return null;
                }
            case TOPRIGHT:
                if (!hasPositionCode1OrCode2(11, 12)) {
                    return board.getField(fieldRow - 1, fieldColumn + 1);
                } else {
                    return null;
                }
            case BOTTOMRIGHT:
                if (!hasPositionCode1OrCode2(12, 13)) {
                    return board.getField(fieldRow + 1, fieldColumn + 1);
                } else {
                    return null;
                }
            case BOTTOMLEFT:
                if (!hasPositionCode1OrCode2(13, 14)) {
                    return board.getField(fieldRow + 1, fieldColumn - 1);
                } else {
                    return null;
                }
            case TOPLEFT:
                if (!hasPositionCode1OrCode2(14, 11)) {
                    return board.getField(fieldRow - 1, fieldColumn - 1);
                } else {
                    return null;
                }
            default:
                throw new ColorShapesRuntimeException("Unknown direction");
        }
    }

    /**
     * Converts field to json object
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addInt("row", row);
        jsonObject.addInt("column", column);
        jsonObject.addBoolean("lockedByGrid", lockedByGrid);
        jsonObject.addString("walls", "unknown");
        if (rollable == null) {
            jsonObject.addNull("rollable");
        } else {
            jsonObject.addObject("rollable", rollable instanceof Ball ? ((Ball) rollable).toJsonObject() : ((Bomb) rollable).toJsonObject());
        }

        return jsonObject;
    }

    @Override
    public String toString() {
        return "{row: " + row + ", column: " + column + "}";
    }
}
