
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

import org.nanoboot.powerframework.datetime.UniversalDateTime;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class UniversalDateTimeTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private UniversalDateTimeTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @return id of new session
     */
    public static int saveCurrentUniversalDateTime() {
        UniversalDateTime universalDateTime = UniversalDateTime.getCurrentUniversalDateTime();
        return saveUniversalDateTime(universalDateTime);
    }

    /**
     *
     * @param universalDateTime
     * @return id of new session
     */
    public static int saveUniversalDateTime(UniversalDateTime universalDateTime) {

        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO universal_date_time VALUES(null,");
        insertCommandStringBuilder.append(universalDateTime.getYear());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getMonth());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getDay());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getHour());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getMinute());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getSecond());
        insertCommandStringBuilder.append(',');
        insertCommandStringBuilder.append(universalDateTime.getMillisecond());
        insertCommandStringBuilder.append(')');
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param universalDateTimeId
     * @return
     */
    public static JsonObject loadUniversalDateTime(int universalDateTimeId) {
        return databaseConnection.getRow("universal_date_time", universalDateTimeId);
    }

    /**
     *
     * @param id
     * @return
     */
    public static UniversalDateTime getUniversalDateTime(int id) {
        JsonObject jsonObject = loadUniversalDateTime(id);
        return new UniversalDateTime(
                Integer.parseInt(jsonObject.getString("year")),
                Integer.parseInt(jsonObject.getString("month")),
                Integer.parseInt(jsonObject.getString("day")),
                Integer.parseInt(jsonObject.getString("hour")),
                Integer.parseInt(jsonObject.getString("minute")),
                Integer.parseInt(jsonObject.getString("second")),
                Integer.parseInt(jsonObject.getString("millisecond"))
        );
    }
}
