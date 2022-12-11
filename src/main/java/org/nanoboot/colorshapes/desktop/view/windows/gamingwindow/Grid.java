
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

package org.nanoboot.colorshapes.desktop.view.windows.gamingwindow;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Grid extends GridPane {

    private final ColumnConstraints barColumnConstraints;
    private final ColumnConstraints spaceColumnConstraints;
    private final RowConstraints barRowConstraints;
    private final RowConstraints spaceRowConstraints;

    /**
     *
     */
    public Grid() {
        barColumnConstraints = new ColumnConstraints();
        spaceColumnConstraints = new ColumnConstraints();
        barRowConstraints = new RowConstraints();
        spaceRowConstraints = new RowConstraints();

        barColumnConstraints.setPercentWidth(5);
        spaceColumnConstraints.setPercentWidth(30);
        barRowConstraints.setPercentHeight(5);
        spaceRowConstraints.setPercentHeight(30);

        this.getColumnConstraints().addAll(spaceColumnConstraints, barColumnConstraints, spaceColumnConstraints, barColumnConstraints, spaceColumnConstraints);
        this.getRowConstraints().addAll(spaceRowConstraints, barRowConstraints, spaceRowConstraints, barRowConstraints, spaceRowConstraints);

        this.addRow(0, new GridSpace(), new GridBar(), new GridSpace(), new GridBar(), new GridSpace());
        this.add(new GridBar(), 0, 1, 5, 1);
        this.addRow(2, new GridSpace(), new GridBar(), new GridSpace(), new GridBar(), new GridSpace());
        this.add(new GridBar(), 0, 3, 5, 1);
        this.addRow(4, new GridSpace(), new GridBar(), new GridSpace(), new GridBar(), new GridSpace());
    }
}
