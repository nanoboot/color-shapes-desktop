
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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.datetime.LocalDate;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class PlayerTable extends Table {

    public static final String TABLENAME = "player";
    public static final String NICK = "nick";
    public static final String PASSWORD = "password";//NOSONAR
    public static final String PLAYERCREATEDUNIVERSALDATETIMEID = "player_created_universal_date_time_id";
    public static final String LANGUAGEID = "language_id";
    public static final String SKIN = "skin";
    public static final String ZOOM = "zoom";
    public static final String BALLLIGHTINGID = "ball_lighting_id";
    public static final String SHOWLINESAROUNDBALL = "show_lines_around_ball";
    public static final String SHOWNEXTBALLS = "show_next_balls";
    public static final String SHOWWHEREABALLCANBEMOVED = "show_where_a_ball_can_be_moved";
    public static final String HIGHLIGHTFIELDSAFTERBALLEXPLODED = "highlight_fields_after_ball_exploded";
    public static final String BALLMOVEEFFECTID = "ball_move_effect_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String SEX = "sex";
    public static final String DATEOFBIRTH = "date_of_birth";
    public static final String TIMEZONEID = "time_zone_id";

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private PlayerTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param nick
     * @param password
     * @param languageId
     * @param skin
     * @param zoom
     * @param timeZoneName
     * @return
     */
    public static int savePlayer(String nick, String password, int languageId, int skin, int zoom, String timeZoneName) {
        boolean doesExistATimeZoneWithThisName = !databaseConnection.executeAndReturn("SELECT id FROM time_zone WHERE name='" + timeZoneName + "'").isEmpty();
        int timeZoneId;
        if (!doesExistATimeZoneWithThisName) {
            timeZoneId = TimeZoneTable.saveTimeZone(timeZoneName);
        } else {
            timeZoneId = TimeZoneTable.getId(timeZoneName);
        }
        int currentUniversalDateTimeId = UniversalDateTimeTable.saveCurrentUniversalDateTime();
        if(skin == 0) {skin = 10;}
        String insertCommand = "INSERT INTO player VALUES (null,'"
                + nick + "','"
                + password + "',"
                + currentUniversalDateTimeId + ","
                + languageId + ","
                + skin + ","
                + zoom + ","
                + "1,0,1,0,0,1,null,null,'unknown',null,"
                + timeZoneId + ")";
        return databaseConnection.execute(insertCommand);
    }

    /**
     *
     * @param playerId
     * @return json object representation of the information about the player
     */
    public static JsonObject getRow(int playerId) {
        return databaseConnection.getRow(TABLENAME, playerId);
    }

    private static void updateValue(int playerId, String columnName, int newValue) {
        databaseConnection.updateValue(TABLENAME, playerId, columnName, newValue);
    }

    private static void updateValue(int playerId, String columnName, String newValue) {
        databaseConnection.updateValue(TABLENAME, playerId, columnName, newValue);
    }

    /**
     *
     * @param nick
     * @return player Id
     */
    public static int getId(String nick) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT ID FROM PLAYER WHERE " + NICK + "='" + nick + "';");
        resultOfSqlQuery.moveToTheNextRow();
        return resultOfSqlQuery.getInt("id");
    }

    /**
     *
     * @param playerId
     * @return nick
     */
    public static String getNick(int playerId) {
        return getRow(playerId).getString(NICK);
    }

    /**
     *
     * @param playerId
     * @param newValue
     * @return true for successful nick change, false for unsuccessful nick
     * change
     */
    public static boolean setNick(int playerId, String newValue) {
        try {
            updateValue(playerId, NICK, newValue);
        } catch (Exception e) {
            Logger.getLogger(PlayerTable.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    /**
     * Checks whether a nick already exists
     *
     * @param nick
     * @return
     */
    public static boolean doesExistNick(String nick) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT " + NICK + " from " + TABLENAME + " where " + NICK + " = '" + nick + "'");
        return !resultOfSqlQuery.isEmpty();
    }

    /**
     * Checks if a player with the nick and password exists.
     *
     * @param nick
     * @param password
     * @return
     */
    public static boolean areNickAndPasswordOK(String nick, String password) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT " + NICK + " from Player WHERE " + NICK + "='" + nick + "' AND " + PASSWORD + "='" + password + "';");
        return !resultOfSqlQuery.isEmpty();
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void updatePassword(int playerId, String newValue) {
        updateValue(playerId, PASSWORD, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setLanguageId(int playerId, int newValue) {
        updateValue(playerId, LANGUAGEID, newValue);
    }

    /**
     *
     * @param playerId
     * @return language id
     */
    public static int getLanguageId(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(LANGUAGEID));
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setSkin(int playerId, int newValue) {
        updateValue(playerId, SKIN, newValue);
    }

    /**
     *
     * @param playerId
     * @return skin
     */
    public static int getSkin(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(PlayerTable.SKIN));
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setZoom(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.ZOOM, newValue);
    }

    /**
     *
     * @param playerId
     * @return zoom
     */
    public static int getZoom(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(PlayerTable.ZOOM));
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setBallLightingId(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.BALLLIGHTINGID, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setShowLinesAroundBall(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.SHOWLINESAROUNDBALL, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setShowNextBalls(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.SHOWNEXTBALLS, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setWhereABallCanBeMoved(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.SHOWWHEREABALLCANBEMOVED, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setHighlightFieldsAfterBallExploded(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.HIGHLIGHTFIELDSAFTERBALLEXPLODED, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setBallMoveEffectId(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.BALLMOVEEFFECTID, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setName(int playerId, String newValue) {
        updateValue(playerId, PlayerTable.NAME, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setSurname(int playerId, String newValue) {
        updateValue(playerId, PlayerTable.SURNAME, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void setSex(int playerId, String newValue) {
        updateValue(playerId, PlayerTable.SEX, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void updateDateOfBirth(int playerId, String newValue) {
        updateValue(playerId, PlayerTable.DATEOFBIRTH, newValue);
    }

    /**
     *
     * @param playerId
     * @param newValue
     */
    public static void updateTimeZoneId(int playerId, int newValue) {
        updateValue(playerId, PlayerTable.TIMEZONEID, newValue);
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static int getBallLightingId(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(PlayerTable.BALLLIGHTINGID));
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static int getBallMoveEffectId(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(PlayerTable.BALLMOVEEFFECTID));
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static boolean getLinesAround(int playerId) {
        JsonObject row = getRow(playerId);
        return "1".equals(row.getString(PlayerTable.SHOWLINESAROUNDBALL));
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static boolean getShowWhereABallCanBeMoved(int playerId) {
        JsonObject row = getRow(playerId);
        return "1".equals(row.getString(PlayerTable.SHOWWHEREABALLCANBEMOVED));
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static boolean getHighlightFieldAfterBallExploded(int playerId) {
        JsonObject row = getRow(playerId);
        return "1".equals(row.getString(PlayerTable.HIGHLIGHTFIELDSAFTERBALLEXPLODED));
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static String getName(int playerId) {
        JsonObject row = getRow(playerId);
        return row.getString(PlayerTable.NAME);
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static String getSurname(int playerId) {
        JsonObject row = getRow(playerId);
        return row.getString(PlayerTable.SURNAME);
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static String getSex(int playerId) {
        JsonObject row = getRow(playerId);
        return row.getString(PlayerTable.SEX);
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static LocalDate getDateOfBirth(int playerId) {
        JsonObject row = getRow(playerId);
        String dateOfBirth = row.getString(PlayerTable.DATEOFBIRTH);
        if (dateOfBirth == null) {
            return null;
        }
        String[] array;
        array = dateOfBirth.split("-+");

        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        return new LocalDate(year, month, day);
    }

    /**
     *
     * @param playerId
     * @param localDate
     */
    public static void setDateOfBirth(int playerId, LocalDate localDate) {
        updateDateOfBirth(playerId, localDate.toString());
    }

    /**
     *
     * @param playerId
     * @return
     */
    public static int getTimeZoneId(int playerId) {
        JsonObject row = getRow(playerId);
        return Integer.parseInt(row.getString(PlayerTable.TIMEZONEID));
    }

    /**
     *
     * @param playerId
     * @param timeZoneId
     */
    public static void setTimeZoneId(int playerId, int timeZoneId) {
        updateTimeZoneId(playerId, timeZoneId);
    }
}
