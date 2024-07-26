package hihi.application;

import hihi.application.config.GuiConfig;
import hihi.gui.components.BottomPanel;
import hihi.gui.components.ContentWindow;
import hihi.gui.components.MainMenu;
import hihi.gui.components.SidePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
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
        log.info("Main window started, resolution: {}x{}", primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            updateContentWindowWidth(newValue.doubleValue());
        });


        window = new BorderPane();
        mainMenu = new MainMenu(this);
        bottomPanel = new BottomPanel(10);
        sidePanel = new SidePanel(this, 10);
        contentWindow = new ContentWindow();
        window.setTop(mainMenu);
        window.setLeft(sidePanel);
        window.setCenter(contentWindow);
        window.setBottom(bottomPanel);
        log.info("Main frames attached to main window");
        scene = new Scene(window, GuiConfig.BASE_WIDTH, GuiConfig.BASE_HEIGHT);
        log.info("Scene instantiated");
        primaryStage.setScene(scene);
        setTheme("light");
        primaryStage.show();
        log.info("Theme set to light");
        log.info("Setting up main window completed successfully");
    }

    private void updateContentWindowWidth(double width) {
        log.info("Updating content window. Curren width = {}", width);
        if (sidePanel.getChildren().isEmpty()) contentWindow.setPrefWidth(width - 160);
        double contentWidth = getContentWidth(width);
        log.info("Calculated content window width = {}", contentWidth);
        contentWindow.setPrefWidth(contentWidth);
    }

    private double getContentWidth(double width) {
        double sidePanelPadding = sidePanel.getPadding().getLeft() + sidePanel.getPadding().getRight() * 2;
        double sidePanelButtonWidth = ((ToggleButton) sidePanel.getChildren().getFirst()).getPrefWidth();
        double contentWindowPadding = contentWindow.getPadding().getLeft() + contentWindow.getPadding().getRight() * 2;
        return width - sidePanelPadding - sidePanelButtonWidth - contentWindowPadding;
    }

    public void setTheme(String theme) {
        log.info("Setting theme to: {}", theme);
        if (currentTheme.equals(theme)) return;
        currentTheme = theme;
        scene.getStylesheets().clear();
        String themeFile = theme.equals("light") ? "/light-theme.css" : "/dark-theme.css";
        String stylesheet = Objects.requireNonNull(getClass().getResource(themeFile)).toExternalForm();
        scene.getStylesheets().add(stylesheet);
        log.info("Theme successfully set to: {}", theme);
    }

    public void setContentWindow(ContentWindow newContent) {
        log.info("Setting content window to {}", newContent);
        contentWindow = newContent;
        window.setCenter(contentWindow);
        log.info("Content window successfully set.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
