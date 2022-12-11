
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

package org.nanoboot.colorshapes.desktop.domain;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class GameActionTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private GameActionTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param gameId
     * @param universalDateTimeID
     * @param activatedRow
     * @param activatedColumn
     * @return
     */
    public static int saveGameActionTable(int gameId, int universalDateTimeID, int activatedRow, int activatedColumn) {
        String command = "INSERT INTO game_action VALUES (null,"
                + gameId + ","
                + universalDateTimeID + ","
                + activatedRow + ","
                + activatedColumn + ")";
        return databaseConnection.execute(command);
    }

}
