
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

import org.nanoboot.colorshapes.desktop.ColorShapes;
import javafx.event.ActionEvent;
import org.nanoboot.powerframework.simplicity.window.SLayout;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class AboutThisGameWindow extends ColorShapesAbstractWindow {

    private Text text;

    /**
     *
     * @param windowColourSkin
     */
    public AboutThisGameWindow() {

    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction() {
        this.setWindowTitle(ColorShapes.getServices().getLanguageService().getTextConstant(1211));
        this.text = new Text(ColorShapes.getServices().getLanguageService().getTextConstant(100001) + "\nColor Shapes " + ColorShapes.getColorShapesVersion());
        this.text.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        SLayout sLayout = new SLayout();
        sLayout.getChildren().add(text);
        sLayout.setAlignment(Pos.TOP_LEFT);
        this.applicationArea.getChildren().add(sLayout);
        this.showOnlyTheCloseButton();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        setWidth((bounds.getWidth()) / 4);
        setHeight((bounds.getWidth()) / 4 / 4 * 3);
    }

    private void handleCloseButtonAction(ActionEvent event) {
        // Button was clicked, do something...

        this.close();
    }

}
