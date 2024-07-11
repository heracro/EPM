package org.epm.task.model;

import org.epm.common.model.DataModel;

public abstract class TaskData<T extends TaskData<T>> implements DataModel {

    @Override
    public boolean isValidEntity() {
        return false;
    }
}
