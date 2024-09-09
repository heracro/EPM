package hihi.content.project.boxes;

import hihi.content.common.LayoutConfigurationUtils;
import hihi.content.common.contentDetails.BoxElementConfig;
import hihi.content.common.dataModel.BoxedLayoutFragment;
import hihi.content.project.Project;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RightPickersBox implements BoxedLayoutFragment {

    private Project project;

    private static final double leftPartRatio = 0.4;

    private final List<BoxElementConfig> order;

    public RightPickersBox(Project project) {
        log.info("\033[32m RightPickersBox({}) \033[0m", project.uidProperty());
        this.project = project;
        this.order = List.of(
                new BoxElementConfig(new HBox(), new TextField(), "Hours planned", project::workingHoursPlannedProperty),
                new BoxElementConfig(new Region(), null, null, null),
                new BoxElementConfig(new HBox(), new TextField(), "Hours worked", project::workingHoursCountProperty),
                new BoxElementConfig(new Region(), null, null, null),
                new BoxElementConfig(new HBox(), new ComboBox<>(), "Cause", project::causeProperty),
                new BoxElementConfig(new Region(), null, null, null),
                new BoxElementConfig(new HBox(), new ComboBox<>(), "Status", project::statusProperty)
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
