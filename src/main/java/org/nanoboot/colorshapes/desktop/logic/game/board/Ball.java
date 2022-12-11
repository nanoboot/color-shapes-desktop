
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
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Ball implements Rollable {

    private int colour;

    private int value;

    private final boolean unmoveable;
    private final boolean unbreakable;

    /**
     *
     * @param colour
     * @param value
     * @param unmoveable
     * @param unbreakable
     */
    public Ball(int colour, int value, boolean unmoveable, boolean unbreakable) {
        if (!isColourValid(colour)) {
            throw new ColorShapesRuntimeException("Colour must be between 0 and 16.");
        }
        this.colour = colour;
        if (!isValueValid(value)) {
            throw new ColorShapesRuntimeException("Value is out of range <-2;2>.");
        }
        this.value = value;
        this.unmoveable = unmoveable;
        this.unbreakable = unbreakable;
    }

    private boolean isColourValid(int colour) {
        return (colour >= 0) && (colour <= 16);
    }

    private boolean isValueValid(int value) {
        return (value >= -2) && (value <= 2);
    }

    /**
     *
     * @return
     */
    public int getColour() {
        return this.colour;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     *
     * @return
     */
    public boolean isColorful() {
        return colour != 0;
    }

    /**
     *
     * @return
     */
    public boolean isRainbow() {
        return colour == 0;
    }

    /**
     *
     * @return
     */
    public boolean isUnmoveable() {
        return this.unmoveable;
    }

    /**
     *
     * @return
     */
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    @Override
    public String toString() {
        return colour
                + " "
                + value
                + " "
                + (unmoveable ? 1 : 0)
                + " "
                + (unbreakable ? 1 : 0);
    }

    @Override
    public boolean isBall() {
        return true;
    }

    @Override
    public boolean isBomb() {
        return false;
    }

    /**
     * Converts ball to json object
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addString("colour", colour == 0 ? "RAINBOW" : org.nanoboot.powerframework.simplicity.EnumColour.convertNumberToEnumColour(colour).toString());
        jsonObject.addInt("value", value);
        jsonObject.addBoolean("unmoveable", this.unmoveable);
        jsonObject.addBoolean("unbreakable", this.unbreakable);
        return jsonObject;
    }
}
