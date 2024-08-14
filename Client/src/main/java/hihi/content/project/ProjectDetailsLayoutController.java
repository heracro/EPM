package hihi.content.project;

import hihi.adapter.AdapterBuilder;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.content.bom.Bom;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.enums.LocationType;
import hihi.content.enums.ProjectCause;
import hihi.content.enums.ProjectStatus;
import hihi.content.task.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class ProjectDetailsLayoutController extends ContentDetailsLayoutController<Project> {

    //Layout elements
    @FXML
    private TextField nameField;
    @FXML
    private TextArea bodyField;
    @FXML
    private Label plannedStartDateLabel;
    @FXML
    private DatePicker plannedStartDateField;
    @FXML
    private Label plannedEndDateLabel;
    @FXML
    private DatePicker plannedEndDateField;
    @FXML
    private Label realStartDateLabel;
    @FXML
    private DatePicker realStartDateField;
    @FXML
    private Label realEndDateLabel;
    @FXML
    private DatePicker realEndDateField;
    @FXML
    private Label materialStartDateLabel;
    @FXML
    private DatePicker materialsReadyDateField;
    @FXML
    private Label workingHoursCountLabel;
    @FXML
    private TextField workingHoursCountField;
    @FXML
    private Label workingHoursPlannedLabel;
    @FXML
    private TextField workingHoursPlannedField;
    @FXML
    private TextField projectLocationField;
    @FXML
    private ComboBox<LocationType> locationTypeField;
    @FXML
    private ComboBox<ProjectStatus> statusField;
    @FXML
    private ComboBox<ProjectCause> causeField;
    @FXML
    private ListView<String> tagListField;
    @FXML
    private BorderPane tablePlaceholder;
    @FXML
    private Label bomTableLabel = new Label("Bill of materials");
    @FXML
    private TableView<Bom> bomTable = new TableView<>();
    @FXML
    private Label taskTableLabel = new Label("Tasks");
    @FXML
    private TableView<Task> taskTable = new TableView<>();
    @FXML
    private VBox contentBox;

    public ProjectDetailsLayoutController() {
        super("Project");
        log.info("\033[92m ProjectDetailsLayoutController() \033[m");
    }

    @Override
    protected void setAdapter() {
        adapter = new AdapterBuilder().setModuleConfig(ModuleConfig.getInstance(moduleName)).build();
    }

    public void initialize() {
        log.info("\033[92m super.initialize() \033[0m");
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
        tablePlaceholder.widthProperty().addListener((observable, oldValue, newValue) -> {
            log.info("\033[92m Table Placeholder {} width change: {} --> {}\nWidth = [min={}, pref={}, max={}, current={}]\033[m",
                    tablePlaceholder, oldValue, newValue, tablePlaceholder.getMinWidth(), tablePlaceholder.getPrefWidth(),
                    tablePlaceholder.getMaxWidth(), tablePlaceholder.getWidth());
            adjustLayout(oldValue.doubleValue(), newValue.doubleValue());
        });
        tablePlaceholder.setMinWidth(200);
    }

    @Override
    public void setContent(Project project) {
        log.info("\033[92m setContent({})\033[0m", project);
        uidField.textProperty().bind(project.uidProperty().asString());
        nameField.textProperty().bindBidirectional(project.nameProperty());
        bodyField.textProperty().bindBidirectional(project.bodyProperty());
        plannedStartDateField.valueProperty().bindBidirectional(project.plannedStartDateProperty());
        plannedEndDateField.valueProperty().bindBidirectional(project.plannedEndDateProperty());
        realStartDateField.valueProperty().bindBidirectional(project.realStartDateProperty());
        realEndDateField.valueProperty().bindBidirectional(project.realEndDateProperty());
        materialsReadyDateField.valueProperty().bindBidirectional(project.materialsReadyDateProperty());
        workingHoursCountField.textProperty().bindBidirectional(
                project.workingHoursCountProperty(), new NumberStringConverter());
        workingHoursPlannedField.textProperty().bindBidirectional(
                project.workingHoursPlannedProperty(), new NumberStringConverter());
        projectLocationField.textProperty().bindBidirectional(project.projectLocationProperty());
        locationTypeField.setItems(FXCollections.observableArrayList(LocationType.values()));
        locationTypeField.valueProperty().bindBidirectional(project.locationTypeProperty());
        statusField.setItems(FXCollections.observableArrayList(ProjectStatus.values()));
        statusField.valueProperty().bindBidirectional(project.statusProperty());
        causeField.setItems(FXCollections.observableArrayList(ProjectCause.values()));
        causeField.valueProperty().bindBidirectional(project.causeProperty());
    }

    private void adjustLayout(double oldWidth, double newWidth) {
        log.info("\033[92m adjustLayout(oldWidth={}, newWidth={})\033[0m", oldWidth, newWidth);
        if (oldWidth >= GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH
                && newWidth < GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH) {
            log.info("\033[92m Window SHRINKED \033[m");
            setSlimLayout();
        } else if (oldWidth < GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH
                && newWidth >= GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH) {
            log.info("\033[92m Window EXPANDED \033[m");
            setWideLayout();
        }
    }

    private void setWideLayout() {
        log.info("\033[92m setWideLayout()\033[0m");
        setWidePickersBox();
        setWideTablesView();
    }

    protected void setWideTablesView() {
        log.info("\033[92m setWideTablesView()\033[0m");
        double spaceWidth = 20;
        HBox tables = new HBox(spaceWidth);
        VBox bomBox = new VBox(bomTableLabel, bomTable);
        HBox.setHgrow(bomBox, Priority.ALWAYS);
        bomBox.prefWidthProperty().bind(tablePlaceholder.widthProperty().subtract(spaceWidth).multiply(0.4));
        VBox taskBox = new VBox(taskTableLabel, taskTable);
        HBox.setHgrow(taskBox, Priority.ALWAYS);
        taskBox.prefWidthProperty().bind(tablePlaceholder.widthProperty().subtract(spaceWidth).multiply(0.6));
        bomBox.setAlignment(Pos.CENTER);
        taskBox.setAlignment(Pos.CENTER);
        tables.getChildren().addAll(bomBox, taskBox);
        tablePlaceholder.setCenter(tables);
    }

    private void setWidePickersBox() {
    }

    private void setSlimLayout() {
        log.info("\033[92m setSlimLayout()\033[0m");
        setSlimPickersBox();
        setSlimTablesView();
    }

    private void setSlimTablesView() {
        VBox tables = new VBox(10); // 10 to odstęp między tabelami
        tables.getChildren().addAll(bomTableLabel, bomTable, taskTableLabel, taskTable);
        tables.setAlignment(Pos.CENTER);
        tablePlaceholder.setCenter(tables);
    }

    private void setSlimPickersBox() {

    }

}

