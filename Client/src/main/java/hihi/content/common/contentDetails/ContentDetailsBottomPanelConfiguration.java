package hihi.content.common.contentDetails;

import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.BottomPanelButtonConfig;
import hihi.content.project.ProjectDetailsLayoutController;

public abstract class ContentDetailsBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentDetailsLayoutController<? extends AbstractContent> layoutController;

    protected ContentDetailsBottomPanelConfiguration(ProjectDetailsLayoutController layoutController) {
        this.layoutController = layoutController;
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Save to DB", event -> storeEntry(), null
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Cancel", event -> cancelEdit(), null
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Export to file", event -> exportEntry(), null
        ));
    }

    protected abstract void storeEntry();

    protected void cancelEdit() {
        layoutController.getMainController().setContentListView(layoutController.getModuleName());
    }

    protected abstract void exportEntry();
}
