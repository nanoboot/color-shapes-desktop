
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

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Services {

    private final Logic logic;

    private final ApplicationService applicationService;
    private final BeforeLoginService beforeLoginService;
    private final PlayerService playerService;
    private final AuthenticationService authenticationService;
    private final GameService gameService;
    private final GameCompositionService gameCompositionService;
    private final LanguageService languageService;
    private final PlayerSLookService lookService;
    private final CurrentLookService currentLookService;

    public Services(Logic logic) {
        this.logic = logic;
        applicationService = new ApplicationService(logic);
        beforeLoginService = new BeforeLoginService(logic);
        playerService = new PlayerService(logic);
        authenticationService = new AuthenticationService(logic);
        gameService = new GameService(logic);
        gameCompositionService = new GameCompositionService(logic);
        languageService = new LanguageService(logic);
        lookService = new PlayerSLookService(logic);
        this.currentLookService = new CurrentLookService(logic);
    }

    /**
     *
     * @return
     */
    public ApplicationService getApplicationService() {
        return applicationService;
    }

    /**
     *
     * @return
     */
    public BeforeLoginService getBeforeLoginService() {
        return beforeLoginService;
    }

    /**
     *
     * @return
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     *
     * @return
     */
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    /**
     *
     * @return
     */
    public GameService getGameService() {
        return gameService;
    }

    /**
     *
     * @return
     */
    public GameCompositionService getGameCompositionService() {
        return gameCompositionService;
    }

    /**
     *
     * @return
     */
    public LanguageService getLanguageService() {
        return languageService;
    }

    /**
     *
     * @return
     */
    public PlayerSLookService getLookService() {
        return lookService;
    }

    public CurrentLookService getCurrentLookService() {
        return this.currentLookService;
    }

}
