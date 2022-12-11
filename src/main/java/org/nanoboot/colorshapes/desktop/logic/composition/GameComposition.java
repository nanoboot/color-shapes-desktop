
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

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class GameComposition extends LockableObject {

    private BoardComposition boardComposition;

    private BallFactoryComposition ballFactoryComposition;

    private BallThrowerComposition ballThrowerComposition;

    private BallDetonatorComposition ballDetonatorComposition;

    private OtherComposition otherComposition;

    /**
     *
     * @param gameCompositionJsonObject
     */
    public GameComposition(JsonObject gameCompositionJsonObject) {
        updateByJsonObject(gameCompositionJsonObject);
    }

    /**
     * Constructor
     */
    public GameComposition() {
        this.boardComposition = new BoardComposition();
        this.ballFactoryComposition = new BallFactoryComposition();
        this.ballThrowerComposition = new BallThrowerComposition();
        this.ballDetonatorComposition = new BallDetonatorComposition();
        this.otherComposition = new OtherComposition();
    }

    /**
     *
     * @param boardComposition
     * @param ballFactoryComposition
     * @param ballThrowerComposition
     * @param ballDetonatorComposition
     * @param otherComposition
     */
    public GameComposition(
            BoardComposition boardComposition,
            BallFactoryComposition ballFactoryComposition,
            BallThrowerComposition ballThrowerComposition,
            BallDetonatorComposition ballDetonatorComposition,
            OtherComposition otherComposition
    ) {
        this.boardComposition = boardComposition;
        this.ballFactoryComposition = ballFactoryComposition;
        this.ballThrowerComposition = ballThrowerComposition;
        this.ballDetonatorComposition = ballDetonatorComposition;
        this.otherComposition = new OtherComposition();
    }

    private GameComposition(GameComposition gameComposition) {
        this.setBoardComposition(gameComposition.getBoardComposition().getCopy());
        this.setBallFactoryComposition(gameComposition.getBallFactoryComposition().getCopy());
        this.setBallThrowerComposition(gameComposition.getBallThrowerComposition().getCopy());
        this.setBallDetonatorComposition(gameComposition.getBallDetonatorComposition().getCopy());
        this.setOtherComposition(gameComposition.getOtherComposition().getCopy());
    }

    /**
     *
     * @param gameCompositionJsonObject
     */
    public void updateByJsonObject(JsonObject gameCompositionJsonObject) {
        JsonObject boardCompositionJsonObject = gameCompositionJsonObject.getObject("board composition");
        JsonObject ballFactoryCompositionJsonObject = gameCompositionJsonObject.getObject("ball factory composition");
        JsonObject ballThrowerCompositionJsonObject = gameCompositionJsonObject.getObject("ball thrower composition");
        JsonObject ballDetonatorCompositionJsonObject = gameCompositionJsonObject.getObject("ball detonator composition");
        JsonObject otherCompositionJsonObject = gameCompositionJsonObject.getObject("other composition");

        this.boardComposition = new BoardComposition(boardCompositionJsonObject);
        this.ballFactoryComposition = new BallFactoryComposition(ballFactoryCompositionJsonObject);
        this.ballThrowerComposition = new BallThrowerComposition(ballThrowerCompositionJsonObject);
        this.ballDetonatorComposition = new BallDetonatorComposition(ballDetonatorCompositionJsonObject);
        this.otherComposition = new OtherComposition(otherCompositionJsonObject);
    }

    /**
     *
     * @return
     */
    @Override
    public GameComposition getCopy() {
        return new GameComposition(this);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isThisObjectValid() {
        boolean objectIsValid = true;

        ArrayList<LockableObject> list = (ArrayList<LockableObject>) this.getListOfLockableObjects();
        for (LockableObject element : list) {
            if (!element.isThisObjectValid()) {
                objectIsValid = false;
                break;
            }
        }
        return objectIsValid;
    }

    /**
     *
     */
    @Override
    public void setRandomValues() {
        ArrayList<LockableObject> list = (ArrayList<LockableObject>) this.getListOfLockableObjects();
        for (LockableObject element : list) {
            element.setRandomValues();
        }
    }

    /**
     *
     */
    @Override
    public void setDefaultValues() {
        ArrayList<LockableObject> list = (ArrayList<LockableObject>) this.getListOfLockableObjects();
        for (LockableObject element : list) {
            element.setDefaultValues();
        }
    }

    /**
     *
     * @param moduleForBoard
     */
    public void setBoardComposition(BoardComposition moduleForBoard) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.boardComposition = moduleForBoard;
    }

    /**
     *
     * @return
     */
    public BoardComposition getBoardComposition() {
        return this.boardComposition;
    }

    /**
     *
     * @param moduleForFactoryProducingBalls
     */
    public void setBallFactoryComposition(BallFactoryComposition moduleForFactoryProducingBalls) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.ballFactoryComposition = moduleForFactoryProducingBalls;
    }

    /**
     *
     * @return
     */
    public BallFactoryComposition getBallFactoryComposition() {
        return this.ballFactoryComposition;
    }

    /**
     *
     * @param moduleForBallThrower
     */
    public void setBallThrowerComposition(BallThrowerComposition moduleForBallThrower) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.ballThrowerComposition = moduleForBallThrower;
    }

    /**
     *
     * @return
     */
    public BallThrowerComposition getBallThrowerComposition() {
        return this.ballThrowerComposition;
    }

    /**
     *
     * @param moduleForBallDetonator
     */
    public void setBallDetonatorComposition(BallDetonatorComposition moduleForBallDetonator) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.ballDetonatorComposition = moduleForBallDetonator;
    }

    /**
     *
     * @return
     */
    public BallDetonatorComposition getBallDetonatorComposition() {
        return this.ballDetonatorComposition;
    }

    /**
     *
     * @param moduleOther
     */
    public void setOtherComposition(OtherComposition moduleOther) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.otherComposition = moduleOther;
    }

    /**
     *
     * @return
     */
    public OtherComposition getOtherComposition() {
        return this.otherComposition;
    }

    @Override
    public List getListOfLockableObjects() {
        ArrayList<LockableObject> list = new ArrayList<>();
        list.add(this.boardComposition);
        list.add(this.ballFactoryComposition);
        list.add(this.ballThrowerComposition);
        list.add(this.ballDetonatorComposition);
        list.add(this.otherComposition);
        return list;
    }

    /**
     *
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject gameCompositionJsonObject = new JsonObject();

        JsonObject boardCompositionJsonObject = this.boardComposition.toJsonObject();
        JsonObject ballFactoryCompositionJsonObject = this.ballFactoryComposition.toJsonObject();
        JsonObject ballThrowerCompositionJsonObject = this.ballThrowerComposition.toJsonObject();
        JsonObject ballDetonatorCompositionJsonObject = this.ballDetonatorComposition.toJsonObject();
        JsonObject otherCompositionJsonObject = this.otherComposition.toJsonObject();

        gameCompositionJsonObject.addObject("board composition", boardCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball factory composition", ballFactoryCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball thrower composition", ballThrowerCompositionJsonObject);
        gameCompositionJsonObject.addObject("ball detonator composition", ballDetonatorCompositionJsonObject);
        gameCompositionJsonObject.addObject("other composition", otherCompositionJsonObject);

        return gameCompositionJsonObject;
    }
}
