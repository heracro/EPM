package hihi.event;

import hihi.application.config.ModuleConfig;
import hihi.components.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SidePanelButtonEventHandler implements EventHandler<ActionEvent> {

    private final MainController mainController;
    private final String moduleName;

    @Override
    public void handle(ActionEvent event) {
        log.info("\033[35mSide Panel button pressed: {} -> {}\033[m",
                moduleName, ModuleConfig.getInstance(moduleName).getListControllerClass().getSimpleName());
        log.info("\033[35mModule configuration used: {}", ModuleConfig.getInstance(moduleName));
        mainController.setContentListView(moduleName);
    }

}
