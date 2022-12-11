
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

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public enum ExplodingShapeType {

    /**
     * line
     */
    LINE,
    /**
     * block
     */
    BLOCK,
    /**
     * ring
     */
    RING,
    /**
     * square
     */
    SQUARE,
    /**
     * custom
     */
    CUSTOM;

    /**
     *
     * @param id
     * @return
     */
    public static ExplodingShapeType convertFromInt(int id) {
        ExplodingShapeType explodingShape;
        switch (id) {
            case 1:
                explodingShape = ExplodingShapeType.LINE;
                break;
            case 2:
                explodingShape = ExplodingShapeType.BLOCK;
                break;
            case 3:
                explodingShape = ExplodingShapeType.RING;
                break;
            case 4:
                explodingShape = ExplodingShapeType.SQUARE;
                break;
            case 5:
                explodingShape = ExplodingShapeType.CUSTOM;
                break;
            default:
                throw new ColorShapesRuntimeException("There is no such exploding shape type. It can be only 1,2,3,4 or 5.");
        }
        return explodingShape;
    }
}
