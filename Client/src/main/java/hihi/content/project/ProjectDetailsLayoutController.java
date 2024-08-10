package hihi.content.project;

import hihi.adapters.ProjectAdapter;
import hihi.content.bom.Bom;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.enums.LocationType;
import hihi.content.enums.ProjectCause;
import hihi.content.enums.ProjectStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class ProjectDetailsLayoutController
        extends ContentDetailsLayoutController<Project, ProjectDto, ProjectAdapter> {

    @FXML
    private TextField nameField;
    @FXML
    private TextField bodyField;
    @FXML
    private DatePicker plannedStartDatePicker;
    @FXML
    private DatePicker plannedEndDatePicker;
    @FXML
    private DatePicker realStartDatePicker;
    @FXML
    private DatePicker realEndDatePicker;
    @FXML
    private DatePicker materialsReadyDatePicker;
    @FXML
    private TextField workingHoursCountField;
    @FXML
    private TextField workingHoursPlannedField;
    @FXML
    private TextField projectLocationField;
    @FXML
    private ComboBox<LocationType> locationTypeComboBox;
    @FXML
    private ComboBox<ProjectStatus> statusComboBox;
    @FXML
    private ComboBox<ProjectCause> causeComboBox;
    @FXML
    private TableView<Bom> bomTable;

    public ProjectDetailsLayoutController() {
        super(new ProjectAdapter(), "Project");
        log.info("\033[92m ProjectDetailsLayoutController() \033[m");
    }

    public void initialize() {
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
    }

    @Override
    public void setContent(Project project) {
        log.info("\033[92m setContent({})\033[0m", project);
        uidField.textProperty().bind(project.uidProperty().asString());
        nameField.textProperty().bindBidirectional(project.nameProperty());
        bodyField.textProperty().bindBidirectional(project.bodyProperty());
        plannedStartDatePicker.valueProperty().bindBidirectional(project.plannedStartDateProperty());
        plannedEndDatePicker.valueProperty().bindBidirectional(project.plannedEndDateProperty());
        realStartDatePicker.valueProperty().bindBidirectional(project.realStartDateProperty());
        realEndDatePicker.valueProperty().bindBidirectional(project.realEndDateProperty());
        materialsReadyDatePicker.valueProperty().bindBidirectional(project.materialsReadyDateProperty());
        workingHoursCountField.textProperty().bindBidirectional(
                project.workingHoursCountProperty(), new NumberStringConverter());
        workingHoursPlannedField.textProperty().bindBidirectional(
                project.workingHoursPlannedProperty(), new NumberStringConverter());
        projectLocationField.textProperty().bindBidirectional(project.projectLocationProperty());
        locationTypeComboBox.setItems(FXCollections.observableArrayList(LocationType.values()));
        locationTypeComboBox.valueProperty().bindBidirectional(project.locationTypeProperty());
        statusComboBox.setItems(FXCollections.observableArrayList(ProjectStatus.values()));
        statusComboBox.valueProperty().bindBidirectional(project.statusProperty());
        causeComboBox.setItems(FXCollections.observableArrayList(ProjectCause.values()));
        causeComboBox.valueProperty().bindBidirectional(project.causeProperty());
    }

}

