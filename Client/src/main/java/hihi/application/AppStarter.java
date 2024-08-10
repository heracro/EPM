package hihi.application;

import hihi.application.config.AppConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import static hihi.application.config.GuiConfig.BASE_HEIGHT;
import static hihi.application.config.GuiConfig.BASE_WIDTH;

@Slf4j
@Component
public class AppStarter extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.launch(AppStarter.class, args);
    }

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AppConfig.class);
        builder.headless(false).web(org.springframework.boot.WebApplicationType.NONE);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/Main.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);
        scene.getStylesheets().setAll("/styles/scene.css", "/styles/themes/dark-theme.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("EPM by Limonek");
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
