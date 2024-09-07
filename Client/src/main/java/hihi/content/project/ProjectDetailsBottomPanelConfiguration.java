package hihi.content.project;

import hihi.content.common.contentDetails.ContentDetailsBottomPanelConfiguration;
import hihi.content.common.dataModel.BottomPanelButtonConfig;
import hihi.content.enums.ProjectStatus;
import lombok.NonNull;

import java.util.EnumSet;
import java.util.function.BooleanSupplier;

public class ProjectDetailsBottomPanelConfiguration extends ContentDetailsBottomPanelConfiguration {

    private Project content;

    public ProjectDetailsBottomPanelConfiguration(ProjectDetailsLayoutController layoutController, @NonNull Project content) {
        super(layoutController);
        this.content = content;
        addCommonButtonsConfiguration();
    }

    private void addCommonButtonsConfiguration() {
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Start project", event -> startProject(), startProjectButtonVisibilityCondition
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "End project", event -> endProject(), endProjectButtonVisibilityCondition
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Cancel project", event -> cancelProject(), cancelProjectButtonVisibilityCondition
        ));
        bottomPanelButtonConfigs.add(new BottomPanelButtonConfig(
                "Check materials", event -> checkMaterialsAvailability(), checkMaterialsButtonVisibilityCondition
        ));
    }

    @Override
    protected void storeEntry() {

    }

    @Override
    protected void exportEntry() {

    }

    protected void startProject() {

    }

    protected void endProject() {

    }

    protected void cancelProject() {

    }

    protected void checkMaterialsAvailability() {

    }

    private final BooleanSupplier startProjectButtonVisibilityCondition =
            () -> content.statusProperty().get() != ProjectStatus.READY;

    private final BooleanSupplier endProjectButtonVisibilityCondition =
            () -> content.statusProperty().get() == ProjectStatus.ONGOING;

    private final BooleanSupplier cancelProjectButtonVisibilityCondition =
            () -> EnumSet.of(ProjectStatus.PLANNED,
                        ProjectStatus.AWAITING_MATERIALS,
                        ProjectStatus.READY,
                        ProjectStatus.ONGOING)
                    .contains(content.statusProperty().get());

    private final BooleanSupplier checkMaterialsButtonVisibilityCondition =
            () -> EnumSet.of(ProjectStatus.PLANNED, ProjectStatus.AWAITING_MATERIALS)
                    .contains(content.statusProperty().get());
}
