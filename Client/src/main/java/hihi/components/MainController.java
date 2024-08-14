package hihi.components;

import hihi.application.config.ModuleConfig;
import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleConfig.getInstance(moduleName).getListLayoutPath()));
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

    public <Content extends AbstractContent, Controller extends ContentDetailsLayoutController<Content>>
    void setContentDetailsView(String moduleName, Content content) {
        log.info("\033[31m setContentDetailsView({}, {})\033[m", moduleName, content.uidProperty().getValue());
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(ModuleConfig.getInstance(moduleName).getDetailsLayoutPath()));
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

 }
