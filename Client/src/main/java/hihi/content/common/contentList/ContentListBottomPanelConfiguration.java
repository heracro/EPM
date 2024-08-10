package hihi.content.common.contentList;

import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.ButtonConfig;

public abstract class ContentListBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentListBottomPanelConfiguration() {
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        buttonConfigs.add(new ButtonConfig("Refresh list", event -> refreshList()));
        buttonConfigs.add(new ButtonConfig("New...", event -> createNewEntry()));
        buttonConfigs.add(new ButtonConfig("Delete selected", event -> deleteSelectedEntries()));
    }

    protected abstract void refreshList();
    protected abstract void createNewEntry();
    protected abstract void deleteSelectedEntries();

}
