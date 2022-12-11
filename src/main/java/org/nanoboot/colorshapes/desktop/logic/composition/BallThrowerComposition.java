
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

package org.nanoboot.colorshapes.desktop.logic.composition;

import org.nanoboot.colorshapes.desktop.logic.composition.lockableobject.LockableObject;
import java.util.ArrayList;
import java.util.List;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class BallThrowerComposition extends LockableObject {

    private int countOfBallsThrownAtTheBeginningOfTheGame;
    private int countOfBallsThrowingDuringTheGame;

    private int ballFrequency;
    private int automaticBombFrequency;
    private int manualBombFrequency;

    private boolean showThePositionsOfTheNextBalls;

    /**
     *
     * @param ballThrowerCompositionJsonObject
     */
    public BallThrowerComposition(JsonObject ballThrowerCompositionJsonObject) {
        updateByJsonObject(ballThrowerCompositionJsonObject);
    }

    /**
     * Constructor
     */
    public BallThrowerComposition() {
        setDefaultValues();
    }

    /**
     * Constructor
     *
     * @param countOfBallsThrownAtTheBeginningOfTheGame
     * @param countOfBallsThrowingDuringTheGame
     * @param ballFrequency
     * @param automaticBombFrequency
     * @param manualBombFrequency
     * @param showNextBallsPositions
     */
    public BallThrowerComposition(
            int countOfBallsThrownAtTheBeginningOfTheGame,
            int countOfBallsThrowingDuringTheGame,
            int ballFrequency,
            int automaticBombFrequency,
            int manualBombFrequency,
            boolean showNextBallsPositions
    ) {
        this.setCountOfBallsThrownAtTheBeginningOfTheGame(countOfBallsThrownAtTheBeginningOfTheGame);
        this.setCountOfBallsThrowDuringTheGame(countOfBallsThrowingDuringTheGame);
        this.setBallFrequency(ballFrequency);
        this.setAutomaticBombFrequency(automaticBombFrequency);
        this.setManualBombFrequency(manualBombFrequency);
        this.setShowingNextBallsPositions(showNextBallsPositions);
    }

    private void updateByJsonObject(JsonObject ballThrowerCompositionJsonObject) {
        this.countOfBallsThrownAtTheBeginningOfTheGame = ballThrowerCompositionJsonObject.getInt("start");
        this.countOfBallsThrowingDuringTheGame = ballThrowerCompositionJsonObject.getInt("during");
        this.ballFrequency = ballThrowerCompositionJsonObject.getInt("ball frequency");
        this.automaticBombFrequency = ballThrowerCompositionJsonObject.getInt("automatic bomb frequency");
        this.manualBombFrequency = ballThrowerCompositionJsonObject.getInt("manual bomb frequency");

        this.showThePositionsOfTheNextBalls = ballThrowerCompositionJsonObject.getBoolean("show positions");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        return true;
    }

    /**
     *
     */
    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.setCountOfBallsThrownAtTheBeginningOfTheGame(5);
        this.setCountOfBallsThrowDuringTheGame(3);
        this.setBallFrequency(1);
        this.setAutomaticBombFrequency(0);
        this.setManualBombFrequency(0);
        this.setShowingNextBallsPositions(false);
    }

    /**
     *
     */
    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.setCountOfBallsThrownAtTheBeginningOfTheGame(PseudoRandomGenerator.getInstance().getInt(1, 8));
        this.setCountOfBallsThrowDuringTheGame(PseudoRandomGenerator.getInstance().getInt(1, 8));

        this.setBallFrequency(this.getNotNullRandomFrequency());
        this.setAutomaticBombFrequency(this.getRandomFrequency());
        this.setManualBombFrequency(this.getRandomFrequency());
        this.setShowingNextBallsPositions(PseudoRandomGenerator.getInstance().getBoolean());
    }

    /**
     *
     * @return
     */
    @Override
    public BallThrowerComposition getCopy() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        BallThrowerComposition copy = new BallThrowerComposition();

        copy.setCountOfBallsThrownAtTheBeginningOfTheGame(this.getCountOfBallsThrownAtTheBeginningOfTheGame());
        copy.setCountOfBallsThrowDuringTheGame(this.getCountOfBallsThrownDuringTheGame());
        copy.setBallFrequency(this.getBallFrequency());
        copy.setAutomaticBombFrequency(this.getAutomaticBombFrequency());
        copy.setManualBombFrequency(this.getManualBombFrequency());
        copy.setShowingNextBallsPositions(this.getShowingNextBallsPosition());

        return copy;
    }

    /**
     *
     * @param count
     */
    public void setCountOfBallsThrownAtTheBeginningOfTheGame(int count) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.countOfBallsThrownAtTheBeginningOfTheGame = count;
    }

    /**
     *
     * @return
     */
    public int getCountOfBallsThrownAtTheBeginningOfTheGame() {
        return this.countOfBallsThrownAtTheBeginningOfTheGame;
    }

    /**
     *
     * @param count
     */
    public void setCountOfBallsThrowDuringTheGame(int count) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.countOfBallsThrowingDuringTheGame = count;
    }

    /**
     *
     * @return
     */
    public int getCountOfBallsThrownDuringTheGame() {
        return this.countOfBallsThrowingDuringTheGame;
    }

    /**
     *
     * @param frequency
     */
    public void setBallFrequency(int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.ballFrequency = frequency;
    }

    /**
     *
     * @return
     */
    public int getBallFrequency() {
        return this.ballFrequency;
    }

    /**
     *
     * @param frequency
     */
    public void setAutomaticBombFrequency(int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.automaticBombFrequency = frequency;
    }

    /**
     *
     * @return
     */
    public int getAutomaticBombFrequency() {
        return this.automaticBombFrequency;
    }

    /**
     *
     * @param frequency
     */
    public void setManualBombFrequency(int frequency) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.manualBombFrequency = frequency;
    }

    /**
     *
     * @return
     */
    public int getManualBombFrequency() {
        return this.manualBombFrequency;
    }

    /**
     *
     * @param value
     */
    public void setShowingNextBallsPositions(boolean value) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.showThePositionsOfTheNextBalls = value;
    }

    /**
     *
     * @return
     */
    public boolean getShowingNextBallsPosition() {
        return this.showThePositionsOfTheNextBalls;
    }

    @Override
    public List getListOfLockableObjects() {
        return new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject ballThrowerCompositionJsonObject = new JsonObject();

        ballThrowerCompositionJsonObject.addInt("start", this.countOfBallsThrownAtTheBeginningOfTheGame);
        ballThrowerCompositionJsonObject.addInt("during", this.countOfBallsThrowingDuringTheGame);
        ballThrowerCompositionJsonObject.addInt("ball frequency", this.ballFrequency);
        ballThrowerCompositionJsonObject.addInt("automatic bomb frequency", this.automaticBombFrequency);
        ballThrowerCompositionJsonObject.addInt("manual bomb frequency", this.manualBombFrequency);

        ballThrowerCompositionJsonObject.addBoolean("show positions", this.showThePositionsOfTheNextBalls);

        return ballThrowerCompositionJsonObject;
    }
}
