
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
import org.nanoboot.colorshapes.desktop.logic.composition.BallFactoryComposition;
import org.nanoboot.colorshapes.desktop.logic.game.board.Ball;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BallFactory {

    private final BallFactoryComposition ballFactoryComposition;
    private final PseudoRandomGenerator pseudoRandomGenerator;

    private final int color1Max;
    private final int color2Max;
    private final int color3Max;
    private final int color4Max;
    private final int color5Max;
    private final int color6Max;
    private final int color7Max;
    private final int color8Max;
    private final int color9Max;
    private final int color10Max;
    private final int color11Max;
    private final int color12Max;
    private final int color13Max;
    private final int color14Max;
    private final int color15Max;
    private final int color16Max;

    private final int valueMinusTwoMax;
    private final int valueMinusOneMax;
    private final int valueZeroMax;
    private final int valuePlusOneMax;
    private final int valuePlusTwoMax;

    private final int colourfulMax;
    private final int rainbowMax;

    private final int unmoveableProbability;
    private final int unbreakableProbability;

    BallFactory(BallFactoryComposition ballFactoryComposition, PseudoRandomGenerator pseudoRandomGenerator) {
        this.ballFactoryComposition = ballFactoryComposition;
        this.pseudoRandomGenerator = pseudoRandomGenerator;

        color1Max = ballFactoryComposition.getColourFrequency(1);
        color2Max = color1Max + ballFactoryComposition.getColourFrequency(2);
        color3Max = color2Max + ballFactoryComposition.getColourFrequency(3);
        color4Max = color3Max + ballFactoryComposition.getColourFrequency(4);
        color5Max = color4Max + ballFactoryComposition.getColourFrequency(5);
        color6Max = color5Max + ballFactoryComposition.getColourFrequency(6);
        color7Max = color6Max + ballFactoryComposition.getColourFrequency(7);
        color8Max = color7Max + ballFactoryComposition.getColourFrequency(8);
        color9Max = color8Max + ballFactoryComposition.getColourFrequency(9);
        color10Max = color9Max + ballFactoryComposition.getColourFrequency(10);
        color11Max = color10Max + ballFactoryComposition.getColourFrequency(11);
        color12Max = color11Max + ballFactoryComposition.getColourFrequency(12);
        color13Max = color12Max + ballFactoryComposition.getColourFrequency(13);
        color14Max = color13Max + ballFactoryComposition.getColourFrequency(14);
        color15Max = color14Max + ballFactoryComposition.getColourFrequency(15);
        color16Max = color15Max + ballFactoryComposition.getColourFrequency(16);

        valueMinusTwoMax = ballFactoryComposition.getValueFrequency(-2);
        valueMinusOneMax = valueMinusTwoMax + ballFactoryComposition.getValueFrequency(-1);
        valueZeroMax = valueMinusOneMax + ballFactoryComposition.getValueFrequency(0);
        valuePlusOneMax = valueZeroMax + ballFactoryComposition.getValueFrequency(1);
        valuePlusTwoMax = valuePlusOneMax + ballFactoryComposition.getValueFrequency(2);

        colourfulMax = ballFactoryComposition.getColorfulBallFrequency();
        rainbowMax = colourfulMax + ballFactoryComposition.getRainbowBallFrequency();

        unmoveableProbability = ballFactoryComposition.getUnmoveableBallsProbability();
        unbreakableProbability = ballFactoryComposition.getUnbreakableBallsProbability();
    }

    /**
     *
     * @return
     */
    public Ball getNextBall() {
        return new Ball(getColour(), getValue(), getUnmoveable(), getUnbreakable());
    }

    private int getColour() {//NOSONAR
        int colourfulOrRainbowNumber = this.pseudoRandomGenerator.getInt(1, this.rainbowMax);
        if (colourfulOrRainbowNumber <= colourfulMax) {
            int number = this.pseudoRandomGenerator.getInt(1, this.color16Max);
            if (number <= color1Max) {
                return 1;
            }
            if (number <= color2Max) {
                return 2;
            }
            if (number <= color3Max) {
                return 3;
            }
            if (number <= color4Max) {
                return 4;
            }
            if (number <= color5Max) {
                return 5;
            }
            if (number <= color6Max) {
                return 6;
            }
            if (number <= color7Max) {
                return 7;
            }
            if (number <= color8Max) {
                return 8;
            }
            if (number <= color9Max) {
                return 9;
            }
            if (number <= color10Max) {
                return 10;
            }
            if (number <= color11Max) {
                return 11;
            }
            if (number <= color12Max) {
                return 12;
            }
            if (number <= color13Max) {
                return 13;
            }
            if (number <= color14Max) {
                return 14;
            }
            if (number <= color15Max) {
                return 15;
            }
            if (number <= color16Max) {
                return 16;
            }
            throw new ColorShapesRuntimeException("Fatal error");
        };
        if (colourfulOrRainbowNumber <= rainbowMax) {
            return 0;
        }
        throw new ColorShapesRuntimeException("Fatal error");

    }

    private int getValue() {
        int number = this.pseudoRandomGenerator.getInt(1, this.valuePlusTwoMax);
        if (number <= valueMinusTwoMax) {
            return -2;
        }
        if (number <= valueMinusOneMax) {
            return -1;
        }
        if (number <= valueZeroMax) {
            return 0;
        }
        if (number <= valuePlusOneMax) {
            return 1;
        }
        if (number <= valuePlusTwoMax) {
            return 2;
        }
        throw new ColorShapesRuntimeException("Fatal error");
    }

    private boolean getUnmoveable() {
        if (unmoveableProbability == 0) {
            return false;
        }
        return unmoveableProbability >= this.pseudoRandomGenerator.getInt(0, 100);
    }

    private boolean getUnbreakable() {
        if (unbreakableProbability == 0) {
            return false;
        }
        return unbreakableProbability >= this.pseudoRandomGenerator.getInt(0, 100);
    }
}
