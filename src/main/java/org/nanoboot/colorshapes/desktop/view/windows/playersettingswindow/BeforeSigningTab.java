
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
import org.nanoboot.colorshapes.desktop.view.windows.ColourComboBox;
import org.nanoboot.colorshapes.desktop.view.windows.LanguageComboBox;
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.simplicity.EnumColour;
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BeforeSigningTab extends Tab {

    private SLabel displayLanguageLabel;
    private SLabel colourSkinLabel;
    private SLabel zoomLabel;

    private LanguageComboBox languageComboBox;
    private ColourComboBox colourComboBox;

    private TextField zoomTextField;

    private Button saveBeforeSigningChangesButton;

    private final CellLayout cellLayout;

    /**
     *
     */
    public BeforeSigningTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(10901));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, this.displayLanguageLabel, this.languageComboBox);
        cellLayout.addNodes(2, this.colourSkinLabel, this.colourComboBox);
        cellLayout.addNodes(3, this.zoomLabel, this.zoomTextField);
        cellLayout.addNode(this.saveBeforeSigningChangesButton, 2, 5);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(2, 5, "50% 50%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initComboBoxes();
        initTextFields();
        initButtons();
    }

    private void initLabels() {
        displayLanguageLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(211));
        colourSkinLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(231));
        zoomLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10102));
    }

    private void initComboBoxes() {
        this.languageComboBox = new LanguageComboBox();
        this.languageComboBox.setValue(ColorShapes.getServices().getLanguageService().convertLanguageIdToLanguageName(ColorShapes.getServices().getBeforeLoginService().getLanguageId()));
        this.colourComboBox = new ColourComboBox();
        this.colourComboBox.setColourNumber(ColorShapes.getServices().getBeforeLoginService().getColourSkin().getNumber());
    }

    private void initTextFields() {
        this.zoomTextField = new TextField(String.valueOf(ColorShapes.getServices().getBeforeLoginService().getZoom()));
    }

    private void initButtons() {
        this.saveBeforeSigningChangesButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10991));
        this.saveBeforeSigningChangesButton.setOnAction(this::handleSaveLookChangesButtonAction);
    }

    private void handleSaveLookChangesButtonAction(Event e) {
        int currentBeforeSigningLanguageId = this.languageComboBox.getSelectedLanguageId();
        ColorShapes.getServices().getBeforeLoginService().setLanguageId(currentBeforeSigningLanguageId);

        int currentBeforeSigningColourNumber = this.colourComboBox.getColourNumber();
        ColorShapes.getServices().getBeforeLoginService().setColourSkin(EnumColour.convertNumberToEnumColour(currentBeforeSigningColourNumber));

        int currentBeforeSigningZoom = Integer.parseInt(this.zoomTextField.getText());
        ColorShapes.getServices().getBeforeLoginService().setZoom(currentBeforeSigningZoom);
    }
}
