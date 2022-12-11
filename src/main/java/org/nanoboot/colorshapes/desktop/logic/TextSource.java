
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

package org.nanoboot.colorshapes.desktop.logic;

import org.nanoboot.colorshapes.desktop.domain.LanguageTable;
import org.nanoboot.colorshapes.desktop.domain.TextConstantTable;
import org.nanoboot.powerframework.database.ResultOfSqlQuery;
import org.nanoboot.powerframework.json.JsonObject;

/**
 *
 * @author Robert Vokáč robertvokac@nanoboot.orgt Vokáč robertvokac@nanoboot.org
 */
public class TextSource {

    private final Logic logic;
    private int languageId;

    /**
     *
     * @param logic
     */
    public TextSource(Logic logic) {
        this.logic = logic;
        this.languageId = logic.getApplication().getApplicationLook().getLanguageId();
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     *
     * @param type
     * @param languageID
     * @return
     */
    public String getTextConstant(int type) {
        return TextConstantTable.getTextConstant(type, languageId);
    }

    /**
     *
     * @return
     */
    public ResultOfSqlQuery getLanguages() {
        return LanguageTable.getLanguages();
    }

    /**
     *
     * @param language
     * @return
     */
    public int convertLanguageNameToLanguageId(String language) {
        return LanguageTable.getIdFromLanguageName(language);
    }

    /**
     *
     * @param languageID
     * @return
     */
    public String convertLanguageIdToLanguageName(int languageId) {
        return LanguageTable.getLanguageNameFromLanguageId(languageId);
    }

    /**
     *
     * @return
     */
    public JsonObject createNewLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public JsonObject editLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public JsonObject updateLanguage() {
        throw new UnsupportedOperationException();
    }
}
