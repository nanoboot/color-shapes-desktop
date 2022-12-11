
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

package org.nanoboot.colorshapes.desktop.services;

import org.nanoboot.colorshapes.desktop.logic.Logic;
import org.nanoboot.colorshapes.desktop.logic.Player;
import org.nanoboot.colorshapes.desktop.logic.composition.GameComposition;
import org.nanoboot.powerframework.datetime.LocalDate;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class PlayerService {

    private final Logic logic;

    public PlayerService(Logic logic) {
        this.logic = logic;
    }

    public Player getPlayer() {
        return logic.getPlayer();
    }

    /**
     *
     * @param value
     */
    public void setKeepingSignedIn(boolean value) {
        getPlayer().setKeepingSignedIn(value);
    }

    /**
     *
     */
    public GameComposition getLastPlayedGameComposition() {
        return getPlayer().getLastPlayedGameComposition();
    }

    /**
     *
     * @return Nick of logged in player.
     */
    public String getPlayerNick() {
        return getPlayer().getPlayerNick();
    }

    /**
     *
     * @param playerNick
     * @param newPassword
     */
    public void setPlayerNick(String playerNick, String password) {
        getPlayer().setPlayerNick(playerNick, password);
    }

    /**
     *
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     */
    public void updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        getPlayer().updatePassword(oldPassword, newPassword, confirmNewPassword);

    }

    /**
     *
     * @return
     */
    public String getBallLightingName() {
        return getPlayer().getBallLightingName();
    }

    /**
     *
     * @param name
     */
    public void setBallLightingName(String name) {
        getPlayer().setBallLightingName(name);
    }

    /**
     *
     * @return
     */
    public boolean getLinesAround() {
        return getPlayer().getLinesAround();
    }

    /**
     *
     * @param value
     */
    public void setLinesAround(boolean value) {
        getPlayer().setLinesAround(value);
    }

    /**
     *
     * @return
     */
    public boolean getShowWhereABallCanBeMoved() {
        return getPlayer().getShowWhereABallCanBeMoved();
    }

    /**
     *
     * @param value
     */
    public void setShowWhereABallCanBeMoved(boolean value) {
        getPlayer().setShowWhereABallCanBeMoved(value);
    }

    /**
     *
     * @return
     */
    public boolean getHighlightFieldAfterBallExploded() {
        return getPlayer().getHighlightFieldAfterBallExploded();
    }

    /**
     *
     * @param value
     */
    public void setShowHighlightFieldAfterBallExploded(boolean value) {
        getPlayer().setShowHighlightFieldAfterBallExploded(value);
    }

    /**
     *
     * @return
     */
    public String getBallMoveEffectName() {
        return getPlayer().getBallMoveEffectName();
    }

    /**
     *
     * @param name
     */
    public void setBallMoveEffectName(String name) {
        getPlayer().setBallMoveEffectName(name);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return getPlayer().getName();
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        getPlayer().setName(name);
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return getPlayer().getSurname();
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        getPlayer().setSurname(surname);
    }

    /**
     *
     * @return
     */
    public String getSexName() {
        return getPlayer().getSex();
    }

    /**
     *
     * @param sexName
     */
    public void setSexName(String sexName) {
        getPlayer().setSex(sexName);
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfBirth() {
        return getPlayer().getDateOfBirth();
    }

    /**
     *
     * @param localDate
     */
    public void setDateOfBirth(LocalDate localDate) {
        getPlayer().setDateOfBirth(localDate);
    }

    /**
     *
     * @return
     */
    public String getTimeZoneName() {
        return getPlayer().getTimeZoneName();
    }

    /**
     *
     * @param timeZoneName
     */
    public void setTimeZoneName(String timeZoneName) {
        getPlayer().setTimeZoneName(timeZoneName);
    }

}
