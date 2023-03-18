
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

import javafx.event.Event;
import javafx.scene.control.Tab;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.PasswordField;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class SigningTab extends Tab {

    private OldPlayerService playerService;
    private ResourcePack resourcePack;
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
        super();
        this.setText(resourcePack.getText(10001));
        this.playerNick = playerService.getPlayerNick();
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
        playerNickLabel = new SLabel(resourcePack.getText(111));
        passwordLabel = new SLabel(resourcePack.getText(112));
        oldPasswordLabel = new SLabel(resourcePack.getText(10021));
        newPasswordLabel = new SLabel(resourcePack.getText(10022));
        confirmNewPasswordLabel = new SLabel(resourcePack.getText(10023));
        passwordIsNotMandatoryLabel = new SLabel(resourcePack.getText(221));
        ifYouWantNoPasswordLabel = new SLabel(resourcePack.getText(222));
    }

    private void initTextFields() {
        playerNickTextField = new TextField(playerService.getPlayerNick());
    }

    private void initPasswordFields() {
        passwordPasswordField = new PasswordField();
        oldPasswordPasswordField = new PasswordField();
        newPasswordPasswordField = new PasswordField();
        confirmNewPasswordPasswordField = new PasswordField();
    }

    private void initButtons() {
        updatePlayerNickButton = new Button(resourcePack.getText(10013));
        this.updatePlayerNickButton.setOnAction(this::handleUpdatePlayerNickButtonAction);
        updatePasswordButton = new Button(resourcePack.getText(10024));
        this.updatePasswordButton.setOnAction(this::handleUpdatePasswordButtonAction);
    }

    private void handleUpdatePlayerNickButtonAction(Event e) {
        String currentPlayerNick = this.playerNickTextField.getText();
        String password = this.passwordPasswordField.getText();
        if (!this.playerNick.equals(currentPlayerNick)) {
            playerService.setPlayerNick(currentPlayerNick, password);
        }
    }

    private void handleUpdatePasswordButtonAction(Event e) {
        String oldPassword = this.oldPasswordPasswordField.getText();
        String newPassword = this.newPasswordPasswordField.getText();
        String confirmNewPassword = this.confirmNewPasswordPasswordField.getText();
        playerService.updatePassword(oldPassword, newPassword, confirmNewPassword);
    }
}
