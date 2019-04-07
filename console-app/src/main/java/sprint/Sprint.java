package sprint;

import userstory.UserStory;

import java.time.LocalDate;
import java.util.List;


public class Sprint {

    private static int counter = 1;

    private int id;
    private String name;
    private LocalDate endDate;
    private List<UserStory> Tasks;

    public Sprint(String name, LocalDate endDate) {
        this.name = name;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId() {

        this.id = this.id = Sprint.counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<UserStory> getTasks() {
        return Tasks;
    }

    public void setTasks(List<UserStory> tasks) {
        Tasks = tasks;
    }
}

