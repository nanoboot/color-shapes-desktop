
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

import org.nanoboot.colorshapes.desktop.logic.Authentication;
import org.nanoboot.colorshapes.desktop.logic.CreateNewPlayerResult;
import org.nanoboot.colorshapes.desktop.logic.Logic;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class AuthenticationService {

    private final Logic logic;
    private final Authentication authentication;

    public AuthenticationService(Logic logic) {
        this.logic = logic;
        this.authentication = logic.getAuthentication();
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
        return authentication.createNewPlayer(nick, password, confirmPassword, languageId, skin, zoom, timeZoneId);

    }

    public boolean logIn(String nick, String password, boolean keepingLogged) {
        return this.authentication.logIn(nick, password, keepingLogged);
    }

    /**
     * Log out current player. No player will be automatically logged in.
     */
    public void logOut() {
        authentication.logOut();
    }

    /**
     *
     * @return * If a player is logged in, returns true, otherwise returns
     * false.
     */
    public boolean isLoggedIn() {
        return logic.getPlayer() != null;
    }
}
