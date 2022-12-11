
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
import java.util.Arrays;
import java.util.List;
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class BallFactoryComposition extends LockableObject {

    private final int[] colourFrequency = new int[17];

    private int valueMinusTwoFrequency;
    private int valueMinusOneFrequency;
    private int valueZeroFrequency;
    private int valuePlusOneFrequency;
    private int valuePlusTwoFrequency;

    private int colorfulBallFrequency;
    private int rainbowBallFrequency;

    private int unmoveableBallProbability;
    private int unbreakableBallProbability;

    /**
     *
     * @param ballFactoryCompositionJsonObject
     */
    public BallFactoryComposition(JsonObject ballFactoryCompositionJsonObject) {
        updateByJsonObject(ballFactoryCompositionJsonObject);
    }

    /**
     * Constructor
     */
    public BallFactoryComposition() {

        this.setDefaultValues();
    }

    /**
     * Constructor
     *
     * @param colourFrequency
     * @param colorfulBallFrequency
     * @param rainbowBallFrequency
     * @param valueMinusTwoFrequency
     * @param valueMinusOneFrequency
     * @param valueZeroFrequency
     * @param valuePlusOneFrequency
     * @param valuePlusTwoFrequency
     * @param unmoveableBallProbability
     * @param unbreakableBallProbability
     */
    public BallFactoryComposition(//NOSONAR
            int[] colourFrequency,
            int colorfulBallFrequency,
            int rainbowBallFrequency,
            int valueMinusTwoFrequency,
            int valueMinusOneFrequency,
            int valueZeroFrequency,
            int valuePlusOneFrequency,
            int valuePlusTwoFrequency,
            int unmoveableBallProbability,
            int unbreakableBallProbability) {
        for (int i = 1; i <= 16; i++) {
            this.colourFrequency[i] = colourFrequency[i];
        }
        this.setColorfulBallFrequency(colorfulBallFrequency);
        this.setRainbowBallFrequency(rainbowBallFrequency);
        this.setValueFrequency(-2, valueMinusTwoFrequency);
        this.setValueFrequency(-1, valueMinusOneFrequency);
        this.setValueFrequency(0, valueZeroFrequency);
        this.setValueFrequency(1, valuePlusOneFrequency);
        this.setValueFrequency(2, valuePlusTwoFrequency);
        this.setUnmoveableBallsProbability(unmoveableBallProbability);
        this.setUnbreakableBallsProbability(unbreakableBallProbability);

    }

    /**
     *
     * @param ballFactoryCompositionJsonObject
     */
    public void updateByJsonObject(JsonObject ballFactoryCompositionJsonObject) {
        JsonArray colourFrequencyJsonArray = ballFactoryCompositionJsonObject.getArray("colour frequency");
        for (int i = 0; i < 17; i++) {
            this.colourFrequency[i] = colourFrequencyJsonArray.getInt(i);
        }
        this.colorfulBallFrequency = ballFactoryCompositionJsonObject.getInt("colorful ball frequency");
        this.rainbowBallFrequency = ballFactoryCompositionJsonObject.getInt("rainbow ball frequency");

        this.valueMinusTwoFrequency = ballFactoryCompositionJsonObject.getInt("value minus two frequency");
        this.valueMinusOneFrequency = ballFactoryCompositionJsonObject.getInt("value minus one frequency");
        this.valueZeroFrequency = ballFactoryCompositionJsonObject.getInt("value zero frequency");
        this.valuePlusOneFrequency = ballFactoryCompositionJsonObject.getInt("value plus one frequency");
        this.valuePlusTwoFrequency = ballFactoryCompositionJsonObject.getInt("value plus two frequency");

        this.unmoveableBallProbability = ballFactoryCompositionJsonObject.getInt("unmoveable ball probability");
        this.unbreakableBallProbability = ballFactoryCompositionJsonObject.getInt("unbreakable ball probability");

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        for (int i = 1; i <= 16; i++) {
            if (this.colourFrequency[i] > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        for (int i = 1; i <= 7; i++) {
            this.setColourFrequency(i, 1);
        }
        for (int i = 8; i <= 16; i++) {
            this.setColourFrequency(i, 0);
        }

        for (int i = -2; i <= 2; i++) {
            if (i != 1) {
                this.setValueFrequency(i, 0);
            } else {
                this.setValueFrequency(i, 1);
            }
        }
        this.setColorfulBallFrequency(1);
        this.setRainbowBallFrequency(0);
        this.setUnmoveableBallsProbability(0);
        this.setUnbreakableBallsProbability(0);
    }

    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        boolean value = false;
        for (int i = 1; i <= 16; i++) {

            this.setColourFrequency(i, this.getRandomFrequency());
            if (this.getColourFrequency(i) > 0) {
                value = true;
            }
        }
        if (!value) {
            this.setColourFrequency(PseudoRandomGenerator.getInstance().getInt(1, 16), 1);
        }
        this.valueMinusTwoFrequency = this.getRandomFrequency() / 2;
        this.valueMinusOneFrequency = this.getRandomFrequency() / 2;
        this.valueZeroFrequency = this.getRandomFrequency();
        this.valuePlusOneFrequency = this.getNotNullRandomFrequency();
        this.valuePlusTwoFrequency = this.getRandomFrequency();
        this.setColorfulBallFrequency(PseudoRandomGenerator.getInstance().getInt(40, 100));
        this.setRainbowBallFrequency(this.getRandomFrequency());

        if ((this.getColorfulBallFrequency() == 0) && (this.getRainbowBallFrequency() == 0)) {
            if (PseudoRandomGenerator.getInstance().getBoolean()) {
                this.setColorfulBallFrequency(1);
            } else {
                this.setRainbowBallFrequency(1);
            }
        }

        this.setUnmoveableBallsProbability(PseudoRandomGenerator.getInstance().getInt(0, 30));
        this.setUnbreakableBallsProbability(PseudoRandomGenerator.getInstance().getInt(0, 30));
    }

    /**
     *
     * @return
     */
    @Override
    public BallFactoryComposition getCopy() {
        BallFactoryComposition copy = new BallFactoryComposition();
        for (int i = 1; i <= 16; i++) {
            copy.setColourFrequency(i, this.getColourFrequency(i));
        }
        copy.setColorfulBallFrequency(this.getColorfulBallFrequency());
        copy.setRainbowBallFrequency(this.getRainbowBallFrequency());
        for (int i = -2; i <= 2; i++) {
            copy.setValueFrequency(i, this.getValueFrequency(i));
        }

        copy.setUnmoveableBallsProbability(this.getUnmoveableBallsProbability());
        copy.setUnbreakableBallsProbability(this.getUnbreakableBallsProbability());
        return copy;
    }

    /**
     *
     * @param index
     * @param frequency
     */
    public void setColourFrequency(int index, int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.colourFrequency[index] = frequency;
    }

    /**
     *
     * @param index
     * @return
     */
    public int getColourFrequency(int index) {
        return this.colourFrequency[index];
    }

    /**
     *
     * @return
     */
    public int[] getColourFrequency() {
        return Arrays.copyOf(colourFrequency, colourFrequency.length);
    }

    /**
     *
     * @param value
     * @param frequency
     */
    public void setValueFrequency(int value, int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        if ((value < -2) || (value > 2)) {
            throw new IllegalArgumentException();
        }
        switch (value) {
            case -2:
                this.valueMinusTwoFrequency = frequency;
                break;
            case -1:
                this.valueMinusOneFrequency = frequency;
                break;
            case 0:
                this.valueZeroFrequency = frequency;
                break;
            case 1:
                this.valuePlusOneFrequency = frequency;
                break;
            case 2:
                this.valuePlusTwoFrequency = frequency;
                break;
            default:
                throw new IllegalStateException();
        }

    }

    /**
     *
     * @param value
     * @return
     */
    public int getValueFrequency(int value) {//NOSONAR
        if (!isValueValid(value)) {
            throw new ColorShapesRuntimeException("Value must be at least -2 and at most 2, but the given value is: " + value + ".");
        }
        switch (value) {
            case -2:
                return this.valueMinusTwoFrequency;
            case -1:
                return this.valueMinusOneFrequency;
            case 0:
                return this.valueZeroFrequency;
            case 1:
                return this.valuePlusOneFrequency;
            case 2:
                return this.valuePlusTwoFrequency;
            default:
                throw new IllegalStateException();
        }
    }

    private boolean isValueValid(int value) {
        return (value >= -2) && (value <= 2);
    }

    /**
     *
     * @param frequency
     */
    public void setColorfulBallFrequency(int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.colorfulBallFrequency = frequency;
    }

    /**
     *
     * @return
     */
    public int getColorfulBallFrequency() {
        return this.colorfulBallFrequency;
    }

    /**
     *
     * @param frequency
     */
    public void setRainbowBallFrequency(int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.rainbowBallFrequency = frequency;
    }

    /**
     *
     * @return
     */
    public int getRainbowBallFrequency() {
        return this.rainbowBallFrequency;
    }

    /**
     *
     * @param probability
     */
    public void setUnmoveableBallsProbability(int probability) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.unmoveableBallProbability = probability;
    }

    /**
     *
     * @return
     */
    public int getUnmoveableBallsProbability() {
        return this.unmoveableBallProbability;
    }

    /**
     *
     * @param probability
     */
    public void setUnbreakableBallsProbability(int probability) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.unbreakableBallProbability = probability;
    }

    /**
     *
     * @return
     */
    public int getUnbreakableBallsProbability() {
        return this.unbreakableBallProbability;
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
        JsonObject ballFactoryCompositionJsonObject = new JsonObject();
        JsonArray colourFrequencyJsonArray = new JsonArray();
        for (int element : colourFrequency) {
            colourFrequencyJsonArray.addInt(element);
        }
        ballFactoryCompositionJsonObject.addArray("colour frequency", colourFrequencyJsonArray);

        ballFactoryCompositionJsonObject.addInt("value minus two frequency", this.valueMinusTwoFrequency);
        ballFactoryCompositionJsonObject.addInt("value minus one frequency", this.valueMinusOneFrequency);
        ballFactoryCompositionJsonObject.addInt("value zero frequency", this.valueZeroFrequency);
        ballFactoryCompositionJsonObject.addInt("value plus one frequency", this.valuePlusOneFrequency);
        ballFactoryCompositionJsonObject.addInt("value plus two frequency", this.valuePlusTwoFrequency);

        ballFactoryCompositionJsonObject.addInt("colorful ball frequency", this.colorfulBallFrequency);
        ballFactoryCompositionJsonObject.addInt("rainbow ball frequency", this.rainbowBallFrequency);

        ballFactoryCompositionJsonObject.addInt("unmoveable ball probability", this.unmoveableBallProbability);
        ballFactoryCompositionJsonObject.addInt("unbreakable ball probability", this.unbreakableBallProbability);

        return ballFactoryCompositionJsonObject;
    }
}
