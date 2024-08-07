package hihi.content.common;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbstractContent {

    protected final BooleanProperty selected = new SimpleBooleanProperty(false);
    protected final IntegerProperty uid = new SimpleIntegerProperty();

}
