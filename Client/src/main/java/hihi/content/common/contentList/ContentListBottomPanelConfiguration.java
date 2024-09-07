package hihi.content.common.contentList;

import hihi.application.config.ModuleConfig;
import hihi.content.common.BottomPanelConfiguration;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.BottomPanelButtonConfig;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class ContentListBottomPanelConfiguration
        extends BottomPanelConfiguration {

    protected ContentListLayoutController<? extends AbstractContent> layoutController;

    protected ContentListBottomPanelConfiguration(ContentListLayoutController<? extends AbstractContent> layoutController) {
        this.layoutController = layoutController;
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Refresh list", event -> refreshList(), null
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "New...", event -> createNewEntry(), null
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Delete selected", event -> deleteSelectedEntries(), null
        ));
    }

    protected void refreshList() {
        layoutController.loadContentToTable();
    }

    protected void createNewEntry() {
        try {
            ModuleConfig config = ModuleConfig.getInstance(layoutController.getModuleName());
            AbstractContent content = config.getContentClass().getDeclaredConstructor().newInstance();
            layoutController.getMainController().setContentDetailsView(layoutController.getModuleName(), content);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Failed to create new entry: {}", e.getMessage());
        }
    }

    protected abstract void deleteSelectedEntries();

}
