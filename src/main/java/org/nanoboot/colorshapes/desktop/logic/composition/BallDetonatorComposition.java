
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
import org.nanoboot.powerframework.json.JsonValueType;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class BallDetonatorComposition extends LockableObject {

    private ExplodingShapeType explodingShapeType = null;

    private int minimumLineLenght = 0;
    private int minimumBlockSize = 0;

    private Shape customExplodingShape = null;

    /**
     *
     * @param ballDetonatorCompositionJsonObject
     */
    public BallDetonatorComposition(JsonObject ballDetonatorCompositionJsonObject) {
        updateByJsonObject(ballDetonatorCompositionJsonObject);
    }

    /**
     * Constructor
     */
    public BallDetonatorComposition() {

        this.setDefaultValues();
    }

    /**
     *
     * @param explodingShape
     * @param minimumLineLenght
     * @param minimumBlockSize
     * @param customExplodingShape
     */
    public BallDetonatorComposition(
            ExplodingShapeType explodingShape,
            int minimumLineLenght,
            int minimumBlockSize,
            Shape customExplodingShape
    ) {
        if (!this.isThisCombinationValid(explodingShape, minimumLineLenght, minimumBlockSize, customExplodingShape)) {
            throw new ColorShapesRuntimeException("New BallDetonatorComposition instance can't be created. The parameter combination is not valid.");
        }
        this.explodingShapeType = explodingShape;
        this.minimumLineLenght = minimumLineLenght;
        this.minimumBlockSize = minimumBlockSize;
        this.customExplodingShape = customExplodingShape;
    }

    /**
     *
     * @param ballDetonatorCompositionJsonObject
     */
    public void updateByJsonObject(JsonObject ballDetonatorCompositionJsonObject) {
        this.explodingShapeType = ExplodingShapeType.valueOf(ballDetonatorCompositionJsonObject.getString("exploding shape type"));
        this.minimumLineLenght = ballDetonatorCompositionJsonObject.getInt("minimum line lenght");
        this.minimumBlockSize = ballDetonatorCompositionJsonObject.getInt("minimum block size");
        if (ballDetonatorCompositionJsonObject.getJsonValueType("custom exploding shape") == JsonValueType.NULL) {
            this.customExplodingShape = null;
        } else {
            this.customExplodingShape = new Shape(ballDetonatorCompositionJsonObject.getObject("custom exploding shape"));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        return isThisCombinationValid(explodingShapeType, minimumLineLenght, minimumBlockSize, customExplodingShape);
    }

    private boolean isThisCombinationValid(ExplodingShapeType explodingShape,//NOSONAR
            int minimumLineLenght,
            int minimumBlockSize,
            Shape customExplodingShape) {
        boolean valueToReturn;

        boolean ringOrSquareCombinationIsValid = isRingOrSquareCombinationValid(minimumLineLenght, minimumBlockSize, customExplodingShape);

        switch (explodingShape) {
            case LINE:
                valueToReturn = isLineCombinationValid(minimumLineLenght, minimumBlockSize, customExplodingShape);
                break;
            case BLOCK:
                valueToReturn = isBlockCombinationValid(minimumLineLenght, minimumBlockSize, customExplodingShape);
                break;
            case CUSTOM:
                valueToReturn = isCustomShapeCombinationValid(minimumLineLenght, minimumBlockSize, customExplodingShape);
                break;
            default:
                if (explodingShapeTypeIsRingOrSquare(explodingShape)) {
                    valueToReturn = ringOrSquareCombinationIsValid;
                } else {
                    throw new IllegalStateException();
                }
        }
        return valueToReturn;
    }

    private static boolean explodingShapeTypeIsRingOrSquare(ExplodingShapeType explodingShape) {
        return explodingShape == ExplodingShapeType.RING || explodingShape == ExplodingShapeType.SQUARE;
    }

    private static boolean isCustomShapeCombinationValid(int minimumLineLenght1, int minimumBlockSize1, Shape customExplodingShape1) {
        return minimumLineLenght1 == 0 && minimumBlockSize1 == 0 && customExplodingShape1 != null;
    }

    private boolean isLineCombinationValid(int minimumLineLenght1, int minimumBlockSize1, Shape customExplodingShape1) {
        return this.isMinimumLineLenghtValid(minimumLineLenght1) && minimumBlockSize1 == 0 && customExplodingShape1 == null;
    }

    private boolean isBlockCombinationValid(int minimumLineLenght1, int minimumBlockSize1, Shape customExplodingShape1) {
        return this.isMinimumBlockSizeValid(minimumBlockSize1) && minimumLineLenght1 == 0 && customExplodingShape1 == null;
    }

    private static boolean isRingOrSquareCombinationValid(int minimumLineLenght1, int minimumBlockSize1, Shape customExplodingShape1) {
        return minimumLineLenght1 == 0 && minimumBlockSize1 == 0 && customExplodingShape1 == null;
    }

    private boolean isMinimumLineLenghtValid(int value) {
        return (value >= 2) && (value <= 32);
    }

    private boolean isMinimumBlockSizeValid(int value) {
        return (value >= 3) && (value <= 32);
    }

    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.explodingShapeType = ExplodingShapeType.LINE;
        this.minimumLineLenght = 5;
        this.minimumBlockSize = 0;
        this.customExplodingShape = null;
    }

    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();

        this.minimumLineLenght = 0;
        this.minimumBlockSize = 0;
        this.customExplodingShape = null;

        int choice = PseudoRandomGenerator.getInstance().getInt(1, 3);
        switch (choice) {
            case 1: {
                this.explodingShapeType = ExplodingShapeType.LINE;
                this.minimumLineLenght = PseudoRandomGenerator.getInstance().getInt(2, 32);
            }
            break;
//            case 2: {
//                this.explodingShapeType = ExplodingShapeType.BLOCK;
//                this.minimumBlockSize = PseudoRandomGenerator.getInstance().getInt(2, 32);
//            }
//            break;
            case 2:
                this.explodingShapeType = ExplodingShapeType.RING;
                break;
            case 3:
                this.explodingShapeType = ExplodingShapeType.SQUARE;
                break;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public BallDetonatorComposition getCopy() {
        return new BallDetonatorComposition(this.explodingShapeType, this.minimumLineLenght, this.minimumBlockSize, this.customExplodingShape);
    }

    /**
     *
     * @param explodingShapeType
     */
    public void setExplodingShapeType(ExplodingShapeType explodingShapeType) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.explodingShapeType = explodingShapeType;
        if (this.explodingShapeType != ExplodingShapeType.CUSTOM) {
            this.customExplodingShape = null;
        }

    }

    /**
     *
     * @return
     */
    public ExplodingShapeType getExplodingShapeType() {
        return this.explodingShapeType;
    }

    /**
     *
     * @param value
     */
    public void setMinimumLineLength(int value) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if (!((value >= 2) && (value <= 32))) {
            throw new IllegalArgumentException();
        }
        this.minimumLineLenght = value;
    }

    /**
     *
     * @return
     */
    public int getMinimumLineLenght() {
        return this.minimumLineLenght;
    }

    /**
     *
     * @param value
     */
    public void setMinimumBlockSize(int value) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if (!((value >= 3) && (value <= 32))) {
            throw new IllegalArgumentException();
        }
        this.minimumBlockSize = value;
    }

    /**
     *
     * @return
     */
    public int getMinimumBlockSize() {
        return minimumBlockSize;
    }

    /**
     *
     * @return
     */
    public Shape getCustomExplodingShape() {
        if (this.getExplodingShapeType() == ExplodingShapeType.CUSTOM) {
            return this.customExplodingShape;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     *
     * @param customExplodingShape
     */
    public void setCustomExplodingShape(Shape customExplodingShape) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if (this.getExplodingShapeType() == ExplodingShapeType.CUSTOM) {
            this.customExplodingShape = customExplodingShape;

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List getListOfLockableObjects() {
        ArrayList<LockableObject> list = new ArrayList<>();
        list.add(this.customExplodingShape);
        return list;
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject ballDetonatorCompositionJsonObject = new JsonObject();

        ballDetonatorCompositionJsonObject.addString("exploding shape type", this.explodingShapeType.toString());
        ballDetonatorCompositionJsonObject.addInt("minimum line lenght", this.minimumLineLenght);
        ballDetonatorCompositionJsonObject.addInt("minimum block size", this.minimumBlockSize);
        if (this.customExplodingShape == null) {
            ballDetonatorCompositionJsonObject.addNull("custom exploding shape");
        } else {
            ballDetonatorCompositionJsonObject.addObject("custom exploding shape", this.customExplodingShape.toJsonObject());
        }

        return ballDetonatorCompositionJsonObject;
    }

    public int getMinimumCountOfBalls() {
        switch (explodingShapeType) {
            case LINE:
                return this.minimumLineLenght;
            case BLOCK:
                return this.minimumBlockSize;
            default:
                return 4;
        }
    }
}
