
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

package org.nanoboot.colorshapes.desktop.localisation.impl;

import lombok.Getter;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;

import java.util.HashMap;
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ResourcePackImpl implements ResourcePack {
    /**
     * language code.
     */
    @Getter
    private String lang;
    /**
     * Internal map.
     */
    private final HashMap<String, String> map;

    /**
     * Constructor.
     * @param langIn language code
     * @param mapIn input map
     */
    public ResourcePackImpl(final String langIn,
                            final HashMap<String, String> mapIn) {
        this.lang = langIn;
        this.map = mapIn;
    }

    @Override
    public String getLanguageCode() {
        return null;
    }

    @Override
    public String getOriginalLanguageName() {
        return null;
    }

    @Override
    public String getEnglishLanguageName() {
        return null;
    }

    @Override
    public final String getText(final String key) {
        return map.get(key);
    }

    @Override
    public final String getTextOrDefault(final String key,
                                         final String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public final void setText(final String key, final String value) {
        map.replace(key, value);
    }

    @Override
    public final void addText(final String key, final String value) {
        map.put(key, value);
    }

    @Override
    public void removeText(String key) {
        map.remove(key);
    }

}
