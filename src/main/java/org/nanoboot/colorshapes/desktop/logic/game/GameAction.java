
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

package org.nanoboot.colorshapes.desktop.logic.game;

import org.nanoboot.powerframework.datetime.UniversalDateTime;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class GameAction {

    private final int row;
    private final int column;
    private final UniversalDateTime universalDateTime;

    /**
     *
     * @param row
     * @param column
     */
    public GameAction(int row, int column) {
        this.row = row;
        this.column = column;
        this.universalDateTime = UniversalDateTime.getCurrentUniversalDateTime();

    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @return
     */
    public UniversalDateTime getUniversalDateTime() {
        return universalDateTime;
    }

    @Override
    public String toString() {
        return "*********************************\nField activated:: row: " + row + " column: " + column + " universalDateTime: " + universalDateTime.toString();
    }
}
