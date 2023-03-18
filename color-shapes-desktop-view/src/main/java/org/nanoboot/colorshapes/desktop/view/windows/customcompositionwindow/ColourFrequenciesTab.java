
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

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ColourFrequenciesTab extends Tab {

    private SLabel lightGreenLabel;
    private SLabel redLabel;
    private SLabel darkBlueLabel;
    private SLabel yellowLabel;
    private SLabel lightBlueLabel;
    private SLabel purpleLabel;
    private SLabel brownLabel;
    private SLabel pinkLabel;
    private SLabel greenLabel;
    private SLabel goldLabel;
    private SLabel orangeLabel;
    private SLabel whiteLabel;
    private SLabel greyLabel;
    private SLabel blackLabel;
    private SLabel blueLabel;
    private SLabel armyGreenLabel;

    private TextField lightGreenTextField;
    private TextField redTextField;
    private TextField darkBlueTextField;
    private TextField yellowTextField;
    private TextField lightBlueTextField;
    private TextField purpleTextField;
    private TextField brownTextField;
    private TextField pinkTextField;
    private TextField greenTextField;
    private TextField goldTextField;
    private TextField orangeTextField;
    private TextField whiteTextField;
    private TextField greyTextField;
    private TextField blackTextField;
    private TextField blueTextField;
    private TextField armyGreenTextField;

    private List TextFieldList;
    private final CellLayout cellLayout;

    protected ResourcePack resourcePack;
    /**
     *
     */
    public ColourFrequenciesTab() {
        //super(resourcePack.getText(20201));//todo
        super();
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNode(this.lightGreenLabel, 2, 1);
        cellLayout.addNode(this.lightGreenTextField, 3, 1);
        cellLayout.addNode(this.redLabel, 2, 2);
        cellLayout.addNode(this.redTextField, 3, 2);
        cellLayout.addNode(this.darkBlueLabel, 2, 3);
        cellLayout.addNode(this.darkBlueTextField, 3, 3);
        cellLayout.addNode(this.yellowLabel, 2, 4);
        cellLayout.addNode(this.yellowTextField, 3, 4);
        cellLayout.addNode(this.lightBlueLabel, 2, 5);
        cellLayout.addNode(this.lightBlueTextField, 3, 5);
        cellLayout.addNode(this.purpleLabel, 2, 6);
        cellLayout.addNode(this.purpleTextField, 3, 6);
        cellLayout.addNode(this.brownLabel, 2, 7);
        cellLayout.addNode(this.brownTextField, 3, 7);
        cellLayout.addNode(this.pinkLabel, 2, 8);
        cellLayout.addNode(this.pinkTextField, 3, 8);
        cellLayout.addNode(this.greenLabel, 2, 9);
        cellLayout.addNode(this.greenTextField, 3, 9);
        cellLayout.addNode(this.goldLabel, 2, 10);
        cellLayout.addNode(this.goldTextField, 3, 10);
        cellLayout.addNode(this.orangeLabel, 2, 11);
        cellLayout.addNode(this.orangeTextField, 3, 11);
        cellLayout.addNode(this.whiteLabel, 2, 12);
        cellLayout.addNode(this.whiteTextField, 3, 12);
        cellLayout.addNode(this.greyLabel, 2, 13);
        cellLayout.addNode(this.greyTextField, 3, 13);
        cellLayout.addNode(this.blackLabel, 2, 14);
        cellLayout.addNode(this.blackTextField, 3, 14);
        cellLayout.addNode(this.blueLabel, 2, 15);
        cellLayout.addNode(this.blueTextField, 3, 15);
        cellLayout.addNode(this.armyGreenLabel, 2, 16);
        cellLayout.addNode(this.armyGreenTextField, 3, 16);
        ScrollPane scrollPane = new ScrollPane(cellLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-border-color: transparent");
        setContent(scrollPane);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(3, 16, "10% 70% 20%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
    }

    private void initLabels() {
        lightGreenLabel = new SLabel(resourcePack.getText(51));
        redLabel = new SLabel(resourcePack.getText(52));
        darkBlueLabel = new SLabel(resourcePack.getText(53));
        yellowLabel = new SLabel(resourcePack.getText(54));
        lightBlueLabel = new SLabel(resourcePack.getText(55));
        purpleLabel = new SLabel(resourcePack.getText(56));
        brownLabel = new SLabel(resourcePack.getText(57));
        pinkLabel = new SLabel(resourcePack.getText(58));
        greenLabel = new SLabel(resourcePack.getText(59));
        goldLabel = new SLabel(resourcePack.getText(60));
        orangeLabel = new SLabel(resourcePack.getText(61));
        whiteLabel = new SLabel(resourcePack.getText(62));
        greyLabel = new SLabel(resourcePack.getText(63));
        blackLabel = new SLabel(resourcePack.getText(64));
        blueLabel = new SLabel(resourcePack.getText(65));
        armyGreenLabel = new SLabel(resourcePack.getText(66));
    }

    private void initTextFields() {
        lightGreenTextField = new TextField();
        redTextField = new TextField();
        darkBlueTextField = new TextField();
        yellowTextField = new TextField();
        lightBlueTextField = new TextField();
        purpleTextField = new TextField();
        brownTextField = new TextField();
        pinkTextField = new TextField();
        greenTextField = new TextField();
        goldTextField = new TextField();
        orangeTextField = new TextField();
        whiteTextField = new TextField();
        greyTextField = new TextField();
        blackTextField = new TextField();
        blueTextField = new TextField();
        armyGreenTextField = new TextField();

        TextFieldList = new ArrayList<>();
        TextFieldList.add(lightGreenTextField);
        TextFieldList.add(redTextField);
        TextFieldList.add(darkBlueTextField);
        TextFieldList.add(yellowTextField);
        TextFieldList.add(lightBlueTextField);
        TextFieldList.add(purpleTextField);
        TextFieldList.add(brownTextField);
        TextFieldList.add(pinkTextField);
        TextFieldList.add(greenTextField);
        TextFieldList.add(goldTextField);
        TextFieldList.add(orangeTextField);
        TextFieldList.add(whiteTextField);
        TextFieldList.add(greyTextField);
        TextFieldList.add(blackTextField);
        TextFieldList.add(blueTextField);
        TextFieldList.add(armyGreenTextField);
    }

    /**
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        JsonArray colourFrequenciesJsonArray = jsonObject.getArray("colour frequency");
        for (int i = 1; i <= 16; i++) {
            int frequency = colourFrequenciesJsonArray.getInt(i);
            String frequencyString = String.valueOf(frequency);
            TextField TextField = (TextField) TextFieldList.get(i - 1);
            TextField.setText(frequencyString);
        }
    }

    /**
     * @return
     */
    public JsonArray toJsonArray() {
        JsonArray colourFrequencyJsonArray = new JsonArray();
        colourFrequencyJsonArray.addInt(0);
        for (Object element : TextFieldList) {
            TextField TextField = (TextField) element;
            String string = TextField.getText();
            int number = Integer.parseInt(string);
            colourFrequencyJsonArray.addInt(number);
        }
        return colourFrequencyJsonArray;
    }
}
