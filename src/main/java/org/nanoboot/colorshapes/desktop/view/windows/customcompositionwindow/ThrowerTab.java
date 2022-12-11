
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
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ThrowerTab extends Tab {

    private SLabel startLabel;
    private SLabel duringLabel;
    private SLabel ballFrequencyLabel;
    private SLabel automaticBombFrequencyLabel;
    private SLabel manualBombFrequencyLabel;

    private TextField startTextField;
    private TextField duringTextField;
    private TextField ballFrequencyTextField;
    private TextField automaticBombTextField;
    private TextField manualBombFrequencyTextField;
    private SCheckBox showThePositionsOfTheNextBallsCheckBox;

    private final CellLayout cellLayout;

    /**
     *
     */
    public ThrowerTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(20300));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNode(this.startLabel, 1, 1, 2, 1);
        cellLayout.addNode(this.startTextField, 2, 2);
        cellLayout.addNode(this.duringLabel, 1, 3, 2, 1);
        cellLayout.addNode(this.duringTextField, 2, 4);
        cellLayout.addNodes(5, this.ballFrequencyLabel, this.ballFrequencyTextField);
        cellLayout.addNodes(6, this.automaticBombFrequencyLabel, this.automaticBombTextField);
        cellLayout.addNodes(7, this.manualBombFrequencyLabel, this.manualBombFrequencyTextField);
//        cellLayout.addNode(this.showThePositionsOfTheNextBallsCheckBox, 1, 8, 2, 1);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(2, 8, "50% 50%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
        initCheckBox();
    }

    private void initCheckBox() {
        this.showThePositionsOfTheNextBallsCheckBox = new SCheckBox();
        showThePositionsOfTheNextBallsCheckBox.setCheckBoxText(ColorShapes.getServices().getLanguageService().getTextConstant(20306));

    }

    private void initLabels() {
        startLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20301));
        duringLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20302));
        ballFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20303));
        automaticBombFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20304));
        manualBombFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20305));
    }

    private void initTextFields() {
        startTextField = new TextField();
        duringTextField = new TextField();
        ballFrequencyTextField = new TextField();
        automaticBombTextField = new TextField();
        manualBombFrequencyTextField = new TextField();
    }

    /**
     *
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.startTextField.setText(String.valueOf(jsonObject.getInt("start")));
        this.duringTextField.setText(String.valueOf(jsonObject.getInt("during")));
        this.ballFrequencyTextField.setText(String.valueOf(jsonObject.getInt("ball frequency")));
        this.automaticBombTextField.setText(String.valueOf(jsonObject.getInt("automatic bomb frequency")));
        this.manualBombFrequencyTextField.setText(String.valueOf(jsonObject.getInt("manual bomb frequency")));
        this.showThePositionsOfTheNextBallsCheckBox.setSelected(jsonObject.getBoolean("show positions"));
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject ballThrowerCompositionJsonObject = new JsonObject();

        ballThrowerCompositionJsonObject.addInt("start", Integer.parseInt(this.startTextField.getText()));
        ballThrowerCompositionJsonObject.addInt("during", Integer.parseInt(this.duringTextField.getText()));
        ballThrowerCompositionJsonObject.addInt("ball frequency", Integer.parseInt(this.ballFrequencyTextField.getText()));
        ballThrowerCompositionJsonObject.addInt("automatic bomb frequency", Integer.parseInt(this.automaticBombTextField.getText()));
        ballThrowerCompositionJsonObject.addInt("manual bomb frequency", Integer.parseInt(this.manualBombFrequencyTextField.getText()));

        ballThrowerCompositionJsonObject.addBoolean("show positions", this.showThePositionsOfTheNextBallsCheckBox.isSelected());

        return ballThrowerCompositionJsonObject;
    }
}
