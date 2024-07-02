package hihi.gui.event;


import hihi.application.MainWindow;

public class ThemeMenuListener implements MenuActionListener {
    private final MainWindow mainWindow;
    private final String theme;

    public ThemeMenuListener(MainWindow mainWindow, String theme) {
        this.mainWindow = mainWindow;
        this.theme = theme;
    }

    @Override
    public void onMenuItemSelected() {
        mainWindow.setTheme(theme);
    }
}
