
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

package org.nanoboot.colorshapes.desktop.logic.composition;

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.logic.composition.lockableobject.LockableObject;
import java.util.ArrayList;
import java.util.List;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class BoardComposition extends LockableObject {

    private int gridProbability;
    private int gridCount;
    private int wallProbability;
    private int wallCount;

    private Shape shape;

    /**
     *
     * @param boardCompositionJsonObject
     */
    public BoardComposition(JsonObject boardCompositionJsonObject) {
        updateByJsonObject(boardCompositionJsonObject);
    }

    /**
     * Constructor
     */
    public BoardComposition() {
        this.setDefaultValues();
    }

    /**
     *
     * @param shape
     * @param gridProbability
     * @param gridCount
     * @param wallProbability
     * @param wallCount
     */
    public BoardComposition(Shape shape, int gridProbability, int gridCount, int wallProbability, int wallCount) {
        this.shape = shape;
        this.setGrid(gridProbability, gridCount);
        this.setWall(wallProbability, wallCount);
    }

    private void updateByJsonObject(JsonObject boardCompositionJsonObject) {
        this.gridProbability = boardCompositionJsonObject.getInt("grid probability");
        this.gridCount = boardCompositionJsonObject.getInt("grid count");
        this.wallProbability = boardCompositionJsonObject.getInt("wall probability");
        this.wallCount = boardCompositionJsonObject.getInt("wall count");

        this.shape = new Shape(boardCompositionJsonObject.getObject("shape"));
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        return shapeIsValid(this.shape) && probabilitiesAreValid(gridProbability, wallProbability);
    }

    private boolean shapeIsValid(Shape shape) {
        int height = shape.getHeight();
        int width = shape.getWidth();
        int[] array = {height, width};
        for (int element : array) {
            if (!((element >= 3) && (element <= 32))) {
                return false;
            }
        }
        return true;
    }

    private boolean probabilitiesAreValid(int gridProbability, int wallProbability) {
        int[] array = {gridProbability, wallProbability};
        for (int element : array) {
            if (!this.isProbabilityValid(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.setShape(new Shape(9, 9));
        gridProbability = 0;
        gridCount = 0;
        wallProbability = 0;
        wallCount = 0;

    }

    /**
     *
     */
    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        int height = PseudoRandomGenerator.getInstance().getInt(3, 32);
        int width = PseudoRandomGenerator.getInstance().getInt(3, 32);
        Shape tempShape = new Shape(height, width);
        this.setShape(tempShape);
        this.gridProbability = this.getRandomProbability();
        this.gridCount = gridProbability == 0 ? 0 : PseudoRandomGenerator.getInstance().getInt(3, 16);
        this.wallProbability = this.getRandomProbability();
        this.wallCount = wallProbability == 0 ? 0 : PseudoRandomGenerator.getInstance().getInt(3, 16);
    }

    /**
     *
     * @return
     */
    @Override
    public BoardComposition getCopy() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        return new BoardComposition(
                this.shape.getCopy(),
                this.gridProbability,
                this.gridCount,
                this.wallProbability,
                this.wallCount
        );
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     *
     * @param shape
     */
    public void setShape(Shape shape) {
        throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if (this.shapeIsValid(shape)) {
            this.shape = shape;
        } else {
            throw new ColorShapesRuntimeException("Shape of board is not valid.");
        }
    }

    /**
     *
     * @param probability
     * @param count
     */
    public void setGrid(int probability, int count) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if ((probability > 0) && (count == 0)) {
            throw new ColorShapesRuntimeException("For probability zero must be count zero.");
        }
        this.gridProbability = probability;
        this.gridCount = count;
    }

    /**
     *
     * @param probability
     * @param count
     */
    public void setWall(int probability, int count) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if ((probability > 0) && (count == 0)) {
            throw new ColorShapesRuntimeException("For probability zero must be count zero.");
        }
        this.wallProbability = probability;
        this.wallCount = count;
    }

    @Override
    public List getListOfLockableObjects() {
        ArrayList<LockableObject> list = new ArrayList<>();
        list.add(this.shape);
        return list;
    }

    /**
     *
     * @return
     */
    public int getGridProbability() {
        return gridProbability;
    }

    /**
     *
     * @return
     */
    public int getGridCount() {
        return gridCount;
    }

    /**
     *
     * @return
     */
    public int getWallProbability() {
        return wallProbability;
    }

    /**
     *
     * @return
     */
    public int getWallCount() {
        return wallCount;
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject boardCompositionJsonObject = new JsonObject();
        boardCompositionJsonObject.addInt("grid probability", this.gridProbability);
        boardCompositionJsonObject.addInt("grid count", this.gridCount);
        boardCompositionJsonObject.addInt("wall probability", this.wallProbability);
        boardCompositionJsonObject.addInt("wall count", this.wallCount);

        JsonObject shapeJsonObject = this.shape.toJsonObject();
        boardCompositionJsonObject.addObject("shape", shapeJsonObject);

        return boardCompositionJsonObject;
    }
}
