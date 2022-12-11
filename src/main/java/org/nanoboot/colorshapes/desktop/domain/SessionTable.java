
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
public class SessionTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private SessionTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param startUniversalDateTimeId
     * @return id of new session
     */
    public static int saveSession(int startUniversalDateTimeId) {
        return databaseConnection.execute("INSERT INTO session(start_universal_date_time_id) values(" + startUniversalDateTimeId + ")");
    }

    /**
     *
     * @param sessionId
     * @return
     */
    public static JsonObject loadSession(int sessionId) {
        return databaseConnection.getRow("session", sessionId);
    }

    /**
     *
     * @param sessionId
     * @param universalDateTimeId
     */
    public static void setEndUniversalDateTimeId(int sessionId, int universalDateTimeId) {
        databaseConnection.updateValue("session", sessionId, "end_universal_date_time_id", Integer.toString(universalDateTimeId));
    }
}
