
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

import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.colorshapes.desktop.window.ColourComboBox;
import org.nanoboot.colorshapes.desktop.window.LanguageComboBox;
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.RadioButton;
import org.nanoboot.powerframework.view.window.controls.SCheckBox;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;
import javafx.scene.control.ToggleGroup;
import org.nanoboot.powerframework.view.EnumColour;
import org.nanoboot.powerframework.view.window.controls.Button;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
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
    private SCheckBox highlightCellAfterBallExplodedCheckBox;

    private RadioButton noExtraEffectRadioButton;
    private RadioButton arrowRadioButton;
    private RadioButton highlightCellsRadioButton;

    private ToggleGroup ballMoveEffectToogleGroup;

    private Button loadDefaultButton;
    private Button saveLookChanges;

    private final String languageId;
    private final int colourNumber;
    private final int zoom;
    private final String ballLightingName;
    private final boolean linesAround;
    private final boolean showBallMoves;
    private final boolean highlightCellBecauseOfExplosion;
    private final String ballMoveEffectName;

    private final CellLayout cellLayout;
    private ResourcePack resourcePack;
    private CurrentLookService lookService;
    private SessionService playerSessionService;
    private PlayerService playerService;

    /**
     *
     */
    public LookTab() {
        super();
        this.setText(resourcePack.getText(10101));
        this.setClosable(false);

        this.languageId = lookService.getLanguageId();
        this.colourNumber = lookService.getColourSkin().getNumber();
        this.zoom = (int) lookService.getZoom();
        this.ballLightingName = playerSessionService.getCurrentSession().getPlayer().getBallLightingName();
        this.linesAround = playerSessionService.getCurrentSession().getPlayer().isLinesAround();
        this.showBallMoves = playerSessionService.getCurrentSession().getPlayer().isShowWhereABallCanBeMoved();
        this.highlightCellBecauseOfExplosion = playerSessionService.getCurrentSession().getPlayer().isShowHighlightCellAfterBallExploded();
        this.ballMoveEffectName = playerSessionService.getCurrentSession().getPlayer().getBallMoveEffectName();

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
//        cellLayout.addNode(this.highlightCellAfterBallExplodedCheckBox, 1, 9, 2, 1);
        cellLayout.addNodes(8, this.ballMoveEffectLabel, this.noExtraEffectRadioButton);
//        cellLayout.addNode(this.arrowRadioButton, 2, 11);
        cellLayout.addNode(this.highlightCellsRadioButton, 2, 9);
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
        displayLanguageLabel = new SLabel(resourcePack.getText(211));
        colourSkinLabel = new SLabel(resourcePack.getText(231));
        zoomLabel = new SLabel(resourcePack.getText(10102));
        ballLightingLabel = new SLabel(resourcePack.getText(10113));
        ballMoveEffectLabel = new SLabel(resourcePack.getText(10124));
    }

    private void initComboBoxes() {
        this.languageComboBox = new LanguageComboBox();
        this.colourComboBox = new ColourComboBox();
        this.colourComboBox.setColourNumber(lookService.getColourSkin().getNumber());
    }

    private void initTextFields() {
        this.zoomTextField = new TextField(String.valueOf(this.zoom));
    }

    private void initRadioButtons() {
        noneRadioButton = new RadioButton(resourcePack.getText(10114));
        this.noneRadioButton.setId("none");
        if ("none".equals(this.ballLightingName)) {
            noneRadioButton.setSelected(true);
        }

        aboveRadioButton = new RadioButton(resourcePack.getText(10115));
        this.aboveRadioButton.setId("above");
        if ("above".equals(this.ballLightingName)) {
            aboveRadioButton.setSelected(true);
        }

        aheadRadioButton = new RadioButton(resourcePack.getText(10116));
        this.aheadRadioButton.setId("ahead");
        if ("ahead".equals(this.ballLightingName)) {
            aheadRadioButton.setSelected(true);
        }

        ballLightingToogleGroup = new ToggleGroup();

        noneRadioButton.setToggleGroup(ballLightingToogleGroup);
        aboveRadioButton.setToggleGroup(ballLightingToogleGroup);
        aheadRadioButton.setToggleGroup(ballLightingToogleGroup);

        noExtraEffectRadioButton = new RadioButton(resourcePack.getText(10125));
        this.noExtraEffectRadioButton.setId("no");
        if ("no".equals(this.ballMoveEffectName)) {
            noExtraEffectRadioButton.setSelected(true);
        }

        arrowRadioButton = new RadioButton(resourcePack.getText(10126));
        this.arrowRadioButton.setId("arrow");
        if ("arrow".equals(this.ballMoveEffectName)) {
            arrowRadioButton.setSelected(true);
        }

        highlightCellsRadioButton = new RadioButton(resourcePack.getText(10127));
        this.highlightCellsRadioButton.setId("highlight");
        if ("highlight".equals(this.ballMoveEffectName)) {
            highlightCellsRadioButton.setSelected(true);
        }

        ballMoveEffectToogleGroup = new ToggleGroup();

        noExtraEffectRadioButton.setToggleGroup(ballMoveEffectToogleGroup);
        arrowRadioButton.setToggleGroup(ballMoveEffectToogleGroup);
        highlightCellsRadioButton.setToggleGroup(ballMoveEffectToogleGroup);

    }

    private void initCheckBoxes() {
        showLineAroundBallCheckBox = new SCheckBox(resourcePack.getText(10121));
        showLineAroundBallCheckBox.setSelected(this.linesAround);

        showWhereABallCanBeMovedCheckBox = new SCheckBox(resourcePack.getText(10122));
        showWhereABallCanBeMovedCheckBox.setSelected(this.showBallMoves);

        highlightCellAfterBallExplodedCheckBox = new SCheckBox(resourcePack.getText(10123));
        highlightCellAfterBallExplodedCheckBox.setSelected(this.highlightCellBecauseOfExplosion);
    }

    private void initButtons() {
        this.loadDefaultButton = new Button(resourcePack.getText(10140));
        this.loadDefaultButton.setOnAction(this::handleLoadDefaultButtonAction);
        this.saveLookChanges = new Button(resourcePack.getText(10141));
        this.saveLookChanges.setOnAction(this::handleSaveLookChangesButtonAction);
    }

    private void handleLoadDefaultButtonAction(Event e) {
        this.languageComboBox.setDefault();
    }

    private void handleSaveLookChangesButtonAction(Event e) {
        String currentLanguageId = this.languageComboBox.getSelectedLanguageId();
        lookService.setLanguageId(currentLanguageId);

        int currentColourNumber = this.colourComboBox.getColourNumber();
        lookService.setColourSkin(EnumColour.convertNumberToEnumColour(currentColourNumber));

        int currentZoom = Integer.parseInt(this.zoomTextField.getText());
        lookService.setZoom(currentZoom);

        Player player = playerSessionService.getCurrentSession().getPlayer();
        String currentBallLightingName = ((RadioButton) this.ballLightingToogleGroup.getSelectedToggle()).getId();
        player.setBallLightingName(currentBallLightingName);

        boolean currentShowLineAroundBall = this.showLineAroundBallCheckBox.isSelected();
        player.setLinesAround(currentShowLineAroundBall);

        boolean currentshowWhereABallCanBeMoved = this.showWhereABallCanBeMovedCheckBox.isSelected();
        player.setShowWhereABallCanBeMoved(currentshowWhereABallCanBeMoved);

        boolean currentHighlightCellAfterBallExploded = this.highlightCellAfterBallExplodedCheckBox.isSelected();
        player.setShowHighlightCellAfterBallExploded(currentHighlightCellAfterBallExploded);

        String currentBallMoveEffectName = ((RadioButton) this.ballMoveEffectToogleGroup.getSelectedToggle()).getId();
        player.setBallMoveEffectName(currentBallMoveEffectName);
        playerService.updatePlayer(player);
    }
}
