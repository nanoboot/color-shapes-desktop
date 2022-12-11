
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
import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.powerframework.simplicity.window.CellLayout;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.window.controls.PasswordField;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class SigningTab extends Tab {

    private SLabel playerNickLabel;
    private SLabel passwordLabel;
    private SLabel oldPasswordLabel;
    private SLabel newPasswordLabel;
    private SLabel confirmNewPasswordLabel;
    private SLabel passwordIsNotMandatoryLabel;
    private SLabel ifYouWantNoPasswordLabel;

    private TextField playerNickTextField;

    private PasswordField passwordPasswordField;
    private PasswordField oldPasswordPasswordField;
    private PasswordField newPasswordPasswordField;
    private PasswordField confirmNewPasswordPasswordField;

    private Button updatePlayerNickButton;
    private Button updatePasswordButton;

    private final CellLayout cellLayout;

    private final String playerNick;

    /**
     *
     */
    public SigningTab() {
        super(ColorShapes.getServices().getLanguageService().getTextConstant(10001));
        this.playerNick = ColorShapes.getServices().getPlayerService().getPlayerNick();
        this.setClosable(false);
        cellLayout = initCellLayout();

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNodes(1, this.playerNickLabel, this.playerNickTextField);
        cellLayout.addNodes(2, this.passwordLabel, this.passwordPasswordField);
        cellLayout.addNode(this.updatePlayerNickButton, 2, 3);
        cellLayout.addNodes(4, this.oldPasswordLabel, this.oldPasswordPasswordField);
        cellLayout.addNodes(5, this.newPasswordLabel, this.newPasswordPasswordField);
        cellLayout.addNodes(6, this.confirmNewPasswordLabel, this.confirmNewPasswordPasswordField);
        cellLayout.addNode(this.passwordIsNotMandatoryLabel, 1, 7, 2, 1);
        cellLayout.addNode(this.ifYouWantNoPasswordLabel, 1, 8, 2, 1);
        cellLayout.addNode(this.updatePasswordButton, 2, 9);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(2, 9, "50% 50%");
        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
        initPasswordFields();
        initButtons();
    }

    private void initLabels() {
        playerNickLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(111));
        passwordLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(112));
        oldPasswordLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10021));
        newPasswordLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10022));
        confirmNewPasswordLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(10023));
        passwordIsNotMandatoryLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(221));
        ifYouWantNoPasswordLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(222));
    }

    private void initTextFields() {
        playerNickTextField = new TextField(ColorShapes.getServices().getPlayerService().getPlayerNick());
    }

    private void initPasswordFields() {
        passwordPasswordField = new PasswordField();
        oldPasswordPasswordField = new PasswordField();
        newPasswordPasswordField = new PasswordField();
        confirmNewPasswordPasswordField = new PasswordField();
    }

    private void initButtons() {
        updatePlayerNickButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10013));
        this.updatePlayerNickButton.setOnAction(this::handleUpdatePlayerNickButtonAction);
        updatePasswordButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(10024));
        this.updatePasswordButton.setOnAction(this::handleUpdatePasswordButtonAction);
    }

    private void handleUpdatePlayerNickButtonAction(Event e) {
        String currentPlayerNick = this.playerNickTextField.getText();
        String password = this.passwordPasswordField.getText();
        if (!this.playerNick.equals(currentPlayerNick)) {
            ColorShapes.getServices().getPlayerService().setPlayerNick(currentPlayerNick, password);
        }
    }

    private void handleUpdatePasswordButtonAction(Event e) {
        String oldPassword = this.oldPasswordPasswordField.getText();
        String newPassword = this.newPasswordPasswordField.getText();
        String confirmNewPassword = this.confirmNewPasswordPasswordField.getText();
        ColorShapes.getServices().getPlayerService().updatePassword(oldPassword, newPassword, confirmNewPassword);
    }
}
