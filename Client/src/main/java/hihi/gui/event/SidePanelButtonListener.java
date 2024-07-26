package hihi.gui.event;

import hihi.gui.components.ContentWindow;
import hihi.application.MainWindow;
import hihi.gui.layout.BottomPanelConfig;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SidePanelButtonListener implements EventHandler<ActionEvent> {

    private final MainWindow mainWindow;
    private final Class<? extends ContentWindow> layoutClass;

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            log.info("Side Panel Button Clicked. Trying to apply layout: {}", layoutClass.getSimpleName());
            mainWindow.setContentWindow(layoutClass.getDeclaredConstructor().newInstance());
            log.info("Successfully applied layout: {}", layoutClass.getSimpleName());
        } catch (Exception e) {
            log.error("Error applying layout to ContentWindow: {}", e.getMessage(), e);
        }
    }
}
