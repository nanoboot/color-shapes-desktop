
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

package org.nanoboot.colorshapes.desktop.view.windows.customcompositionwindow;

import javafx.scene.control.Tab;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.SCheckBox;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class OtherTab extends Tab {

    private SCheckBox allowStepBackCheckBox;
    private SCheckBox freeModeCheckBox;
    private SCheckBox trainerCheckBox;

    private final CellLayout cellLayout;

    protected ResourcePack resourcePack;
    /**
     *
     */
    public OtherTab() {
        super();
        this.setText(resourcePack.getText(20500));
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
        allowStepBackCheckBox = new SCheckBox(resourcePack.getText(20501));
        freeModeCheckBox = new SCheckBox(resourcePack.getText(20502));
        trainerCheckBox = new SCheckBox(resourcePack.getText(20503));
    }

    /**
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.allowStepBackCheckBox.setSelected(jsonObject.getBoolean("allowed step back"));
        this.freeModeCheckBox.setSelected(jsonObject.getBoolean("free mode"));
        this.trainerCheckBox.setSelected(jsonObject.getBoolean("trainer"));
    }

    /**
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
