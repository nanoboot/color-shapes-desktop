
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

package org.nanoboot.colorshapes.desktop.window;

import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.view.window.Window;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public abstract class ColorShapesAbstractWindow extends Window {
    public static final String CLS_VIEW_IMAGES_LOGO_PNG = "images/logo.png";
    protected ResourcePack resourcePack;

    /**
     *
     */
    public ColorShapesAbstractWindow(Object... args) {
        super(args);
        //todo
        this.setIcon(getTitleIconPath());
    }
    /**
     * @return
     */
    private String getTitleIconPath() {
        return CLS_VIEW_IMAGES_LOGO_PNG;
    }
    public String getResult() {
        //todo
        return "";
    }
    public void setWindowTitle(String title) {
        super.setWindowTitle("Color Shapes - " + title);
    }
}
