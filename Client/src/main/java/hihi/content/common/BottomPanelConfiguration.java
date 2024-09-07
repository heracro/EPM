package hihi.content.common;

import hihi.application.config.GuiConfig;
import hihi.components.MainController;
import hihi.content.common.dataModel.BottomPanelButtonConfig;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BottomPanelConfiguration {

    protected List<BottomPanelButtonConfig> bottomPanelButtonConfigs;
    private static final String BOTTOM_BUTTON_STYLE_CLASS = "bottom-panel-button";

    protected BottomPanelConfiguration() {
        bottomPanelButtonConfigs = new ArrayList<>();
    }

    public void setupBottomPanel(HBox bottomPanel) {
        log.info("\033[93m setupBottomPanel({}) \033[0m", bottomPanel);
        addButtons(bottomPanel);
        bottomPanel.setPrefHeight(GuiConfig.BOTTOM_PANEL_HEIGHT);
        bottomPanel.setMinHeight(GuiConfig.BOTTOM_PANEL_HEIGHT);
        bottomPanel.setMaxHeight(GuiConfig.BOTTOM_PANEL_HEIGHT);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.widthProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonLayout(bottomPanel, newValue.doubleValue());
        });
        //VBox.setVgrow(bottomPanel, Priority.NEVER);
        log.info("\033[93m Bottom Panel set up: {}, prefHeight: {}, height: {}, heightProperty: {}, " +
                "alignment: {}, styleClasses: {} \033[m", bottomPanel, bottomPanel.getPrefHeight(),
                bottomPanel.getHeight(), bottomPanel.heightProperty(), bottomPanel.getAlignment(),
                bottomPanel.getStyleClass().toString());
    }

    private void addButtons(HBox bottomPanel) {
        log.info("\033[93m addButtons({}) \033[0m", bottomPanel);
        for (BottomPanelButtonConfig buttonConfig : bottomPanelButtonConfigs) {
            Button button = new Button(buttonConfig.label());
            button.setOnAction(buttonConfig.eventHandler());
            button.setPrefHeight(GuiConfig.BOTTOM_PANEL_BUTTON_HEIGHT);
            button.getStyleClass().addAll(BOTTOM_BUTTON_STYLE_CLASS);
            if (buttonConfig.visibilityCondition() != null) {
                button.setVisible(buttonConfig.visibilityCondition().getAsBoolean());
            }
            bottomPanel.getChildren().add(button);
        }
    }

    private void updateButtonLayout(HBox bottomPanel, double panelWidth) {
        log.info("\033[93m updateButtonLayout({}, {}) \033[0m", bottomPanel, panelWidth);
        int buttonCount = bottomPanelButtonConfigs.size();
        double buttonWidth = 2 * panelWidth / (3 * buttonCount);
        double margin = (GuiConfig.BOTTOM_PANEL_HEIGHT - GuiConfig.BOTTOM_PANEL_BUTTON_HEIGHT) / 2;
        bottomPanel.setSpacing(buttonWidth / 2);
        bottomPanel.setPadding(new Insets(margin, buttonWidth / 4, margin, buttonWidth / 4));
        for (Node node : bottomPanel.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(buttonWidth);
                ((Button) node).setPrefHeight(GuiConfig.BOTTOM_PANEL_BUTTON_HEIGHT);
            }
        }
    }

}
