
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
import org.nanoboot.colorshapes.desktop.logic.game.board.Ball;
import org.nanoboot.colorshapes.desktop.logic.game.board.Rollable;
import java.util.List;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ResultCounter {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ResultCounter() {
        //Not meant to be instantiated.
    }

    /**
     * Counts score
     *
     * @param rollable
     * @param minRollableCountCount
     * @return
     */
    public static int countScore(List<Rollable> rollable, int minRollableCountCount) {
        int ballsCount = 0;
        int multiplier = 1;
        for (Rollable element : rollable) {
            if (!element.isBall()) {
                return 0;
            }
            Ball ball = (Ball) element;
            ++ballsCount;
            multiplier = multiplier * ball.getValue();
            if (multiplier == 0) {
                return 0;
            }
        }
        if (ballsCount < minRollableCountCount) {
            throw new ColorShapesRuntimeException("Balls were exploded, but their count (" + ballsCount + ") was smaller than the minimum (" + minRollableCountCount + ").");
        }
        int ballsOverMinimum = ballsCount - minRollableCountCount;
        switch (ballsOverMinimum) {
            case 0:
                return 10 * multiplier;
            case 1:
                return 12 * multiplier;
            case 2:
                return 18 * multiplier;
            case 3:
                return 28 * multiplier;
            case 4:
                return 42 * multiplier;
            default:
                return (42 + ((ballsOverMinimum - 4) * 5)) * multiplier;
        }
    }

}
