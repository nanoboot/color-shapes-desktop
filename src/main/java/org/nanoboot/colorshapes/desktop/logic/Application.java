
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

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.colorshapes.desktop.domain.ApplicationTable;
import org.nanoboot.colorshapes.desktop.domain.PlayerTable;
import javafx.application.Platform;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Application {

    private final Logic logic;
    private boolean running = false;

    private final ApplicationLookForBeforeLogin applicationLookForBeforeLogin = new ApplicationLookForBeforeLogin();

    /**
     * Constructor
     *
     * @param logic
     */
    public Application(Logic logic) {
        this.logic = logic;
    }

    /**
     * Starts logic layer.
     */
    public void start() {
        running = true;
        logic.getLogger().logApplicationStart();

        if (!this.isThereAnAutomaticallyLoggedInPlayer()) {
            logic.addChange("MODE LOGIN");
        } else {
            String nick;
            String password;
            JsonObject player = PlayerTable.getRow(this.getAutomaticallyLoggedInPlayerId());
            nick = player.getString("nick");
            password = player.getString("password");
            logic.getAuthentication().logIn(nick, password, true);
        }
    }

    /**
     * Stops the logic layer.
     */
    public void exit() {
        running = false;
        if (logic.getAuthentication().isLoggedIn()) {
            logic.getAuthentication().logOut();
        }
        logic.getLogger().logApplicationEnd();
        ColorShapes.getLogic().addChange("EXIT");
        Platform.exit();
    }

    /**
     *
     * @return
     */
    public int getAutomaticallyLoggedInPlayerId() {
        return ApplicationTable.getAutomaticallyLoggedInPlayerId();
    }

    public boolean isThereAnAutomaticallyLoggedInPlayer() {
        return this.getAutomaticallyLoggedInPlayerId() != 0;
    }

    public ApplicationLook getApplicationLook() {
        return this.applicationLookForBeforeLogin;
    }

    public boolean isRunning() {
        return running;
    }
}
