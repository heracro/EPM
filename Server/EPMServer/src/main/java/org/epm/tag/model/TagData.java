package org.epm.tag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.AbstractModuleData;
import org.epm.tag.enums.TagType;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class TagData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private TagType type;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    public boolean isValidEntity() {
        throw new UnsupportedOperationException("TagData::isValidEntity is not implemented yet");
    }

    @JsonIgnore
    public boolean isValidDTO() {
        throw new UnsupportedOperationException("TagData::isValidDTO is not implemented yet");
    }

}
