
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

package org.nanoboot.colorshapes.desktop.logic;

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.domain.ApplicationTable;
import org.nanoboot.colorshapes.desktop.domain.BallLightingTable;
import org.nanoboot.colorshapes.desktop.domain.BallMoveEffectTable;
import org.nanoboot.colorshapes.desktop.domain.GameCompositionTable;
import org.nanoboot.colorshapes.desktop.domain.GameTable;
import org.nanoboot.colorshapes.desktop.domain.PlayerTable;
import org.nanoboot.colorshapes.desktop.domain.TimeZoneTable;
import org.nanoboot.colorshapes.desktop.logic.composition.GameComposition;
import org.nanoboot.powerframework.datetime.LocalDate;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Player {

    /**
     * This is player Id. If no player is logged in, Id is zero.
     */
    private final int id;
    private final Logic logic;

    /**
     *
     * @param playerId
     * @param logic
     */
    public Player(int playerId, Logic logic) {
        this.id = playerId;
        this.logic = logic;
    }

    public ApplicationLook getApplicationLook() {
        return new ApplicationLookForPlayer(this.id);
    }

    /**
     *
     * @return Player Id used to identify player in database
     */
    public int getPlayerId() {
        return this.id;
    }

    /**
     *
     * @param value
     */
    public void setKeepingSignedIn(boolean value) {
        int tempPlayerId;
        if (value) {
            tempPlayerId = id;
        } else {
            tempPlayerId = 0;
        }
        ApplicationTable.setAutomaticallyLoggedInPlayerId(tempPlayerId);
    }

    /**
     *
     */
    public GameComposition getLastPlayedGameComposition() {
        int lastPlayedGameCompositionId = GameTable.getLastPlayedGameCompositionId(this.id);
        return GameCompositionTable.getGameComposition(lastPlayedGameCompositionId);
    }

    /**
     *
     * @return Nick of logged in player.
     */
    public String getPlayerNick() {
        return PlayerTable.getNick(id);
    }

    /**
     *
     * @param playerNick
     * @param newPassword
     */
    public void setPlayerNick(String playerNick, String password) {
        if (!PlayerTable.areNickAndPasswordOK(this.getPlayerNick(), password)) {
            return;
        }
        PlayerTable.setNick(id, playerNick);
    }

    /**
     *
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     */
    public void updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        if ((PlayerTable.areNickAndPasswordOK(PlayerTable.getNick(id), oldPassword)) && (newPassword.equals(confirmNewPassword))) {
            PlayerTable.updatePassword(id, newPassword);
        } else {
            throw new ColorShapesRuntimeException("Password was not changed.");
        }

    }

    /**
     *
     * @return
     */
    public String getBallLightingName() {
        int ballLightingId = PlayerTable.getBallLightingId(id);
        return BallLightingTable.getName(ballLightingId);
    }

    /**
     *
     * @param name
     */
    public void setBallLightingName(String name) {
        int ballLightingId = BallLightingTable.getId(name);
        PlayerTable.setBallLightingId(id, ballLightingId);
    }

    /**
     *
     * @return
     */
    public boolean getLinesAround() {
        return PlayerTable.getLinesAround(id);
    }

    /**
     *
     * @param value
     */
    public void setLinesAround(boolean value) {
        PlayerTable.setShowLinesAroundBall(id, value ? 1 : 0);
    }

    /**
     *
     * @return
     */
    public boolean getShowWhereABallCanBeMoved() {
        return PlayerTable.getShowWhereABallCanBeMoved(id);
    }

    /**
     *
     * @param value
     */
    public void setShowWhereABallCanBeMoved(boolean value) {
        PlayerTable.setWhereABallCanBeMoved(id, value ? 1 : 0);
    }

    /**
     *
     * @return
     */
    public boolean getHighlightFieldAfterBallExploded() {
        return PlayerTable.getHighlightFieldAfterBallExploded(id);
    }

    /**
     *
     * @param value
     */
    public void setShowHighlightFieldAfterBallExploded(boolean value) {
        PlayerTable.setHighlightFieldsAfterBallExploded(id, value ? 1 : 0);
    }

    /**
     *
     * @return
     */
    public String getBallMoveEffectName() {
        int ballMoveEffectId = PlayerTable.getBallMoveEffectId(id);
        return BallMoveEffectTable.getName(ballMoveEffectId);
    }

    /**
     *
     * @param name
     */
    public void setBallMoveEffectName(String name) {
        int ballMoveEffectId = BallMoveEffectTable.getId(name);
        PlayerTable.setBallMoveEffectId(id, ballMoveEffectId);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return PlayerTable.getName(id);
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        PlayerTable.setName(id, name);
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return PlayerTable.getSurname(id);
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        PlayerTable.setSurname(id, surname);
    }

    /**
     *
     * @return
     */
    public String getSex() {
        return PlayerTable.getSex(id);
    }

    /**
     *
     * @param sexName
     */
    public void setSex(String sex) {
        PlayerTable.setSex(id, sex);
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfBirth() {
        return PlayerTable.getDateOfBirth(id);
    }

    /**
     *
     * @param localDate
     */
    public void setDateOfBirth(LocalDate localDate) {
        PlayerTable.setDateOfBirth(id, localDate);
    }

    /**
     *
     * @return
     */
    public String getTimeZoneName() {
        int timeZoneId = PlayerTable.getTimeZoneId(id);
        return TimeZoneTable.getName(timeZoneId);
    }

    /**
     *
     * @param timeZoneName
     */
    public void setTimeZoneName(String timeZoneName) {
        int timeZoneId = TimeZoneTable.getId(timeZoneName);
        PlayerTable.updateTimeZoneId(id, timeZoneId);
    }

}
