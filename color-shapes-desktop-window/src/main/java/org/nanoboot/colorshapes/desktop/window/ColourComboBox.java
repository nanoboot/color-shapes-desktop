
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

package org.nanoboot.colorshapes.desktop.window;

import org.nanoboot.colorshapes.desktop.localisation.impl.DummyResourcePackImpl;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.view.window.controls.SComboBox;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ColourComboBox extends SComboBox {

    private ResourcePack resourcePack = DummyResourcePackImpl.getInstance();
    /**
     *
     */
    public ColourComboBox() {
        for (int colourNumber = 51; colourNumber <= 66; colourNumber++) {
            getItems().add(resourcePack.getText(colourNumber));
        }
    }

    /**
     * @return
     */
    public int getColourNumber() {
        return this.getSelectionModel().getSelectedIndex() + 1;
    }

    /**
     *
     */
    public void setDefault() {
        setValue(resourcePack.getText(60));
    }

    /**
     * @param colourNumber
     */
    public void setColourNumber(int colourNumber) {
        this.setValue(resourcePack.getText(50 + colourNumber));
    }
}
