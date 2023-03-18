
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

package org.nanoboot.colorshapes.desktop.view.windows.gamingwindow;

import org.nanoboot.colorshapes.engine.infrastructure.GameSource;
import javafx.event.Event;
import javafx.scene.control.Label;
import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import org.nanoboot.powerframework.view.layouts.CellLayout;
import org.nanoboot.powerframework.view.window.controls.Button;
import org.nanoboot.powerframework.view.window.controls.TextField;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class TempCommandWindow extends ColorShapesAbstractWindow {

    private final GameSource gameSource;
    private TextField commandTextField;
    private Button executeButton;
    private CellLayout cellLayout;
    private Label l;

    public TempCommandWindow(GameSource gameSource) {
        this.gameSource=gameSource;
    }
    /**
     *
     */
    @Override
    protected void initAreaForUserInteraction(Object... args) {
        this.setWindowTitle("Execute command");
        cellLayout = new CellLayout(1, 3, "100%");
        commandTextField = new TextField();
        executeButton = new Button("Execute command");
        cellLayout.addNodes(1, commandTextField);
        cellLayout.addNodes(2, executeButton);
        executeButton.setOnAction(this::handleExecuteButtonAction);
        this.applicationArea.getChildren().addAll(cellLayout);
        l = new Label(String.valueOf(org.nanoboot.powerframework.random.generators.RandomGenerator.getDefaultImplStatic().nextInt(0, 999999)));
        cellLayout.addNodes(3, l);
    }

    private void handleExecuteButtonAction(Event e) {
        String string = this.commandTextField.getText();
        String[] splitCommand = string.split(";+");
        for (String command : splitCommand) {
            this.gameSource.getConnection().putInputEvent(gameSource.getGameId(), command);
        }

    }
}
