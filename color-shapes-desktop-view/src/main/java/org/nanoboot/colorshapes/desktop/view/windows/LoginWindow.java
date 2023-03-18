
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

import org.nanoboot.colorshapes.desktop.localisation.impl.DummyResourcePackImpl;
import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import org.nanoboot.colorshapes.engine.infrastructure.Connection;
import org.nanoboot.colorshapes.engine.infrastructure.Engine;
import org.nanoboot.colorshapes.engine.infrastructure.GameSource;
import org.nanoboot.powerframework.view.View;
import org.nanoboot.powerframework.view.window.controls.PasswordField;
import org.nanoboot.powerframework.view.window.controls.TextField;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.SCheckBox;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import org.nanoboot.powerframework.view.boxes.AlertBox;
import org.nanoboot.powerframework.view.layouts.CellLayout;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class LoginWindow extends ColorShapesAbstractWindow {

    private final Engine engine;
    private CellLayout cellLayout;

    private SLabel nickSLabel;
    private TextField nickSTextField;
    private SLabel passwordSLabel;
    private PasswordField passwordSPasswordField;

    private SCheckBox keepMeSignedInSCheckBox;

    private Button signInButton;
    private Button viewHelpButton;
    private Button createNewPlayerButton;

    /**
     *
     */
    public LoginWindow(Engine engine) {
        this.engine=engine;
    }

    /**
     *
     */
    @Override
    public void onResizingWindow() {
    }

    /**
     *
     */
    @Override
    public void onClosingWindow() {
        engine.stop();
        View.stopSimplicity();
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction(Object... args) {
        this.resourcePack = new DummyResourcePackImpl();
        cellLayout = new CellLayout(2, 8, "50% 50%");

        this.showOnlyTheCloseButton();
        this.applicationArea.getChildren().addAll(this.cellLayout);

        nickSLabel = new SLabel(resourcePack.getText("common.nick") + ":");
        nickSTextField = new TextField();

        passwordSLabel = new SLabel(resourcePack.getText("common.password") + ":");
        passwordSPasswordField = new PasswordField();

        this.keepMeSignedInSCheckBox = new SCheckBox();
        this.keepMeSignedInSCheckBox.setCheckBoxText(resourcePack.getText("loginwindow.keep-me-logged"));

        signInButton = new Button(resourcePack.getText("loginwindow.sign-in"));
        viewHelpButton = new Button(resourcePack.getText("loginwindow.view-help"));
        createNewPlayerButton = new Button(resourcePack.getText("common.create-new-player"));
        this.signInButton.setOnAction(this::handleSignInButtonAction);
        this.viewHelpButton.setOnAction(this::handleViewHelpButtonAction);
        this.createNewPlayerButton.setOnAction(this::handleCreateNewPlayerButtonAction);

        this.setWindowTitle(resourcePack.getText("loginwindow.login"));
        this.applicationArea.setAlignment(Pos.BOTTOM_CENTER);
        this.cellLayout.addNodes(1, this.nickSLabel, nickSTextField);
        this.cellLayout.addNodes(2, this.passwordSLabel, this.passwordSPasswordField);
        this.cellLayout.addNodes(3, this.keepMeSignedInSCheckBox, this.signInButton);
        this.cellLayout.addNodes(4, this.viewHelpButton, this.createNewPlayerButton);
        this.setWidth(90 * View.getDpmm());
        this.setHeight(45 * View.getDpmm());
    }

    private void handleSignInButtonAction(ActionEvent event) {
        String nick = this.nickSTextField.getText();
        String password = this.passwordSPasswordField.getText();
        String connectionId = engine.connect(nick, password);
        if (connectionId != null) {
            //todo
            //show new game window

//            GamingWindow gamingWindow = new GamingWindow();
//            gamingWindow.showAndWait();
            new GameWindow(new GameSource(engine.getConnection(engine.connect("a","b")), "")).show();
        } else {
            AlertBox.showBox(resourcePack.getText("common.alert"
            ), resourcePack.getText("loginwindow.invalid-nick-or-password"));
        }
    }

    private void handleViewHelpButtonAction(ActionEvent event) {
        new TestingColours();
        HelpWindow helpWindow = new HelpWindow();
        helpWindow.show();
    }

    private void handleCreateNewPlayerButtonAction(ActionEvent event) {
        RegistrationWindow window = new RegistrationWindow(engine);
        window.show();
    }
}
