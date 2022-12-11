
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

import org.nanoboot.colorshapes.desktop.domain.GameCompositionTable;
import org.nanoboot.colorshapes.desktop.logic.composition.GameComposition;
import org.nanoboot.colorshapes.desktop.logic.game.Game;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class GameManager {

    private final Logic logic;

    private Game game = null;

    private GameComposition currentGameComposition = new GameComposition();
    private int currentGameCompositionId;

    GameManager(Logic logic) {
        this.logic = logic;
    }

    public GameComposition getGameComposition() {
        return this.currentGameComposition;
    }

    public void setGameComposition(GameComposition gameComposition) {
        this.currentGameComposition = gameComposition;
        currentGameCompositionId = GameCompositionTable.getId(this.currentGameComposition);
    }

    public void playNewGame() {
        if (this.game != null) {
            game.end(true);
        }
        Game game = new Game(this.currentGameComposition);
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    /**
     *
     * @param jsonObject
     */
    public void setGameCompositionByJsonObject(JsonObject jsonObject) {
        this.currentGameComposition = new GameComposition(jsonObject);
        currentGameCompositionId = GameCompositionTable.getId(this.currentGameComposition);
    }

    /**
     *
     * @return
     */
    public JsonObject getGameCompositionAsJsonObject() {
        return this.currentGameComposition.toJsonObject();
    }

    /**
     *
     */
    public void setGameCompositionDefault() {
        this.currentGameComposition.setDefaultValues();
    }

    /**
     *
     */
    public void setGameCompositionRandom() {
        this.currentGameComposition.setRandomValues();
    }

    public int getGameCompositionId() {
        return this.currentGameCompositionId;
    }

    public void activateField(int row, int column) {
        this.game.activateField(row, column);
    }
}
