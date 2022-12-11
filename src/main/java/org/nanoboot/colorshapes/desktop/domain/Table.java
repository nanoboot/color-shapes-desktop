
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

import org.nanoboot.powerframework.database.DatabaseConnection;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Table {

    /**
     *
     */
    protected static DatabaseConnection databaseConnection;

    /**
     * Constructor
     */
    public Table() {//NOSONAR
        //Implicit constructor.
    }

    /**
     *
     * @param databaseConnectionParameter
     */
    public static void setDatabaseConnection(DatabaseConnection databaseConnectionParameter) {
        databaseConnection = databaseConnectionParameter;
    }

    static DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    /**
     * Converts boolean to int. True is 1, false is 0.
     *
     * @param value
     * @return
     */
    protected static int booleanToInt(boolean value) {
        return value ? 1 : 0;
    }
}
