package hihi.content.common;

import hihi.application.config.GuiConfig;
import javafx.scene.layout.Region;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentSizeManipulators {

    public static void setWidth(Region region, double min, double percent, double max) {
        log.info("\033[95m setWidth(region={}, min={}, percent={}, max={}) \033[m", region, min, percent, max);
        region.setMinWidth(min);
        region.setMaxWidth(max);
        if (region.getParent() != null && region.getParent() instanceof Region) {
            log.info("\033[95m Parent {} has widths: min={}, pref={}, max={}, current={}\033[m",
                    region.getParent(), ((Region) region.getParent()).getMinWidth(),
                    ((Region) region.getParent()).getPrefWidth(), ((Region) region.getParent()).getMaxWidth(),
                    ((Region) region.getParent()).getWidth());
            log.info("\033[95m Parent {} has Properties: min={}, pref={}, max={}, current={}\033[m",
                    region.getParent(), ((Region) region.getParent()).minWidthProperty(),
                    ((Region) region.getParent()).prefWidthProperty(), ((Region) region.getParent()).maxWidthProperty(),
                    ((Region) region.getParent()).widthProperty());
        } else {
            log.info("\033[95m Parent is NULL\033[m");
        }
        region.parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                region.prefWidthProperty().bind(((Region) newParent).widthProperty().multiply(percent / 100.0));
            }
        });
        log.info("\033[95m Width set to: <{}, {}, {}> \033[m", region.getMinWidth(), region.getWidth(), region.getMaxWidth());
    }

    public static void setWidth(Region targetRegion, Region referenceRegion, double scale) {
        log.info("\033[95m setWidth(targetRegion={}, referenceRegion={}, scale={}) \033[m", targetRegion, referenceRegion, scale);
        log.info("\033[95m Reference region's widths: min={}, pref={}, max={}, current={} \033[m",
                referenceRegion.getMinWidth(), referenceRegion.getPrefWidth(), referenceRegion.getMaxWidth(), referenceRegion.getWidth());
        log.info("\033[95m Reference region's Property values: min={}, pref={}, max={}, current={} \033[m",
                referenceRegion.minWidthProperty(), referenceRegion.prefWidthProperty(), referenceRegion.maxWidthProperty(), referenceRegion.widthProperty());
        targetRegion.minWidthProperty().bind(referenceRegion.minWidthProperty().multiply(scale));
        targetRegion.maxWidthProperty().bind(referenceRegion.maxWidthProperty().multiply(scale));
        targetRegion.prefWidthProperty().bind(referenceRegion.widthProperty().multiply(scale));
        log.info("\033[95m Width set to: <{}, {}, {}> \033[m", targetRegion.getMinWidth(), targetRegion.getWidth(), targetRegion.getMaxWidth());
    }

    public static void setWidth(Region targetRegion, Region referenceRegion) {
        log.info("\033[95m setWidth(targetRegion={}, referenceRegion={}) \033[m", targetRegion, referenceRegion);
        setWidth(targetRegion, referenceRegion, 1.0);
    }

    public static void setWidth(Region targetRegion, double scale) {
        setWidth(targetRegion, GuiConfig.MIN_DYNAMIC_CONTENT_WIDTH * scale, scale, GuiConfig.MAX_DYNAMIC_CONTENT_WIDTH * scale);
    }

    public static void setPercentWidth(Region region, double percent) {
        log.info("\033[95m setPercentWidth(region={}, percent={}) \033[m", region, percent);
        setWidth(region, (Region) region.getParent(), percent / 100.0);
    }

    public static void setHeight(Region region, double min, double percent, double max) {
        log.info("\033[95m setHeight(region={}, min={}, percent={}, max={}) \033[m", region, min, percent, max);
        region.setMinHeight(min);
        region.setMaxHeight(max);
        region.parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                region.prefHeightProperty().bind(((Region) newParent).heightProperty().multiply(percent / 100.0));
            }
        });
    }

    public static void setHeight(Region targetRegion, Region referenceRegion, double scale) {
        log.info("\033[95m setHeight(targetRegion={}, referenceRegion={}, scale={}) \033[m", targetRegion, referenceRegion, scale);
        targetRegion.minHeightProperty().bind(referenceRegion.minHeightProperty().multiply(scale));
        targetRegion.maxHeightProperty().bind(referenceRegion.maxHeightProperty().multiply(scale));
        targetRegion.prefHeightProperty().bind(referenceRegion.heightProperty().multiply(scale));
    }

    public static void setHeight(Region targetRegion, Region referenceRegion) {
        log.info("\033[95m setHeight(targetRegion={}, referenceRegion={}) \033[m", targetRegion, referenceRegion);
        setHeight(targetRegion, referenceRegion, 100.0);
    }

    public static void setPercentHeight(Region region, double percent) {
        log.info("\033[95m setPercentHeight(region={}, percent={}) \033[m", region, percent);
        setHeight(region, (Region) region.getParent(), percent);
    }

}
