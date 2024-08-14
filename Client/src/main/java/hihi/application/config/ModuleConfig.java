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
import java.util.NoSuchElementException;

/**
 * This class is a mixture of Builder and Multiton, a bit weird tool which doesn't let create ad hoc objects in set
 * of instances, but instead throws an exception with message pointing to a builder. Of course builder also doesn't let
 * creating multiple instances of the same configuration, and since config's values purely depend on module name, there's
 * no way to duplicate module with different configuration.
 * To see examples how it can be used for various module types - check AppConfig class, where instances are created.
 */

@Getter
@Slf4j
@ToString
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class ModuleConfig {

    private String moduleName;
    private String endpoint;
    private Class<? extends AbstractContent> contentClass;
    private Class<? extends AbstractDto> dtoClass;
    private Class<? extends ContentListLayoutController<? extends AbstractContent>> listControllerClass;
    private Class<? extends ContentDetailsLayoutController<? extends AbstractContent>> detailsControllerClass;
    private Class<?> listBottomPanelConfiguration;
    private Class<?> detailsBottomPanelConfiguration;
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
        ModuleConfig config = instances.get(moduleName);
        if (config == null) {
            throw new NoSuchElementException("No ModuleConfig found for module: " + moduleName +
                    ". Use Builder to configure it before use.");
        }
        return config;
    }

    private static boolean exists(String moduleName) {
        return instances.containsKey(moduleName);
    }

    private ModuleConfig(ModuleConfig.Builder builder) {
        this.moduleName = builder.moduleName;
        this.endpoint = builder.endpoint;
        this.contentClass = builder.contentClass;
        this.dtoClass = builder.dtoClass;
        this.listControllerClass = builder.listControllerClass;
        this.detailsControllerClass = builder.detailsControllerClass;
        this.listBottomPanelConfiguration = builder.listBottomPanelConfiguration;
        this.detailsBottomPanelConfiguration = builder.detailsBottomPanelConfiguration;
        this.superResourceModule = builder.superResourceModule;
        this.type = builder.type;
        this.detailsLayoutPath = builder.detailsLayoutPath;
        this.listLayoutPath = builder.listLayoutPath;
        this.modulePath = builder.modulePath;
        this.classPath = builder.classPath;
        this.contentConstructor = builder.contentConstructor;
        this.dtoConstructor = builder.dtoConstructor;
        instances.put(moduleName, this);
    }

    public boolean isDependant() {
        return type.equals(ResourceType.DEPENDANT);
    }

    public boolean isPrimary() {
        return type.equals(ResourceType.PRIMARY);
    }

    public boolean hasDetailsView() {
        return detailsLayoutPath != null && detailsControllerClass != null && contentClass != null;
    }

    public boolean hasListView() {
        return listLayoutPath != null && listControllerClass != null && contentClass != null;
    }

    public boolean hasDetailsViewBottomPanel() {
        return detailsBottomPanelConfiguration != null;
    }

    public boolean hasListViewBottomPanel() {
        return listBottomPanelConfiguration != null;
    }

    public boolean hasBottomPanel() {
        return (hasListView() || hasDetailsView()) &&
                (!hasListView() || listBottomPanelConfiguration != null) &&
                (!hasDetailsView() || detailsBottomPanelConfiguration != null);
    }

    @ToString
    @SuppressWarnings("unchecked")
    public static class Builder {

        private String moduleName;
        private String endpoint;
        private Class<? extends AbstractContent> contentClass;
        private Class<? extends AbstractDto> dtoClass;
        private Class<? extends ContentListLayoutController<? extends AbstractContent>> listControllerClass;
        private Class<? extends ContentDetailsLayoutController<? extends AbstractContent>> detailsControllerClass;
        private Class<?> listBottomPanelConfiguration;
        private Class<?> detailsBottomPanelConfiguration;
        private String superResourceModule;
        private ResourceType type;
        private String detailsLayoutPath;
        private String listLayoutPath;
        private String modulePath;
        private String classPath;
        private Constructor<? extends AbstractContent> contentConstructor;
        private Constructor<? extends AbstractDto> dtoConstructor;

        public Builder(String moduleName) {
            if (ModuleConfig.exists(moduleName)) {
                throw new IllegalStateException("ModuleConfig for '" + moduleName +
                        "' already exists and cannot be redefined.");
            }
            this.moduleName = moduleName;
            this.modulePath = "hihi.content." + moduleName.toLowerCase();
            this.classPath = modulePath + "." + moduleName;
            this.type = ResourceType.PRIMARY;
            this.setEndpoint();
            log.info("\033[91m Builder({}) --> {}\033[m", moduleName, this);
        }

        public Builder setListViewControllerClass() {
            this.listLayoutPath = "/layout/content/" + moduleName.toLowerCase() + "/" + moduleName + "ListLayout.fxml";
            try {
                this.listControllerClass =
                        (Class<? extends ContentListLayoutController<? extends AbstractContent>>)
                                Class.forName(classPath + "ListLayoutController");
            } catch (ClassNotFoundException e) {
                log.error("Missing list view controller class: {}", classPath + "ListLayoutController");
                throw new IllegalStateException("Missing list view controller class: {}" + classPath + "ListLayoutController", e);
            }
            log.info("\033[91m setListViewControllerClass() --> {}\033[m", this);
            return this;
        }

        public Builder setDetailsViewControllerClass() {
            this.detailsLayoutPath = "/layout/content/" + moduleName.toLowerCase() + "/" + moduleName + "DetailsLayout.fxml";
            try {
                this.detailsControllerClass = (
                        Class<? extends ContentDetailsLayoutController<? extends AbstractContent>>)
                        Class.forName(classPath + "DetailsLayoutController");
            } catch (ClassNotFoundException e) {
                log.error("Missing details view controller class: {}", classPath + "DetailsLayoutController");
                throw new IllegalStateException("Missing details view controller class: " + classPath + "DetailsLayoutController", e);
            }
            log.info("\033[91m setDetailsViewControllerClass() --> {}\033[m", this);
            return this;
        }

        public Builder setListViewBottomPanelConfiguration() {
            try {
                this.listBottomPanelConfiguration = Class.forName(classPath + "ListBottomPanelConfiguration");
            } catch (ClassNotFoundException e) {
                log.error("Missing bottom panel configuration for list view: {}",
                        classPath + "ListBottomPanelConfiguration");
                throw new IllegalStateException("Missing bottom panel configuration for list view: " +
                        classPath + "ListBottomPanelConfiguration", e);
            }
            log.info("\033[91m setListViewBottomPanelConfiguration() --> {}\033[m", this);
            return this;
        }

        public Builder setDetailsViewBottomPanelConfiguration() {
            try {
                this.detailsBottomPanelConfiguration = Class.forName(classPath + "DetailsBottomPanelConfiguration");
            } catch (ClassNotFoundException e) {
                log.error("Missing bottom panel configuration for details view: {}",
                        classPath + "DetailsBottomPanelConfiguration");
                throw new IllegalStateException("Missing bottom panel configuration for details view: " +
                        classPath + "DetailsBottomPanelConfiguration", e);
            }
            log.info("\033[91m setDetailsViewBottomPanelConfiguration() --> {}\033[m", this);
            return this;
        }

        public Builder setSuperResourceModule(String superResourceModule) {
            this.superResourceModule = superResourceModule;
            this.type = ResourceType.DEPENDANT;
            setEndpoint();
            log.info("\033[91m setSuperResourceModule() --> {}\033[m", this);
            return this;
        }

        public ModuleConfig build() {
            return new ModuleConfig(this);
        }

        Builder setOnlyContentClass() {
            try {
                this.contentClass = (Class<? extends AbstractContent>) Class.forName(classPath);
            } catch (ClassNotFoundException e) {
                log.error("Missing content class: {}", classPath);
                throw new IllegalStateException("Missing content class: " + classPath, e);
            }
            return this;
        }

        Builder setContentAndDtoClasses() {
            setOnlyContentClass();
            try {
                this.dtoClass = (Class<? extends AbstractDto>) Class.forName(classPath + "Dto");
            } catch (ClassNotFoundException e) {
                log.error("Missing DTO class: {}", classPath + "Dto");
                throw new IllegalStateException("Missing DTO class: " + classPath + "Dto", e);
            }
            try {
                this.contentConstructor = contentClass.getDeclaredConstructor(dtoClass);
            } catch (NoSuchMethodException e) {
                log.error("Proper content constructor not found in {}", contentClass);
                throw new IllegalStateException("Proper content constructor not found in " + contentClass + " class.", e);
            }
            try {
                this.dtoConstructor = dtoClass.getDeclaredConstructor(contentClass);
            } catch (NoSuchMethodException e) {
                log.error("Proper DTO constructor not found in {}", dtoClass);
                throw new IllegalStateException("Proper DTO constructor not found in " + dtoClass + " class.", e);
            }
            log.info("\033[91m setContentAndDtoClasses() --> {}\033[m", this);
            return this;
        }

        private Builder setEndpoint() {
            if (type.equals(ResourceType.PRIMARY)) {
                this.endpoint = "/" + pluralize(moduleName).toLowerCase();
            } else {
                this.endpoint = "/" + pluralize(superResourceModule).toLowerCase() +
                        "/{pid}/" + pluralize(moduleName).toLowerCase();
            }
            log.info("\033[91m setEndpoint() --> {}\033[m", this);
            return this;
        }

        private static String pluralize(String moduleName) {
            if (moduleName.endsWith("y")) {
                return moduleName.substring(0, moduleName.length() - 1) + "ies";
            }
            return moduleName + "s";
        }

    }

}

