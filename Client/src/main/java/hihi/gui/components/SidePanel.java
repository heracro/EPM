package hihi.gui.components;

import hihi.application.MainWindow;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleButtonConfig;
import hihi.gui.event.SidePanelButtonListener;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class SidePanel extends VBox {

    private final MainWindow mainWindow;
    private Label connectionStatusLabel;

    public SidePanel(MainWindow mainWindow, double spacing) {
        super(spacing);
        this.mainWindow = mainWindow;
        this.getStyleClass().add("side-panel");
        createModuleButtons();
        createSpacer();
        createConnectionStatusLabel();
    }

    private void createModuleButtons() {
        List<ModuleButtonConfig> moduleButtonConfigs = GuiConfig.MODULE_BUTTONS_CONFIG;
        ToggleGroup moduleButtons = new ToggleGroup();
        for (ModuleButtonConfig buttonConfig : moduleButtonConfigs) {
            ToggleButton toggleButton = new ToggleButton(buttonConfig.label());
            toggleButton.setMaxWidth(GuiConfig.LEFT_PANEL_BUTTON_WIDTH);
            toggleButton.setToggleGroup(moduleButtons);
            toggleButton.setOnAction(new SidePanelButtonListener(mainWindow, buttonConfig.layoutClass()));
            this.getChildren().add(toggleButton);
        }
    }

    private void createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        this.getChildren().add(spacer);
    }

    private void createConnectionStatusLabel() {
        connectionStatusLabel = new Label("Brak połączenia");
        connectionStatusLabel.getStyleClass().add("connection-status");
        connectionStatusLabel.getStyleClass().add("disconnected");
        this.getChildren().add(connectionStatusLabel);
    }

    public void updateConnectionStatus(boolean status) {
        if (status) {
            connectionStatusLabel.getStyleClass().removeAll("disconnected");
            connectionStatusLabel.getStyleClass().add("connected");
            connectionStatusLabel.setText("Connected");
        } else {
            connectionStatusLabel.getStyleClass().removeAll("connected");
            connectionStatusLabel.getStyleClass().add("disconnected");
            connectionStatusLabel.setText("Disconnected");
        }
    }

}
