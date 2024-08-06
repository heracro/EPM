package hihi.gui.layout.content.material;

import hihi.dto.MaterialDto;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Material {

    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final IntegerProperty uid = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty norm = new SimpleStringProperty();
    private final StringProperty dimensions = new SimpleStringProperty();
    private final FloatProperty weight = new SimpleFloatProperty();
    private final IntegerProperty totalQty = new SimpleIntegerProperty();
    private final IntegerProperty freeQty = new SimpleIntegerProperty();
    private final StringProperty store = new SimpleStringProperty();
    private final StringProperty datasheet = new SimpleStringProperty();
    private final StringProperty unit = new SimpleStringProperty();

    public Material(MaterialDto dto) {
        uid.set(dto.getUid());
        name.set(dto.getName());
        norm.set(dto.getNorm());
        dimensions.set(dto.getDimensions());
        weight.set(dto.getWeight());
        totalQty.set(dto.getTotalQty());
        freeQty.set(dto.getFreeQty());
        store.set(dto.getStore());
        datasheet.set(dto.getDatasheet());
        unit.set(dto.getUnit().toValue());
    }

}
