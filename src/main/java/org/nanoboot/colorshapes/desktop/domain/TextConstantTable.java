
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

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class TextConstantTable extends Table {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private TextConstantTable() {
        //Not meant to be instantiated.
    }

    /**
     *
     * @param textConstantTypeId
     * @param languageId
     * @return text constant
     */
    public static String getTextConstant(int textConstantTypeId, int languageId) {
        String string = databaseConnection.getString("TEXT_CONSTANT", "VALUE", "language_id =" + languageId + " AND text_constant_type_id =" + textConstantTypeId);

        return string == null ? "Not found" : string;
    }
}
