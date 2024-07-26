package hihi.gui.layout.content.project;

import hihi.adapters.ProjectAdapter;
import hihi.dto.ProjectDto;
import hihi.gui.components.ContentWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class ProjectListLayout extends ContentWindow {

    private TableView<Project> projectTable;

    public ProjectListLayout() {
        this.setPadding(new Insets(10));

        projectTable = new TableView<>();

        TableColumn<Project, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().getSelected().asObject());
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        selectColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        selectColumn.getStyleClass().add("table-column-checkbox");

        TableColumn<Project, String> uidColumn = new TableColumn<>("UID");
        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asString());
        uidColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        uidColumn.getStyleClass().add("table-column-uid");

        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.2));
        nameColumn.getStyleClass().add("table-column-name");

        TableColumn<Project, LocalDate> plannedStartDateColumn = new TableColumn<>("Planned Start Date");
        plannedStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedStartDate());
        plannedStartDateColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        plannedStartDateColumn.getStyleClass().add("table-column-date");

        TableColumn<Project, LocalDate> plannedEndDateColumn = new TableColumn<>("Planned End Date");
        plannedEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedEndDate());
        plannedEndDateColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        plannedEndDateColumn.getStyleClass().add("table-column-date");

        TableColumn<Project, LocalDate> realStartDateColumn = new TableColumn<>("Start Date");
        realStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealStartDate());
        realStartDateColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        realStartDateColumn.getStyleClass().add("table-column-date");

        TableColumn<Project, LocalDate> realEndDateColumn = new TableColumn<>("End Date");
        realEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealEndDate());
        realEndDateColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.05));
        realEndDateColumn.getStyleClass().add("table-column-date");

        TableColumn<Project, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus().asString());
        statusColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.1));
        statusColumn.getStyleClass().add("table-column-status");

        TableColumn<Project, String> causeColumn = new TableColumn<>("Cause");
        causeColumn.setCellValueFactory(cellData -> cellData.getValue().getCause().asString());
        causeColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.1));
        causeColumn.getStyleClass().add("table-column-cause");

        TableColumn<Project, String> projectLocationColumn = new TableColumn<>("Location");
        projectLocationColumn.setCellValueFactory(cellData -> cellData.getValue().getProjectLocation());
        projectLocationColumn.prefWidthProperty().bind(projectTable.widthProperty().multiply(0.3));
        projectLocationColumn.getStyleClass().add("table-column-projectLocation");

        projectTable.getColumns().addAll(
                selectColumn, uidColumn, nameColumn, plannedStartDateColumn, plannedEndDateColumn,
                realStartDateColumn, realEndDateColumn, statusColumn, causeColumn, projectLocationColumn
        );

        this.setCenter(projectTable);
        this.getStylesheets().add("/project-layout.css");
        loadProjects();
    }

    private void loadProjects() {
        try {
            ProjectAdapter adapter = new ProjectAdapter();
            List<ProjectDto> projectDtos = adapter.getProjects();
            ObservableList<Project> projects = FXCollections.observableArrayList();
            for (ProjectDto projectDto : projectDtos) {
                log.info("Fetching Project DTO to content view table: {}", projectDto);
                projects.add(new Project(projectDto));
                log.info("Project in list: {}", projects.getLast());
            }
            projectTable.setItems(projects);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

}