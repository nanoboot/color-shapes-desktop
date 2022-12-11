
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

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Walls {

    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;

    /**
     *
     * @param direction
     * @return
     */
    public boolean isWallAtDirection(Direction direction) {
        switch (direction) {
            case TOP:
                return top;
            case RIGHT:
                return right;
            case BOTTOM:
                return bottom;
            case LEFT:
                return left;
            default:
                throw new ColorShapesRuntimeException("Fatal error");
        }
    }

    /**
     *
     * @return
     */
    public boolean isTop() {
        return top;
    }

    /**
     *
     * @param top
     */
    public void setTop(boolean top) {
        this.top = top;
    }

    /**
     *
     * @return
     */
    public boolean isRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     *
     * @return
     */
    public boolean isBottom() {
        return bottom;
    }

    /**
     *
     * @param bottom
     */
    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    /**
     *
     * @return
     */
    public boolean isLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Destroys all walls.
     */
    public void destroyAll() {
        top = false;
        right = false;
        bottom = false;
        left = false;
    }

    /**
     *
     * @return
     */
    public boolean hasAWall() {
        return top || right || bottom || left;
    }

    @Override
    public String toString() {
        return top + " " + right + " " + bottom + " " + left;
    }
}
