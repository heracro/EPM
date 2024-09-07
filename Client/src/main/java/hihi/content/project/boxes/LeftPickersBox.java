package hihi.content.project.boxes;

import hihi.content.common.contentDetails.BoxElementConfig;
import hihi.content.common.dataModel.BoxedLayoutFragment;
import hihi.content.project.Project;
import hihi.content.common.LayoutConfigurationUtils;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LeftPickersBox implements BoxedLayoutFragment {

    private Project project;
    
    private static final double leftPartRatio = 0.4;

    private final List<BoxElementConfig> order;

    public LeftPickersBox(Project project) {
        log.info("\033[32m LeftPickersBox({}) \033[0m", project.uidProperty());
        this.project = project;
        this.order = List.of(
                new BoxElementConfig(new HBox(), "Planned start", project::plannedStartDateProperty),
                new BoxElementConfig(new HBox(), "Actual start", project::realStartDateProperty),
                new BoxElementConfig(new Region(), null, null),
                new BoxElementConfig(new HBox(), "Planned end", project::plannedEndDateProperty),
                new BoxElementConfig(new HBox(), "Actual end", project::realEndDateProperty),
                new BoxElementConfig(new Region(), null, null),
                new BoxElementConfig(new HBox(), "Materials ready", project::materialsReadyDateProperty)
        );
    }

    public VBox setupBox() {
        VBox box = new VBox();
        for (BoxElementConfig config : order) {
            if (config.node() instanceof HBox row) {
                Label label = new Label(config.label());
                label.prefWidthProperty().bind(row.widthProperty().multiply(leftPartRatio));
                label.setTextAlignment(TextAlignment.CENTER);
                DatePicker picker = new DatePicker();
                VBox.setVgrow(picker, Priority.ALWAYS);
                LayoutConfigurationUtils.bindFieldToProperty(picker, config.propertySupplier().get());
                row.getChildren().addAll(label, picker);
            } else if (config.node() instanceof Region spacer) {
                spacer.getStyleClass().add("vspacer");
            } else {
                throw new UnsupportedOperationException("Layout for " + config.node().getClass().getSimpleName()
                        + " undefined");
            }
            box.getChildren().add(config.node());
        }
        return box;
    }

}
