
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

package org.nanoboot.colorshapes.desktop.view;

import org.nanoboot.colorshapes.desktop.view.windows.LoginWindow;
import org.nanoboot.colorshapes.engine.infrastructure.Engine;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ColorShapesDesktopRunner implements org.nanoboot.powerframework.view.ViewRunner {
    private final Engine engine;

    public ColorShapesDesktopRunner(Engine engine) {
        this.engine = engine;
    }

    public void runApp() {
        engine.start();
        LoginWindow loginWindow = new LoginWindow(engine);
        loginWindow.show();
    }
}
