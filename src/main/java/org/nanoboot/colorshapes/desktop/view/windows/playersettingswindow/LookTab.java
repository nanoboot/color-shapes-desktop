
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
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.RadioButton;
import org.nanoboot.powerframework.simplicity.window.controls.SCheckBox;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;
import javafx.scene.control.ToggleGroup;
import org.nanoboot.powerframework.simplicity.EnumColour;
import org.nanoboot.powerframework.simplicity.window.controls.Button;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class LookTab extends Tab {

    private SLabel displayLanguageLabel;
    private SLabel colourSkinLabel;
    private SLabel zoomLabel;
    private SLabel ballLightingLabel;
    private SLabel ballMoveEffectLabel;

    private LanguageComboBox languageComboBox;
    private ColourComboBox colourComboBox;

    private TextField zoomTextField;

    private RadioButton noneRadioButton;
    private RadioButton aboveRadioButton;
    private RadioButton aheadRadioButton;

    private ToggleGroup ballLightingToogleGroup;

    private SCheckBox showLineAroundBallCheckBox;
    private SCheckBox showWhereABallCanBeMovedCheckBox;
    private SCheckBox highlightFieldAfterBallExplodedCheckBox;

    private RadioButton noExtraEffectRadioButton;
    private RadioButton arrowRadioButton;
    private RadioButton highlightFieldsRadioButton;

    private ToggleGroup ballMoveEffectToogleGroup;

    private Button loadDefaultButton;
    private Button saveLookChanges;

    private final int languageId;
    private final int colourNumber;
    private final int zoom;
    private final String ballLightingName;
    private final boolean linesAround;
    private final boolean showBallMoves;
    private final boolean highlightFieldBecauseOfExplosion;
    private final String ballMoveEffectName;

    private final CellLayout cellLayout;

    /**
     *
     */
    public LookTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(10101));
        this.setClosable(false);

        this.languageId = ColorShapes.getServices().getLookService().getLanguageId();
        this.colourNumber = ColorShapes.getServices().getLookService().getColourSkin().getNumber();
        this.zoom = (int) ColorShapes.getServices().getLookService().getZoom();
        this.ballLightingName = ColorShapes.getServices().getPlayerService().getBallLightingName();
        this.linesAround = ColorShapes.getServices().getPlayerService().getLinesAround();
        this.showBallMoves = ColorShapes.getServices().getPlayerService().getShowWhereABallCanBeMoved();
        this.highlightFieldBecauseOfExplosion = ColorShapes.getServices().getPlayerService().getHighlightFieldAfterBallExploded();
        this.ballMoveEffectName = ColorShapes.getServices().getPlayerService().getBallMoveEffectName();

        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, this.displayLanguageLabel, this.languageComboBox);
        cellLayout.addNodes(2, this.colourSkinLabel, this.colourComboBox);
        cellLayout.addNodes(3, this.zoomLabel, this.zoomTextField);
        cellLayout.addNodes(4, this.ballLightingLabel, this.noneRadioButton);
        cellLayout.addNode(this.aboveRadioButton, 2, 5);
        cellLayout.addNode(this.aheadRadioButton, 2, 6);
        cellLayout.addNode(this.showLineAroundBallCheckBox, 1, 7, 2, 1);
//        cellLayout.addNode(this.showWhereABallCanBeMovedCheckBox, 1, 8, 2, 1);
//        cellLayout.addNode(this.highlightFieldAfterBallExplodedCheckBox, 1, 9, 2, 1);
        cellLayout.addNodes(8, this.ballMoveEffectLabel, this.noExtraEffectRadioButton);
//        cellLayout.addNode(this.arrowRadioButton, 2, 11);
        cellLayout.addNode(this.highlightFieldsRadioButton, 2, 9);
        cellLayout.addNodes(10, this.loadDefaultButton, this.saveLookChanges);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(2, 13, "50% 50%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initComboBoxes();
        initTextFields();
        initRadioButtons();
        initCheckBoxes();
        initRadioButtons();
        initButtons();
    }

    private void initLabels() {
        displayLanguageLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(211));
        colourSkinLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(231));
        zoomLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10102));
        ballLightingLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10113));
        ballMoveEffectLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10124));
    }

    private void initComboBoxes() {
        this.languageComboBox = new LanguageComboBox();
        this.colourComboBox = new ColourComboBox();
        this.colourComboBox.setColourNumber(ColorShapes.getServices().getLookService().getColourSkin().getNumber());
    }

    private void initTextFields() {
        this.zoomTextField = new TextField(String.valueOf(this.zoom));
    }

    private void initRadioButtons() {
        noneRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10114));
        this.noneRadioButton.setId("none");
        if ("none".equals(this.ballLightingName)) {
            noneRadioButton.setSelected(true);
        }

        aboveRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10115));
        this.aboveRadioButton.setId("above");
        if ("above".equals(this.ballLightingName)) {
            aboveRadioButton.setSelected(true);
        }

        aheadRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10116));
        this.aheadRadioButton.setId("ahead");
        if ("ahead".equals(this.ballLightingName)) {
            aheadRadioButton.setSelected(true);
        }

        ballLightingToogleGroup = new ToggleGroup();

        noneRadioButton.setToggleGroup(ballLightingToogleGroup);
        aboveRadioButton.setToggleGroup(ballLightingToogleGroup);
        aheadRadioButton.setToggleGroup(ballLightingToogleGroup);

        noExtraEffectRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10125));
        this.noExtraEffectRadioButton.setId("no");
        if ("no".equals(this.ballMoveEffectName)) {
            noExtraEffectRadioButton.setSelected(true);
        }

        arrowRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10126));
        this.arrowRadioButton.setId("arrow");
        if ("arrow".equals(this.ballMoveEffectName)) {
            arrowRadioButton.setSelected(true);
        }

        highlightFieldsRadioButton = new RadioButton(ColorShapes.getServices().getLanguageService().getTextConstant(10127));
        this.highlightFieldsRadioButton.setId("highlight");
        if ("highlight".equals(this.ballMoveEffectName)) {
            highlightFieldsRadioButton.setSelected(true);
        }

        ballMoveEffectToogleGroup = new ToggleGroup();

        noExtraEffectRadioButton.setToggleGroup(ballMoveEffectToogleGroup);
        arrowRadioButton.setToggleGroup(ballMoveEffectToogleGroup);
        highlightFieldsRadioButton.setToggleGroup(ballMoveEffectToogleGroup);

    }

    private void initCheckBoxes() {
        showLineAroundBallCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(10121));
        showLineAroundBallCheckBox.setSelected(this.linesAround);

        showWhereABallCanBeMovedCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(10122));
        showWhereABallCanBeMovedCheckBox.setSelected(this.showBallMoves);

        highlightFieldAfterBallExplodedCheckBox = new SCheckBox(ColorShapes.getServices().getLanguageService().getTextConstant(10123));
        highlightFieldAfterBallExplodedCheckBox.setSelected(this.highlightFieldBecauseOfExplosion);
    }

    private void initButtons() {
        this.loadDefaultButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10140));
        this.loadDefaultButton.setOnAction(this::handleLoadDefaultButtonAction);
        this.saveLookChanges = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10141));
        this.saveLookChanges.setOnAction(this::handleSaveLookChangesButtonAction);
    }

    private void handleLoadDefaultButtonAction(Event e) {
        this.languageComboBox.setDefault();
    }

    private void handleSaveLookChangesButtonAction(Event e) {
        int currentLanguageId = this.languageComboBox.getSelectedLanguageId();
        ColorShapes.getServices().getLookService().setLanguageId(currentLanguageId);

        int currentColourNumber = this.colourComboBox.getColourNumber();
        ColorShapes.getServices().getLookService().setColourSkin(EnumColour.convertNumberToEnumColour(currentColourNumber));

        int currentZoom = Integer.parseInt(this.zoomTextField.getText());
        ColorShapes.getServices().getLookService().setZoom(currentZoom);

        String currentBallLightingName = ((RadioButton) this.ballLightingToogleGroup.getSelectedToggle()).getId();
        ColorShapes.getServices().getPlayerService().setBallLightingName(currentBallLightingName);

        boolean currentShowLineAroundBall = this.showLineAroundBallCheckBox.isSelected();
        ColorShapes.getServices().getPlayerService().setLinesAround(currentShowLineAroundBall);

        boolean currentshowWhereABallCanBeMoved = this.showWhereABallCanBeMovedCheckBox.isSelected();
        ColorShapes.getServices().getPlayerService().setShowWhereABallCanBeMoved(currentshowWhereABallCanBeMoved);

        boolean currentHighlightFieldAfterBallExploded = this.highlightFieldAfterBallExplodedCheckBox.isSelected();
        ColorShapes.getServices().getPlayerService().setShowHighlightFieldAfterBallExploded(currentHighlightFieldAfterBallExploded);

        String currentBallMoveEffectName = ((RadioButton) this.ballMoveEffectToogleGroup.getSelectedToggle()).getId();
        ColorShapes.getServices().getPlayerService().setBallMoveEffectName(currentBallMoveEffectName);
    }
}
