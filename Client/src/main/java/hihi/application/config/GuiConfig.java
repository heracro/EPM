package hihi.application.config;

import hihi.gui.layout.content.material.MaterialListLayout;
import hihi.gui.layout.content.project.ProjectListLayout;

import java.util.Arrays;
import java.util.List;

public class GuiConfig {
    public static final String API_URL = "http://localhost:12321";
    public static final Integer BASE_WIDTH = 1600;
    public static final Integer BASE_HEIGHT = 1000;
    public static final Integer LEFT_PANEL_BUTTON_WIDTH = 160;

    public static final List<ModuleButtonConfig> MODULE_BUTTONS_CONFIG = Arrays.asList(

            new ModuleButtonConfig("Material", MaterialListLayout.class),
            /*
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


    //total 75
    public static double[] PROJECT_LIST_COL_WIDTHS =
            {0.04, 0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.06, 0.25};

    public static double[] MATERIAL_LIST_COL_WIDTH =
            {0.04, 0.3, 0.1, 0.1, 0.04, 0.04, 0.04, 0.04, 0.3};
}
