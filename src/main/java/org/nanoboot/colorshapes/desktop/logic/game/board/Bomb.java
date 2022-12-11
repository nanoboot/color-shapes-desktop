
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
public class Bomb implements Rollable {

    private final boolean automatic;
    private boolean exploded = false;

    /**
     *
     * @param automatic
     */
    public Bomb(boolean automatic) {
        this.automatic = automatic;
        exploded = automatic;
    }

    /**
     *
     * @return
     */
    public boolean isAutomatic() {
        return this.automatic;
    }

    public boolean isManual() {
        return !this.automatic;
    }

    /**
     * Executes the explosion.
     */
    public void executeExplosion() {
        if (exploded) {
            throw new ColorShapesRuntimeException("Bomb has already exploded");
        }
        this.exploded = true;
    }

    /**
     *
     * @return
     */
    public boolean isExploded() {
        return exploded;
    }

    @Override
    public boolean isBall() {
        return false;
    }

    @Override
    public boolean isBomb() {
        return true;
    }

    /**
     * Converts bomb to json object
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addBoolean("automatic", automatic);
        jsonObject.addBoolean("exploded", exploded);
        return jsonObject;
    }
}
