
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

package org.nanoboot.colorshapes.desktop.domain;

import org.nanoboot.powerframework.database.ResultOfSqlQuery;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class LanguageTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private LanguageTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @return
     */
    public static ResultOfSqlQuery getLanguages() {
        return databaseConnection.executeAndReturn("SELECT id,language_name_original FROM LANGUAGE;");
    }

    /**
     *
     * @param languageName
     * @return
     */
    public static int getIdFromLanguageName(String languageName) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT id FROM LANGUAGE WHERE language_name_original='" + languageName + "'");
        resultOfSqlQuery.moveToTheNextRow();
        return Integer.parseInt(resultOfSqlQuery.getString("id"));
    }

    /**
     *
     * @param languageID
     * @return
     */
    public static String getLanguageNameFromLanguageId(int languageID) {
        ResultOfSqlQuery resultOfSqlQuery = databaseConnection.executeAndReturn("SELECT language_name_original FROM LANGUAGE WHERE ID=" + languageID);
        resultOfSqlQuery.moveToTheNextRow();
        return resultOfSqlQuery.getString("language_name_original");
    }
}
