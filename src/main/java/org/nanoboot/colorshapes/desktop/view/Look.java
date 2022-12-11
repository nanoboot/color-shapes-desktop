
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

package org.nanoboot.colorshapes.desktop.view;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Look {

    private static String ballLighting;
    private static boolean showLineAroundBall;
    private static boolean showWhereABallCanBeMoved;
    private static boolean hightlightFieldsAfterBallExploded;
    private static String ballMoveEffect;

    /**
     *
     * @return
     */
    public static String getBallLighting() {
        return ballLighting;
    }

    /**
     *
     * @param ballLighting
     */
    public static void setBallLighting(String ballLighting) {
        Look.ballLighting = ballLighting;
    }

    /**
     *
     * @return
     */
    public static boolean getShowLineAroundBall() {
        return showLineAroundBall;
    }

    /**
     *
     * @param showLineAroundBall
     */
    public static void setShowLineAroundBall(boolean showLineAroundBall) {
        Look.showLineAroundBall = showLineAroundBall;
    }

    /**
     *
     * @return
     */
    public static boolean getShowWhereABallCanBeMoved() {
        return showWhereABallCanBeMoved;
    }

    /**
     *
     * @param showWhereABallCanBeMoved
     */
    public static void setShowWhereABallCanBeMoved(boolean showWhereABallCanBeMoved) {
        Look.showWhereABallCanBeMoved = showWhereABallCanBeMoved;
    }

    /**
     *
     * @return
     */
    public static boolean getHightlightFieldsAfterBallExploded() {
        return hightlightFieldsAfterBallExploded;
    }

    /**
     *
     * @param hightlightFieldsAfterBallExploded
     */
    public static void setHightlightFieldsAfterBallExploded(boolean hightlightFieldsAfterBallExploded) {
        Look.hightlightFieldsAfterBallExploded = hightlightFieldsAfterBallExploded;
    }

    /**
     *
     * @return
     */
    public static String getBallMoveEffect() {
        return ballMoveEffect;
    }

    /**
     *
     * @param ballMoveEffect
     */
    public static void setBallMoveEffect(String ballMoveEffect) {
        Look.ballMoveEffect = ballMoveEffect;
    }

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private Look() {
        // Not meant to be instantiated.
    }
}
