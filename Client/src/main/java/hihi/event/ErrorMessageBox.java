package hihi.event;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorMessageBox {

    public static void showError(String message) {
        Platform.runLater(() -> {
            ButtonType closeButton = new ButtonType("Close application");
            Alert alert = new Alert(Alert.AlertType.ERROR, message, closeButton);
            alert.setTitle("Terminal error");
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == closeButton) {
                    closeApplication();
                }
            });
        });
    }

    private static void closeApplication() {
        log.warn("Application terminated due to error which makes going on pointless");
        Platform.exit();
        System.exit(1);
    }
}
