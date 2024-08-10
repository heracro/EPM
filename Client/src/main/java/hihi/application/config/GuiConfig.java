package hihi.application.config;

import hihi.content.delivery.DeliveryListLayoutController;
import hihi.content.material.MaterialListLayoutController;
import hihi.content.project.ProjectListLayoutController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class GuiConfig {
    public static final String API_URL = "http://localhost:12321";
    public static final Integer BASE_WIDTH = 1600;
    public static final Integer BASE_HEIGHT = 1000;
    public static final Integer LEFT_PANEL_WIDTH = 200;
    public static final Integer LEFT_PANEL_BUTTON_WIDTH = LEFT_PANEL_WIDTH - 40;
    public static final Integer LEFT_PANEL_BUTTON_HEIGHT = 30;

    public static final Map<String, ModuleConfig> MODULES_CONFIG = Map.of(
            "Material", new ModuleConfig(MaterialListLayoutController.class, "/layout/content/material/MaterialListLayout.fxml"),
            "Delivery", new ModuleConfig(DeliveryListLayoutController.class, "/layout/content/delivery/DeliveryListLayout.fxml"),
            "Project", new ModuleConfig(ProjectListLayoutController.class, "/layout/content/project/ProjectListLayout.fxml")
    );

    public static Map.Entry<String, ModuleConfig> getConfigForModule(String moduleName) {
        return MODULES_CONFIG.entrySet().stream()
                .filter(entry -> entry.getKey().equals(moduleName))
                .findFirst()
                .orElse(null);
    }


    //total 75
    public static final double[] PROJECT_LIST_COL_WIDTHS =
            {0.02, 0.04, 0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.06, 0.23};

    public static final double[] MATERIAL_LIST_COL_WIDTH =
            {0.02, 0.04, 0.3, 0.1, 0.1, 0.04, 0.04, 0.04, 0.04, 0.28};

    public static final double[] DELIVERY_LIST_COL_WIDTH =
            {0.02, 0.04, 0.4, 0.1, 0.1, 0.1, 0.1, 0.1};
}
