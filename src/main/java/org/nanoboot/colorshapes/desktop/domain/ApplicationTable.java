
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

import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ApplicationTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ApplicationTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param installationUniqueNumber
     * @param firstLaunchUniversalDateTimeId
     * @param automaticallyLoggedInPlayerId
     * @param beforeLoginLanguageId
     * @param beforeLoginColourSkin
     * @param beforeLoginZoom
     * @return
     */
    public static int saveApplication(int installationUniqueNumber, int firstLaunchUniversalDateTimeId, int automaticallyLoggedInPlayerId, int beforeLoginLanguageId, int beforeLoginColourSkin, int beforeLoginZoom) {
        StringBuilder insertCommand = new StringBuilder("INSERT INTO application values(null,");
        insertCommand.append(installationUniqueNumber).append(',');
        insertCommand.append(firstLaunchUniversalDateTimeId).append(',');
        insertCommand.append(automaticallyLoggedInPlayerId).append(',');
        insertCommand.append(beforeLoginLanguageId).append(',');
        insertCommand.append(beforeLoginColourSkin).append(',');
        insertCommand.append(beforeLoginZoom);
        insertCommand.append(")");

        return databaseConnection.execute(insertCommand.toString());
    }

    /**
     *
     * @return json object representation of information about this application
     * installation
     */
    private static JsonObject getRow() {
        return databaseConnection.getRow("application", 1);
    }

    /**
     *
     * @param columnName
     * @param newValue
     */
    private static void updateValue(String columnName, int newValue) {
        databaseConnection.updateValue("application", 1, columnName, newValue);
    }

    /**
     *
     * @param newValue
     */
    public static void setAutomaticallyLoggedInPlayerId(int newValue) {
        updateValue("automatically_logged_in_player_id", newValue);
    }

    /**
     *
     * @param newValue
     */
    public static void setBeforeLoginLanguageId(int newValue) {
        updateValue("before_login_language_id", newValue);
    }

    /**
     *
     * @param newValue
     */
    public static void setBeforeLoginColourSkin(int newValue) {
        updateValue("before_login_colour_skin", newValue);
    }

    /**
     *
     * @param newValue
     */
    public static void setBeforeLoginZoom(int newValue) {
        updateValue("before_login_zoom", newValue);
    }

    /**
     *
     * @param newValue
     */
    public static int getBeforeLoginLanguageId() {
        JsonObject row = getRow();
        return Integer.parseInt(row.getString("before_login_language_id"));
    }

    /**
     *
     * @param newValue
     */
    public static int getBeforeLoginColourSkin() {
        JsonObject row = getRow();
        return Integer.parseInt(row.getString("before_login_colour_skin"));
    }

    /**
     *
     * @param newValue
     */
    public static int getBeforeLoginZoom() {
        JsonObject row = getRow();
        return Integer.parseInt(row.getString("before_login_zoom"));
    }

    /**
     *
     * @param newValue
     */
    public static int getAutomaticallyLoggedInPlayerId() {
        JsonObject row = getRow();
        return Integer.parseInt(row.getString("automatically_logged_in_player_id"));
    }
}
