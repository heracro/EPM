package hihi.application;

import hihi.gui.components.BottomPanel;
import hihi.gui.layout.BottomPanelConfig;
import hihi.gui.components.ContentWindow;
import javafx.scene.layout.BorderPane;

public class MainController {
    private final BorderPane root;
    private ContentWindow currentContent;
    private BottomPanel bottomPanel;

    public MainController(BorderPane root) {
        this.root = root;
        this.bottomPanel = new BottomPanel(10);
        this.root.setBottom(bottomPanel);
    }

    public void setView(ContentWindow contentWindow, BottomPanelConfig bottomPanelConfig) {
        // Update main content
        if (currentContent != null) {
            // Transfer data if needed
            transferData(currentContent, contentWindow);
        }
        currentContent = contentWindow;
        root.setCenter(currentContent);

        // Update bottom panel buttons
        bottomPanel.configureButtons(bottomPanelConfig.getButtonConfigs());
    }

    private void transferData(ContentWindow from, ContentWindow to) {
        // Implement data transfer logic here if needed
    }
}
