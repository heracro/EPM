package hihi.content.common.dataModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.function.BooleanSupplier;


public record BottomPanelButtonConfig(
        String label,
        EventHandler<ActionEvent> eventHandler,
        BooleanSupplier visibilityCondition) {

}
