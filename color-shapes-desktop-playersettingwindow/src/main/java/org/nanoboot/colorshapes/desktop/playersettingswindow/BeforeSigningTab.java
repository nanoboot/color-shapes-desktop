
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

import org.nanoboot.colorshapes.desktop.window.ColourComboBox;
import org.nanoboot.colorshapes.desktop.window.LanguageComboBox;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.colorshapes.engine.services.api.RepositoryService;
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.view.EnumColour;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
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

    //repositoryService.getSystemPropertyRepository().getProperty("beforelogin.language-code")
    //repositoryService.getSystemPropertyRepository().getProperty("beforelogin.colour-skin")

    private ResourcePack beforeSigningResourcePack;

    //todo
    private RepositoryService repositoryService;

    /**
     *
     */
    public BeforeSigningTab() {
        super();
        this.setText(beforeSigningResourcePack.getText(10901));
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
        displayLanguageLabel = new SLabel(beforeSigningResourcePack.getText(211));
        colourSkinLabel = new SLabel(beforeSigningResourcePack.getText(231));
        zoomLabel = new SLabel(beforeSigningResourcePack.getText(10102));
    }

    private void initComboBoxes() {
        this.languageComboBox = new LanguageComboBox();
        this.languageComboBox.setValue(beforeSigningResourcePack.getOriginalLanguageName());
        this.colourComboBox = new ColourComboBox();
        this.colourComboBox.setColourNumber(beforeLoginService.getColourSkin().getNumber());
    }

    private void initTextFields() {
        this.zoomTextField = new TextField(String.valueOf(beforeLoginService.getZoom()));
    }

    private void initButtons() {
        this.saveBeforeSigningChangesButton = new Button(beforeSigningResourcePack.getText(10991));
        this.saveBeforeSigningChangesButton.setOnAction(this::handleSaveLookChangesButtonAction);
    }

    private void handleSaveLookChangesButtonAction(Event e) {
        String currentBeforeSigningLanguageId = this.languageComboBox.getSelectedLanguageId();
        beforeLoginService.setLanguageId(currentBeforeSigningLanguageId);

        int currentBeforeSigningColourNumber = this.colourComboBox.getColourNumber();
        beforeLoginService.setColourSkin(EnumColour.convertNumberToEnumColour(currentBeforeSigningColourNumber));

        int currentBeforeSigningZoom = Integer.parseInt(this.zoomTextField.getText());
        beforeLoginService.setZoom(currentBeforeSigningZoom);
    }
}
