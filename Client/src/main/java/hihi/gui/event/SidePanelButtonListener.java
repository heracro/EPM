package hihi.gui.event;

import hihi.gui.components.ContentWindow;
import hihi.application.MainWindow;
import hihi.gui.layout.BottomPanelConfig;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SidePanelButtonListener implements EventHandler<ActionEvent> {

    private final MainWindow mainWindow;
    private final ContentWindow contentWindow;
    private final BottomPanelConfig bottomPanelConfig;

    @Override
    public void handle(ActionEvent actionEvent) {
        mainWindow.getContentWindow().getChildren().setAll(contentWindow.getChildren());
    }
}
