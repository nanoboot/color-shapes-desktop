
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

package org.nanoboot.colorshapes.desktop.view.windows;

import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import org.nanoboot.colorshapes.desktop.view.windows.customcompositionwindow.*;
import org.nanoboot.powerframework.view.View;
import javafx.event.ActionEvent;
import org.nanoboot.powerframework.view.window.controls.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class CustomCompositionWindow extends ColorShapesAbstractWindow {

    private TabPane tabPane;
    private CellLayout buttonsCellLayout;

    private Button beginButton;
    private Button defaultButton;
    private Button randomButton;

    private BoardTab boardTab;
    private BallsTab ballsTab;
    private ThrowerTab throwerTab;
    private DetonatorTab detonatorTab;
    private OtherTab otherTab;

    private final JsonObject jsonObject;

    /**
     * Constructor
     */
    public CustomCompositionWindow(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.showOnlyTheCloseButton();
        this.setWidth(160 * View.getDpmm());
        this.setHeight(160 * View.getDpmm());
        this.centerOnScreen();
        updateByJsonObject(this.jsonObject);
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction(Object... args) {
        initModality(Modality.APPLICATION_MODAL);
        String title = resourcePack.getText(20000);
        this.setWindowTitle(title);

        boardTab = new BoardTab();
        ballsTab = new BallsTab();
        throwerTab = new ThrowerTab();
        detonatorTab = new DetonatorTab();
        otherTab = new OtherTab();

        tabPane = new TabPane();
        tabPane.getTabs().addAll(boardTab, ballsTab, throwerTab, detonatorTab/*, otherTab*/);

        buttonsCellLayout = new CellLayout(3, 1, "33% 33% 33%");

        beginButton = new Button(resourcePack.getText(20003));
        defaultButton = new Button(resourcePack.getText(20005));
        randomButton = new Button(resourcePack.getText(20006));

        beginButton.setOnAction(this::handleBeginAction);
        defaultButton.setOnAction(this::handleDefaultAction);
        randomButton.setOnAction(this::handleRandomAction);

        buttonsCellLayout.addNodes(1, beginButton, defaultButton, randomButton);
        VBox vBox = new VBox(tabPane, buttonsCellLayout);
        VBox.setVgrow(tabPane, Priority.ALWAYS);
        this.applicationArea.getChildren().add(vBox);
    }

    /**
     * @param gameCompositionJsonObject
     */
    public void updateByJsonObject(JsonObject gameCompositionJsonObject) {
        JsonObject boardCompositionJsonObject = gameCompositionJsonObject.getObject("board composition");
        JsonObject ballFactoryCompositionJsonObject = gameCompositionJsonObject.getObject("ball factory composition");
        JsonObject ballThrowerCompositionJsonObject = gameCompositionJsonObject.getObject("ball thrower composition");
        JsonObject ballDetonatorCompositionJsonObject = gameCompositionJsonObject.getObject("ball detonator composition");
        JsonObject otherCompositionJsonObject = gameCompositionJsonObject.getObject("other composition");

        boardTab.updateByJsonObject(boardCompositionJsonObject);
        ballsTab.updateByJsonObject(ballFactoryCompositionJsonObject);
        throwerTab.updateByJsonObject(ballThrowerCompositionJsonObject);
        detonatorTab.updateByJsonObject(ballDetonatorCompositionJsonObject);
        otherTab.updateByJsonObject(otherCompositionJsonObject);
    }

    /**
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject gameCompositionJsonObject = new JsonObject();

        JsonObject boardCompositionJsonObject = this.boardTab.toJsonObject();
        JsonObject ballFactoryCompositionJsonObject = this.ballsTab.toJsonObject();
        JsonObject ballThrowerCompositionJsonObject = this.throwerTab.toJsonObject();
        JsonObject ballDetonatorCompositionJsonObject = this.detonatorTab.toJsonObject();
        JsonObject otherCompositionJsonObject = this.otherTab.toJsonObject();

        gameCompositionJsonObject.addObject("board composition", boardCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball factory composition", ballFactoryCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball thrower composition", ballThrowerCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball detonator composition", ballDetonatorCompositionJsonObject);
        gameCompositionJsonObject.addObject("other composition", otherCompositionJsonObject);

        return gameCompositionJsonObject;
    }

    private void handleBeginAction(ActionEvent event) {

//        gameCompositionService.setGameComposition(this.toJsonObject());
//        gameService.playNewGame();

        this.close();
    }

    private void handleDefaultAction(ActionEvent event) {
//        gameCompositionService.setGameCompositionDefault();
//        this.updateByJsonObject(gameCompositionService.getGameComposition());
    }

    private void handleRandomAction(ActionEvent event) {
//        gameCompositionService.setGameCompositionRandom();
//        this.updateByJsonObject(gameCompositionService.getGameComposition());
    }

    /**
     *
     */
    @Override
    public void onClosingWindow() {
//        gameCompositionService.setGameComposition(this.toJsonObject());
    }
}
