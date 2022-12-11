
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

package org.nanoboot.colorshapes.desktop.view;

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.powerframework.simplicity.EnumColour;
import org.nanoboot.colorshapes.desktop.view.windows.SigningWindow;
import org.nanoboot.colorshapes.desktop.logic.NotifyableAboutChanges;
import org.nanoboot.powerframework.simplicity.Simplicity;
import org.nanoboot.powerframework.simplicity.window.WindowColourSkin;
import org.nanoboot.colorshapes.desktop.view.windows.GameWindow;
import org.nanoboot.powerframework.simplicity.Screen;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class View implements NotifyableAboutChanges {

    private GameWindow gameWindow;
    private SigningWindow loginWindow = null;

    private final ChangeExecutor changeExecutor;

    /**
     *
     */
    public View() {
        changeExecutor = new ChangeExecutor(this);
    }

    /**
     *
     * @return
     */
    public double getDpmm() {
        return Screen.getDpmm();
    }

    public void start() {
        Simplicity.startSimplicity(new org.nanoboot.colorshapes.desktop.view.RunObject(), new WindowColourSkin(EnumColour.GOLD));
    }

    /**
     *
     * @return
     */
    public String getTitleIconPath() {
        return "/images/logo.png";
    }

    /**
     *
     * @return
     */
    public WindowColourSkin getWindowColourSkin() {
        return new WindowColourSkin(ColorShapes.getServices().getCurrentLookService().getColourSkin());
    }

    /**
     *
     * @return
     */
    public GameWindow getGameWindow() {
        return this.gameWindow;
    }

    void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    SigningWindow getLoginWindow() {
        return loginWindow;
    }

    void setLoginWindow(SigningWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    @Override
    public void notifyAboutChange(String change) {
        this.changeExecutor.notifyAboutChange(change);
    }

}
