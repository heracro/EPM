package hihi.application.config;

import hihi.content.common.contentList.ContentListLayoutController;

public record ModuleConfig(
        Class<? extends ContentListLayoutController<?,?,?>> layoutClass,
        String fxmlPath) {

}

