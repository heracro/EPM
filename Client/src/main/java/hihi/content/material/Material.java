package hihi.content.material;

import hihi.content.common.AbstractContent;
import javafx.beans.property.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Material extends AbstractContent {

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

    public StringProperty nameProperty() { return name; }
    public StringProperty normProperty() { return norm; }
    public StringProperty dimensionsProperty() { return dimensions; }
    public FloatProperty weightProperty() { return weight; }
    public IntegerProperty totalQtyProperty() { return totalQty; }
    public IntegerProperty freeQtyProperty() { return freeQty; }
    public StringProperty storeProperty() { return store; }
    public StringProperty datasheetProperty() { return datasheet; }
    public StringProperty unitProperty() { return unit; }

}
