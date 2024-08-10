package hihi.content.common.dataModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class AbstractContent {

    protected final BooleanProperty selected = new SimpleBooleanProperty(false);
    protected final IntegerProperty uid = new SimpleIntegerProperty();

    public BooleanProperty selectedProperty() { return selected; }
    public IntegerProperty uidProperty() { return uid; }

    public boolean isSelected() {
        return selected.get();
    }

    public void toggleSelected() {
        selectedProperty().set(!selected.get());
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

}
