package hihi.components;

import hihi.application.config.ModuleConfig;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainController {

    @FXML
    public BorderPane mainWindow;
    @FXML
    public MenuBar mainMenu;
    @FXML
    public VBox sidePanel;
    @FXML
    private BorderPane contentPane;
    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void initialize() {
        log.info("\033[31m MainController initialized\033[m");
        log.info("\033[31m MainController: {}\033[m", this);
        log.info("\033[31m contentPane: {} \033[m", contentPane);
    }

    public <Controller extends ContentListLayoutController<?,?,?>>
    void setContentListView(Map.Entry<String, ModuleConfig> moduleConfig) {
        log.info("\033[31m setContentListView({})\033[m", moduleConfig.getKey());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(moduleConfig.getValue().fxmlPath()));
        try {
            Node node = loader.load();
            Controller controller = loader.getController();
            controller.setMainController(this);
            contentPane.setCenter(node);
            log.info("\033[31m contentView's parent: {}\033[m", node.getParent());
        } catch (IOException e) {
            log.error("Failed to load content view", e);
        }
    }

    public <Content extends AbstractContent, Controller extends ContentDetailsLayoutController<Content,?,?>>
    void setContentObjectView(Map.Entry<String, ModuleConfig> moduleConfig, Content content) {
        log.info("\033[31m setContentObjectView({}, {})\033[m", moduleConfig.getKey(), content.uidProperty().getValue());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getContentViewFxmlPath(moduleConfig)));
        try {
            Node node = loader.load();
            Controller controller = loader.getController();
            controller.setContent(content);
            contentPane.setCenter(node);
            log.info("\033[31m contentView's parent: {}", node.getParent());
        } catch (IOException e) {
            log.error("Failed to load content view", e);
        }
    }

    private String getContentViewFxmlPath(Map.Entry<String, ModuleConfig> moduleConfig) {
        return  "/layout/content/" + moduleConfig.getKey().toLowerCase()
                + "/Single" + moduleConfig.getKey() + "Layout.fxml";
    }

    private String getBottomViewFxmlPath(Map.Entry<String, ModuleConfig> moduleConfig) {
        return  "/layout/content/" + moduleConfig.getKey().toLowerCase()
                + "/" + moduleConfig.getKey() + "BottomLayout.fxml";
    }

 }
