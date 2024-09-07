package hihi.content.common.contentDetails;

import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public abstract class ContentDetailsLayoutController<
        Content extends AbstractContent>
        extends ContentLayoutController {

    @FXML
    protected Label uidField;

    protected Content content;

    public ContentDetailsLayoutController(String moduleName, Content content) {
        super(moduleName);
        this.content = content;
        log.info("\033[96m ContentDetailsLayoutController({}, {}) \033[m", moduleName, content);
    }

    public void initialize() {
        log.info("\033[96m initialize()\033[m");
        super.initialize();
        log.info("\033[96m END OF initialize()\033[m");
    }

}
