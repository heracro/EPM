package hihi.content.common.contentDetails;

import hihi.adapters.MainResourceAbstractAdapter;
import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public abstract class ContentDetailsLayoutController<
        Content extends AbstractContent,
        DTO extends AbstractDto,
        Adapter extends MainResourceAbstractAdapter<DTO>>
        extends ContentLayoutController<Adapter> {

    @FXML
    protected GridPane grid;
    @FXML
    protected Label uidField;

    public ContentDetailsLayoutController(Adapter adapter, String contentName) {
        super(adapter, contentName);
        log.info("\033[96m ContentDetailsLayoutController({}, {}) \033[m", adapter, contentName);
    }

    public abstract void setContent(Content content);

    public void initialize() {
        super.initialize();
        grid.getStyleClass().add("content-grid");
    }
}
