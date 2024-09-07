package hihi.content.tag;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.enums.TagType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class TagListLayoutController
        extends ContentListLayoutController<Tag> {

    @FXML
    private TableColumn<Tag, String> tagNameColumn;
    @FXML
    private TableColumn<Tag, TagType> tagTypeColumn;

    public TagListLayoutController() {
        super("Tag");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.TAG_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }

}
