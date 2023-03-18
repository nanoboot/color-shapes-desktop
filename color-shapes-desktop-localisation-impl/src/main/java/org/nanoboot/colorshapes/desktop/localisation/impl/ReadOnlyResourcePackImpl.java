
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

import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ReadOnlyResourcePackImpl implements ResourcePack {
    private final ResourcePack resourcePack;

    public ReadOnlyResourcePackImpl(ResourcePack resourcePackIn) {
        this.resourcePack = resourcePackIn;
    }

    @Override
    public String getLanguageCode() {
        return resourcePack.getLanguageCode();
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
    public String getText(String key) {
        return resourcePack.getText(key);
    }

    @Override
    public String getTextOrDefault(String key, String defaultValue) {
        return resourcePack.getTextOrDefault(key, defaultValue);
    }

    @Override
    public final void setText(String key, String value) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public final void addText(String key, String value) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public void removeText(String s) {
        throw new RuntimeException("Unsupported operation");
    }
}
