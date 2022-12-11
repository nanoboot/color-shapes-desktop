
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

package org.nanoboot.colorshapes.desktop.view.windows.customcompositionwindow;

import javafx.scene.control.Tab;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.SCheckBox;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class OtherTab extends Tab {

    private SCheckBox allowStepBackCheckBox;
    private SCheckBox freeModeCheckBox;
    private SCheckBox trainerCheckBox;

    private final CellLayout cellLayout;

    /**
     *
     */
    public OtherTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(20500));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, allowStepBackCheckBox);
        cellLayout.addNodes(2, freeModeCheckBox);
        cellLayout.addNodes(3, trainerCheckBox);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(1, 3, "100%");
        return cellLayout;
    }

    private void initNodes() {
        initCheckBoxes();
    }

    private void initCheckBoxes() {
        allowStepBackCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(20501));
        freeModeCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(20502));
        trainerCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(20503));
    }

    /**
     *
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.allowStepBackCheckBox.setSelected(jsonObject.getBoolean("allowed step back"));
        this.freeModeCheckBox.setSelected(jsonObject.getBoolean("free mode"));
        this.trainerCheckBox.setSelected(jsonObject.getBoolean("trainer"));
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject otherCompositionJsonObject = new JsonObject();

        otherCompositionJsonObject.addBoolean("allowed step back", this.allowStepBackCheckBox.isSelected());
        otherCompositionJsonObject.addBoolean("free mode", this.freeModeCheckBox.isSelected());
        otherCompositionJsonObject.addBoolean("trainer", this.trainerCheckBox.isSelected());

        return otherCompositionJsonObject;
    }
}
