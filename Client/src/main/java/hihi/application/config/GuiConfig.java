package hihi.application.config;

import hihi.gui.components.ContentWindow;
import hihi.gui.layout.content.project.ProjectListLayout;

import java.util.Arrays;
import java.util.List;

public class GuiConfig {
    public static final String API_URL = "http://localhost:12321";
    public static final Integer BASE_WIDTH = 1600;
    public static final Integer BASE_HEIGHT = 1000;
    public static final Integer LEFT_PANEL_BUTTON_WIDTH = 160;

    public static final List<ModuleButtonConfig> MODULE_BUTTONS_CONFIG = Arrays.asList(
            /*
            new ModuleButtonConfig("Material", ProjectListLayout.class),
            new ModuleButtonConfig("Delivery", DeliveryListLayout.class),
            new ModuleButtonConfig("Invoice", InvoiceListLayout.class),
            new ModuleButtonConfig("Shop", ShopListLayout.class),
             */
            new ModuleButtonConfig("Project", ProjectListLayout.class)/*,
            new ModuleButtonConfig("Change Log", ChangeLogListLayout.class),
            new ModuleButtonConfig("Bom", BomListLayout.class),
            new ModuleButtonConfig("Tag", TagListLayout.class),
            new ModuleButtonConfig("Task", TaskListLayout.class),
            new ModuleButtonConfig("Settings", SettingsListLayout.class)
            */
    );

}
