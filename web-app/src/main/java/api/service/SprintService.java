package api.service;

import api.domain.Sprint;
import api.domain.State;
import api.domain.Task;
import api.dto.Tasks;
import api.repository.SprintRepository;
import api.repository.TaskRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SprintService {

    @Inject
    SprintRepository sprintRepository;
    @Inject
    TaskRepository taskRepository;

    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public Sprint findById(Long id) {
        return sprintRepository.findById(id);
    }

    public List<Sprint> findAll() {
        return this.sprintRepository.findAll();
    }

    public void delete(Long id) {
        sprintRepository.delete(id);
    }

    public Sprint update(Sprint sprint) {
        return sprintRepository.update(sprint);
    }

    public void moveTasksToSprint(Long id, Tasks tasks) {
        Sprint sprintEntity = sprintRepository.findById(id);
        List<Task> taskEntities = taskRepository.findByIds(tasks.getTasks());
        sprintEntity.addTasksToSprint(taskEntities);
        sprintRepository.update(sprintEntity);
    }

    public void updateState(Long id, State state) {
        Sprint sprintEntity = sprintRepository.findById(id);
        sprintEntity.setState(state);
        sprintRepository.update(sprintEntity);
    }
}
