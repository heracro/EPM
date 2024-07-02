package hihi.gui.components;

import hihi.application.MainWindow;
import hihi.gui.event.ConnectionTestMenuListener;
import hihi.gui.event.ThemeMenuListener;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;

public class MainMenu extends MenuBar {

    public MainMenu(MainWindow mainWindow) {
        createConnectionMenu(mainWindow);
        createThemeMenu(mainWindow);
        this.setVisible(false);
    }

    private void createConnectionMenu(MainWindow mainWindow) {
        Menu connectionMenu = new Menu("Connection");
        MenuItem testConnectionMenuItem = new MenuItem("Test connection");
        testConnectionMenuItem.setAccelerator(
                KeyCombination.keyCombination("Ctrl+Alt+T"));
        testConnectionMenuItem.setOnAction(
                e -> new ConnectionTestMenuListener(mainWindow).onMenuItemSelected());
        connectionMenu.getItems().add(testConnectionMenuItem);
        this.getMenus().add(connectionMenu);
    }

    private void createThemeMenu(MainWindow mainWindow) {
        Menu themeMenu = new Menu("Theme");
        MenuItem lightThemeMenuItem = new MenuItem("Light theme");
        lightThemeMenuItem.setOnAction(
                e -> new ThemeMenuListener(mainWindow, "light").onMenuItemSelected());
        MenuItem darkThemeMenuItem = new MenuItem("Dark theme");
        darkThemeMenuItem.setOnAction(
                e -> new ThemeMenuListener(mainWindow, "dark").onMenuItemSelected());
        themeMenu.getItems().addAll(lightThemeMenuItem, darkThemeMenuItem);
        this.getMenus().add(themeMenu);
    }

}
