package hihi.content.bom;

import hihi.content.common.dataModel.AbstractContent;
import javafx.beans.property.*;

public class Bom extends AbstractContent {

    private IntegerProperty projectId = new SimpleIntegerProperty();
    private IntegerProperty materialId = new SimpleIntegerProperty();
    private StringProperty materialName = new SimpleStringProperty();
    private FloatProperty qty = new SimpleFloatProperty();

    public Bom(BomDto dto) {
        projectId.set(dto.getProjectId());
        materialId.set(dto.getMaterialId());
        materialName.set(dto.getMaterialName());
        qty.set(dto.getQty());
    }

    public IntegerProperty projectIdProperty() { return projectId; }
    public IntegerProperty materialIdProperty() { return materialId; }
    public StringProperty materialNameProperty() { return materialName; }
    public FloatProperty qtyProperty() { return qty; }

}
