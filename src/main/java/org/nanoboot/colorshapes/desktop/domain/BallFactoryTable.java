
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

import org.nanoboot.colorshapes.desktop.logic.composition.BallFactoryComposition;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class BallFactoryTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private BallFactoryTable() {
        //Not meant to be instantiated.
    }

    static BallFactoryComposition getBallFactoryComposition(int ballFactoryCompositionId) {
        BallFactoryComposition ballFactoryComposition;
        JsonObject ballFactoryCompositionJsonObject = databaseConnection.getRow("ball_factory", ballFactoryCompositionId);

        int colourFrequencyId = Integer.parseInt(ballFactoryCompositionJsonObject.getString("colour_frequency_id"));

        int[] colourFrequency = ColourFrequencyTable.getColourFrequency(colourFrequencyId);

        ballFactoryComposition = new BallFactoryComposition(
                colourFrequency,
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("colourful_ball_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("rainbow_ball_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("value_minus_two_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("value_minus_one_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("value_zero_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("value_plus_one_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("value_plus_two_frequency")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("unmoveable_ball_probability")),
                Integer.parseInt(ballFactoryCompositionJsonObject.getString("unbreakable_ball_probability"))
        );
        return ballFactoryComposition;
    }

    /**
     *
     * @param colourFrequencyId
     * @param colourfulBallFrequency
     * @param rainbowBallFrequency
     * @param valueMinusTwoFrequency
     * @param valueMinusOneFrequency
     * @param valueZeroFrequency
     * @param valuePlusOneFrequency
     * @param valuePlusTwoFrequency
     * @param unmoveableBallProbability
     * @param unbreakableBallProbability
     * @return
     */
    public static int saveBallFactory(int colourFrequencyId, int valueMinusTwoFrequency, int valueMinusOneFrequency, int valueZeroFrequency, int valuePlusOneFrequency, int valuePlusTwoFrequency, int colourfulBallFrequency, int rainbowBallFrequency, int unmoveableBallProbability, int unbreakableBallProbability) {//NOSONAR
        StringBuilder insertCommandStringBuilder = new StringBuilder("INSERT INTO ball_factory VALUES (null,");
        insertCommandStringBuilder.append(colourFrequencyId).append(",");
        insertCommandStringBuilder.append(colourfulBallFrequency).append(",");
        insertCommandStringBuilder.append(rainbowBallFrequency).append(",");
        insertCommandStringBuilder.append(valueMinusTwoFrequency).append(",");
        insertCommandStringBuilder.append(valueMinusOneFrequency).append(",");
        insertCommandStringBuilder.append(valueZeroFrequency).append(",");
        insertCommandStringBuilder.append(valuePlusOneFrequency).append(",");
        insertCommandStringBuilder.append(valuePlusTwoFrequency).append(",");
        insertCommandStringBuilder.append(unmoveableBallProbability).append(",");
        insertCommandStringBuilder.append(unbreakableBallProbability).append(")");
        return databaseConnection.execute(insertCommandStringBuilder.toString());
    }

    /**
     *
     * @param ballFactoryComposition
     * @return
     */
    public static int getId(BallFactoryComposition ballFactoryComposition) {
        int[] colourFrequency = ballFactoryComposition.getColourFrequency();
        int colourFrequencyId = ColourFrequencyTable.getId(colourFrequency);
        int valueMinusTwoFrequency = ballFactoryComposition.getValueFrequency(-2);
        int valueMinusOneFrequency = ballFactoryComposition.getValueFrequency(-1);
        int valueZeroFrequency = ballFactoryComposition.getValueFrequency(0);
        int valuePlusOneFrequency = ballFactoryComposition.getValueFrequency(1);
        int valuePlusTwoFrequency = ballFactoryComposition.getValueFrequency(2);
        int colorfulBallFrequency = ballFactoryComposition.getColorfulBallFrequency();
        int rainbowBallFrequency = ballFactoryComposition.getRainbowBallFrequency();
        int unmoveableBallProbability = ballFactoryComposition.getUnmoveableBallsProbability();
        int unbreakableBallProbability = ballFactoryComposition.getUnbreakableBallsProbability();

        String sqlQueryString
                = "SELECT * FROM ball_factory WHERE "
                + "colour_frequency_id = "
                + colourFrequencyId
                + " AND value_minus_two_frequency= "
                + valueMinusTwoFrequency
                + " AND value_minus_one_frequency = "
                + valueMinusOneFrequency
                + " AND value_zero_frequency = "
                + valueZeroFrequency
                + " AND value_plus_one_frequency = "
                + valuePlusOneFrequency
                + " AND value_plus_two_frequency = "
                + valuePlusTwoFrequency
                + " AND colourful_ball_frequency = "
                + colorfulBallFrequency
                + " AND rainbow_ball_frequency= "
                + rainbowBallFrequency
                + " AND unmoveable_ball_probability = "
                + unmoveableBallProbability
                + " AND unbreakable_ball_probability = "
                + unbreakableBallProbability;

        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn(sqlQueryString);
        if (resultOfSqlQuery.isEmpty()) {
            return saveBallFactory(colourFrequencyId, valueMinusTwoFrequency, valueMinusOneFrequency, valueZeroFrequency, valuePlusOneFrequency, valuePlusTwoFrequency, colorfulBallFrequency, rainbowBallFrequency, unmoveableBallProbability, unbreakableBallProbability);
        } else {
            resultOfSqlQuery.moveToTheNextRow();
            return resultOfSqlQuery.getInt("id");
        }
    }
}
