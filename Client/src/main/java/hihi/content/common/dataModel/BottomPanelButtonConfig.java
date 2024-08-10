package hihi.content.common.dataModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public record BottomPanelButtonConfig(
        String label,
        EventHandler<ActionEvent> eventHandler) {

}
