
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

import org.nanoboot.colorshapes.desktop.logic.composition.ExplodingShapeType;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ExplodingShapeTypeTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ExplodingShapeTypeTable() {
        //Not meant to be instantiated.
    }

    static ExplodingShapeType getExplodingShapeType(int explodingShapeTypeId) {
        ExplodingShapeType explodingShapeType;
        JsonObject boardCompositionJsonObject = databaseConnection.getRow("exploding_shape_type", explodingShapeTypeId);

        explodingShapeType = ExplodingShapeType.valueOf(boardCompositionJsonObject.getString("name"));

        return explodingShapeType;
    }

    /**
     *
     * @param name
     * @return
     */
    public static int saveExplodingShapeType(String name) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO exploding_shape_type VALUES (null,'");
        insertCommandStringBuilder.append(name).append("')");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param name
     * @return id of the row with the given name
     */
    public static int getId(String name) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT id FROM exploding_shape_type where name='" + name + "'");
        resultOfSqlQuery.moveToTheNextRow();
        return resultOfSqlQuery.getInt("id");
    }

}
