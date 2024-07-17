package org.epm.common.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.epm.bom.model.BomDTO;
import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.invoice.model.InvoiceDTO;
import org.epm.material.model.MaterialDTO;
import org.epm.project.model.ProjectDTO;
import org.epm.shop.model.ShopDTO;
import org.epm.tag.model.TagDTO;
import org.epm.task.model.TaskDTO;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BomDTO.class, name = "Bom"),
        @JsonSubTypes.Type(value = ChangeLogDTO.class, name = "ChangeLog"),
        @JsonSubTypes.Type(value = DeliveryDTO.class, name = "Delivery"),
        @JsonSubTypes.Type(value = InvoiceDTO.class, name = "Invoice"),
        @JsonSubTypes.Type(value = MaterialDTO.class, name = "Material"),
        @JsonSubTypes.Type(value = ProjectDTO.class, name = "Project"),
        @JsonSubTypes.Type(value = ShopDTO.class, name = "Shop"),
        @JsonSubTypes.Type(value = TagDTO.class, name = "Tag"),
        @JsonSubTypes.Type(value = TaskDTO.class, name = "Task")
})
@SuppressWarnings("unused")
public interface IDTO {

    Integer getUid();

    String getAction();

    void setAction(String action);

    boolean isValidDTO();

    private Long getId() {
        throw new UnsupportedOperationException("ID is not available in DTO");
    }

    private void setId(Long id) {
        throw new UnsupportedOperationException("ID is not available in DTO");
    }
}
