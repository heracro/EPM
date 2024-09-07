package hihi.content.project;

import hihi.adapter.AdapterBuilder;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.content.bom.Bom;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.enums.LocationType;
import hihi.content.enums.ProjectCause;
import hihi.content.enums.ProjectStatus;
import hihi.content.project.boxes.LeftPickersBox;
import hihi.content.project.boxes.RightPickersBox;
import hihi.content.task.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.converter.NumberStringConverter;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@ToString(callSuper = true)
public class ProjectDetailsLayoutController extends ContentDetailsLayoutController<Project> {

    //Layout elements
    @FXML
    private TextField nameField;
    @FXML
    private TextArea bodyField;
    @FXML
    private TextField projectLocationField;
    @FXML
    private ComboBox<LocationType> locationTypeField;
    @FXML
    private HBox pickersBox;
    @FXML
    private VBox leftPickersBox;
    @FXML
    private VBox rightPickersBox;
    @FXML
    private ListView<String> tagListField;
    @FXML
    private BorderPane tablePlaceholder;
    private final Label bomTableLabel = new Label("Bill of materials");
    private final TableView<Bom> bomTable = new TableView<>();
    private final Label taskTableLabel = new Label("Tasks");
    private final TableView<Task> taskTable = new TableView<>();

    @FXML
    private VBox contentBox;

    public ProjectDetailsLayoutController(Project project) {
        super("Project", project);
        log.info("\033[92m ProjectDetailsLayoutController() \033[m");

    }

    public void initialize() {
        log.info("\033[92m initialize() \033[0m");
        super.initialize();
        setContent();
        tablePlaceholder.widthProperty().addListener((observable, oldValue, newValue) -> {
            log.info("\033[92m Table Placeholder {} width change: {} --> {}\nWidth = [min={}, pref={}, max={}, current={}]\033[m",
                    tablePlaceholder, oldValue, newValue, tablePlaceholder.getMinWidth(), tablePlaceholder.getPrefWidth(),
                    tablePlaceholder.getMaxWidth(), tablePlaceholder.getWidth());
            adjustLayout(oldValue.doubleValue(), newValue.doubleValue());
        });
        tablePlaceholder.setMinWidth(200);
    }

    public void setContent() {
        log.info("\033[92m setContent({})\033[0m", content);
        new ProjectDetailsBottomPanelConfiguration(this, content).setupBottomPanel(bottomPanel);
        uidField.textProperty().bind(content.uidProperty().asString());
        nameField.textProperty().bindBidirectional(content.nameProperty());
        bodyField.textProperty().bindBidirectional(content.bodyProperty());
        projectLocationField.textProperty().bindBidirectional(content.projectLocationProperty());
        locationTypeField.setItems(FXCollections.observableArrayList(LocationType.values()));
        locationTypeField.valueProperty().bindBidirectional(content.locationTypeProperty());
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
        log.info("\033[92m setWidePickersBox()\033[0m");
        Region spacer = new Region();
        spacer.getStyleClass().add("hspacer");
        pickersBox.getChildren().clear();
        pickersBox.getChildren().addAll(
                new LeftPickersBox(content).setupBox(),
                spacer,
                new RightPickersBox(content).setupBox()
        );
    }

    private void setSlimLayout() {
        log.info("\033[92m setSlimLayout()\033[0m");
        setSlimPickersBox();
        setSlimTablesView();
    }

    private void setSlimTablesView() {
        log.info("\033[92m setSlimTablesView()\033[0m");
        VBox tables = new VBox();
        tables.getChildren().addAll(bomTableLabel, bomTable, taskTableLabel, taskTable);
        tables.setAlignment(Pos.CENTER);
        tablePlaceholder.setCenter(tables);
    }

    private void setSlimPickersBox() {
        log.info("\033[92m setSlimPickersBox()\033[0m");
        VBox slimPickersBox = new VBox();
        pickersBox.getChildren().clear();
        Region spacer = new Region();
        spacer.getStyleClass().add("vspacer");
        slimPickersBox.getChildren().addAll(
                new LeftPickersBox(content).setupBox(),
                spacer,
                new RightPickersBox(content).setupBox()
        );
        pickersBox.getChildren().add(slimPickersBox);
    }



}

