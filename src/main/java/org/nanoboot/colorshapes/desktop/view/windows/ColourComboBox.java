
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
import org.nanoboot.powerframework.simplicity.window.controls.SComboBox;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ColourComboBox extends SComboBox {

    /**
     *
     */
    public ColourComboBox() {
        for (int colourNumber = 51; colourNumber <= 66; colourNumber++) {
            getItems().add(ColorShapes.getServices().getLanguageService().getTextConstant(colourNumber));
        }
    }

    /**
     *
     * @return
     */
    public int getColourNumber() {
        return this.getSelectionModel().getSelectedIndex() + 1;
    }

    /**
     *
     */
    public void setDefault() {
        setValue(ColorShapes.getServices().getLanguageService().getTextConstant(60));
    }

    /**
     *
     * @param colourNumber
     */
    public void setColourNumber(int colourNumber) {
        this.setValue(ColorShapes.getServices().getLanguageService().getTextConstant(50 + colourNumber));
    }
}
