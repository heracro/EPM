package hihi.content.project;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.enums.ProjectCause;
import hihi.content.enums.ProjectStatus;
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
public class ProjectListLayoutController extends ContentListLayoutController<Project> {

    @FXML
    private TableColumn<Project, String> nameColumn;
    @FXML
    private TableColumn<Project, LocalDate> plannedStartDateColumn;
    @FXML
    private TableColumn<Project, LocalDate> plannedEndDateColumn;
    @FXML
    private TableColumn<Project, LocalDate> realStartDateColumn;
    @FXML
    private TableColumn<Project, LocalDate> realEndDateColumn;
    @FXML
    private TableColumn<Project, ProjectStatus> statusColumn;
    @FXML
    private TableColumn<Project, ProjectCause> causeColumn;
    @FXML
    private TableColumn<Project, String> projectLocationColumn;

    public ProjectListLayoutController() {
        super("Project");
        log.info("\033[93m ProjectListLayoutController() \033[0m");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.PROJECT_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        new ProjectListBottomPanelConfiguration(this).setupBottomPanel(bottomPanel);
        setupColumns();
    }

}