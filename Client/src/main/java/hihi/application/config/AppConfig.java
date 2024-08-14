package hihi.application.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.google.common.eventbus.EventBus;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.module.InvalidModuleDescriptorException;
import java.util.ArrayList;
import java.util.List;

import static hihi.application.config.GuiConfig.DEBUG_MODE;


@Slf4j
@Configuration
@SpringBootApplication(exclude = {
        WebMvcAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ComponentScan(basePackages = "hihi")
public class AppConfig {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @PostConstruct
    public void configureLogging() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        if (DEBUG_MODE) {
            rootLogger.setLevel(Level.DEBUG);
        } else {
            rootLogger.setLevel(Level.INFO);
        }
    }

    /**
     * Module controllers' configuration. Create instances and provide list for quick pre-start test
     * and SidePanel creation.
     */
    public static List<ModuleConfig> MODULES_CONFIG;

    /**
     * Long method just to precisely trace mistakes in configuration during development. This won't be at all in prod.
     * @throws InvalidModuleDescriptorException
     */
    public static void testModulesConfiguration() throws InvalidModuleDescriptorException {
        log.info("\033[93m testModulesConfiguration() \033[m");
        ModuleConfig material = new ModuleConfig.Builder("Material")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setListViewBottomPanelConfiguration()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Material created: {} \033[m", material);
        ModuleConfig delivery = new ModuleConfig.Builder("Delivery")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setListViewBottomPanelConfiguration()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Delivery created: {} \033[m", delivery);
        ModuleConfig shop = new ModuleConfig.Builder("Shop")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setListViewBottomPanelConfiguration()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Shop created: {} \033[m", shop);
        ModuleConfig invoice = new ModuleConfig.Builder("Invoice")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setListViewBottomPanelConfiguration()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Invoice created: {} \033[m", invoice);
        ModuleConfig tag = new ModuleConfig.Builder("Tag")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .build();
        log.info("\033[93m Tag created: {} \033[m", tag);
        ModuleConfig project = new ModuleConfig.Builder("Project")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setListViewBottomPanelConfiguration()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Project created: {} \033[m", project);
        ModuleConfig bom = new ModuleConfig.Builder("Bom")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setDetailsViewBottomPanelConfiguration()
                .setSuperResourceModule("Project")
                .build();
        log.info("\033[93m Bom created: {} \033[m", bom);
        ModuleConfig task = new ModuleConfig.Builder("Task")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setDetailsViewBottomPanelConfiguration()
                .setSuperResourceModule("Project")
                .build();
        log.info("\033[93m Task created: {} \033[m", task);
        ModuleConfig changeLog = new ModuleConfig.Builder("ChangeLog")
                .setContentAndDtoClasses()
                .setListViewControllerClass()
                .setDetailsViewControllerClass()
                .setDetailsViewBottomPanelConfiguration()
                .setSuperResourceModule("Project")
                .build();
        log.info("\033[93m ChangeLog created: {} \033[m", changeLog);
        ModuleConfig settings = new ModuleConfig.Builder("Settings")
                .setOnlyContentClass()
                .setDetailsViewControllerClass()
                .setDetailsViewBottomPanelConfiguration()
                .build();
        log.info("\033[93m Settings created: {} \033[m", settings);
        MODULES_CONFIG = new ArrayList<>();
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Material"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Material Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Delivery"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Delivery Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Shop"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Shop Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Invoice"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Invoice Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Tag"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Tag Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Project"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Project Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Bom"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Bom Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("Task"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Task Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            MODULES_CONFIG.add(ModuleConfig.getInstance("ChangeLog"));
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of ChangeLog Config: {}", e.getMessage());
            e.printStackTrace();
        }
        try {
            ModuleConfig ss = ModuleConfig.getInstance("Settings");
            MODULES_CONFIG.add(ss);
        } catch (InvalidModuleDescriptorException e) {
            log.error("Failed to get instance of Settings Config: {}", e.getMessage());
            e.printStackTrace();
        }
        if (MODULES_CONFIG.size() < 9) {
            throw new RuntimeException("Configs check failed!");
        }
    }

}
