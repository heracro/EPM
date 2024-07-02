package hihi.application;

import hihi.application.config.GuiConfig;
import hihi.gui.components.BottomPanel;
import hihi.gui.components.ContentWindow;
import hihi.gui.components.MainMenu;
import hihi.gui.components.SidePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class MainWindow extends Application {
    private Scene scene;
    private BorderPane window;
    private MainMenu mainMenu;
    @Getter
    private SidePanel sidePanel;
    @Getter
    private ContentWindow contentWindow;
    private BottomPanel bottomPanel;
    private String currentTheme = "none";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Client Application");
        primaryStage.setWidth(GuiConfig.BASE_WIDTH);
        primaryStage.setHeight(GuiConfig.BASE_HEIGHT);

        window = new BorderPane();
        mainMenu = new MainMenu(this);
        bottomPanel = new BottomPanel(10);
        sidePanel = new SidePanel(10);
        contentWindow = new ContentWindow();
        window.setTop(mainMenu);
        window.setLeft(sidePanel);
        window.setCenter(contentWindow);
        window.setBottom(bottomPanel);

        scene = new Scene(window, GuiConfig.BASE_WIDTH, GuiConfig.BASE_HEIGHT);

        scene.setOnKeyPressed(event -> {
            if (event.isAltDown()) {
                mainMenu.setVisible(!mainMenu.isVisible());
            }
        });

        primaryStage.setScene(scene);
        setTheme("light");
        primaryStage.show();
    }

    public void setTheme(String theme) {
        if (currentTheme.equals(theme)) return;
        currentTheme = theme;
        scene.getStylesheets().clear();
        String themeFile = theme.equals("light") ? "/light-theme.css" : "/dark-theme.css";
        String stylesheet = Objects.requireNonNull(getClass().getResource(themeFile)).toExternalForm();
        scene.getStylesheets().add(stylesheet);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
