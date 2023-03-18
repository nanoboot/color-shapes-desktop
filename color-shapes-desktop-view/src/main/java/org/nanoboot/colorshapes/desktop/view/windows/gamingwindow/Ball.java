
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

package org.nanoboot.colorshapes.desktop.view.windows.gamingwindow;

import org.nanoboot.colorshapes.desktop.view.ColorShapesDesktopException;
import org.nanoboot.colorshapes.desktop.view.GameLook;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import static javafx.scene.paint.CycleMethod.NO_CYCLE;

import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class Ball extends StackPane implements Rollable {

    private static final Color[][] colourArray = new Color[2][17];

    static {
        colourArray[0][1] = Color.rgb(142, 255, 142);
        colourArray[1][1] = Color.rgb(0, 255, 0);
        colourArray[0][2] = Color.rgb(255, 105, 105);
        colourArray[1][2] = Color.rgb(255, 71, 71);
        colourArray[0][3] = Color.rgb(43, 128, 213);
        colourArray[1][3] = Color.rgb(0, 70, 255);
        colourArray[0][4] = Color.rgb(255, 255, 164);
        colourArray[1][4] = Color.rgb(244, 244, 0);
        colourArray[0][5] = Color.rgb(98, 255, 255);
        colourArray[1][5] = Color.rgb(0, 225, 225);
        colourArray[0][6] = Color.rgb(178, 113, 178);
        colourArray[1][6] = Color.rgb(139, 73, 139);
        colourArray[0][7] = Color.rgb(133, 84, 57);
        colourArray[1][7] = Color.rgb(107, 37, 4);
        colourArray[0][8] = Color.rgb(255, 218, 236);
        colourArray[1][8] = Color.rgb(255, 171, 213);
        colourArray[0][9] = Color.rgb(0, 204, 0);
        colourArray[1][9] = Color.rgb(0, 153, 0);
        colourArray[0][10] = Color.rgb(255, 228, 119);
        colourArray[1][10] = Color.rgb(255, 185, 0);
        colourArray[0][11] = Color.rgb(253, 154, 73);
        colourArray[1][11] = Color.rgb(244, 128, 32);
        colourArray[0][12] = Color.rgb(255, 255, 255);
        colourArray[1][12] = Color.rgb(238, 238, 238);
        colourArray[0][13] = Color.rgb(204, 204, 204);
        colourArray[1][13] = Color.rgb(178, 178, 178);
        colourArray[0][14] = Color.rgb(32, 32, 32);
        colourArray[1][14] = Color.rgb(0, 0, 0);
        colourArray[0][15] = Color.rgb(111, 183, 255);
        colourArray[1][15] = Color.rgb(0, 128, 255);
        colourArray[0][16] = Color.rgb(108, 119, 46);
        colourArray[1][16] = Color.rgb(75, 83, 32);
    }

    private final Ellipse ellipse;
    private ParallelTransition jumpParallelTransition;
    private Text text;
    private Line rope1;
    private Line rope2;

    private StackPane parent;
    private int colour;
    private int value;
    private boolean unmoveable;
    private boolean unbreakable;
    private boolean exploded;

    /**
     * @param parent
     * @param colour
     * @param value
     * @param unmoveable
     * @param unbreakable
     */
    public Ball(StackPane parent, int colour, int value, boolean unmoveable, boolean unbreakable) {
        this.parent = parent;
        this.colour = colour;
        this.value = value;
        this.unmoveable = unmoveable;
        this.unbreakable = unbreakable;
        this.minHeightProperty().bind(parent.heightProperty().divide(2).multiply(0.85));
        this.minWidthProperty().bind(parent.widthProperty().divide(2).multiply(0.85));
        this.maxHeightProperty().bind(parent.heightProperty().divide(2).multiply(0.85));
        this.maxWidthProperty().bind(parent.widthProperty().divide(2).multiply(0.85));

        this.ellipse = new Ellipse();

        ellipse.radiusXProperty().bind(this.minHeightProperty());
        ellipse.radiusYProperty().bind(this.minWidthProperty());

        ellipse.minHeight(0);
        ellipse.minWidth(0);

        if (colour == 0)//rainbow colour
        {
            Stop[] stops = new Stop[]{new Stop(0, Color.RED),
                    new Stop(0.25, Color.GREEN),
                    new Stop(0.50, Color.BLUE),
                    new Stop(0.75, Color.ORANGE),
                    new Stop(1, Color.YELLOW)};
            LinearGradient linearGradient = new LinearGradient(0.5, 0.1, 0.5, 0.9, true, NO_CYCLE, stops);
            ellipse.setFill(linearGradient);
        } else {
            switch (GameLook.getBallLighting()) {
                case "none":
                    ellipse.setFill(colourArray[1][colour]);
                    break;
                case "above":
                    Stop[] stops = new Stop[]{new Stop(0, colourArray[0][colour]), new Stop(0.1, colourArray[0][colour]), new Stop(0.9, colourArray[1][colour]), new Stop(1, colourArray[1][colour])};
                    LinearGradient linearGradient = new LinearGradient(0.5, 0.1, 0.5, 0.9, true, NO_CYCLE, stops);
                    ellipse.setFill(linearGradient);
                    break;
                case "ahead":
                    Stop[] stops2 = new Stop[]{new Stop(0, colourArray[0][colour]), new Stop(1, colourArray[1][colour])};
                    RadialGradient radialGradient = new RadialGradient(30, 0, 0.3, 0.3, 0.5, true, NO_CYCLE, stops2);
                    ellipse.setFill(radialGradient);
                    break;
                default:
                    throw new ColorShapesDesktopException("No such ball lighting: " + GameLook.getBallLighting());
            }
        }

        if (GameLook.getShowLineAroundBall()) {
            ellipse.setStroke(Color.BLACK);
            ellipse.strokeWidthProperty().bind(ellipse.radiusXProperty().multiply(2).multiply(0.04));
        }

        if (unmoveable) {
            rope1 = new Line();
            rope1.setStartX(0);
            rope1.setStartY(0);
            rope1.endXProperty().bind(this.widthProperty().multiply(2));
            rope1.endYProperty().bind(this.heightProperty().multiply(2));
            rope1.setStroke(Color.BLACK);
            rope1.strokeWidthProperty().bind(ellipse.radiusXProperty().multiply(2).multiply(0.04));

            rope2 = new Line();
            rope2.startXProperty().bind(this.widthProperty().multiply(2));
            rope2.setStartY(0);
            rope2.setEndX(0);
            rope2.endYProperty().bind(this.heightProperty().multiply(2));
            rope2.setStroke(Color.BLACK);
            rope2.strokeWidthProperty().bind(ellipse.radiusXProperty().multiply(2).multiply(0.04));
            this.getChildren().addAll(rope1, rope2);
        }
        if (unbreakable) {
            ellipse.setStroke(Color.GREY);
            ellipse.strokeWidthProperty().bind(ellipse.radiusXProperty().multiply(2).multiply(0.1));
        }
        this.getChildren().add(ellipse);
        if (value != 1) {
            text = new Text(String.valueOf(value));
            if (colour == 0 || colour == 7) {
                text.setFill(Color.WHITE);
            }
            this.getChildren().add(text);
            DoubleProperty textSize = new SimpleDoubleProperty();

            textSize.bind(ellipse.radiusYProperty());
            ;
            text.styleProperty().bind(Bindings.concat("-fx-font-size: ", textSize.asString(), ";"));

        }
        ellipse.setScaleX(0.5);
        ellipse.setScaleY(0.5);
    }

    /**
     *
     */
    public void inflate() {
        ScaleTransition scaleTransformation = new ScaleTransition(Duration.millis(300), ellipse);
        scaleTransformation.setFromX(0.5);
        scaleTransformation.setToX(1);
        scaleTransformation.setFromY(0.5);
        scaleTransformation.setToY(1);
        scaleTransformation.play();
    }

    /**
     *
     */
    public void explode() {
        this.exploded = true;
        Polygon star = new Polygon(
                50, 0,
                61, 39,
                100, 39,
                70, 61,
                80, 100,
                50, 77,
                20, 100,
                30, 61,
                0, 39,
                39, 39,
                49, 0,
                50, 0);
        star.setFill(Color.ORANGE);
        star.scaleXProperty().bind(this.heightProperty().divide(100));
        star.scaleYProperty().bind(this.widthProperty().divide(100));
        this.getChildren().add(star);
        StackPane.setAlignment(star, Pos.CENTER);

        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.millis(300));
        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(400));
        fadeTransition1.setFromValue(1.0);
        fadeTransition1.setToValue(0.7);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(300));
        fadeTransition2.setFromValue(0.7);
        fadeTransition2.setToValue(0);
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.setNode(this);
        sequentialTransition.getChildren().addAll(pauseTransition, fadeTransition1, fadeTransition2);
        sequentialTransition.play();
    }

    /**
     * @param value
     */
    public void jump(boolean value) {
        if (value) {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
            scaleTransition.setFromX(1);
            scaleTransition.setToX(0.8);
            scaleTransition.setFromY(0.8);
            scaleTransition.setToY(1);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300));

            translateTransition.fromYProperty().bind(ellipse.radiusYProperty().multiply(0.1));
            translateTransition.toYProperty().bind(ellipse.radiusYProperty().multiply(-0.1));

            jumpParallelTransition = new ParallelTransition();
            jumpParallelTransition.getChildren().addAll(scaleTransition, translateTransition);
            jumpParallelTransition.setNode(ellipse);
            jumpParallelTransition.setCycleCount(PathTransition.INDEFINITE);
            jumpParallelTransition.setAutoReverse(true);
            jumpParallelTransition.play();
        } else {
            jumpParallelTransition.stop();
            ellipse.setScaleX(1);
            ellipse.setScaleY(1);

        }
    }

    @Override
    public boolean isBall() {
        return true;
    }

    @Override
    public boolean isBomb() {
        return false;
    }

    @Override
    public boolean exploded() {
        return this.exploded;
    }
}
