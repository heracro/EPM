package org.epm.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.DataModel;

@Slf4j
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class TaskData implements DataModel {

    private Integer id;

    @JsonIgnore
    @Override
    public boolean isValidEntity() {
        return false;
    }

    @Override
    public boolean isValidDTO() {
        return false;
    }
}
