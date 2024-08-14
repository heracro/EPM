package hihi.application.config;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import hihi.content.common.contentList.ContentListLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import hihi.content.common.dataModel.AbstractDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
@ToString
@AllArgsConstructor
public class ModuleConfig {

    private String moduleName;
    private String endpoint;
    private Class<? extends AbstractContent> contentClass;
    private Class<? extends AbstractDto> dtoClass;
    private Class<? extends ContentListLayoutController<? extends AbstractContent>> listControllerClass;
    private Class<? extends ContentDetailsLayoutController<? extends AbstractContent>> detailsControllerClass;
    private String superResourceModule;
    private ResourceType type;
    private String detailsLayoutPath;
    private String listLayoutPath;
    private String modulePath;
    private String classPath;
    private Constructor<? extends AbstractContent> contentConstructor;
    private Constructor<? extends AbstractDto> dtoConstructor;

    //"Multiton"
    @Getter(AccessLevel.NONE)
    private static final Map<String, ModuleConfig> instances = new HashMap<>();
    public static ModuleConfig getInstance(String moduleName) {
        return instances.computeIfAbsent(moduleName, ModuleConfig::new);
    }

    @SuppressWarnings("unchecked")
    private ModuleConfig(String moduleName) {
        this.moduleName = moduleName;
        this.listLayoutPath = "/layout/content/" + moduleName.toLowerCase() + "/" + moduleName + "ListLayout.fxml";
        this.detailsLayoutPath = "/layout/content/" + moduleName.toLowerCase() + "/" + moduleName + "DetailsLayout.fxml";
        this.modulePath = "hihi.content." + moduleName.toLowerCase();
        this.classPath = modulePath + "." + moduleName;
        try {
            this.contentClass = (Class<? extends AbstractContent>) Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", classPath);
            throw new IllegalStateException("Class not found: " + classPath, e);
        }
        try {
            this.dtoClass = (Class<? extends AbstractDto>) Class.forName(classPath + "Dto");
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", classPath + "Dto");
            throw new IllegalStateException("Class not found: " + classPath + "Dto", e);
        }
        try {
            this.listControllerClass =
                    (Class<? extends ContentListLayoutController<? extends AbstractContent>>)
                            Class.forName(classPath + "ListLayoutController");
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", classPath + "ListLayoutController");
            throw new IllegalStateException("Class not found: " + classPath + "ListLayoutController", e);
        }
        try {
            this.detailsControllerClass = (
                    Class<? extends ContentDetailsLayoutController<? extends AbstractContent>>)
                    Class.forName(classPath + "DetailsLayoutController");
        } catch (ClassNotFoundException e) {
            log.error("Class not found: {}", classPath + "DetailsLayoutController");
            throw new IllegalStateException("Class not found: " + classPath + "DetailsLayoutController", e);
        }
        try {
            this.contentConstructor = contentClass.getDeclaredConstructor(dtoClass);
        } catch (NoSuchMethodException e) {
            log.error("Constructor not found: {}", contentClass);
            throw new IllegalStateException("No constructor found in " + contentClass + " class.", e);
        }
        try {
            this.dtoConstructor = dtoClass.getDeclaredConstructor(contentClass);
        } catch (NoSuchMethodException e) {
            log.error("Constructor not found: {}", dtoClass);
            throw new IllegalStateException("No constructor found in " + dtoClass + " class.", e);
        }
        this.type = ResourceType.PRIMARY;
        setEndpoint();
    }

    public ModuleConfig setSuperResourceModule(String superResourceModule) {
        this.superResourceModule = superResourceModule;
        this.type = ResourceType.DEPENDANT;
        setEndpoint();
        return this;
    }

    private void setEndpoint() {
        if (type.equals(ResourceType.PRIMARY)) {
            this.endpoint = "/" + pluralize(moduleName).toLowerCase();
        } else {
            this.endpoint = "/" + pluralize(superResourceModule).toLowerCase() +
                    "/{pid}/" + pluralize(moduleName).toLowerCase();
        }
    }

    private static String pluralize(String moduleName) {
        if (moduleName.endsWith("y")) {
            return moduleName.substring(0, moduleName.length() - 1) + "ies";
        }
        return moduleName + "s";
    }

    public boolean isDependant() {
        return type.equals(ResourceType.DEPENDANT);
    }

    public boolean isPrimary() {
        return type.equals(ResourceType.PRIMARY);
    }

}

