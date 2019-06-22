package api.dto;

import java.util.List;

public class Tasks {
    private List<Long> tasks;

    public List<Long> getTasks() {
        return tasks;
    }

    public void setTasks(List<Long> tasks) {
        this.tasks = tasks;
    }
}
