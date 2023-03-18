
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

import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import javafx.stage.Modality;
import org.nanoboot.powerframework.view.window.controls.TabPane;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class PlayerSettingsWindow extends ColorShapesAbstractWindow {

    private TabPane tabPane;

    private SigningTab signingTab;
    private LookTab lookTab;
    private OptionalDataTab optionalDataTab;
    private OtherTab otherTab;
    private BeforeSigningTab beforeSigningTab;

    /**
     * Constructor
     */
    public PlayerSettingsWindow() {
        initModality(Modality.APPLICATION_MODAL);
        this.showOnlyTheCloseButton();
        this.setWidth(120 * View.getDpmm());
        this.setHeight(140 * View.getDpmm() + 50);
        this.centerOnScreen();
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction() {
        String title = resourcePack.getText(1115);
        this.setWindowTitle(title);

        signingTab = new SigningTab();
        lookTab = new LookTab();
        optionalDataTab = new OptionalDataTab();
        otherTab = new OtherTab();
        beforeSigningTab = new BeforeSigningTab();

        tabPane = new TabPane();
        tabPane.getTabs().addAll(signingTab, lookTab, optionalDataTab, otherTab, beforeSigningTab);

        this.applicationArea.getChildren().add(tabPane);
    }

    /**
     *
     */
    @Override
    public void onClosingWindow() {

    }
}
