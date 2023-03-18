
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

package org.nanoboot.colorshapes.desktop.view.windows.customcompositionwindow;

import javafx.scene.control.Tab;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.json.JsonArray;
import org.nanoboot.powerframework.json.JsonObject;
import org.nanoboot.powerframework.view.window.controls.TabPane;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class BallsTab extends Tab {

    private final TabPane tabPane;
    private final ColourFrequenciesTab colourFrequenciesTab;
    private final ValuesFrequenciesTab valuesFrequenciesTab;
    private final BallsOtherTab ballsOtherTab;

    private ResourcePack resourcePack;
    /**
     *
     */
    public BallsTab() {
        super();
        this.setText(resourcePack.getText(20200));
        this.setClosable(false);
        this.colourFrequenciesTab = new ColourFrequenciesTab();
        this.valuesFrequenciesTab = new ValuesFrequenciesTab();
        this.ballsOtherTab = new BallsOtherTab();
        this.tabPane = new TabPane();
        tabPane.getTabs().addAll(colourFrequenciesTab, valuesFrequenciesTab, ballsOtherTab);
        this.setContent(tabPane);
    }

    /**
     * @param jsonObject
     */
    public void updateByJsonObject(JsonObject jsonObject) {
        this.colourFrequenciesTab.updateByJsonObject(jsonObject);
        this.valuesFrequenciesTab.updateByJsonObject(jsonObject);
        this.ballsOtherTab.updateByJsonObject(jsonObject);
    }

    /**
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject ballFactoryCompositionJsonObject = new JsonObject();
        JsonArray colourFrequencyJsonArray = this.colourFrequenciesTab.toJsonArray();

        ballFactoryCompositionJsonObject.addArray("colour frequency", colourFrequencyJsonArray);

        ballFactoryCompositionJsonObject.addInt("value minus two frequency", this.valuesFrequenciesTab.getMinusTwoFrequency());
        ballFactoryCompositionJsonObject.addInt("value minus one frequency", this.valuesFrequenciesTab.getMinusOneFrequency());
        ballFactoryCompositionJsonObject.addInt("value zero frequency", this.valuesFrequenciesTab.getZeroFrequency());
        ballFactoryCompositionJsonObject.addInt("value plus one frequency", this.valuesFrequenciesTab.getPlusOneFrequency());
        ballFactoryCompositionJsonObject.addInt("value plus two frequency", this.valuesFrequenciesTab.getPlusTwoFrequency());

        ballFactoryCompositionJsonObject.addInt("colorful ball frequency", this.ballsOtherTab.getColourfulBallFrequency());
        ballFactoryCompositionJsonObject.addInt("rainbow ball frequency", this.ballsOtherTab.getRainbowBallFrequency());
        ballFactoryCompositionJsonObject.addInt("unmoveable ball probability", this.ballsOtherTab.getUnmoveableBallsProbability());
        ballFactoryCompositionJsonObject.addInt("unbreakable ball probability", this.ballsOtherTab.getUnbreakableBallsProbability());

        return ballFactoryCompositionJsonObject;
    }
}
