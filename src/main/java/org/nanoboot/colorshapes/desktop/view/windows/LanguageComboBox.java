
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

import org.nanoboot.colorshapes.desktop.ColorShapes;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.simplicity.window.controls.SComboBox;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class LanguageComboBox extends SComboBox {

    /**
     *
     */
    public LanguageComboBox() {
        ResultOfSqlQuery languages = ColorShapes.getServices().getLanguageService().getLanguages();
        while (languages.hasNextRow()) {
            languages.moveToTheNextRow();
            getItems().add(languages.getString("language_name_original"));
        }
        setDefault();
    }

    /**
     *
     * @return
     */
    public int getSelectedLanguageId() {
        return ColorShapes.getServices().getLanguageService().convertLanguageNameToLanguageId(getValue().toString());
    }

    /**
     *
     */
    public void setDefault() {
        int currentLanguageId = ColorShapes.getServices().getCurrentLookService().getLanguageId();
        setValue(ColorShapes.getServices().getLanguageService().convertLanguageIdToLanguageName(currentLanguageId));
    }
}
