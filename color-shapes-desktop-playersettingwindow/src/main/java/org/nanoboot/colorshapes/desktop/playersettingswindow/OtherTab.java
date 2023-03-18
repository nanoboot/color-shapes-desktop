
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

package org.nanoboot.colorshapes.desktop.playersettingswindow;

import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.colorshapes.desktop.window.TimeZoneComboBox;
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.SLabel;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class OtherTab extends Tab {


    private SLabel timeZoneLabel;
    private TimeZoneComboBox timeZoneComboBox;

    private Button saveOtherChangesButton;

    private CellLayout cellLayout;
    private ResourcePack resourcePack;
    /**
     *
     */
    public OtherTab() {
        super();
        this.setText(resourcePack.getText(10801));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, this.timeZoneLabel, this.timeZoneComboBox);
        cellLayout.addNode(this.saveOtherChangesButton, 2, 5);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(2, 5, "50% 50%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initComboBoxes();
        initButtons();
    }

    private void initLabels() {
        timeZoneLabel = new SLabel(resourcePack.getText(10811));
    }

    private void initComboBoxes() {
        this.timeZoneComboBox = new TimeZoneComboBox();
        timeZoneComboBox.setValue(playerSessionService.getCurrentSession().getPlayer().getTimeZoneId());

    }

    private void initButtons() {
        this.saveOtherChangesButton = new Button(resourcePack.getText(10891));
        this.saveOtherChangesButton.setOnAction(this::handleSaveOtherChangesButtonAction);
    }

    private void handleSaveOtherChangesButtonAction(Event e) {
        String timeZoneName = this.timeZoneComboBox.getValue().toString();
        playerSessionService.getCurrentSession().getPlayer().setTimeZoneId(timeZoneName);
        playerService.updatePlayer(playerSessionService.getCurrentSession().getPlayer());
    }

}
