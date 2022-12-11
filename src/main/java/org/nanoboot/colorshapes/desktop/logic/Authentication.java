
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
import org.nanoboot.colorshapes.desktop.domain.PlayerTable;
import org.nanoboot.colorshapes.desktop.view.Look;
import org.nanoboot.powerframework.simplicity.Screen;

/**
 * This class is used to manage access to the game.
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Authentication {

    private final Logic logic;

    /**
     * @param logic
     */
    Authentication(Logic logic) {
        this.logic = logic;
    }

    /**
     * This method is used to create new player.
     *
     * @param nick <ul><li>From 1 to 16 characters.</li><li>Only a-z, A-Z and
     * 0-1 characters</li><li>This nick cannot be already used.</li></ul>
     * @param password <ul><li>From 0 to 16 characters.</li><li>Only a-z, A-Z
     * and 0-1 characters</li><li>For empty password use "".</li></ul>
     * @return True for successful login. False for failed login.
     */
    public CreateNewPlayerResult createNewPlayer(String nick, String password, String confirmPassword, int languageId, int skin, int zoom, int timeZoneId) {//NOSONAR
        if (this.doesExistThisNick(nick)) {
            return CreateNewPlayerResult.NICK_ALREADY_EXISTS;
        }
        if (nick.length() > 16) {
            return CreateNewPlayerResult.TOO_LONG_NICK;
        }
        if (nick.isEmpty()) {
            return CreateNewPlayerResult.NICK_IS_EMPTY;
        }
        if (!password.equals(confirmPassword)) {
            return CreateNewPlayerResult.PASSWORD_AND_CONFIRM_PASSWORD_DO_NOT_MATCH;
        }
        if (password.length() > 16) {
            return CreateNewPlayerResult.TOO_LONG_PASSWORD;
        }
        String regexp = "[A-Za-z0-9]+";
        if (!(nick.matches(regexp) && (("".equals(password)) || (password.matches(regexp))))) {
            return CreateNewPlayerResult.NICK_OR_PASSWORD_CONTAINS_FORBIDDEN_CHARACTERS;
        }
        PlayerTable.savePlayer(nick, password, languageId, skin, zoom, "Europe/Prague");

        return CreateNewPlayerResult.PLAYER_CREATED;

    }

    /**
     * Logs a player in.
     *
     * @param nick
     * @param password
     * @param keepingLogged
     * @return
     */
    public boolean logIn(String nick, String password, boolean keepingLogged) {
        if (this.isLoggedIn()) {
            throw new ColorShapesRuntimeException("A player is already logged in.");
        }
        if (this.areNickAndPasswordOK(nick, password)) {
            Player player = new Player(PlayerTable.getId(nick), logic);
            logic.setPlayer(player);
            logic.setApplicationLook(player.getApplicationLook());
            logic.getTextSource().setLanguageId(logic.getApplicationLook().getLanguageId());
            Screen.setZoom(this.logic.getApplicationLook().getZoom());
            this.logic.getLogger().logPlayerLoggedIn(PlayerTable.getId(nick));
            if (keepingLogged) {
                player.setKeepingSignedIn(true);
            }
            this.logic.getGameManager().setGameComposition(player.getLastPlayedGameComposition());

            Look.setBallLighting(player.getBallLightingName());
            Look.setShowLineAroundBall(player.getLinesAround());
            Look.setShowWhereABallCanBeMoved(player.getShowWhereABallCanBeMoved());
            Look.setHightlightFieldsAfterBallExploded(player.getHighlightFieldAfterBallExploded());
            Look.setBallMoveEffect(player.getBallMoveEffectName());
            this.logic.addChange("MODE GAME");
            this.logic.addChange("WAIT 2000");

            return true;
        } else {
            return false;
        }
    }

    /**
     * Log out current player. No player will be automatically logged in.
     */
    public void logOut() {
        this.logic.getLogger().logPlayerLoggedOut();
        logic.setPlayer(null);
        logic.setApplicationLook(logic.getApplication().getApplicationLook());
        logic.getTextSource().setLanguageId(logic.getApplicationLook().getLanguageId());
        Screen.setZoom(this.logic.getApplicationLook().getZoom());
        this.logic.addChange("MODE LOGIN");
    }

    /**
     *
     * @return * If a player is logged in, returns true, otherwise returns
     * false.
     */
    public boolean isLoggedIn() {
        return logic.getPlayer() != null;
    }

    /**
     * This method is used to check whether a nick exists.
     *
     * @param nick
     * @return If this nick exists, returns true, otherwise returns false.
     */
    boolean doesExistThisNick(String nick) {
        // Player with this nick exist if the count of rows is not zero.
        return PlayerTable.doesExistNick(nick);
    }

    /**
     * This method is used to verify nick and password.
     *
     * @param nick
     * @param password
     * @return Returns true for right nick and password.
     */
    private boolean areNickAndPasswordOK(String nick, String password) {
        return PlayerTable.areNickAndPasswordOK(nick, password);
    }
}
