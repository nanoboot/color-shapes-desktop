
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

package org.nanoboot.colorshapes.desktop.data;

import org.nanoboot.powerframework.database.Database;
import org.nanoboot.powerframework.database.DatabaseConnection;

/**
 * Represents data layer.
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Data {

    private static final String DATABASENAME = "data";

    private final DatabaseConnection databaseConnection;

    private final boolean launchedForFirstTime;

    /**
     * Constructor
     */
    public Data() {
        launchedForFirstTime = !Database.existsDatabase(DATABASENAME);
        if (launchedForFirstTime) {
            Database.createDatabase(DATABASENAME);
        }
        this.databaseConnection = Database.createDatabaseConnection(DATABASENAME);
    }

    /**
     *
     * @return
     */
    public DatabaseConnection getDatabaseConnection() {
        return this.databaseConnection;
    }

    /**
     *
     * @return
     */
    public boolean isLaunchedForFirstTime() {
        return this.launchedForFirstTime;
    }
}
