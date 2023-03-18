
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

import org.nanoboot.powerframework.view.layouts.SLayout;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class BoardContainer extends SLayout {

    private static Board board;

    /**
     *
     */
    public BoardContainer() {
        super();
        //this.setStyle("-fx-background-color:orange;");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        RowConstraints rowConstraints = new RowConstraints();
        columnConstraints.setPercentWidth(100);
        rowConstraints.setPercentHeight(100);
        this.getColumnConstraints().addAll(columnConstraints);
        this.getRowConstraints().addAll(rowConstraints);
        this.setSpacing(false);
//this.setGridLinesVisible(true);
    }

    /**
     * @param board
     */
    public void setBoard(Board board) {
        BoardContainer.board = board;
        this.getChildren().clear();
//        HBox hBox=new HBox();
//        hBox.setAlignment(Pos.CENTER);
//        hBox.setStyle("-fx-background-color:green;-fx-border-color:blue;-fx-border-width:5;");
        this.add(board, 0, 0);
        BoardContainer.setValignment(board, VPos.CENTER);
        BoardContainer.setHalignment(board, HPos.CENTER);
        if (this.getChildren().contains(this.board)) {
        }
        board.toBack();
    }

    /**
     *
     */
    public void updateSize() {
        if ((Board) this.getNodeFromGridPane(this, 0, 0) != null) {
            ((Board) this.getNodeFromGridPane(this, 0, 0)).updateSize();
        }

    }

}
