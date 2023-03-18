
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

import org.nanoboot.colorshapes.desktop.view.ColorShapesDesktopException;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.RadioButton;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class DetonatorTab extends Tab {

    private SLabel shapeToExplodeLabel;

    private SLabel minimumLenghtLabel;
    private SLabel minimumSizeLabel;

    private RadioButton lineRadioButton;
    private RadioButton blockRadioButton;
    private RadioButton squareRadioButton;
    private RadioButton ringRadioButton;
    private RadioButton customRadioButton;

    private TextField minimumLenghtTextField;
    private TextField minimumSizeTextField;

    private Button setCustomShapeToExplodeButton;

    private CellLayout cellLayout;

    private ToggleGroup toogleGroup;

    private ResourcePack resourcePack;
    /**
     *
     */
    public DetonatorTab() {
        super();
        this.setText(resourcePack.getText(20400));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, this.shapeToExplodeLabel);
        cellLayout.addNodes(2, this.lineRadioButton, this.minimumLenghtLabel, this.minimumLenghtTextField);
//        cellLayout.addNodes(3, this.blockRadioButton, this.minimumSizeLabel, this.minimumSizeTextField);
        cellLayout.addNodes(3, this.squareRadioButton);
        cellLayout.addNodes(4, this.ringRadioButton);
//        cellLayout.addNode(this.customRadioButton, 1, 6);
//        cellLayout.addNode(this.setCustomShapeToExplodeButton, 2, 6, 2, 1);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(3, 6, "50% 25% 25%");
        return cellLayout;
    }

    private void initNodes() {
        initRadioButtons();
        initLabels();
        initTextFields();
        initButtons();
    }

    private void initRadioButtons() {
        lineRadioButton = new RadioButton(resourcePack.getText(20402));
        blockRadioButton = new RadioButton(resourcePack.getText(20403));
        squareRadioButton = new RadioButton(resourcePack.getText(20404));
        ringRadioButton = new RadioButton(resourcePack.getText(20405));
        customRadioButton = new RadioButton(resourcePack.getText(20406));

        toogleGroup = new ToggleGroup();

        lineRadioButton.setToggleGroup(toogleGroup);
        blockRadioButton.setToggleGroup(toogleGroup);
        squareRadioButton.setToggleGroup(toogleGroup);
        ringRadioButton.setToggleGroup(toogleGroup);
        customRadioButton.setToggleGroup(toogleGroup);
    }

    private void initButtons() {
        this.setCustomShapeToExplodeButton = new Button(resourcePack.getText(20421));
    }

    private void initLabels() {
        shapeToExplodeLabel = new SLabel(resourcePack.getText(20401));

        minimumLenghtLabel = new SLabel(resourcePack.getText(20411));
        minimumSizeLabel = new SLabel(resourcePack.getText(20412));
    }

    private void initTextFields() {
        minimumLenghtTextField = new TextField();
        minimumSizeTextField = new TextField();
    }

    /**
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        String explodingShapeType = jsonObject.getString("exploding shape type");
        switch (explodingShapeType) {
            case "LINE":
                this.lineRadioButton.setSelected(true);
                break;
            case "BLOCK":
                this.blockRadioButton.setSelected(true);
                break;
            case "RING":
                this.ringRadioButton.setSelected(true);
                break;
            case "SQUARE":
                this.squareRadioButton.setSelected(true);
                break;
            case "CUSTOM":
                this.customRadioButton.setSelected(true);
                break;
        }
        this.minimumLenghtTextField.setText(String.valueOf(jsonObject.getInt("minimum line lenght")));
        this.minimumSizeTextField.setText(String.valueOf(jsonObject.getInt("minimum block size")));
    }

    /**
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject ballDetonatorCompositionJsonObject = new JsonObject();

        String explodingShapeType;

        if (lineRadioButton.isSelected()) {
            explodingShapeType = "LINE";
        } else if (blockRadioButton.isSelected()) {
            explodingShapeType = "BLOCK";
        } else if (ringRadioButton.isSelected()) {
            explodingShapeType = "RING";
        } else if (squareRadioButton.isSelected()) {
            explodingShapeType = "SQUARE";
        } else if (customRadioButton.isSelected()) {
            explodingShapeType = "CUSTOM";
        } else {
            throw new ColorShapesDesktopException("Fatal error.");
        }

        ballDetonatorCompositionJsonObject.addString("exploding shape type", explodingShapeType);
        ballDetonatorCompositionJsonObject.addInt("minimum line lenght", Integer.parseInt(this.minimumLenghtTextField.getText()));
        ballDetonatorCompositionJsonObject.addInt("minimum block size", Integer.parseInt(this.minimumSizeTextField.getText()));
        if (!("CUSTOM".equals(explodingShapeType))) {
            ballDetonatorCompositionJsonObject.addNull("custom exploding shape");
        } else {
            JsonObject shapeJsonObject = new JsonObject();
            shapeJsonObject.addInt("height", 3);
            shapeJsonObject.addInt("width", 3);

            shapeJsonObject.addArray("hole list", new JsonArray());

            ballDetonatorCompositionJsonObject.addObject("custom exploding shape", shapeJsonObject);
        }

        return ballDetonatorCompositionJsonObject;
    }
}
