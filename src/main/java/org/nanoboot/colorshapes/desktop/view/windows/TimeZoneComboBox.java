
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

package org.nanoboot.colorshapes.desktop.view.windows;

import java.util.List;
import org.nanoboot.powerframework.simplicity.window.controls.SComboBox;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class TimeZoneComboBox extends SComboBox {

    /**
     *
     */
    public TimeZoneComboBox() {
        List<String> timeZoneList = org.nanoboot.powerframework.datetime.TimeZone.getListOfTimeZoneIDs();

        for (String element : timeZoneList) {
            getItems().add(element);
        }
        setValue("Universal");
    }
}
