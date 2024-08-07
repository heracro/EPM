package hihi.adapters;

import hihi.content.task.TaskDto;

public class TaskAdapter extends DependantResourceAbstractAdapter<TaskDto> {
    @Override
    protected String getEndpoint(Integer parentUid) {
        return "/projects/" + parentUid + "/tasks";
    }

    @Override
    protected Class<TaskDto> getDtoClass() {
        return TaskDto.class;
    }

    @Override
    protected String getEntityName() {
        return "Task";
    }
}
