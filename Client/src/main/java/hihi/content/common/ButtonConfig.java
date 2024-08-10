package hihi.content.common;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public record ButtonConfig(
        String label,
        EventHandler<ActionEvent> eventHandler) {

}
