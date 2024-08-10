package hihi.content.common.dataModel;

import javafx.scene.control.TableColumn;

public record ListViewColumnConfig<Content extends AbstractContent>(
        TableColumn<Content, ?> column,
        String propertyName,
        Double width
) {}
