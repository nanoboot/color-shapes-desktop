
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
import org.nanoboot.colorshapes.desktop.window.ColourComboBox;
import org.nanoboot.colorshapes.desktop.window.LanguageComboBox;
import org.nanoboot.colorshapes.engine.entity.core.PlayerDto;
import org.nanoboot.colorshapes.engine.infrastructure.Engine;
import org.nanoboot.colorshapes.engine.services.utils.RegistrationError;
import org.nanoboot.powerframework.json.JsonObject;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import org.nanoboot.powerframework.view.View;
import org.nanoboot.powerframework.view.boxes.AlertBox;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.boxes.MessageBox;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.PasswordField;
import org.nanoboot.powerframework.view.window.controls.TextField;
import org.nanoboot.powerframework.view.layouts.CellLayout;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class RegistrationWindow extends ColorShapesAbstractWindow {

    private final Engine engine;
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
    public RegistrationWindow(Engine engine) {
        this.engine = engine;
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction(Object... args) {
        this.resourcePack=new DummyResourcePackImpl();
        initModality(Modality.APPLICATION_MODAL);
        this.setWidth(120 * View.getDpmm());
        this.setHeight((97.5 + (2 / 3)) * View.getDpmm());

        this.showOnlyTheCloseButton();
        cellLayout = new CellLayout(2, 10, "50% 50%");

        displayLanguageLabel = new SLabel(resourcePack.getText(211) + ":");
        languageComboBox = new LanguageComboBox();

        nickSLabel = new SLabel(resourcePack.getText("common.nick") + ":");
        nickSTextField = new TextField();

        passwordSLabel = new SLabel(resourcePack.getText("common.password") + ":");
        passwordSPasswordField = new PasswordField();

        confirmPasswordSLabel = new SLabel(resourcePack.getText(214) + ":");
        confirmPasswordSPasswordField = new PasswordField();

        passwordIsNotMandatorySLabel = new SLabel(resourcePack.getText(221));
        emptyPasswordFieldForNoPasswordSLabel = new SLabel(resourcePack.getText(222));
        restrictionsSLabel = new SLabel(resourcePack.getText(223));
        lengthSLabel = new SLabel(resourcePack.getText(224));

        colourSkinSLabel = new SLabel(resourcePack.getText(231) + ":");
        colourComboBox = new ColourComboBox();

        createNewPlayerButton = new Button(resourcePack.getText("common.create-new-player"));

        this.createNewPlayerButton.setOnAction(this::handleCreateNewPlayerButtonAction);
        this.setWindowTitle(resourcePack.getText(201));

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
        String languageID;
        int skin;

        languageID = languageComboBox.getSelectedLanguageId();

        nick = this.nickSTextField.getText().toString();
        password = this.passwordSPasswordField.getText().toString();
        confirmPassword = this.confirmPasswordSPasswordField.getText().toString();

        skin = colourComboBox.getColourNumber();

        PlayerDto newPlayer = new PlayerDto();
        newPlayer.setNick(nick);
        newPlayer.setLanguage(languageID);
        JsonObject properties = new JsonObject();
        properties.add("languageCode", languageID);
        properties.add("skin", skin);
        properties.add("zoom", 100);//zoom - 100 - todo
        //time zone- todo
        newPlayer.setProperties(properties);

        RegistrationError result = engine.newPlayer(newPlayer, password, confirmPassword);

        if (result == null) {
            //ok
            MessageBox.showBox(resourcePack.getText(1), resourcePack.getText(1002));
            this.close();
            return;
        }
        switch (result) {
            case NICK_ALREADY_EXISTS:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(244));
                break;
            case TOO_LONG_NICK:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(241));
                break;
            case NICK_IS_EMPTY:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(243));
                break;
            case PASSWORD_AND_CONFIRM_PASSWORD_DO_NOT_MATCH:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(245));
                break;
            case TOO_LONG_PASSWORD:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(242));
                break;
            case NICK_OR_PASSWORD_CONTAINS_FORBIDDEN_CHARACTERS:
                AlertBox.showBox(resourcePack.getText(2), resourcePack.getText(223));
                break;
            default:
                throw new IllegalStateException();
        }

    }
}
