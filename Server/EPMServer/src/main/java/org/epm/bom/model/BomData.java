package org.epm.bom.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.epm.bom.enums.BomStatus;
import org.epm.common.model.AbstractModuleData;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class BomData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BomStatus status;

    @Column(nullable = false)
    private Integer qty;

    private Integer reservedQty;

    public String toString() {
        return "Bom {"
                + " uid: " + getUid()
                + ", id: " + getId()
                + ", status: " + getStatus()
                + ", project uid: " + getProjectUid()
                + ", material uid: " + getMaterialUid()
                + ", qty: " + getQty() + "(r. " + getReservedQty() + ")"
                + "}";
    }

    public abstract Integer getProjectUid();
    public abstract Integer getMaterialUid();
}
