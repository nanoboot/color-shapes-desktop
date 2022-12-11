
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

package org.nanoboot.colorshapes.desktop.services;

import org.nanoboot.colorshapes.desktop.logic.ApplicationLook;
import org.nanoboot.colorshapes.desktop.logic.Logic;
import org.nanoboot.powerframework.simplicity.EnumColour;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BeforeLoginService {

    private final Logic logic;
    private final ApplicationLook applicationLookForBeforeLogin;

    public BeforeLoginService(Logic logic) {
        this.logic = logic;
        this.applicationLookForBeforeLogin = logic.getApplication().getApplicationLook();
    }

    public int getLanguageId() {
        return applicationLookForBeforeLogin.getLanguageId();
    }

    public void setLanguageId(int id) {
        applicationLookForBeforeLogin.setLanguageId(id);
    }

    public EnumColour getColourSkin() {
        return applicationLookForBeforeLogin.getColourSkin();
    }

    public void setColourSkin(EnumColour colourSkin) {
        applicationLookForBeforeLogin.setColourSkin(colourSkin);
    }

    public int getZoom() {
        return applicationLookForBeforeLogin.getZoom();
    }

    public void setZoom(int zoom) {
        applicationLookForBeforeLogin.setZoom(zoom);
    }

}
