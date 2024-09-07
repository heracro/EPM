package hihi.components;

import hihi.application.config.ModuleConfig;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainController {

    @FXML
    private BorderPane mainWindow;
    @FXML
    private MenuBar mainMenu;
    @FXML
    private VBox sidePanel;
    @FXML
    private BorderPane contentPane;

    @FXML
    public void initialize() {
        log.info("\033[31m MainController initialized\033[m");
        log.info("\033[31m MainController: {}\033[m", this);
        log.info("\033[31m contentPane: {} \033[m", contentPane);

    }

    public <Controller extends ContentListLayoutController<?>>
    void setContentListView(String moduleName) {
        log.info("\033[31m setContentListView({})\033[m", moduleName);
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(ModuleConfig.getInstance(moduleName).getListLayoutPath()));
        try {
            Node contentNode = contentLoader.load();
            Controller controller = contentLoader.getController();
            controller.setMainController(this);
            contentPane.setCenter(contentNode);
            VBox.setVgrow(contentPane, Priority.ALWAYS);
            BorderPane.setAlignment(contentNode, Pos.CENTER);
            log.info("\033[31m contentView's parent: {}\033[m", contentNode.getParent());
        } catch (IOException e) {
            log.error("Failed to load content view", e);
        }
    }

    public <Content extends AbstractContent, Controller extends ContentDetailsLayoutController<Content>>
    void setContentDetailsView(String moduleName, Content content) {
        log.info("\033[31m setContentDetailsView({}, {})\033[m", moduleName, content.uidProperty().getValue());
        FXMLLoader contentLoader = new FXMLLoader(
                getClass().getResource(ModuleConfig.getInstance(moduleName).getDetailsLayoutPath()));
        contentLoader.setControllerFactory(singleParameterConstructorFactory(content));
        try {
            Node contentNode = contentLoader.load();
            contentPane.setCenter(contentNode);
            VBox.setVgrow(contentPane, Priority.ALWAYS);
            log.info("\033[31m contentView's parent: {}", contentNode.getParent());
        } catch (IOException e) {
            log.error("Failed to load content view", e);
        }
    }

    private <Content extends AbstractContent> Callback<Class<?>, Object>
    singleParameterConstructorFactory(Content content) {
        return controllerClass -> {
            try {
                return controllerClass.getConstructor(content.getClass()).newInstance(content);
            } catch (InvocationTargetException | InstantiationException
                     | IllegalAccessException | NoSuchMethodException e) {
                log.error("Failed to load details view due to constructor error. Error: {}", e.getMessage());
                return null;
            }
        };
    }

 }
