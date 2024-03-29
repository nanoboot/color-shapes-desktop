
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
import org.nanoboot.powerframework.time.moment.LocalDate;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.RadioButton;
import org.nanoboot.powerframework.view.window.controls.SLabel;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class OptionalDataTab extends Tab {

    private OldPlayerService playerService;
    private SLabel nameLabel;
    private SLabel surnameLabel;

    private SLabel sexLabel;

    private SLabel dateOfBirthLabel;
    private SLabel yearLabel;
    private SLabel monthLabel;
    private SLabel dayLabel;

    private TextField nameTextField;
    private TextField surnameTextField;

    private RadioButton manRadioButton;
    private RadioButton womanRadioButton;
    private RadioButton unknownRadioButton;

    private TextField yearTextField;
    private TextField monthTextField;
    private TextField dayTextField;

    private Button saveOptionalDataChangesButton;

    private final LocalDate dateOfBirth;
    private final String name;
    private final String surname;
    private final String sexName;
    private int year;
    private int month;
    private int day;

    private ResourcePack resourcePack;

    private final CellLayout cellLayout;

    private javafx.scene.control.ToggleGroup sexToogleGroup;

    /**
     *
     */
    public OptionalDataTab() {
        super();
        this.setText(resourcePack.getText(10701));
        this.setClosable(false);
        cellLayout = initCellLayout();

        name = playerService.getName();
        surname = playerService.getSurname();
        sexName = playerService.getSexName();
        this.dateOfBirth = playerService.getDateOfBirth();

        if (dateOfBirth != null) {
            year = dateOfBirth.getYear();
            month = dateOfBirth.getMonth();
            day = dateOfBirth.getDay();
        }

        initNodes();

        fillCellLayout();
    }

    private void fillCellLayout() {
        cellLayout.addNode(this.nameLabel, 1, 1);
        cellLayout.addNode(this.nameTextField, 2, 1, 2, 1);
        cellLayout.addNode(this.surnameLabel, 1, 2);
        cellLayout.addNode(this.surnameTextField, 2, 2, 2, 1);
        cellLayout.addNode(this.sexLabel, 1, 3);
        cellLayout.addNode(this.manRadioButton, 2, 3, 2, 1);
        cellLayout.addNode(this.womanRadioButton, 2, 4, 2, 1);
        cellLayout.addNode(this.unknownRadioButton, 2, 5, 2, 1);
        cellLayout.addNodes(6, this.dateOfBirthLabel, this.yearLabel, this.yearTextField);
        cellLayout.addNode(this.monthLabel, 2, 7);
        cellLayout.addNode(this.monthTextField, 3, 7);
        cellLayout.addNode(this.dayLabel, 2, 8);
        cellLayout.addNode(this.dayTextField, 3, 8);
        cellLayout.addNode(this.saveOptionalDataChangesButton, 2, 9, 2, 1);

        setContent(cellLayout);
    }

    private CellLayout initCellLayout() {
        CellLayout cellLayout = new CellLayout(3, 9, "33% 33% 33%");

        return cellLayout;
    }

    private void initNodes() {
        initLabels();
        initTextFields();
        initRadioButtons();
        initButtons();
    }

    private void initLabels() {
        nameLabel = new SLabel(resourcePack.getText(10711));
        surnameLabel = new SLabel(resourcePack.getText(10712));
        sexLabel = new SLabel(resourcePack.getText(10713));
        dateOfBirthLabel = new SLabel(resourcePack.getText(10717));
        yearLabel = new SLabel(resourcePack.getText(10718));
        monthLabel = new SLabel(resourcePack.getText(10719));
        dayLabel = new SLabel(resourcePack.getText(10720));
    }

    private void initTextFields() {
        this.nameTextField = new TextField(name);
        this.surnameTextField = new TextField(surname);
        this.yearTextField = new TextField(String.valueOf(dateOfBirth == null ? "" : year));
        this.monthTextField = new TextField(String.valueOf(dateOfBirth == null ? "" : month));
        this.dayTextField = new TextField(String.valueOf(dateOfBirth == null ? "" : day));
    }

    private void initRadioButtons() {
        sexToogleGroup = new javafx.scene.control.ToggleGroup();

        manRadioButton = new RadioButton(resourcePack.getText(10714));
        this.manRadioButton.setId("man");
        if ("man".equals(this.sexName)) {
            manRadioButton.setSelected(true);
        }
        manRadioButton.setToggleGroup(sexToogleGroup);

        womanRadioButton = new RadioButton(resourcePack.getText(10715));
        this.womanRadioButton.setId("woman");
        if ("woman".equals(this.sexName)) {
            womanRadioButton.setSelected(true);
        }
        womanRadioButton.setToggleGroup(sexToogleGroup);

        unknownRadioButton = new RadioButton(resourcePack.getText(10716));
        this.unknownRadioButton.setId("man");
        if ("unknown".equals(this.sexName)) {
            unknownRadioButton.setSelected(true);
        }
        unknownRadioButton.setToggleGroup(sexToogleGroup);

    }

    private void initButtons() {
        this.saveOptionalDataChangesButton = new Button(resourcePack.getText(10791));
        this.saveOptionalDataChangesButton.setOnAction(this::handlesaveOptionalDataChangesButtonAction);
    }

    private void handlesaveOptionalDataChangesButtonAction(Event e) {
        String currentName = this.nameTextField.getText();
        playerService.setName(currentName);

        String currentSurname = this.surnameTextField.getText();
        playerService.setSurname(currentSurname);

        String currentSexName = ((RadioButton) this.sexToogleGroup.getSelectedToggle()).getId();
        playerService.setSexName(currentSexName);

        if (!(this.yearTextField.getText().isEmpty()
                || this.monthTextField.getText().isEmpty()
                || this.dayTextField.getText().isEmpty())) {
            int year = Integer.parseInt(this.yearTextField.getText());
            int month = Integer.parseInt(this.monthTextField.getText());
            int day = Integer.parseInt(this.dayTextField.getText());
            playerService.setDateOfBirth(new LocalDate(year, month, day));
        }
    }
}
