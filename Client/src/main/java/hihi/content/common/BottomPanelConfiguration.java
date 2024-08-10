package hihi.content.common;

import hihi.content.common.dataModel.BottomPanelButtonConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BottomPanelConfiguration {

    @FXML
    protected HBox bottomPanel;
    protected List<BottomPanelButtonConfig> bottomPanelButtonConfigs;
    private static final String BOTTOM_BUTTON_STYLE_CLASS = "bottom-panel-button";

    protected BottomPanelConfiguration() {
        bottomPanelButtonConfigs = new ArrayList<>();
    }

    protected void loadButtons() {
        for (BottomPanelButtonConfig config : bottomPanelButtonConfigs) {
            Button button = new Button(config.label());
            button.getStyleClass().add(BOTTOM_BUTTON_STYLE_CLASS);
            button.setOnAction(config.eventHandler());
            bottomPanel.getChildren().add(button);
        }
    }
}
