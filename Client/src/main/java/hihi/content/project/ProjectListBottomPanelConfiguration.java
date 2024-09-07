package hihi.content.project;

import hihi.content.common.contentList.ContentListBottomPanelConfiguration;
import hihi.content.common.dataModel.BottomPanelButtonConfig;
import org.springframework.stereotype.Component;

@Component
public class ProjectListBottomPanelConfiguration extends ContentListBottomPanelConfiguration {

    public ProjectListBottomPanelConfiguration(ProjectListLayoutController layoutController) {
        super(layoutController);
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Check materials", event -> checkMaterialsForSelectedProjects(), null
        ));
    }

    @Override
    protected void deleteSelectedEntries() {

    }

    protected void checkMaterialsForSelectedProjects() {

    }

}
