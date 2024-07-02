package hihi.application.config;

import java.util.Arrays;
import java.util.List;

public class GuiConfig {
    public static final String API_URL = "http://91.150.146.210:21370/";
    public static final Integer BASE_WIDTH = 1200;
    public static final Integer BASE_HEIGHT = 800;
    public static final Integer LEFT_PANEL_BUTTON_WIDTH = 160;
    public static final List<String> MODULE_BUTTONS = Arrays.asList(
            "Project", "Material", "Shopping List", "Delivery",
            "Project Report", "Delivery Report", "Settings"
    );
}
