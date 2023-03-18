
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: The desktop UI for on Color Lines Engine.
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

package org.nanoboot.colorshapes.desktop.view;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class GameLook {

    private static String ballLighting;
    private static boolean showLineAroundBall;
    private static boolean showWhereABallCanBeMoved;
    private static boolean hightlightCellsAfterBallExploded;
    private static String ballMoveEffect;

    /**
     * @return
     */
    public static String getBallLighting() {
        return ballLighting;
    }

    /**
     * @param ballLighting
     */
    public static void setBallLighting(String ballLighting) {
        GameLook.ballLighting = ballLighting;
    }

    /**
     * @return
     */
    public static boolean getShowLineAroundBall() {
        return showLineAroundBall;
    }

    /**
     * @param showLineAroundBall
     */
    public static void setShowLineAroundBall(boolean showLineAroundBall) {
        GameLook.showLineAroundBall = showLineAroundBall;
    }

    /**
     * @return
     */
    public static boolean getShowWhereABallCanBeMoved() {
        return showWhereABallCanBeMoved;
    }

    /**
     * @param showWhereABallCanBeMoved
     */
    public static void setShowWhereABallCanBeMoved(boolean showWhereABallCanBeMoved) {
        GameLook.showWhereABallCanBeMoved = showWhereABallCanBeMoved;
    }

    /**
     * @return
     */
    public static boolean getHightlightCellsAfterBallExploded() {
        return hightlightCellsAfterBallExploded;
    }

    /**
     * @param hightlightCellsAfterBallExploded
     */
    public static void setHightlightCellsAfterBallExploded(boolean hightlightCellsAfterBallExploded) {
        GameLook.hightlightCellsAfterBallExploded = hightlightCellsAfterBallExploded;
    }

    /**
     * @return
     */
    public static String getBallMoveEffect() {
        return ballMoveEffect;
    }

    /**
     * @param ballMoveEffect
     */
    public static void setBallMoveEffect(String ballMoveEffect) {
        GameLook.ballMoveEffect = ballMoveEffect;
    }

    /**
     * Constructor
     * <p>
     * Not meant to be instantiated.
     */
    private GameLook() {
        // Not meant to be instantiated.
    }
}
