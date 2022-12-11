
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

import org.nanoboot.powerframework.database.ResultOfSqlQuery;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class TimeZoneTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private TimeZoneTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param name
     * @return
     */
    public static int saveTimeZone(String name) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO time_zone VALUES (null,'");
        insertCommandStringBuilder.append(name).append("')");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param name
     * @return id of the row with the given name
     */
    public static int getId(String name) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT id FROM time_zone WHERE name='" + name + "'");
        if (resultOfSqlQuery.isEmpty()) {
            TimeZoneTable.saveTimeZone(name);
            resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT id FROM time_zone WHERE name='" + name + "'");
        }
        resultOfSqlQuery.moveToTheNextRow();
        return resultOfSqlQuery.getInt("id");
    }

    /**
     *
     * @param id
     * @return name of the row with the given id
     */
    public static String getName(int id) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT name FROM time_zone WHERE id = " + id);
        resultOfSqlQuery.moveToTheNextRow();
        return resultOfSqlQuery.getString("name");
    }
}
