package hihi.components;

import com.google.common.eventbus.EventBus;
import hihi.application.config.GuiConfig;
import hihi.event.ConnectionTestEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainMenuController {

    @FXML
    public MenuBar mainMenu;
    public final EventBus eventBus;

    @FXML
    public void initialize() {
        log.info("Initialize Main Menu Controller");
    }

    @FXML
    private void testConnection() {
        try {
            HttpResponse<String> response = newHttpClient().send(
                    HttpRequest.newBuilder().uri(URI.create(GuiConfig.API_URL + "/utils")).GET().build(),
                    HttpResponse.BodyHandlers.ofString());
            log.info("Received response with status code: {}", response.statusCode());
            eventBus.post(new ConnectionTestEvent(response.statusCode() == 200));
        } catch (InterruptedException | IOException e) {
            log.error("Exception occurred while sending request: {}",
                    e.getMessage().substring(0, Math.min(200, e.getMessage().length())));
            eventBus.post(new ConnectionTestEvent(false));
        }
    }

    @FXML
    public void loadDarkTheme() {
        log.info("Loading Dark Theme");
        Scene scene = mainMenu.getScene();
        if (scene == null) throw new IllegalStateException("Scene is null");
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll("/styles/style.css", "/styles/themes/dark-theme.css");
    }

    @FXML
    private void loadLightTheme() {
        log.info("Loading Light Theme");
        Scene scene = mainMenu.getScene();
        if (scene == null) throw new IllegalStateException("Scene is null");
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll("/styles/style.css", "/styles/themes/light-theme.css");
    }

}
