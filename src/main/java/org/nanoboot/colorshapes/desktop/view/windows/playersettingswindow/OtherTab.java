
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

package org.nanoboot.colorshapes.desktop.view.windows.playersettingswindow;

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.colorshapes.desktop.view.windows.TimeZoneComboBox;
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class OtherTab extends Tab {

    private SLabel timeZoneLabel;
    private TimeZoneComboBox timeZoneComboBox;

    private Button saveOtherChangesButton;

    private CellLayout cellLayout;

    /**
     *
     */
    public OtherTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(10801));
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
        timeZoneLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10811));
    }

    private void initComboBoxes() {
        this.timeZoneComboBox = new TimeZoneComboBox();
        timeZoneComboBox.setValue(ColorShapes.getServices().getPlayerService().getTimeZoneName());

    }

    private void initButtons() {
        this.saveOtherChangesButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10891));
        this.saveOtherChangesButton.setOnAction(this::handleSaveOtherChangesButtonAction);
    }

    private void handleSaveOtherChangesButtonAction(Event e) {
        String timeZoneName = this.timeZoneComboBox.getValue().toString();
        ColorShapes.getServices().getPlayerService().setTimeZoneName(timeZoneName);
    }

}
