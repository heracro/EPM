package hihi.gui.event;

import hihi.application.config.GuiConfig;
import hihi.application.MainWindow;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static java.net.http.HttpClient.newHttpClient;

@Slf4j
public class ConnectionTestMenuListener implements MenuActionListener {
    private final MainWindow mainWindow;

    public ConnectionTestMenuListener(MainWindow mainWindow) {
        log.info("ConnectionTestMenuListener::ConnectionTestMenuListener()");
        this.mainWindow = mainWindow;
    }

    @Override
    public void onMenuItemSelected() {
        log.info("ConnectionTestMenuListener::onMenuItemSelected()");
        mainWindow.getSidePanel().updateConnectionStatus(testConnection());
    }

    public boolean testConnection() {
        String url = GuiConfig.API_URL + "api/cause?lang=pl";
        HttpClient client = newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        log.info("ConnectionTestMenuListener::testConnection(): request = {}", request.toString());
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Received response with status code: {}", response.statusCode());
        } catch (InterruptedException | IOException e) {
            log.error("Exception occurred while sending request: ", e);
            return false;
        }
        if (response.statusCode() != 200) return false;
        List<String> body = Arrays.asList(response.body().split(","));
        log.info("Response body: {}", body);
        return !body.isEmpty();
    }
}
