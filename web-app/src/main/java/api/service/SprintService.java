package api.service;

import api.domain.Sprint;
import api.repository.SprintRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SprintService {

    @Inject
    SprintRepository sprintRepository;

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
}
