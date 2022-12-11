
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

package org.nanoboot.colorshapes.desktop.view.windows;

import org.nanoboot.colorshapes.desktop.ColorShapes;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import org.nanoboot.powerframework.simplicity.boxes.AlertBox;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.window.controls.SCheckBox;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.PasswordField;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;
import org.nanoboot.powerframework.simplicity.window.CellLayout;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class SigningWindow extends ColorShapesAbstractWindow {

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
    public SigningWindow() {
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
        ColorShapes.getServices().getApplicationService().exit();
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction() {
        cellLayout = new CellLayout(2, 8, "50% 50%");

        this.showOnlyTheCloseButton();
        this.applicationArea.getChildren().addAll(this.cellLayout);

        nickSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(111) + ":");
        nickSTextField = new TextField();

        passwordSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(112) + ":");
        passwordSPasswordField = new PasswordField();

        this.keepMeSignedInSCheckBox = new SCheckBox();
        this.keepMeSignedInSCheckBox.setCheckBoxText(ColorShapes.getServices().getLanguageService().getTextConstant(121));

        signInButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(131));
        viewHelpButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(132));
        createNewPlayerButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(133));
        this.signInButton.setOnAction(this::handleSignInButtonAction);
        this.viewHelpButton.setOnAction(this::handleViewHelpButtonAction);
        this.createNewPlayerButton.setOnAction(this::handleCreateNewPlayerButtonAction);

        this.setWindowTitle(ColorShapes.getServices().getLanguageService().getTextConstant(101));
        this.applicationArea.setAlignment(Pos.BOTTOM_CENTER);
        this.cellLayout.addNodes(1, this.nickSLabel, nickSTextField);
        this.cellLayout.addNodes(2, this.passwordSLabel, this.passwordSPasswordField);
        this.cellLayout.addNodes(3, this.keepMeSignedInSCheckBox, this.signInButton);
        this.cellLayout.addNodes(4, this.viewHelpButton, this.createNewPlayerButton);
        this.setWidth(90 * ColorShapes.getView().getDpmm());
        this.setHeight(45 * ColorShapes.getView().getDpmm());
    }

    private void handleSignInButtonAction(ActionEvent event) {
        String nick = this.nickSTextField.getText();
        String password = this.passwordSPasswordField.getText();
        if (ColorShapes.getServices().getAuthenticationService().logIn(nick, password, this.keepMeSignedInSCheckBox.isSelected())) {

        } else {
            AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(151));
        }
    }

    private void handleViewHelpButtonAction(ActionEvent event) {
        HelpWindow helpWindow = new HelpWindow();
        helpWindow.show();
    }

    private void handleCreateNewPlayerButtonAction(ActionEvent event) {
        CreatingNewPlayerWindow window = new CreatingNewPlayerWindow();
        window.show();
    }
}
