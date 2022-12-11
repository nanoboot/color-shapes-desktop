
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

package org.nanoboot.colorshapes.desktop.view.windows.gamingwindow;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Bomb extends StackPane implements Rollable {

    private final Circle circle;
    private final Rectangle rectangle;
    private final Circle button;
    private final boolean automatic;

    private ParallelTransition jumpParallelTransition;
    private StackPane parent;
    private boolean exploded;

    /**
     *
     * @param parent
     * @param automatic
     */
    public Bomb(StackPane parent, boolean automatic) {
        this.parent = parent;
        this.automatic = automatic;

        this.minHeightProperty().bind(parent.heightProperty().multiply(0.9));
        this.minWidthProperty().bind(parent.widthProperty().multiply(0.9));
        this.maxHeightProperty().bind(parent.heightProperty().multiply(0.9));
        this.maxWidthProperty().bind(parent.widthProperty().multiply(0.9));

        this.circle = new Circle();
        circle.setFill(Color.BLACK);

        circle.radiusProperty().bind(this.minHeightProperty().divide(2.5));

        circle.minHeight(0);
        circle.minWidth(0);
        this.getChildren().add(circle);
        StackPane.setAlignment(circle, Pos.BOTTOM_CENTER);

        this.rectangle = new Rectangle();
        this.rectangle.widthProperty().bind(this.minWidthProperty().divide(4));
        this.rectangle.heightProperty().bind(this.minHeightProperty().divide(4));
        this.rectangle.xProperty().bind(this.minWidthProperty().divide(4));
        this.rectangle.setY(0);
        StackPane.setAlignment(rectangle, Pos.TOP_CENTER);
        this.rectangle.setFill(Color.BLACK);
        this.getChildren().add(rectangle);
        if (automatic) {
            button = null;
        } else {
            button = new Circle();
            button.setFill(Color.RED);
            button.radiusProperty().bind(this.minHeightProperty().divide(6));
            this.getChildren().add(button);
        }
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
        star.scaleXProperty().bind(this.heightProperty().divide(150));
        star.scaleYProperty().bind(this.widthProperty().divide(150));
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
        sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                parent.getChildren().clear();
                parent.setId("null");
            }
        });
    }

    public boolean isAutomatic() {
        return automatic;
    }

    /**
     *
     * @param value
     */
    public void jump(boolean value) {
        if (this.automatic) {
            return;
        }
        if (value) {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300));
            scaleTransition.setFromX(1);
            scaleTransition.setToX(0.8);
            scaleTransition.setFromY(0.8);
            scaleTransition.setToY(1);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300));

            translateTransition.fromYProperty().bind(this.maxWidthProperty().multiply(0.1));
            translateTransition.toYProperty().bind(this.maxHeightProperty().multiply(-0.1));

            jumpParallelTransition = new ParallelTransition();
            jumpParallelTransition.getChildren().addAll(scaleTransition, translateTransition);
            jumpParallelTransition.setNode(this);
            jumpParallelTransition.setCycleCount(PathTransition.INDEFINITE);
            jumpParallelTransition.setAutoReverse(true);
            jumpParallelTransition.play();
        } else {
            jumpParallelTransition.stop();
            this.setScaleX(1);
            this.setScaleY(1);
            Polygon p = new Polygon();

        }
    }

    @Override
    public boolean isBall() {
        return false;
    }

    @Override
    public boolean isBomb() {
        return true;
    }

    @Override
    public void inflate() {
        // Bomb can't be inflated. This method of interface Rollable does nothing.
    }

    @Override
    public boolean exploded() {
        return this.exploded;
    }
}
