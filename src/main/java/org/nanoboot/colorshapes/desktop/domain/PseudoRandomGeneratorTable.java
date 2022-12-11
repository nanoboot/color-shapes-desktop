
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
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class PseudoRandomGeneratorTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private PseudoRandomGeneratorTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param magicNumber
     * @param magicUniversalDateTimeId
     * @return
     */
    public static int savePseudoRandomGenerator(int magicNumber, int magicUniversalDateTimeId) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO pseudo_random_generator VALUES (null,");
        insertCommandStringBuilder.append(magicNumber).append(",");
        insertCommandStringBuilder.append(magicUniversalDateTimeId).append(")");

        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param id
     * @return instance of PseudoRandomGenerator
     */
    public static PseudoRandomGenerator getPseudoRandomGenerator(int id) {
        JsonObject jsonObject = databaseConnection.getRow("pseudo_random_generator", id);
        long magicNumber = Long.parseLong(jsonObject.getString("magic_number"));
        int universalDateTimeId = Integer.parseInt(jsonObject.getString("magic_universal_date_time_id"));
        UniversalDateTime universalDateTime = UniversalDateTimeTable.getUniversalDateTime(universalDateTimeId);
        return new PseudoRandomGenerator(magicNumber, universalDateTime);
    }
}
