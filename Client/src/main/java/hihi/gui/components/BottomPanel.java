package hihi.gui.components;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.List;
import java.util.Map;

public class BottomPanel extends HBox {

    public BottomPanel(double spacing) {
        super(spacing);
        this.getStyleClass().add("bottom-panel");
    }

    public void configureButtons(List<Map.Entry<String, EventHandler<ActionEvent>>> buttonConfiguration) {
        this.getChildren().clear();
        for (Map.Entry<String, EventHandler<ActionEvent>> entry : buttonConfiguration) {
            String buttonText = entry.getKey();
            EventHandler<ActionEvent> eventHandler = entry.getValue();
            Button button = new Button(buttonText);
            button.getStyleClass().add("bottom-panel-button");
            button.setOnAction(eventHandler);
            this.getChildren().add(button);
        }
    }
}
