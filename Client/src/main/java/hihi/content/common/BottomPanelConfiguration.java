package hihi.content.common;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BottomPanelConfiguration {

    @FXML
    protected HBox bottomPanel;
    protected List<ButtonConfig> buttonConfigs;
    private static final String BOTTOM_BUTTON_STYLE_CLASS = "bottom-panel-button";

    protected BottomPanelConfiguration() {
        buttonConfigs = new ArrayList<>();
    }

    protected void loadButtons() {
        for (ButtonConfig config : buttonConfigs) {
            Button button = new Button(config.label());
            button.getStyleClass().add(BOTTOM_BUTTON_STYLE_CLASS);
            button.setOnAction(config.eventHandler());
            bottomPanel.getChildren().add(button);
        }
    }
}
