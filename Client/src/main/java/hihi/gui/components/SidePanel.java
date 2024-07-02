package hihi.gui.components;

import hihi.application.config.GuiConfig;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class SidePanel extends VBox {

    private Label connectionStatusLabel;

    public SidePanel(double spacing) {
        super(spacing);
        this.getStyleClass().add("side-panel");
        createModuleButtons();
        createSpacer();
        createConnectionStatusLabel();
    }

    private void createModuleButtons() {
        List<String> moduleButtonLabels = GuiConfig.MODULE_BUTTONS;
        ToggleGroup moduleButtons = new ToggleGroup();
        for (String label : moduleButtonLabels) {
            ToggleButton toggleButton = new ToggleButton(label);
            toggleButton.setMaxWidth(GuiConfig.LEFT_PANEL_BUTTON_WIDTH);
            toggleButton.setToggleGroup(moduleButtons);
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
