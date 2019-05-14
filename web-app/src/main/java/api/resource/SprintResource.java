package api.resource;

import api.domain.Sprint;
import api.domain.Task;
import api.repository.SprintRepository;
import api.repository.TaskRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sprints")
public class SprintResource {

    @Inject
    SprintRepository sprintRepository;

    @Inject
    TaskRepository taskRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sprint> getSprints() {
        return sprintRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sprint getSprint(@PathParam("id") String id) {
        return sprintRepository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Sprint createSprints(@Valid Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @POST
    @Path("/{id}/tasks")
    public Response addTaskToSprint(@PathParam("id") String id, Task issue) {
        Task task = taskRepository.findById(issue.getId());
        Sprint sprint = sprintRepository.findById(id);
        sprint.addTaskToSprint(task);
        sprintRepository.save(sprint);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteSprint(@PathParam("id") String id) {
        sprintRepository.delete(id);
    }
}
