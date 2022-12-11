
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
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BoardTab extends Tab {

    private SLabel heightLabel;
    private SLabel widthLabel;
    private SLabel gridProbabilityLabel;
    private SLabel gridCountLabel;
    private SLabel wallProbabilityLabel;
    private SLabel wallCountLabel;

    private TextField heightTextField;
    private TextField widthTextField;
    private TextField gridProbabilityTextField;
    private TextField gridCountTextField;
    private TextField wallProbabilityTextField;
    private TextField wallCountTextField;

    private Button setHolesButton;
    private Button clearAllHolesButton;

    private final CellLayout cellLayout;

    /**
     *
     */
    public BoardTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(20100));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, heightLabel, heightTextField, widthLabel, widthTextField);
        cellLayout.addNodes(2, gridProbabilityLabel, gridProbabilityTextField, gridCountLabel, gridCountTextField);
        cellLayout.addNodes(3, wallProbabilityLabel, wallProbabilityTextField, wallCountLabel, wallCountTextField);
//        cellLayout.addNode(setHolesButton, 1, 4, 2, 1);
//        cellLayout.addNode(clearAllHolesButton, 3, 4, 2, 1);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(4, 3, "30% 20% 30% 20%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
        initButtons();
    }

    private void initLabels() {
        heightLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20101));
        widthLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20102));
        gridProbabilityLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20103));
        gridCountLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20104));
        wallProbabilityLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20105));
        wallCountLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20106));
    }

    private void initTextFields() {
        heightTextField = new TextField();
        widthTextField = new TextField();
        gridProbabilityTextField = new TextField();
        gridCountTextField = new TextField();
        wallProbabilityTextField = new TextField();
        wallCountTextField = new TextField();
    }

    private void initButtons() {
        setHolesButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(20107));
        clearAllHolesButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(20108));
    }

    /**
     *
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.gridProbabilityTextField.setText(String.valueOf(jsonObject.getInt("grid probability")));
        this.gridCountTextField.setText(String.valueOf(jsonObject.getInt("grid count")));
        this.wallProbabilityTextField.setText(String.valueOf(jsonObject.getInt("wall probability")));
        this.wallCountTextField.setText(String.valueOf(jsonObject.getInt("wall count")));

        JsonObject shapeJsonObject = jsonObject.getObject("shape");

        this.heightTextField.setText(String.valueOf(shapeJsonObject.getInt("height")));
        this.widthTextField.setText(String.valueOf(shapeJsonObject.getInt("width")));
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject boardCompositionJsonObject = new JsonObject();
        boardCompositionJsonObject.addInt("grid probability", Integer.parseInt((this.gridProbabilityTextField.getText().toString())));
        boardCompositionJsonObject.addInt("grid count", Integer.parseInt((this.gridCountTextField.getText().toString())));
        boardCompositionJsonObject.addInt("wall probability", Integer.parseInt((this.wallProbabilityTextField.getText().toString())));
        boardCompositionJsonObject.addInt("wall count", Integer.parseInt((this.wallCountTextField.getText().toString())));

        JsonObject shapeJsonObject = new JsonObject();
        shapeJsonObject.addInt("height", Integer.parseInt(this.heightTextField.getText().toString()));
        shapeJsonObject.addInt("width", Integer.parseInt(this.widthTextField.getText().toString()));

        shapeJsonObject.addArray("hole list", new JsonArray());

        boardCompositionJsonObject.addObject("shape", shapeJsonObject);

        return boardCompositionJsonObject;
    }
}
