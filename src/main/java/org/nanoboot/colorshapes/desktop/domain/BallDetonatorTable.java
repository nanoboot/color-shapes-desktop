
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

import org.nanoboot.colorshapes.desktop.logic.composition.BallDetonatorComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.ExplodingShapeType;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BallDetonatorTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private BallDetonatorTable() {
        //Not meant to be instantiated.
    }

    static BallDetonatorComposition getBallDetonatorComposition(int ballDetonatorCompositionId) {
        BallDetonatorComposition ballDetonatorComposition;
        JsonObject ballDetonatorCompositionJsonObject = databaseConnection.getRow("ball_detonator", ballDetonatorCompositionId);

        int explodingShapeTypeId = Integer.parseInt(ballDetonatorCompositionJsonObject.getString("exploding_shape_type_id"));

        ExplodingShapeType explodingShapeType = ExplodingShapeTypeTable.getExplodingShapeType(explodingShapeTypeId);

        ballDetonatorComposition = new BallDetonatorComposition(
                explodingShapeType,
                Integer.parseInt(ballDetonatorCompositionJsonObject.getString("minimum_lenght_line")),
                Integer.parseInt(ballDetonatorCompositionJsonObject.getString("minimum_block_size")),
                null
        );
        return ballDetonatorComposition;
    }

    /**
     *
     * @param explodingShapeTypeId
     * @param minimumLineLenght
     * @param minimumBlockSize
     * @param customExplodingShapeId
     * @return
     */
    public static int saveBallDetonator(int explodingShapeTypeId, int minimumLineLenght, int minimumBlockSize, int customExplodingShapeId) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO ball_detonator VALUES (null,");
        insertCommandStringBuilder.append(explodingShapeTypeId).append(",");
        insertCommandStringBuilder.append(minimumLineLenght).append(",");
        insertCommandStringBuilder.append(minimumBlockSize).append(",");
        insertCommandStringBuilder.append(customExplodingShapeId).append(")");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param ballDetonatorComposition
     * @return
     */
    public static int getId(BallDetonatorComposition ballDetonatorComposition) {
        int explodingShapeTypeId = ExplodingShapeTypeTable.getId(ballDetonatorComposition.getExplodingShapeType().toString());
        int minimumLineLenght = ballDetonatorComposition.getMinimumLineLenght();
        int minimumSizeBlock = ballDetonatorComposition.getMinimumBlockSize();

        String sqlQueryString
                = "SELECT * FROM ball_detonator WHERE "
                + "exploding_shape_type_id = "
                + explodingShapeTypeId
                + " AND minimum_lenght_line= "
                + minimumLineLenght
                + " AND minimum_block_size= "
                + minimumSizeBlock;

        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn(sqlQueryString);
        if (resultOfSqlQuery.isEmpty()) {
            return saveBallDetonator(explodingShapeTypeId, minimumLineLenght, minimumSizeBlock, 0);
        } else {
            resultOfSqlQuery.moveToTheNextRow();
            return resultOfSqlQuery.getInt("id");
        }
    }
}
