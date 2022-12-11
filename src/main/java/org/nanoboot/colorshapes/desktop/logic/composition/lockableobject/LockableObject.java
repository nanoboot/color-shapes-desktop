
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

package org.nanoboot.colorshapes.desktop.logic.composition.lockableobject;

import java.util.ArrayList;
import java.util.List;
import org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator;

/**
 * Represents an object able to lock to prevent changes. If an object is locked,
 * this object can't be unlocked anymore.
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public abstract class LockableObject {

    private boolean locked = false;

    /**
     * Constructor
     */
    public LockableObject() {
    }

    /**
     * Locks this object.
     */
    public void lockThisObject() {
        this.throwUnsupportedOperationExceptionIfThisObjectIsNotValid();
        lockAllLockableObjectsOfThisObject();
        this.locked = true;
    }

    /**
     * This abstract method is used to execute code after this object was locked
     */
    private void lockAllLockableObjectsOfThisObject() {
        ArrayList<LockableObject> list = (ArrayList<LockableObject>) this.getListOfLockableObjects();
        for (LockableObject element : list) {
            element.lockThisObject();
        }
    }

    /**
     *
     * @return
     */
    public boolean isThisObjectLocked() {
        return this.locked;
    }

    /**
     * Throws UnsupportedOperationException, if this object is locked.
     */
    public void throwUnsupportedOperationExceptionIfThisObjectIsLocked() {
        if (this.isThisObjectLocked()) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Throws UnsupportedOperationException, if this object is not valid.
     */
    public void throwUnsupportedOperationExceptionIfThisObjectIsNotValid() {
        if (!this.isThisObjectValid()) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     *
     * @return
     */
    public int getRandomFrequency() {
        if (PseudoRandomGenerator.getInstance().getBoolean()) {
            return PseudoRandomGenerator.getInstance().getInt(1, 16);
        } else {
            return 0;
        }
    }

    /**
     *
     * @return
     */
    public int getNotNullRandomFrequency() {
        return PseudoRandomGenerator.getInstance().getInt(40, 100);
    }

    /**
     *
     * @return
     */
    public int getRandomProbability() {
        if (PseudoRandomGenerator.getInstance().getBoolean()) {
            return PseudoRandomGenerator.getInstance().getInt(0, 100);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param probability
     * @return
     */
    public boolean isProbabilityValid(int probability) {
        return (probability >= 0) && (probability <= 100);
    }

    /**
     *
     * @return
     */
    public abstract boolean isThisObjectValid();

    /**
     * Sets default values.
     */
    public abstract void setDefaultValues();

    /**
     * Sets random values.
     */
    public abstract void setRandomValues();

    /**
     *
     * @return copy of the object
     */
    public abstract Object getCopy();

    /**
     *
     * @return
     */
    public abstract List getListOfLockableObjects();
}
