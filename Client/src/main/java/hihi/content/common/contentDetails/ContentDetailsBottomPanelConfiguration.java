package hihi.content.common.contentDetails;

import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.dataModel.BottomPanelButtonConfig;

public abstract class ContentDetailsBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentDetailsBottomPanelConfiguration() {
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("Save to DB", event -> storeEntry()));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("Cancel", event -> cancelEdit()));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("Export to file", event -> exportEntry()));
    }

    protected abstract void storeEntry();
    protected abstract void cancelEdit();
    protected abstract void exportEntry();
}
