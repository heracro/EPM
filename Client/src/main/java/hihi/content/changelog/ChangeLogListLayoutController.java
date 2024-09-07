package hihi.content.changelog;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.enums.ChangeLogSource;
import hihi.content.enums.ChangeType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class ChangeLogListLayoutController
        extends ContentListLayoutController<ChangeLog> {

    @FXML
    private TableColumn<ChangeLog, Integer> projectUidColumn;
    @FXML
    private TableColumn<ChangeLog, String> projectNameColumn;
    @FXML
    private TableColumn<ChangeLog, LocalDate> timestampColumn;
    @FXML
    private TableColumn<ChangeLog, ChangeLogSource> sourceColumn;
    @FXML
    private TableColumn<ChangeLog, ChangeType> changeTypeColumn;
    @FXML
    private TableColumn<ChangeLog, String> authorColumn;

    public ChangeLogListLayoutController() {
        super("ChangeLog");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.CHANGELOG_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
    }
}
