
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

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public enum Direction {

    /**
     *
     */
    TOP,
    /**
     *
     */
    TOPRIGHT,
    /**
     *
     */
    RIGHT,
    /**
     *
     */
    BOTTOMRIGHT,
    /**
     *
     */
    BOTTOM,
    /**
     *
     */
    BOTTOMLEFT,
    /**
     *
     */
    LEFT,
    /**
     *
     */
    TOPLEFT;
    private static final String ICANTOVERTURN = "I can't over turn.";

    /**
     *
     * @return
     */
    public Direction overTurn() {
        if (!isSlant()) {
            return overTurnStraightDirection(this);
        }
        if (isSlant()) {
            return overTurnSlantDirection(this);
        }
        throw new ColorShapesRuntimeException(ICANTOVERTURN);

    }

    private static Direction overTurnStraightDirection(Direction direction) {
        switch (direction) {
            case TOP:
                return BOTTOM;
            case RIGHT:
                return LEFT;
            case BOTTOM:
                return TOP;
            case LEFT:
                return RIGHT;
            default:
                throw new ColorShapesRuntimeException(ICANTOVERTURN);
        }
    }

    private static Direction overTurnSlantDirection(Direction direction) {
        switch (direction) {
            case TOPRIGHT:
                return BOTTOMLEFT;
            case BOTTOMRIGHT:
                return TOPLEFT;
            case BOTTOMLEFT:
                return TOPRIGHT;
            case TOPLEFT:
                return BOTTOMRIGHT;

            default:
                throw new ColorShapesRuntimeException(ICANTOVERTURN);
        }
    }

    /**
     *
     * @return
     */
    public boolean isSlant() {
        return (this == TOPRIGHT) || (this == BOTTOMRIGHT) || (this == BOTTOMLEFT) || (this == TOPLEFT);

    }

}
