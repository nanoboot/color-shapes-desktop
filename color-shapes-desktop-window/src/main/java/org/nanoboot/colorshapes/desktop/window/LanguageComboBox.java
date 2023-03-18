
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

package org.nanoboot.colorshapes.desktop.window;

import org.nanoboot.colorshapes.desktop.localisation.LocalisationServiceImpl;
import org.nanoboot.colorshapes.engine.core.misc.ColorShapesEngineException;
import org.nanoboot.colorshapes.engine.localisation.api.LocalisationService;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.colorshapes.engine.services.api.RepositoryService;
import org.nanoboot.powerframework.db.manager.Table;
import org.nanoboot.powerframework.view.window.controls.SComboBox;

import java.util.List;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class LanguageComboBox extends SComboBox {

    protected ResourcePack resourcePack;
    private LocalisationService localisationService= new LocalisationServiceImpl();
    private RepositoryService repositoryService;
    /**
     *
     */
    public LanguageComboBox() {
        List<String> languageCodes = localisationService.getAvailableLanguageCodes();
        for(String languageCode:languageCodes){
            getItems().add(localisationService.getResourcePack(languageCode).getOriginalLanguageName());
        }

        setDefault();
    }

    /**
     * @return
     */
    public String getSelectedLanguageId() {
        String languageName = getValue().toString();
        for(String lc : localisationService.getAvailableLanguageCodes()) {
            ResourcePack rp = localisationService.getResourcePack(lc);
            if(rp.getOriginalLanguageName().equals(languageName)) {
                return lc;
            }
        }
        throw new ColorShapesEngineException("Language code was not found for: " + languageName);
    }

    /**
     *
     */
    public void setDefault() {
        String defaultLanguageId = "en";//repositoryService.getSystemPropertyRepository().getProperty("default-language-code");
        setValue(localisationService.getResourcePack(defaultLanguageId).getOriginalLanguageName());
    }
}
