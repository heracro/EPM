package hihi.content.common;

import hihi.adapter.AdapterBuilder;
import hihi.adapter.WarehouseAdapter;
import hihi.application.config.GuiConfig;
import hihi.application.config.ModuleConfig;
import hihi.components.MainController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ToString
public abstract class ContentLayoutController {

    @FXML
    protected BorderPane contentView;
    @FXML
    protected HBox bottomPanel;
    @Autowired @Getter
    protected MainController mainController;
    @Getter
    protected WarehouseAdapter adapter;
    @Getter
    protected String moduleName;
    protected Integer parentUid;

    protected ContentLayoutController(String moduleName) {
        log.info("\033[96m ContentLayoutController({}) \033[m", moduleName);
        this.moduleName = moduleName;
        setAdapter();
    }

    protected void setAdapter() {
        ModuleConfig config = ModuleConfig.getInstance(moduleName);
        log.info("\033[96m setAdapter() for ContentLayoutController in module {} \033[m", moduleName);
        if (config.isPrimary()) {
            log.info("\033[96m PRIMARY module \033[m");
            adapter = new AdapterBuilder()
                    .setModuleConfig(ModuleConfig.getInstance(moduleName))
                    .build();
        } else if (config.isDependant() && parentUid != null) {
            log.info("\033[96m DEPENDANT module \033[m");
            adapter = new AdapterBuilder()
                    .setModuleConfig(ModuleConfig.getInstance(moduleName))
                    .setParentUid(parentUid)
                    .build();
        }
        //log.info("\033[96m Adapter set to: {} \033[m",adapter);
    }

    public void initialize() {
        log.info("\033[96m initialize()\033[m");
        contentView.getStyleClass().addAll("content-view", "content-grid");
        if (bottomPanel != null) bottomPanel.getStyleClass().add("bottom-panel");
        bindFontSize();
    }

    private void bindFontSize() {
        StringBinding fontSizeBinding = Bindings.createStringBinding(
                () -> {
                    double size = Math.min(
                            Math.max(contentView.getWidth() * 0.012, GuiConfig.MIN_FONT_SIZE),
                            GuiConfig.MAX_FONT_SIZE);
                    return "-fx-font-size: " + String.format("%.0f", size) + "px;";
                },
                contentView.widthProperty()
        );
        contentView.styleProperty().bind(fontSizeBinding);
    }

    protected List<Field> getAllFields() {
        List<Field> fields = new ArrayList<>();
        Class<?> superclass = this.getClass().getSuperclass();
        if (superclass != null) {
            fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
        }
        fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
        return fields;
    }

}
