
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

import org.nanoboot.colorshapes.desktop.logic.game.Game;
import org.nanoboot.colorshapes.desktop.domain.*;
import org.nanoboot.powerframework.simplicity.Screen;

/**
 * This class is used to represent all logic in game.
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class Logic {

    private final ChangeNotifier changeNotifier;

    private final Domain domain;
    private final Application application;

    private ApplicationLook applicationLook;
    private final Authentication authentication;
    private Player player = null;
    private final Logger logger;
    private final GameManager gameManager;
    private final TextSource textSource;

    /**
     *
     * @param domain
     * @param changeNotifier
     */
    public Logic(Domain domain, ChangeNotifier changeNotifier) {
        this.domain = domain;
        this.changeNotifier = changeNotifier;

        this.gameManager = new GameManager(this);

        application = new Application(this);
        authentication = new Authentication(this);
        logger = new Logger();
        this.textSource = new TextSource(this);
        this.applicationLook = this.application.getApplicationLook();
        Screen.setZoom(this.applicationLook.getZoom());
    }

    public Application getApplication() {
        return application;
    }

    public ApplicationLook getApplicationLook() {
        return applicationLook;
    }

    public void setApplicationLook(ApplicationLook applicationLook) {
        this.applicationLook = applicationLook;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return this.gameManager.getGame();
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public TextSource getTextSource() {
        return this.textSource;
    }

    /**
     * It is used to store a new command in commandBox
     *
     * @param change
     * @return void
     */
    public void addChange(String change) {
        this.changeNotifier.addChange(change);
    }

    Logger getLogger() {
        return this.logger;
    }

}
