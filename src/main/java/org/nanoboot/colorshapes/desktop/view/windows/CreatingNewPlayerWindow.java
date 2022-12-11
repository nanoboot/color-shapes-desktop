
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

import org.nanoboot.colorshapes.desktop.logic.CreateNewPlayerResult;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import org.nanoboot.powerframework.simplicity.Simplicity;
import org.nanoboot.powerframework.simplicity.boxes.AlertBox;
import org.nanoboot.powerframework.simplicity.window.controls.Button;
import org.nanoboot.powerframework.simplicity.boxes.MessageBox;
import org.nanoboot.powerframework.simplicity.window.controls.SLabel;
import org.nanoboot.powerframework.simplicity.window.controls.PasswordField;
import org.nanoboot.powerframework.simplicity.window.controls.TextField;
import org.nanoboot.powerframework.simplicity.window.CellLayout;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class CreatingNewPlayerWindow extends ColorShapesAbstractWindow {

    private CellLayout cellLayout;

    private SLabel displayLanguageLabel;
    private LanguageComboBox languageComboBox;

    private SLabel nickSLabel;
    private TextField nickSTextField;

    private SLabel passwordSLabel;
    private PasswordField passwordSPasswordField;

    private SLabel confirmPasswordSLabel;
    private PasswordField confirmPasswordSPasswordField;

    private SLabel passwordIsNotMandatorySLabel;
    private SLabel emptyPasswordFieldForNoPasswordSLabel;
    private SLabel restrictionsSLabel;
    private SLabel lengthSLabel;

    private SLabel colourSkinSLabel;
    private ColourComboBox colourComboBox;

    private Button createNewPlayerButton;

    /**
     * Constructor
     */
    public CreatingNewPlayerWindow() {
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction() {
        initModality(Modality.APPLICATION_MODAL);
        this.setWidth(120 * Simplicity.getDpmm());
        this.setHeight((97.5 + (2 / 3)) * Simplicity.getDpmm());

        this.showOnlyTheCloseButton();
        cellLayout = new CellLayout(2, 10, "50% 50%");

        displayLanguageLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(211) + ":");
        languageComboBox = new LanguageComboBox();

        nickSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(111) + ":");
        nickSTextField = new TextField();

        passwordSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(112) + ":");
        passwordSPasswordField = new PasswordField();

        confirmPasswordSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(214) + ":");
        confirmPasswordSPasswordField = new PasswordField();

        passwordIsNotMandatorySLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(221));
        emptyPasswordFieldForNoPasswordSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(222));
        restrictionsSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(223));
        lengthSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(224));

        colourSkinSLabel = new SLabel(ColorShapes.getServices().getLanguageService().getTextConstant(231) + ":");
        colourComboBox = new ColourComboBox();

        createNewPlayerButton = new Button(ColorShapes.getServices().getLanguageService().getTextConstant(133));

        this.createNewPlayerButton.setOnAction(this::handleCreateNewPlayerButtonAction);
        this.setWindowTitle(ColorShapes.getServices().getLanguageService().getTextConstant(201));

        this.applicationArea.getChildren().addAll(this.cellLayout);

        this.applicationArea.setAlignment(Pos.BOTTOM_CENTER);

        cellLayout.addNodes(1, displayLanguageLabel, languageComboBox);
        cellLayout.addNodes(2, nickSLabel, nickSTextField);
        cellLayout.addNodes(3, passwordSLabel, passwordSPasswordField);
        cellLayout.addNodes(4, confirmPasswordSLabel, confirmPasswordSPasswordField);
        cellLayout.addNode(passwordIsNotMandatorySLabel, 1, 5, 2, 1);
        cellLayout.addNode(emptyPasswordFieldForNoPasswordSLabel, 1, 6, 2, 1);
        cellLayout.addNode(restrictionsSLabel, 1, 7, 2, 1);
        cellLayout.addNode(lengthSLabel, 1, 8, 2, 1);
        cellLayout.addNodes(9, colourSkinSLabel, colourComboBox);
        cellLayout.addNode(createNewPlayerButton, 2, 10);
    }

    private void handleCreateNewPlayerButtonAction(ActionEvent event) {
        String nick;
        String password;
        String confirmPassword;
        int languageID;
        int skin;

        languageID = languageComboBox.getSelectedLanguageId();

        nick = this.nickSTextField.getText().toString();
        password = this.passwordSPasswordField.getText().toString();
        confirmPassword = this.confirmPasswordSPasswordField.getText().toString();

        skin = colourComboBox.getColourNumber();

        CreateNewPlayerResult result = ColorShapes.getServices().getAuthenticationService().createNewPlayer(nick, password, confirmPassword, languageID, skin, 100, 1);
        switch (result) {
            case PLAYER_CREATED: {
                MessageBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(1), ColorShapes.getServices().getLanguageService().getTextConstant(1002));
                this.close();
            }
            break;
            case NICK_ALREADY_EXISTS:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(244));
                break;
            case TOO_LONG_NICK:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(241));
                break;
            case NICK_IS_EMPTY:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(243));
                break;
            case PASSWORD_AND_CONFIRM_PASSWORD_DO_NOT_MATCH:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(245));
                break;
            case TOO_LONG_PASSWORD:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(242));
                break;
            case NICK_OR_PASSWORD_CONTAINS_FORBIDDEN_CHARACTERS:
                AlertBox.showBox(ColorShapes.getServices().getLanguageService().getTextConstant(2), ColorShapes.getServices().getLanguageService().getTextConstant(223));
                break;
            default:
                throw new IllegalStateException();
        }

    }
}
