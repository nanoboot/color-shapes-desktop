
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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class Board extends GridPane {

    private ColumnConstraints columnConstraints;
    private RowConstraints rowConstraints;
    private int height;
    private int width;

    private Field field[][];

    private boolean heightIsBiggerThanWidth, widthIsBiggerThanHeight, widthIsTheSameAsHeight;

    private BoardContainer boardContainer;
    private Label label = new Label();

    /**
     *
     * @param height
     * @param width
     * @param boardContainer
     */
    public Board(int height, int width, BoardContainer boardContainer) {
        super();
        field = new Field[height + 1][width + 1];
        this.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.setOnMouseClicked(e -> this.wasClicked(e));

        this.height = height;
        this.width = width;
        this.boardContainer = boardContainer;

        //this.setGridLinesVisible(true);
        label = new Label("čau");

        this.add(label, 0, 0);

        this.setStyle("-fx-background-color:rgb(128,128,128);");
        //this.setMinSize(400, 400);
        this.columnConstraints = new ColumnConstraints();
        this.rowConstraints = new RowConstraints();

        ColumnConstraints zeroColumnConstraints = new ColumnConstraints();
        zeroColumnConstraints.setPercentWidth(0);
        this.getColumnConstraints().add(zeroColumnConstraints);

        RowConstraints zeroRowConstraints = new RowConstraints();
        zeroRowConstraints.setPercentHeight(0);
        this.getRowConstraints().add(zeroRowConstraints);

        double d1 = 100d / (double) width;
        double d2 = 100d / (double) height;
        double minValue = d1 < d2 ? d1 : d2;

        this.columnConstraints.setPercentWidth(minValue);
        this.rowConstraints.setPercentHeight(minValue);

        for (int i = 1; i <= width; i++) {
            this.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 1; i <= height; i++) {
            this.getRowConstraints().add(rowConstraints);
        }

        for (int h = 1; h <= height; h++) {
            for (int w = 1; w <= width; w++) {
                this.field[h][w] = new Field(h, w);
                Field tempField = this.field[h][w];
                this.add(tempField, w, h);

//                Pane pane=new Pane();
//                pane.setStyle("-fx-background-color: rgb(192,192,192);");
//                this.add(pane, width, width);
//                this.setFillHeight(tempField, true);
//                this.setFillHeight(tempField, true);
            }
        }

        widthIsTheSameAsHeight = false;
        heightIsBiggerThanWidth = false;
        widthIsBiggerThanHeight = false;

        if (height == width) {
            widthIsTheSameAsHeight = true;
            heightIsBiggerThanWidth = false;
            widthIsBiggerThanHeight = false;
        } else if (height > width) {
            widthIsTheSameAsHeight = false;
            heightIsBiggerThanWidth = true;
            widthIsBiggerThanHeight = false;

        } else if (height < width) {
            widthIsTheSameAsHeight = false;
            heightIsBiggerThanWidth = false;
            widthIsBiggerThanHeight = true;

        }
//this.setMaxWidth(300);
//this.setMaxHeight(300);
    }

    private int getBiggerSide() {
        if (this.widthIsTheSameAsHeight) {
            return this.height;
        }
        if (this.heightIsBiggerThanWidth) {
            return this.height;
        }
        if (this.widthIsBiggerThanHeight) {
            return this.width;
        }
        throw new RuntimeException();
    }

    /**
     *
     */
    public void updateSize() {
        double w, h;
        w = boardContainer.getWidth();
        h = boardContainer.getHeight();
        double k;
        if (w < h) {
            k = w;
        } else {
            k = h;
        }
        k = k * 0.95;
//        this.setMinSize(k, k);
//        this.setMaxSize(k, k);
        if (w < h) {
            this.setMaxSize(k, k);
        }
        if (w > h) {
            this.setMaxSize(k, k);
        }

        double space = ((((double) this.getWidth() / (width - 1)) / 24) + (((double) this.getHeight() / (height - 1)) / 24)) / 2;

        this.setPadding(new Insets(0d));
    }

    private void wasClicked(MouseEvent e) {
        this.updateSize();
    }

    /**
     *
     * @return
     */
    public int getHeightCount() {
        return height;
    }

    /**
     *
     * @return
     */
    public int getWidthCount() {
        return width;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Field getField(int row, int column) {
        if (row > height) {
            throw new ColorShapesRuntimeException("Row is bigger than height.");
        }
        if (column > width) {
            throw new ColorShapesRuntimeException("Column is bigger than width.");
        }
        return this.field[row][column];
    }

    /**
     *
     * @param row1
     * @param column1
     * @param row2
     * @param column2
     */
    public void moveFromTo(int row1, int column1, int row2, int column2) {
        Field field1 = this.getField(row1, column1);
        Field field2 = this.getField(row2, column2);
        if (!field1.isEmpty()) {

            Rollable rollable = field1.removeRollable();

            field2.insertRollable(rollable);
        } else {
            throw new ColorShapesRuntimeException("There is nothing in the first field");
        }
    }
}
