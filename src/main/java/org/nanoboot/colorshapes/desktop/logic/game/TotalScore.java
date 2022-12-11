
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

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.logic.game.board.Rollable;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import java.util.List;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class TotalScore {

    private int score = 0;
    private boolean locked = false;
    private final int minimumCountOfBalls;

    /**
     * Constructor
     *
     * @param minimumCountOfBalls
     */
    public TotalScore(int minimumCountOfBalls) {
        this.minimumCountOfBalls = minimumCountOfBalls;
    }

    public int getCurrentTotalScore() {
        return this.score;
    }

    /**
     *
     * @param listOfExplodedRollable
     * @return sum of new added score
     */
    public int addListOfExplodedRollablesToCheck(List<Rollable> listOfExplodedRollable) {
        if (locked) {
            throw new ColorShapesRuntimeException("Total score is permanently locked for changes.");
        }
        if (listOfExplodedRollable == null) {
            return 0;
        }

        int scoreToAdd = ResultCounter.countScore(listOfExplodedRollable, minimumCountOfBalls);
        if (scoreToAdd != 0) {
            addNewScoreSum(scoreToAdd);
            ColorShapes.getLogic().addChange("SCORE PLAYER " + this.getCurrentTotalScore());

        }
        return scoreToAdd;

    }

    private void addNewScoreSum(int value) {
        this.score = score + value;
    }

    /**
     * Locks score for changes. If this method is called and then the method
     * addListOfExplodedRollablesToCheck is called, new Exception is thrown.
     */
    public void lockForChanges() {
        this.locked = true;
    }
}
