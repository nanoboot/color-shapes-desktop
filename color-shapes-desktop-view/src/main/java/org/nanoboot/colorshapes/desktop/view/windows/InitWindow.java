
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: The desktop UI for on Color Lines Engine.
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

import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import javafx.event.ActionEvent;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class InitWindow extends ColorShapesAbstractWindow {

    /**
     *
     */
    public InitWindow() {
        //todo loading- splashscreen
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction(Object... args) {
        String title = resourcePack.getText(1201);
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
