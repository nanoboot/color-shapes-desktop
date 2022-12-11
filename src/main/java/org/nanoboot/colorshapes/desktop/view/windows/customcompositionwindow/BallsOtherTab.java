
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
public class BallsOtherTab extends Tab {

    private SLabel colourfulBallFrequencyLabel;
    private SLabel rainbowBallFrequencyLabel;
    private SLabel unmoveableBallsProbabilityLabel;
    private SLabel unbreakableBallsProbabilityLabel;

    private TextField colourfulBallFrequencyTextField;
    private TextField rainbowBallFrequencyTextField;
    private TextField unmoveableBallsProbabilityTextField;
    private TextField unbreakableBallsProbabilityTextField;

    private final CellLayout cellLayout;

    /**
     *
     */
    public BallsOtherTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(20240));
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNode(colourfulBallFrequencyLabel, 2, 1);
        cellLayout.addNode(colourfulBallFrequencyTextField, 3, 1);
        cellLayout.addNode(rainbowBallFrequencyLabel, 2, 2);
        cellLayout.addNode(rainbowBallFrequencyTextField, 3, 2);
        cellLayout.addNode(unmoveableBallsProbabilityLabel, 2, 3);
        cellLayout.addNode(unmoveableBallsProbabilityTextField, 3, 3);
        cellLayout.addNode(unbreakableBallsProbabilityLabel, 2, 4);
        cellLayout.addNode(unbreakableBallsProbabilityTextField, 3, 4);

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
        colourfulBallFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20241));
        rainbowBallFrequencyLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20242));
        unmoveableBallsProbabilityLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20243));
        unbreakableBallsProbabilityLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(20244));
    }

    private void initTextFields() {
        colourfulBallFrequencyTextField = new TextField();
        rainbowBallFrequencyTextField = new TextField();
        unmoveableBallsProbabilityTextField = new TextField();
        unbreakableBallsProbabilityTextField = new TextField();
    }

    /**
     *
     * @return
     */
    public int getColourfulBallFrequency() {
        return Integer.parseInt(colourfulBallFrequencyTextField.getText());
    }

    /**
     *
     * @param value
     */
    public void setColourfulBallFrequency(int value) {
        this.colourfulBallFrequencyTextField.setText(String.valueOf(value));
    }

    /**
     *
     * @return
     */
    public int getRainbowBallFrequency() {
        return Integer.parseInt(rainbowBallFrequencyTextField.getText());
    }

    /**
     *
     * @param value
     */
    public void setRainbowBallFrequency(int value) {
        this.rainbowBallFrequencyTextField.setText(String.valueOf(value));
    }

    /**
     *
     * @return
     */
    public int getUnmoveableBallsProbability() {
        return Integer.parseInt(unmoveableBallsProbabilityTextField.getText());
    }

    /**
     *
     * @param value
     */
    public void setUnmoveableBallsProbability(int value) {
        this.unmoveableBallsProbabilityTextField.setText(String.valueOf(value));
    }

    /**
     *
     * @return
     */
    public int getUnbreakableBallsProbability() {
        return Integer.parseInt(unbreakableBallsProbabilityTextField.getText());
    }

    /**
     *
     * @param value
     */
    public void setUnbreakableBallsProbability(int value) {
        this.unbreakableBallsProbabilityTextField.setText(String.valueOf(value));
    }

    /**
     *
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.setColourfulBallFrequency(jsonObject.getInt("colorful ball frequency"));
        this.setRainbowBallFrequency(jsonObject.getInt("rainbow ball frequency"));
        this.setUnmoveableBallsProbability(jsonObject.getInt("unmoveable ball probability"));
        this.setUnbreakableBallsProbability(jsonObject.getInt("unbreakable ball probability"));
    }
}
