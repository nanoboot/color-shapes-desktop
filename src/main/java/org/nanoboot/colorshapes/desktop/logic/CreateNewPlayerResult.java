
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

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public enum CreateNewPlayerResult {

    /**
     * success
     */
    PLAYER_CREATED,
    /**
     * nick must be unique
     */
    NICK_ALREADY_EXISTS,
    /**
     * nick is too long
     */
    TOO_LONG_NICK,
    /**
     * nick is empty
     */
    NICK_IS_EMPTY,
    /**
     * password and confirm password do not match
     */
    PASSWORD_AND_CONFIRM_PASSWORD_DO_NOT_MATCH,
    /**
     * too long password
     */
    TOO_LONG_PASSWORD,
    /**
     * nick or password contains forbidden characters
     */
    NICK_OR_PASSWORD_CONTAINS_FORBIDDEN_CHARACTERS,
    /**
     * Zoom is lower than 50 (%)
     */
    TOO_LOW_ZOOM;

}
