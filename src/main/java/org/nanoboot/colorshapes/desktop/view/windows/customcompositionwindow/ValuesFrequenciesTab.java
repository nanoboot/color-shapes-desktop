
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
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ValuesFrequenciesTab extends Tab {

    private SLabel valuesMinusTwoFrequencyLabel;
    private SLabel valuesMinusOneFrequencyLabel;
    private SLabel valuesZeroFrequencyLabel;
    private SLabel valuesPlusOneFrequencyLabel;
    private SLabel valuesPlusTwoFrequencyLabel;

    private TextField valuesMinusTwoFrequencyTextField;
    private TextField valuesMinusOneFrequencyTextField;
    private TextField valuesZeroFrequencyTextField;
    private TextField valuesPlusOneFrequencyTextField;
    private TextField valuesPlusTwoFrequencyTextField;
    private final CellLayout cellLayout;

    /**
     *
     */
    public ValuesFrequenciesTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(20221));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNode(valuesMinusTwoFrequencyLabel, 2, 1);
        cellLayout.addNode(valuesMinusTwoFrequencyTextField, 3, 1);
        cellLayout.addNode(valuesMinusOneFrequencyLabel, 2, 2);
        cellLayout.addNode(valuesMinusOneFrequencyTextField, 3, 2);
        cellLayout.addNode(valuesZeroFrequencyLabel, 2, 3);
        cellLayout.addNode(valuesZeroFrequencyTextField, 3, 3);
        cellLayout.addNode(valuesPlusOneFrequencyLabel, 2, 4);
        cellLayout.addNode(valuesPlusOneFrequencyTextField, 3, 4);
        cellLayout.addNode(valuesPlusTwoFrequencyLabel, 2, 5);
        cellLayout.addNode(valuesPlusTwoFrequencyTextField, 3, 5);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(3, 5, "10% 70% 20%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
    }

    private void initLabels() {
        valuesMinusTwoFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20222));
        valuesMinusOneFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20223));
        valuesZeroFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20224));
        valuesPlusOneFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20225));
        valuesPlusTwoFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20226));
    }

    private void initTextFields() {
        valuesMinusTwoFrequencyTextField = new TextField();
        valuesMinusOneFrequencyTextField = new TextField();
        valuesZeroFrequencyTextField = new TextField();
        valuesPlusOneFrequencyTextField = new TextField();
        valuesPlusTwoFrequencyTextField = new TextField();
    }

    int getMinusTwoFrequency() {
        return Integer.parseInt(this.valuesMinusTwoFrequencyTextField.getText());
    }

    int getMinusOneFrequency() {
        return Integer.parseInt(this.valuesMinusOneFrequencyTextField.getText());
    }

    int getZeroFrequency() {
        return Integer.parseInt(this.valuesZeroFrequencyTextField.getText());
    }

    int getPlusOneFrequency() {
        return Integer.parseInt(this.valuesPlusOneFrequencyTextField.getText());
    }

    int getPlusTwoFrequency() {
        return Integer.parseInt(this.valuesPlusTwoFrequencyTextField.getText());
    }

    void setMinusTwoFrequency(int value) {
        this.valuesMinusTwoFrequencyTextField.setText(String.valueOf(value));
    }

    void setMinusOneFrequency(int value) {
        this.valuesMinusOneFrequencyTextField.setText(String.valueOf(value));
    }

    void setZeroFrequency(int value) {
        this.valuesZeroFrequencyTextField.setText(String.valueOf(value));
    }

    void setPlusOneFrequency(int value) {
        this.valuesPlusOneFrequencyTextField.setText(String.valueOf(value));
    }

    void setPlusTwoFrequency(int value) {
        this.valuesPlusTwoFrequencyTextField.setText(String.valueOf(value));
    }

    /**
     *
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.setMinusTwoFrequency(jsonObject.getInt("value minus two frequency"));
        this.setMinusOneFrequency(jsonObject.getInt("value minus one frequency"));
        this.setZeroFrequency(jsonObject.getInt("value zero frequency"));
        this.setPlusOneFrequency(jsonObject.getInt("value plus one frequency"));
        this.setPlusTwoFrequency(jsonObject.getInt("value plus two frequency"));
    }
}
