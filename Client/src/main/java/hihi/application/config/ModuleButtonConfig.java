package hihi.application.config;

import hihi.gui.components.ContentWindow;

public record ModuleButtonConfig(
        String label,
        Class<? extends ContentWindow> layoutClass) {

}

