package hihi.content.common.contentList;

import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.dataModel.BottomPanelButtonConfig;

public abstract class ContentListBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentListBottomPanelConfiguration() {
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("Refresh list", event -> refreshList()));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("New...", event -> createNewEntry()));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig("Delete selected", event -> deleteSelectedEntries()));
    }

    protected abstract void refreshList();
    protected abstract void createNewEntry();
    protected abstract void deleteSelectedEntries();

}
