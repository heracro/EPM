package org.epm.tag.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.DataModel;

@Slf4j
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class TagData implements DataModel {

    private Integer id;

    @Override
    public boolean isValidEntity() {
        return false;
    }

    @Override
    public boolean isValidDTO() {
        return false;
    }

}
