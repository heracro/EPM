package hihi.content.common.contentDetails;

import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.ButtonConfig;

public abstract class ContentDetailsBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentDetailsBottomPanelConfiguration() {
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        buttonConfigs.add(new ButtonConfig("Save to DB", event -> storeEntry()));
        buttonConfigs.add(new ButtonConfig("Cancel", event -> cancelEdit()));
        buttonConfigs.add(new ButtonConfig("Export to file", event -> exportEntry()));
    }

    protected abstract void storeEntry();
    protected abstract void cancelEdit();
    protected abstract void exportEntry();
}
