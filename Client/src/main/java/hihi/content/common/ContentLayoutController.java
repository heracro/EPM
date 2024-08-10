package hihi.content.common;

import hihi.adapters.MainResourceAbstractAdapter;
import hihi.components.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@ToString
public abstract class ContentLayoutController<Adapter extends MainResourceAbstractAdapter<?>> {

    @FXML
    protected BorderPane contentView;
    @FXML
    protected HBox bottomPanel;

    @Autowired
    protected MainController mainController;
    protected Adapter adapter;
    @Getter
    protected String contentName;

    protected ContentLayoutController(Adapter adapter, String contentName) {
        log.info("\033[96m ContentLayoutController({}, {}) \033[m", adapter, contentName);
        this.adapter = adapter;
        this.contentName = contentName;
    }

    public void initialize() {
        contentView.getStyleClass().add("content-view");
        bottomPanel.getStyleClass().add("bottom-panel");
    }

}
