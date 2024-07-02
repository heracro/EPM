package hihi.gui.layout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class BottomPanelConfig {
    @Getter
    @Setter
    private List<Map.Entry<String, EventHandler<ActionEvent>>> buttonConfigs;

    public BottomPanelConfig(List<Map.Entry<String, EventHandler<ActionEvent>>> buttonConfigs) {
        this.buttonConfigs = buttonConfigs;
    }


}
