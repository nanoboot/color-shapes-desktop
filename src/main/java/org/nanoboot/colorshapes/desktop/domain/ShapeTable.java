
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

import org.nanoboot.colorshapes.desktop.logic.composition.Shape;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ShapeTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ShapeTable() {
        //Not meant to be instantiated.
    }

    static Shape getShape(int shapeId) {
        Shape shape;
        JsonObject shapeJsonObject = databaseConnection.getRow("shape", shapeId);

        shape = new Shape(
                Integer.parseInt(shapeJsonObject.getString("height")),
                Integer.parseInt(shapeJsonObject.getString("width"))
        );
        return shape;
    }

    /**
     *
     * @param height
     * @param width
     * @param description
     * @return
     */
    public static int saveShape(int height, int width, String description) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO shape VALUES (null,");
        insertCommandStringBuilder.append(height).append(",");
        insertCommandStringBuilder.append(width).append(",'");
        insertCommandStringBuilder.append(description).append("')");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @return
     */
    public static int saveEmptyShape() {
        return databaseConnection.execute("INSERT INTO shape VALUES (0,1,1,'')");
    }

    /**
     *
     * @param shape
     * @return
     */
    public static int getId(Shape shape) {
        int height = shape.getHeight();
        int width = shape.getWidth();

        String sqlQueryString
                = "SELECT * FROM shape WHERE height = "
                + height
                + " AND width = "
                + width;

        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn(sqlQueryString);
        if (resultOfSqlQuery.isEmpty()) {
            return saveShape(height, width, "height " + height + " * " + "width " + width);
        } else {
            resultOfSqlQuery.moveToTheNextRow();
            return resultOfSqlQuery.getInt("id");
        }
    }
}
