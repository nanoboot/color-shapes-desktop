
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

package org.nanoboot.colorshapes.desktop.localisation;

import org.nanoboot.colorshapes.desktop.localisation.impl.DummyResourcePackImpl;
import org.nanoboot.colorshapes.engine.localisation.api.LocalisationService;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;

import java.util.ArrayList;
import java.util.List;
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class LocalisationServiceImpl implements LocalisationService {
    private static final List<String> codes = new ArrayList<>();
    static {
        codes.add("en");
//        codes.add("cz");
    }

    @Override
    public List<String> getAvailableLanguageCodes() {
        return codes;
    }

    @Override
    public ResourcePack getResourcePack(String s) {
        return DummyResourcePackImpl.getInstance();
    }

    @Override
    public void addResourcePack(ResourcePack resourcePack) {

    }

    @Override
    public void updateResourcePack(ResourcePack resourcePack) {

    }

    @Override
    public void removeResourcePack(ResourcePack resourcePack) {

    }
}
