package hihi.content.project;

import hihi.adapters.ProjectAdapter;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.enums.ProjectCause;
import hihi.content.enums.ProjectStatus;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static hihi.application.config.GuiConfig.PROJECT_LIST_COL_WIDTHS;

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
        return new Project(dto);
    }

    public ProjectListLayoutController() {
        super(new ProjectAdapter(), "Project");
        log.info("\033[93mProjectListLayoutController()\033[0m");
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        addNameColumn(PROJECT_LIST_COL_WIDTHS[2]);
        addPlannedStartColumn(PROJECT_LIST_COL_WIDTHS[3]);
        addPlannedEndColumn(PROJECT_LIST_COL_WIDTHS[4]);
        addRealStartColumn(PROJECT_LIST_COL_WIDTHS[5]);
        addRealEndColumn(PROJECT_LIST_COL_WIDTHS[6]);
        addStatusColumn(PROJECT_LIST_COL_WIDTHS[7]);
        addCauseColumn(PROJECT_LIST_COL_WIDTHS[8]);
        addProjectLocationColumn(PROJECT_LIST_COL_WIDTHS[9]);
    }

    private void addNameColumn(double width) {
        log.info("\033[93m addNameColumn({}) \033[0m", width);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addPlannedStartColumn(double width) {
        log.info("\033[93m addPlannedStartColumn({}) \033[0m", width);
        plannedStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().plannedStartDateProperty());
        plannedStartDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addPlannedEndColumn(double width) {
        log.info("\033[93m addPlannedEndColumn({}) \033[0m", width);
        plannedEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().plannedEndDateProperty());
        plannedEndDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addRealStartColumn(double width) {
        log.info("\033[93m addRealStartColumn({}) \033[0m", width);
        realStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().realStartDateProperty());
        realStartDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addRealEndColumn(double width) {
        log.info("\033[93m addRealEndColumn({}) \033[0m", width);
        realEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().realEndDateProperty());
        realEndDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addStatusColumn(double width) {
        log.info("\033[93m addStatusColumn({}) \033[0m", width);
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        statusColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addCauseColumn(double width) {
        log.info("\033[93m addCauseColumn({}) \033[0m", width);
        causeColumn.setCellValueFactory(cellData -> cellData.getValue().causeProperty());
        causeColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

    private void addProjectLocationColumn(double width) {
        log.info("\033[93m addProjectLocationColumn({}) \033[0m", width);
        projectLocationColumn.setCellValueFactory(cellData -> cellData.getValue().projectLocationProperty());
        projectLocationColumn.prefWidthProperty().bind(table.widthProperty().multiply(width));
    }

}