package hihi.gui.layout.content.project;

import hihi.adapters.ProjectAdapter;
import hihi.application.config.GuiConfig;
import hihi.dto.ProjectDto;
import hihi.gui.components.ContentWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class ProjectListLayout extends ContentWindow {

    private TableView<Project> projectTable;

    public ProjectListLayout() {
        this.setPadding(new Insets(2));

        projectTable = new TableView<>();

        TableColumn<Project, String> uidColumn = new TableColumn<>("UID");
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asString());
        uidColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[0]));

        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[1]));

        TableColumn<Project, LocalDate> plannedStartDateColumn = new TableColumn<>("Planned Start Date");
        plannedStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedStartDate());
        plannedStartDateColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[2]));

        TableColumn<Project, LocalDate> plannedEndDateColumn = new TableColumn<>("Planned End Date");
        plannedEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedEndDate());
        plannedEndDateColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[3]));

        TableColumn<Project, LocalDate> realStartDateColumn = new TableColumn<>("Start Date");
        realStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealStartDate());
        realStartDateColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[4]));

        TableColumn<Project, LocalDate> realEndDateColumn = new TableColumn<>("End Date");
        realEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealEndDate());
        realEndDateColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[5]));

        TableColumn<Project, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus().asString());
        statusColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[6]));

        TableColumn<Project, String> causeColumn = new TableColumn<>("Cause");
        causeColumn.setCellValueFactory(cellData -> cellData.getValue().getCause().asString());
        causeColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[7]));

        TableColumn<Project, String> projectLocationColumn = new TableColumn<>("Location");
        projectLocationColumn.setCellValueFactory(cellData -> cellData.getValue().getProjectLocation());
        projectLocationColumn.prefWidthProperty()
                .bind(projectTable.widthProperty().multiply(GuiConfig.PROJECT_LIST_COL_WIDTHS[8]));

        projectTable.getColumns().addAll(
                uidColumn, nameColumn, plannedStartDateColumn, plannedEndDateColumn,
                realStartDateColumn, realEndDateColumn, statusColumn, causeColumn, projectLocationColumn
        );

        this.setCenter(projectTable);
        loadProjects();
    }

    private void loadProjects() {
        try {
            ProjectAdapter adapter = new ProjectAdapter();
            List<ProjectDto> projectDtos = adapter.getProjects();
            ObservableList<Project> projects = FXCollections.observableArrayList();
            for (ProjectDto projectDto : projectDtos) {
                projects.add(new Project(projectDto));
            }
            log.info("\033[32mFetched {} Projects\033[m", projects.size());
            projectTable.setItems(projects);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

}