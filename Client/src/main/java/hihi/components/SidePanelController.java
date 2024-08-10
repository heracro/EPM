package hihi.components;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import hihi.application.config.ModuleConfig;
import hihi.event.ConnectionTestEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static hihi.application.config.GuiConfig.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class SidePanelController {

    @FXML
    private VBox sidePanel;
    @FXML
    private Region topSpacer;
    @FXML
    private VBox buttonContainer;
    @FXML
    private Label connectionStatusLabel;
    @FXML
    private Region bottomSpacer;
    private final EventBus eventBus;
    private final MainController mainController;

    public void initialize() {
        eventBus.register(this);
        createModuleButtons();
        setConnectionStatus(false);
        sidePanel.getStyleClass().setAll("side-panel", "dark-area");
        topSpacer.setPrefHeight(buttonContainer.getSpacing());
        bottomSpacer.setPrefHeight(buttonContainer.getSpacing());
    }

    @Subscribe
    public void handleConnectionTestEvent(ConnectionTestEvent event) {
        setConnectionStatus(event.isConnectionStatus());
    }

    private void createModuleButtons() {
        ToggleGroup moduleButtons = new ToggleGroup();
        for (Map.Entry<String, ModuleConfig> buttonConfig : MODULES_CONFIG.entrySet()) {
            ToggleButton toggleButton = new ToggleButton(buttonConfig.getKey());
            toggleButton.setPrefWidth(LEFT_PANEL_BUTTON_WIDTH);
            toggleButton.setPrefHeight(LEFT_PANEL_BUTTON_HEIGHT);
            toggleButton.setToggleGroup(moduleButtons);
            toggleButton.setOnAction(e -> {
                log.info("Side Panel button pressed: {} -> {}",
                        buttonConfig.getKey(), buttonConfig.getValue().layoutClass().getSimpleName());
                mainController.setContentListView(buttonConfig);
            });
            buttonContainer.getChildren().add(toggleButton);
        }
        buttonContainer.getStyleClass().setAll("button-container");
    }

    public void setConnectionStatus(boolean status) {
        if (status) {
            connectionStatusLabel.setText("Connected");
            connectionStatusLabel.getStyleClass().setAll("connected", "connection-status");
        } else {
            connectionStatusLabel.setText("Disconnected");
            connectionStatusLabel.getStyleClass().setAll("disconnected", "connection-status");
        }
    }

}

