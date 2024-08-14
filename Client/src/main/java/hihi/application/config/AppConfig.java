package hihi.application.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.google.common.eventbus.EventBus;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.module.InvalidModuleDescriptorException;
import java.util.List;

import static hihi.application.config.GuiConfig.DEBUG_MODE;

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

    public static void testModulesConfiguration() throws InvalidModuleDescriptorException {
        try {
            MODULES_CONFIG = List.of(
                    ModuleConfig.getInstance("Material"),
                    ModuleConfig.getInstance("Delivery"),
                    ModuleConfig.getInstance("Project"),
                    ModuleConfig.getInstance("Shop"),
                    ModuleConfig.getInstance("Invoice"),
                    ModuleConfig.getInstance("Tag"),
                    ModuleConfig.getInstance("Task").setSuperResourceModule("Project"),
                    ModuleConfig.getInstance("Bom").setSuperResourceModule("Project"),
                    ModuleConfig.getInstance("ChangeLog").setSuperResourceModule("Project")
            );
        } catch (InvalidModuleDescriptorException e) {
            throw new RuntimeException("Invalid modules configuration, so it's pointless to run this application.");
        }
    }

}
