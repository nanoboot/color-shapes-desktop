
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
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Shape extends LockableObject {

    private final int height;
    private final int width;
    private final List holeList = new ArrayList<>();

    /**
     *
     * @param shapeJsonObject
     */
    public Shape(JsonObject shapeJsonObject) {
        height = shapeJsonObject.getInt("height");
        width = shapeJsonObject.getInt("width");

        JsonArray holeListJsonArray = shapeJsonObject.getArray("hole list");
        for (int i = 0; i <= holeListJsonArray.getCountOfItems() - 1; i++) {
            Hole hole = new Hole(holeListJsonArray.getObject(i));
            holeList.add(hole);
        }

    }

    /**
     *
     * @param height
     * @param width
     */
    public Shape(int height, int width) {
        if (!((height >= 1) && (width >= 1))) {
            throw new ColorShapesRuntimeException("Height and width must be at least 1.");
        }
        this.height = height;
        this.width = width;
    }

    /**
     * Deletes all holes
     */
    public void deleteAllHoles() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.holeList.clear();
    }

    /**
     *
     * @param row
     * @param column
     */
    public void addHole(int row, int column) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if (row > height) {
            throw new ColorShapesRuntimeException("Row is higher than height");
        }
        if (column > width) {
            throw new ColorShapesRuntimeException("Column is higher than width");
        }
        if (row <= 0) {
            throw new ColorShapesRuntimeException("Column must be at least 1.");
        }
        if (column <= 0) {
            throw new ColorShapesRuntimeException("Column must be at least 1.");
        }
        this.holeList.add(new Hole(column, row));
    }

    /**
     *
     * @return
     */
    public List getHoleListCopy() {
        return new ArrayList<>(this.holeList);
    }

    /**
     *
     */
    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
    }

    /**
     *
     */
    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();

    }

    @Override
    public Shape getCopy() {
        Shape shape = new Shape(height, width);
        ArrayList<Hole> list = (ArrayList<Hole>) this.getHoleListCopy();
        for (Hole hole : list) {
            shape.addHole(hole.getRow(), hole.getColumn());
        }
        return shape;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        return true;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return this.height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return this.width;
    }

    @Override
    public List getListOfLockableObjects() {
        return new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject shapeCompositionJsonObject = new JsonObject();
        shapeCompositionJsonObject.addInt("height", this.getHeight());
        shapeCompositionJsonObject.addInt("width", this.getWidth());

        JsonArray holeListJsonArray = new JsonArray();
        for (Object element : holeList) {
            holeListJsonArray.addObject(((Hole) element).toJsonObject());
        }
        shapeCompositionJsonObject.addArray("hole list", holeListJsonArray);
        return shapeCompositionJsonObject;
    }
}
