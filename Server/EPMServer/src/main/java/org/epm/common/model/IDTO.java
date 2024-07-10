package org.epm.common.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.epm.bom.model.BomDTO;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.material.model.MaterialDTO;
import org.epm.project.model.ProjectDTO;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MaterialDTO.class, name = "MaterialDTO"),
        @JsonSubTypes.Type(value = ProjectDTO.class, name = "ProjectDTO"),
        @JsonSubTypes.Type(value = BomDTO.class, name = "BomDTO"),
        @JsonSubTypes.Type(value = DeliveryDTO.class, name = "DeliveryDTO")
})
public interface IDTO {

    boolean isValidDTO();
    boolean equals(Object that);
    static IDTO randomInstance() {
        throw new UnsupportedOperationException("METHOD 'randomInstance()' NOT IMPLEMENTED");
    }

}
