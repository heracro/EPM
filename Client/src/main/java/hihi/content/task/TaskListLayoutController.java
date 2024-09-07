package hihi.content.task;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.enums.TaskStatus;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class TaskListLayoutController
        extends ContentListLayoutController<Task> {

    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;
    @FXML
    private TableColumn<Task, TaskStatus> statusColumn;

    public TaskListLayoutController() {
        super("Task");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.TASK_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }
}
