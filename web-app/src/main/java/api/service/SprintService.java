package api.service;

import api.domain.Sprint;
import api.repository.SprintRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SprintService {

    @Inject
    SprintRepository sprintRepository;

    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public Sprint findById(String id) {
        return sprintRepository.findById(id);
    }

    public List<Sprint> findAll() {
        return this.sprintRepository.findAll();
    }

    public void delete(String id) {
        sprintRepository.delete(id);
    }

    public Sprint update(Sprint sprint) {
        return sprintRepository.update(sprint);
    }
}
