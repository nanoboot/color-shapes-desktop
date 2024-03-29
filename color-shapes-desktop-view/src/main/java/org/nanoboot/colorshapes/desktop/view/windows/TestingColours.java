
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
import javafx.geometry.Rectangle2D;
import org.nanoboot.powerframework.view.EnumColour;
import org.nanoboot.powerframework.view.View;
import org.nanoboot.powerframework.view.boxes.MessageBox;
import org.nanoboot.powerframework.view.window.WindowColourSkin;
import org.nanoboot.powerframework.view.window.controls.SLabel;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class TestingColours {

    /**
     * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
     */
    private class TestingColourWindow extends ColorShapesAbstractWindow {

        SLabel label;

        /**
         *
         */
        public TestingColourWindow() {
        }

        /**
         *
         */
        @Override
        public void initAreaForUserInteraction(Object... args) {
            this.setWindowTitle("Window Test");
            label = new SLabel("Text");
            this.applicationArea.getChildren().add(label);
        }

    }

    /**
     *
     */
    public TestingColours() {
        WindowColourSkin defaultWindowColourSkin = View.getDefaultWindowColourSkin();

        TestingColourWindow testingColourWindow[] = new TestingColourWindow[17];
        int row, column;
        row = 1;
        column = 1;
        Rectangle2D bounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        int screenWidth = (int) bounds.getWidth();
        int screenHeight = (int) bounds.getHeight();
        int windowWidth = (int) ((double) screenWidth * 0.20);
        int windowHeight = (int) ((double) screenHeight * 0.20);
        int spaceWidth = windowWidth;
        int spaceHeight = windowHeight;
        int i = 0;
        for (row = 1; row <= 4; row++) {
            for (column = 1; column <= 4; column++) {
                i++;
                View.setDefaultWindowColourSkin(new WindowColourSkin(EnumColour.convertNumberToEnumColour(i)));
                testingColourWindow[i] = new TestingColourWindow();
                testingColourWindow[i].setWidth(windowWidth);
                testingColourWindow[i].setHeight(windowHeight);
                testingColourWindow[i].setX((spaceWidth * 0.2 * column) + (windowWidth * (column - 1)));
                testingColourWindow[i].setY((spaceHeight * 0.2 * row) + (windowHeight * (row - 1)));

                testingColourWindow[i].show();
            }
        }
        View.setDefaultWindowColourSkin(defaultWindowColourSkin);
        MessageBox.showBox("Color Lines Sequel", "          ");
        for (int j = 1; j <= 16; j++) {
            testingColourWindow[j].close();
        }
    }

}
