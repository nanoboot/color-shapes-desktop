
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

package org.nanoboot.colorshapes.desktop.logic.game;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ChangeCommands {

    public static final String GAME = "GAME ";
    public static final String NEW = "NEW ";
    public static final String HOLES = "HOLES ";
    public static final String NICK = "NICK ";
    public static final String SCORE = "SCORE ";
    public static final String PLAYER = "PLAYER ";
    public static final String RECORDHOLDER = "RECOR-DHOLDER ";
    public static final String FIELD = "FIELD ";
    public static final String GRID = "GRID ";
    public static final String ON = "ON ";
    public static final String OFF = "OFF ";
    public static final String WALL = "WALL ";
    public static final String TOP = "TOP ";
    public static final String RIGHT = "RIGHT ";
    public static final String BOTTOM = "BOTTOM ";
    public static final String LEFT = "LEFT ";
    public static final String NEXT = "NEXT ";
    public static final String ROLLABLE = "ROLLABLE ";
    public static final String BALL = "BALL ";
    public static final String UNMOVEABLE = "UNMOVEABLE ";
    public static final String MOVEABLE = "MOVEABLE ";
    public static final String UNBREAKABLE = "UNBREAKABLE ";
    public static final String BREAKABLE = "BREAKABLE ";
    public static final String BOMB = "BOMB ";
    public static final String AUTOMATIC = "AUTOMATIC ";
    public static final String MANUAL = "MANUAL ";
    public static final String INFLATE = "INFLATE ";
    public static final String JUMPING = "JUMPING ";
    public static final String EXPLODE = "EXPLODE ";
    public static final String MOVE = "MOVE ";
    public static final String TO = "TO ";
    public static final String CLEAR = "CLEAR ";
    public static final String WAIT = "WAIT ";
    public static final String SPACE = " ";

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ChangeCommands() {
        //Not meant to be instantiated.
    }

    public static String number(int number) {
        return number + " ";
    }
}
