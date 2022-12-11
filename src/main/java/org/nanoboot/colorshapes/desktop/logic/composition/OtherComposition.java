
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
public class OtherComposition extends LockableObject {

    private boolean allowedStepBack;
    private boolean freeMode;
    private boolean trainer;

    /**
     *
     * @param otherJsonObject
     */
    public OtherComposition(JsonObject otherJsonObject) {
        this.updateByJsonObject(otherJsonObject);
    }

    /**
     * Constructor
     */
    public OtherComposition() {

        this.setDefaultValues();
    }

    /**
     *
     * @param allowedStepBack
     * @param freeMode
     * @param trainer
     */
    public OtherComposition(boolean allowedStepBack, boolean freeMode, boolean trainer) {

        this.allowedStepBack = allowedStepBack;
        this.freeMode = freeMode;
        this.trainer = trainer;

    }

    /**
     *
     * @param otherJsonObject
     */
    public void updateByJsonObject(JsonObject otherJsonObject) {
        this.allowedStepBack = otherJsonObject.getBoolean("allowed step back");
        this.freeMode = otherJsonObject.getBoolean("free mode");
        this.trainer = otherJsonObject.getBoolean("trainer");
    }

    @Override
    public boolean isThisObjectValid() {
        return true;
    }

    @Override
    public void setDefaultValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.allowedStepBack = false;
        this.freeMode = false;
        this.trainer = false;

    }

    @Override
    public void setRandomValues() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
    }

    @Override
    public OtherComposition getCopy() {
        OtherComposition copy = new OtherComposition();
        copy.allowedStepBack = this.allowedStepBack;
        copy.freeMode = this.freeMode;
        copy.trainer = this.trainer;
        return copy;
    }

    /**
     *
     * @param allowedStepBack
     */
    public void setAllowedStepBack(boolean allowedStepBack) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.allowedStepBack = allowedStepBack;
    }

    /**
     *
     * @return
     */
    public boolean getAllowedStepBack() {
        return this.allowedStepBack;
    }

    /**
     *
     * @param freeMode
     */
    public void setFreeMode(boolean freeMode) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.freeMode = freeMode;
    }

    /**
     *
     * @return
     */
    public boolean getFreeMode() {
        return this.freeMode;
    }

    /**
     *
     * @param trainer
     */
    public void setTrainer(boolean trainer) {
        this.throwUnsupportedOperationExceptionIfThisObjectIsLocked();
        this.trainer = trainer;
    }

    /**
     *
     * @return
     */
    public boolean getTrainer() {
        return this.trainer;
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
        JsonObject otherCompositionJsonObject = new JsonObject();

        otherCompositionJsonObject.addBoolean("allowed step back", this.allowedStepBack);
        otherCompositionJsonObject.addBoolean("free mode", this.freeMode);
        otherCompositionJsonObject.addBoolean("trainer", this.trainer);
        return otherCompositionJsonObject;
    }
}
