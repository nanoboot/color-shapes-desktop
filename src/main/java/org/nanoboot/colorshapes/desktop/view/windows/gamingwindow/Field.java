
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

import org.nanoboot.colorshapes.desktop.ColorShapesRuntimeException;
import org.nanoboot.colorshapes.desktop.ColorShapes;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import org.nanoboot.powerframework.simplicity.Simplicity;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Field extends StackPane {

    private final int row;
    private final int column;
    private final GridPane fieldInside;
    private final StackPane gridPlace;
    private Grid grid;

    private final StackPane wallTop;
    private final StackPane wallTopRight;
    private final StackPane wallRight;
    private final StackPane wallBottomRight;
    private final StackPane wallBottom;
    private final StackPane wallBottomLeft;
    private final StackPane wallLeft;
    private final StackPane wallTopLeft;

    private final StackPane inside;

    private boolean wallTopBoolean;
    private boolean wallRightBoolean;
    private boolean wallBottomBoolean;
    private boolean wallLeftBoolean;

    private Rollable rollable;

    private boolean ignoreClickActions = false;

    /**
     *
     * @param row
     * @param column
     */
    public Field(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        grid = null;
        wallTopBoolean = false;
        wallRightBoolean = false;
        wallBottomBoolean = false;
        wallLeftBoolean = false;

        rollable = null;

        this.setStyle("-fx-background-color:rgb(192,192,192);-fx-border-color:rgb(128,128,128);-fx-border-width:" + Double.toString(Simplicity.getDpmm() / 4) + ";");
        setOnMouseClicked(event -> this.handleClickedAction(event));

        fieldInside = new GridPane();
        ColumnConstraints wallColumnConstraint = new ColumnConstraints();
        wallColumnConstraint.setPercentWidth(5);
        ColumnConstraints insideColumnConstraint = new ColumnConstraints();
        insideColumnConstraint.setPercentWidth(90);

        RowConstraints wallRowConstraint = new RowConstraints();
        wallRowConstraint.setPercentHeight(5);
        RowConstraints insideRowConstraint = new RowConstraints();
        insideRowConstraint.setPercentHeight(90);

        fieldInside.getColumnConstraints().addAll(wallColumnConstraint, insideColumnConstraint, wallColumnConstraint);
        fieldInside.getRowConstraints().addAll(wallRowConstraint, insideRowConstraint, wallRowConstraint);

        wallTop = new StackPane();
        wallTopRight = new StackPane();
        wallRight = new StackPane();
        wallBottomRight = new StackPane();
        wallBottom = new StackPane();
        wallBottomLeft = new StackPane();
        wallLeft = new StackPane();
        wallTopLeft = new StackPane();
        inside = new StackPane();

        fieldInside.addRow(0, wallTopLeft, wallTop, wallTopRight);
        fieldInside.addRow(1, wallLeft, inside, wallRight);
        fieldInside.addRow(2, wallBottomLeft, wallBottom, wallBottomRight);

        fieldInside.setMinSize(0, 0);
        this.setMinSize(0, 0);

        gridPlace = new StackPane();
        //this.minWidthProperty().bind(this.heightProperty());
        this.getChildren().addAll(fieldInside, gridPlace);

    }

    /**
     *
     * @param event
     */
    public void handleClickedAction(MouseEvent event) {
        if (!ignoreClickActions) {
            ColorShapes.getServices().getGameService().activateField(row, column);
        }
    }

    /**
     *
     * @param value
     */
    public void setIgnoreClickActions(boolean value) {
        this.ignoreClickActions = value;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @param value
     */
    public void setGrid(boolean value) {
        if (value) {
            if (grid != null) {
                throw new ColorShapesRuntimeException("The field has already grid.");
            }
            grid = new Grid();
            gridPlace.getChildren().add(grid);
        } else {
            if (grid == null) {
                throw new ColorShapesRuntimeException("The field has no grid.");
            }
            gridPlace.getChildren().remove(grid);
            grid = null;
        }
    }

    /**
     *
     * @param value
     */
    public void setTopWall(boolean value) {
        this.wallTopBoolean = value;
        if (value) {
            if (wallTopLeft.getChildren().isEmpty()) {
                wallTopLeft.getChildren().add(new Wall());
            }
            if (wallTop.getChildren().isEmpty()) {
                wallTop.getChildren().add(new Wall());
            }
            if (wallTopRight.getChildren().isEmpty()) {
                wallTopRight.getChildren().add(new Wall());
            }
        } else {
            if (!this.wallLeftBoolean) {
                wallTopLeft.getChildren().clear();
            }
            wallTop.getChildren().clear();
            if (!this.wallRightBoolean) {
                wallTopRight.getChildren().clear();
            }
        }
    }

    /**
     *
     * @param value
     */
    public void setRightWall(boolean value) {
        this.wallRightBoolean = value;
        if (value) {
            if (wallTopRight.getChildren().isEmpty()) {
                wallTopRight.getChildren().add(new Wall());
            }
            if (wallRight.getChildren().isEmpty()) {
                wallRight.getChildren().add(new Wall());
            }
            if (wallBottomRight.getChildren().isEmpty()) {
                wallBottomRight.getChildren().add(new Wall());
            }
        } else {
            if (!this.wallTopBoolean) {
                wallTopRight.getChildren().clear();
            }
            wallRight.getChildren().clear();
            if (!this.wallBottomBoolean) {
                wallBottomRight.getChildren().clear();
            }
        }
    }

    /**
     *
     * @param value
     */
    public void setBottomWall(boolean value) {
        this.wallBottomBoolean = value;
        if (value) {
            if (wallBottomLeft.getChildren().isEmpty()) {
                wallBottomLeft.getChildren().add(new Wall());
            }
            if (wallBottom.getChildren().isEmpty()) {
                wallBottom.getChildren().add(new Wall());
            }
            if (wallBottomRight.getChildren().isEmpty()) {
                wallBottomRight.getChildren().add(new Wall());
            }
        } else {
            if (!this.wallLeftBoolean) {
                wallBottomLeft.getChildren().clear();
            }
            wallBottom.getChildren().clear();
            if (!this.wallRightBoolean) {
                wallBottomRight.getChildren().clear();
            }
        }
    }

    /**
     *
     * @param value
     */
    public void setLeftWall(boolean value) {
        this.wallLeftBoolean = value;
        if (value) {
            if (wallTopLeft.getChildren().isEmpty()) {
                wallTopLeft.getChildren().add(new Wall());
            }
            if (wallLeft.getChildren().isEmpty()) {
                wallLeft.getChildren().add(new Wall());
            }
            if (wallBottomLeft.getChildren().isEmpty()) {
                wallBottomLeft.getChildren().add(new Wall());
            }
        } else {
            if (!this.wallTopBoolean) {
                wallTopLeft.getChildren().clear();
            }
            wallLeft.getChildren().clear();
            if (!this.wallBottomBoolean) {
                wallBottomLeft.getChildren().clear();
            }
        }
    }

    /**
     *
     */
    public void highlightExplosion() {
        this.setStyle("-fx-background-color:rgb(255,128,128);-fx-border-color:rgb(128,128,128);-fx-border-width:" + Double.toString(Simplicity.getDpmm() / 4) + ";");
    }

    /**
     *
     */
    public void highlightOff() {
        this.setStyle("-fx-background-color:rgb(192,192,192);-fx-border-color:rgb(128,128,128);-fx-border-width:" + Double.toString(Simplicity.getDpmm() / 4) + ";");
    }

    /**
     *
     */
    public void highlightMovement() {
        this.setStyle("-fx-background-color:rgb(169,169,169);-fx-border-color:rgb(128,128,128);-fx-border-width:" + Double.toString(Simplicity.getDpmm() / 4) + ";");
    }

    /**
     *
     * @return
     */
    public StackPane getParentForRollable() {
        return this.inside;
    }

    /**
     *
     * @param ball
     */
    public void insertRollable(Rollable rollable) {
        this.rollable = rollable;
        this.inside.getChildren().clear();
        if (rollable instanceof Ball) {
            this.inside.getChildren().add((Ball) rollable);
        } else if (rollable instanceof Bomb) {
            this.inside.getChildren().add((Bomb) rollable);
        } else {
            throw new ColorShapesRuntimeException("Inserted rollable must be instance of Ball or Bomb");
        }

    }

    /**
     *
     * @return
     */
    public Rollable getRollable() {
        return this.rollable;
    }

    /**
     *
     */
    public Rollable removeRollable() {
        this.inside.getChildren().clear();
        Rollable tempRollable = this.rollable;
        this.rollable = null;
        return tempRollable;
    }

    public boolean isEmpty() {
        return this.inside.getChildren().isEmpty();
    }

    /**
     *
     * @return
     */
    public boolean hasBall() {
        if (isEmpty()) {
            return false;
        }
        return rollable instanceof Ball;
    }

    /**
     *
     * @return
     */
    public boolean hasBomb() {
        if (isEmpty()) {
            return false;
        }
        return rollable instanceof Bomb;
    }
}
