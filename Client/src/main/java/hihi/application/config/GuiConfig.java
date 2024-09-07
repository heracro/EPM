package hihi.application.config;

import hihi.content.material.Material;

import java.util.List;
import java.util.Map;

public final class GuiConfig {

    public static final boolean DEBUG_MODE = false;

    /*
    Server configuration
     */
    public static final String API_URL = "http://localhost:12321";

    /*
    Application window general configuration
     */
    public static final double BASE_WIDTH = 1600;
    public static final double BASE_HEIGHT = 1000;

    public static final double LEFT_PANEL_WIDTH = 200;
    public static final double LEFT_PANEL_BUTTON_WIDTH = LEFT_PANEL_WIDTH - 40;
    public static final double LEFT_PANEL_BUTTON_HEIGHT = 30;

    public static final double MIN_DYNAMIC_CONTENT_WIDTH = 1200 - LEFT_PANEL_WIDTH; // "feeling" ;)
    public static final double MAX_DYNAMIC_CONTENT_WIDTH = 1920.0 - LEFT_PANEL_WIDTH;

    public static final int MIN_FONT_SIZE = 14;
    public static final int MAX_FONT_SIZE = 18;

    public static final double BOTTOM_PANEL_BUTTON_HEIGHT = LEFT_PANEL_BUTTON_HEIGHT;
    public static final double BOTTOM_PANEL_HEIGHT = BOTTOM_PANEL_BUTTON_HEIGHT + 10;

    /*
    List views configuration
     */
    public static final double[] BASE_COL_WIDTH = {0.02, 0.04};
    public static final double[] BOM_LIST_COL_WIDTH = {0.04, 0.8, 0.19};
    public static final double[] CHANGELOG_LIST_COL_WIDTH = {0.04, 0.4, 0.1, 0.1, 0.1, 0.24};
    public static final double[] DELIVERY_LIST_COL_WIDTH = {0.4, 0.1, 0.1, 0.1, 0.1, 0.1};
    public static final double[] INVOICE_LIST_COL_WIDTH = {0.08, 0.15, 0.15, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.11};
    public static final double[] MATERIAL_LIST_COL_WIDTH = {0.3, 0.1, 0.1, 0.04, 0.04, 0.04, 0.04, 0.28};
    public static final double[] PROJECT_LIST_COL_WIDTH = {0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.06, 0.23};
    public static final double[] SHOP_LIST_COL_WIDTH = {0.2, 0.2, 0.2, 0.14, 0.1, 0.1};
    public static final double[] TAG_LIST_COL_WIDTH = {0.7, 0.29};
    public static final double[] TASK_LIST_COL_WIDTH = {0.6, 0.19, 0.19};

}
