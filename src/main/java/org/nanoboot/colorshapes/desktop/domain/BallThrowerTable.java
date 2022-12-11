
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

import org.nanoboot.colorshapes.desktop.logic.composition.BallThrowerComposition;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BallThrowerTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private BallThrowerTable() {
        //Not meant to be instantiated.
    }

    static BallThrowerComposition getBallThrowerComposition(int ballThrowerCompositionId) {
        BallThrowerComposition ballThrowerComposition;
        JsonObject ballThrowerCompositionJsonObject = databaseConnection.getRow("ball_thrower", ballThrowerCompositionId);

        ballThrowerComposition = new BallThrowerComposition(
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("count_of_balls_thrown_at_the_beginning_of_the_game")),
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("count_of_balls_throwing_during_the_game")),
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("ball_frequency")),
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("automatic_bomb_frequency")),
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("manual_bomb_frequency")),
                Integer.parseInt(ballThrowerCompositionJsonObject.getString("show_the_positions_of_the_next_balls")) == 1
        );
        return ballThrowerComposition;
    }

    /**
     *
     * @param countOfBallsThrownAtTheBeginningOfTheGame
     * @param countOfBallsThrowingDuringTheGame
     * @param ballFrequency
     * @param automaticBombFrequency
     * @param manualBombFrequency
     * @param showThePositionsOfTheNextBalls
     * @return
     */
    public static int saveBallThrower(int countOfBallsThrownAtTheBeginningOfTheGame, int countOfBallsThrowingDuringTheGame, int ballFrequency, int automaticBombFrequency, int manualBombFrequency, boolean showThePositionsOfTheNextBalls) {
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO ball_thrower VALUES (null,");
        insertCommandStringBuilder.append(countOfBallsThrownAtTheBeginningOfTheGame).append(",");
        insertCommandStringBuilder.append(countOfBallsThrowingDuringTheGame).append(",");
        insertCommandStringBuilder.append(ballFrequency).append(",");
        insertCommandStringBuilder.append(automaticBombFrequency).append(",");
        insertCommandStringBuilder.append(manualBombFrequency).append(",");
        insertCommandStringBuilder.append(showThePositionsOfTheNextBalls ? 1 : 0).append(")");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param ballThrowerComposition
     * @return
     */
    public static int getId(BallThrowerComposition ballThrowerComposition) {
        int countOfBallsThrownAtTheBeginningOfTheGame = ballThrowerComposition.getCountOfBallsThrownAtTheBeginningOfTheGame();
        int countOfBallsThrowingDuringTheGame = ballThrowerComposition.getCountOfBallsThrownDuringTheGame();
        int ballFrequency = ballThrowerComposition.getBallFrequency();
        int automaticBombFrequency = ballThrowerComposition.getAutomaticBombFrequency();
        int manualBombFrequency = ballThrowerComposition.getManualBombFrequency();
        boolean showThePositionsOfTheNextBalls = ballThrowerComposition.getShowingNextBallsPosition();

        String sqlQueryString
                = "SELECT * FROM ball_thrower WHERE "
                + "count_of_balls_thrown_at_the_beginning_of_the_game = "
                + countOfBallsThrownAtTheBeginningOfTheGame
                + " AND count_of_balls_throwing_during_the_game= "
                + countOfBallsThrowingDuringTheGame
                + " AND ball_frequency = "
                + ballFrequency
                + " AND automatic_bomb_frequency = "
                + automaticBombFrequency
                + " AND manual_bomb_frequency = "
                + manualBombFrequency
                + " AND show_the_positions_of_the_next_balls = "
                + (showThePositionsOfTheNextBalls ? "1" : "0");

        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn(sqlQueryString);
        if (resultOfSqlQuery.isEmpty()) {
            return saveBallThrower(countOfBallsThrownAtTheBeginningOfTheGame, countOfBallsThrowingDuringTheGame, ballFrequency, automaticBombFrequency, manualBombFrequency, showThePositionsOfTheNextBalls);
        } else {
            resultOfSqlQuery.moveToTheNextRow();
            return resultOfSqlQuery.getInt("id");
        }
    }
}
