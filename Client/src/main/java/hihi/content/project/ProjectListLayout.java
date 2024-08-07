package hihi.content.project;

import hihi.adapters.ProjectAdapter;
import hihi.content.common.ContentListLayout;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static hihi.application.config.GuiConfig.PROJECT_LIST_COL_WIDTHS;

@Slf4j
public class ProjectListLayout extends ContentListLayout<Project, ProjectDto, ProjectAdapter> {

    @Getter
    private TableView<Project> table;

    @Getter
    private final ProjectAdapter adapter = new ProjectAdapter();

    @Override
    protected Project mapInstance(ProjectDto dto) {
        return new Project(dto);
    }

    public ProjectListLayout() {
        this.setPadding(new Insets(2));
        table = new TableView<>();
        addSelectedColumn(PROJECT_LIST_COL_WIDTHS[0]);
        addUidColumn(PROJECT_LIST_COL_WIDTHS[1]);
        addNameColumn(PROJECT_LIST_COL_WIDTHS[2]);
        addPlannedStartColumn(PROJECT_LIST_COL_WIDTHS[3]);
        addPlannedEndColumn(PROJECT_LIST_COL_WIDTHS[4]);
        addRealStartColumn(PROJECT_LIST_COL_WIDTHS[5]);
        addRealEndColumn(PROJECT_LIST_COL_WIDTHS[6]);
        addStatusColumn(PROJECT_LIST_COL_WIDTHS[7]);
        addCauseColumn(PROJECT_LIST_COL_WIDTHS[8]);
        addProjectLocationColumn(PROJECT_LIST_COL_WIDTHS[9]);
        this.setCenter(table);
        loadContentList();
    }

//    private void addUidColumn(double width) {
//        TableColumn<Project, String> uidColumn = new TableColumn<>("UID");
//        uidColumn.setCellValueFactory(cellData -> cellData.getValue().getUid().asString());
//        uidColumn.prefWidthProperty()
//                .bind(table.widthProperty().multiply(width));
//        table.getColumns().add(uidColumn);
//    }

    private void addNameColumn(double width) {
        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(nameColumn);
    }

    private void addPlannedStartColumn(double width) {
        TableColumn<Project, LocalDate> plannedStartDateColumn = new TableColumn<>("Planned Start Date");
        plannedStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedStartDate());
        plannedStartDateColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(plannedStartDateColumn);
    }

    private void addPlannedEndColumn(double width) {
        TableColumn<Project, LocalDate> plannedEndDateColumn = new TableColumn<>("Planned End Date");
        plannedEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getPlannedEndDate());
        plannedEndDateColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(plannedEndDateColumn);
    }

    private void addRealStartColumn(double width) {
        TableColumn<Project, LocalDate> realStartDateColumn = new TableColumn<>("Start Date");
        realStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealStartDate());
        realStartDateColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(realStartDateColumn);
    }

    private void addRealEndColumn(double width) {
        TableColumn<Project, LocalDate> realEndDateColumn = new TableColumn<>("End Date");
        realEndDateColumn.setCellValueFactory(cellData -> cellData.getValue().getRealEndDate());
        realEndDateColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(realEndDateColumn);
    }

    private void addStatusColumn(double width) {
        TableColumn<Project, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus().asString());
        statusColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(statusColumn);
    }

    private void addCauseColumn(double width) {
        TableColumn<Project, String> causeColumn = new TableColumn<>("Cause");
        causeColumn.setCellValueFactory(cellData -> cellData.getValue().getCause().asString());
        causeColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(causeColumn);
    }

    private void addProjectLocationColumn(double width) {
        TableColumn<Project, String> projectLocationColumn = new TableColumn<>("Location");
        projectLocationColumn.setCellValueFactory(cellData -> cellData.getValue().getProjectLocation());
        projectLocationColumn.prefWidthProperty()
                .bind(table.widthProperty().multiply(width));
        table.getColumns().add(projectLocationColumn);
    }

}