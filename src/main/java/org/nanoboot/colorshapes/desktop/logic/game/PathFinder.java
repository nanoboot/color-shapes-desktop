
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
import org.nanoboot.colorshapes.desktop.logic.game.board.Field;
import java.util.ArrayList;
import org.nanoboot.powerframework.collections.Stack;

/**
 *
 * @author rober
 */
public class PathFinder {

    private final Stack<Field> pathFromStartToEndStack = new Stack<>();
    private final ArrayList<Field> visitedFields = new ArrayList<>();
    private final Stack<ParentNode<Field>> nodesBeforeExamined = new Stack<>();
    private final Stack<ParentNode<Field>> nodesAfterExamined = new Stack<>();
    private final ParentNode<Field> startNode;
    private ParentNode<Field> endNode;
    private boolean fieldToFound = false;
    private boolean fieldToNotFound = false;
    private final Field fieldFrom;
    private final Field fieldTo;
    private boolean pathWasSearched = false;
    private static final Direction[] directions = new Direction[]{Direction.TOP, Direction.RIGHT, Direction.BOTTOM, Direction.LEFT};

    /**
     *
     * @param fieldFrom
     * @param fieldTo
     */
    public PathFinder(Field fieldFrom, Field fieldTo) {
        startNode = new ParentNode<>(null, fieldFrom);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;

        nodesBeforeExamined.push(startNode);

        visitedFields.add(fieldFrom);

    }

    /**
     *
     * @return
     */
    public Stack<Field> findPath() {
        if (pathWasSearched) {
            throw new ColorShapesRuntimeException("This method can be used only once.");
        }
        pathWasSearched = true;

        while (!this.fieldToFound && !this.fieldToNotFound) {
            moveAllNodesFromBeforeToAfterExamindes();
            examineAllNodesInNodesAfterExamined();
        }

        if (fieldToFound) {
            fillPathFromStartToEndStack();
        }
        return fieldToFound ? this.pathFromStartToEndStack : null;
    }

    private void moveAllNodesFromBeforeToAfterExamindes() {
        nodesAfterExamined.clear();
        if (this.nodesBeforeExamined.isEmpty()) {
            fieldToNotFound = true;
        }
        for (ParentNode<Field> node : nodesBeforeExamined) {
            nodesAfterExamined.push(node);
        }
    }

    private void examineAllNodesInNodesAfterExamined() {
        for (ParentNode<Field> node : nodesAfterExamined) {
            examineNode(node);
        }
    }

    private void examineNode(ParentNode<Field> node) {
        final Field fieldToExamin = node.getValue();
        if (fieldToExamin.equals(this.fieldTo)) {
            this.endNode = node;
            this.fieldToFound = true;

            return;
        }
        for (Direction direction : directions) {
            if (!fieldToExamin.getWalls().isWallAtDirection(direction)) {

                Field fieldBeforeExaming = fieldToExamin.getFieldAtDirection(direction);

                if (fieldBeforeExaming == null || this.visitedFields.contains(fieldBeforeExaming)) {
                    continue;
                }

                boolean addFieldWithWallsToVisitedFields = true;

                if (fieldBeforeExaming.getWalls().hasAWall()) {
                    addFieldWithWallsToVisitedFields = false;
                }

                if (addFieldWithWallsToVisitedFields) {
                    this.visitedFields.add(fieldBeforeExaming);
                }

                if (fieldBeforeExaming.isEmpty() && !fieldBeforeExaming.isLocked() && !fieldBeforeExaming.getWalls().isWallAtDirection(direction.overTurn())) {
                    this.nodesBeforeExamined.push(node.createChild(fieldBeforeExaming));
                }

            }
        }
    }

    private void fillPathFromStartToEndStack() {

        if (this.endNode != null) {
            ParentNode<Field> tempNode = endNode;
            while (tempNode.getParent() != null) {
                this.pathFromStartToEndStack.push(tempNode.getValue());
                tempNode = tempNode.getParent();
            }
        }
    }
}
