
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

package org.nanoboot.colorshapes.desktop.view.windows;

import javafx.event.ActionEvent;
import org.nanoboot.colorshapes.desktop.ColorShapes;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class HelpWindow extends ColorShapesAbstractWindow {

    /**
     *
     */
    public HelpWindow() {
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction() {
        String title = ColorShapes.getServices().getLanguageService().getTextConstant(1201);
        this.setWindowTitle(title);

        this.setWidth(400);
        this.setHeight(300);
        this.setMinWidth(200);
        this.setMinHeight(150);
        //this.setIcon(ColorShapes.getView().getTitleIconPath());
    }

    private void handleCloseButtonAction(ActionEvent event) {
        // Button was clicked, do something...

        this.close();
    }

}
