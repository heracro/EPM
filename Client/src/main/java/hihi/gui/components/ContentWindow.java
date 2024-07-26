package hihi.gui.components;

import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentWindow extends BorderPane {

    public ContentWindow() {
        super();
        log.info("Plain ContentWindow object created");
    }
}
