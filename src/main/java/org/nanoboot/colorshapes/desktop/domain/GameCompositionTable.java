
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

import org.nanoboot.colorshapes.desktop.logic.composition.OtherComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.GameComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.BallThrowerComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.BallDetonatorComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.BoardComposition;
import org.nanoboot.colorshapes.desktop.logic.composition.BallFactoryComposition;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class GameCompositionTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private GameCompositionTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param boardId
     * @param ballFactoryId
     * @param ballThrowerId
     * @param ballDetonatorId
     * @param otherId
     * @return
     */
    public static int saveGameComposition(int boardId, int ballFactoryId, int ballThrowerId, int ballDetonatorId, int otherId) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO game_composition VALUES (null,");
        insertCommandStringBuilder.append(boardId).append(",");
        insertCommandStringBuilder.append(ballFactoryId).append(",");
        insertCommandStringBuilder.append(ballThrowerId).append(",");
        insertCommandStringBuilder.append(ballDetonatorId).append(",");
        insertCommandStringBuilder.append(otherId).append(")");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param id
     * @return
     */
    public static GameComposition getGameComposition(int id) {
        GameComposition gameComposition;
        JsonObject gameCompositionJsonObject = databaseConnection.getRow("game_composition", id);

        int boardId = Integer.parseInt(gameCompositionJsonObject.getString("board_id"));
        int ballFactoryId = Integer.parseInt(gameCompositionJsonObject.getString("ball_factory_id"));
        int ballThrowerId = Integer.parseInt(gameCompositionJsonObject.getString("ball_thrower_id"));
        int ballDetonatorId = Integer.parseInt(gameCompositionJsonObject.getString("ball_detonator_id"));
        int otherId = Integer.parseInt(gameCompositionJsonObject.getString("other_id"));

        BoardComposition boardComposition = BoardTable.getBoardComposition(boardId);
        BallFactoryComposition ballFactoryComposition = BallFactoryTable.getBallFactoryComposition(ballFactoryId);
        BallThrowerComposition ballThrowerComposition = BallThrowerTable.getBallThrowerComposition(ballThrowerId);
        BallDetonatorComposition ballDetonatorComposition = BallDetonatorTable.getBallDetonatorComposition(ballDetonatorId);
        OtherComposition otherComposition = OtherTable.getOtherComposition(otherId);

        gameComposition = new GameComposition(boardComposition,
                ballFactoryComposition,
                ballThrowerComposition,
                ballDetonatorComposition,
                otherComposition);
        return gameComposition;
    }

    /**
     *
     * @param gameComposition
     * @return
     */
    public static int getId(GameComposition gameComposition) {
        int boardId = BoardTable.getId(gameComposition.getBoardComposition());
        int ballFactoryId = BallFactoryTable.getId(gameComposition.getBallFactoryComposition());
        int ballThrowerId = BallThrowerTable.getId(gameComposition.getBallThrowerComposition());
        int ballDetonatorId = BallDetonatorTable.getId(gameComposition.getBallDetonatorComposition());
        int otherId = OtherTable.getId(gameComposition.getOtherComposition());

        String sqlQueryString
                = "SELECT * FROM game_composition,board,ball_factory,ball_thrower,ball_detonator,other WHERE board_id = "
                + boardId
                + " AND ball_factory_id = "
                + ballFactoryId
                + " AND ball_thrower_id = "
                + ballThrowerId
                + " AND ball_detonator_id = "
                + ballDetonatorId
                + " AND other_id = "
                + otherId
                + " AND game_composition.board_id=board.id"
                + " AND game_composition.ball_factory_id=ball_factory.id"
                + " AND game_composition.ball_thrower_id=ball_thrower.id"
                + " AND game_composition.ball_detonator_id=ball_detonator.id"
                + " AND game_composition.other_id=other.id";
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn(sqlQueryString);
        if (resultOfSqlQuery.isEmpty()) {
            return saveGameComposition(boardId, ballFactoryId, ballThrowerId, ballDetonatorId, otherId);
        } else {
            resultOfSqlQuery.moveToTheNextRow();
            return resultOfSqlQuery.getInt("id");
        }
    }
}
