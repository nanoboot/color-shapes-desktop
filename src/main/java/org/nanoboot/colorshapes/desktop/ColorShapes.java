
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

package org.nanoboot.colorshapes.desktop;

import org.nanoboot.colorshapes.desktop.data.Data;
import org.nanoboot.colorshapes.desktop.domain.Domain;
import org.nanoboot.colorshapes.desktop.logic.ChangeNotifier;
import org.nanoboot.colorshapes.desktop.logic.Logic;
import org.nanoboot.colorshapes.desktop.services.Services;
import org.nanoboot.colorshapes.desktop.view.View;

/**
 * This is the entry point for the application.
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class ColorShapes {

    private static View view;
    private static ChangeNotifier changeNotifier;
    private static Services services;
    private static Logic logic;
    private static Domain domain;
    private static Data data;

    private static final String ColorShapesVERSION = "0.0.0";

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private ColorShapes() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        build();
        start();
        System.exit(0);
    }

    /**
     * Creates application layers and connects them.
     */
    private static void build() {
        view = new View();
        changeNotifier = new ChangeNotifier(view);
        data = new Data();
        domain = new Domain(data);
        logic = new Logic(domain, changeNotifier);
        services = new Services(logic);
    }

    /**
     * Starts the View.
     */
    private static void start() {
        //All main game parts have been loaded. View has now the control.
        view.start();
    }

    /**
     *
     * @return
     */
    public static String getColorShapesVersion() {
        return ColorShapesVERSION;
    }

    public static View getView() {
        return view;
    }

    public static Logic getLogic() {
        return logic;
    }

    public static Services getServices() {
        return services;
    }
}
