package hihi.content.project;

import hihi.adapters.ProjectAdapter;
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
public class ProjectListLayoutController
        extends ContentListLayoutController<Project, ProjectDto, ProjectAdapter> {

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

    @Override
    protected Project mapInstance(ProjectDto dto) {
        log.info("\033[93m mapInstance() \033[0m");
        return new Project(dto);
    }

    @Override
    protected Class<Project> getContentClass() {
        log.info("\033[93m getContentClass() \033[0m");
        return Project.class;
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.PROJECT_LIST_COL_WIDTH).boxed()).toList();
    }

    public ProjectListLayoutController() {
        super(new ProjectAdapter(), "Project");
        log.info("\033[93m ProjectListLayoutController() \033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }

}