package api.resource;

import api.domain.Sprint;
import api.domain.Task;
import api.repository.TaskRepository;
import api.service.SprintService;

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
    SprintService sprintService;

    @Inject
    TaskRepository taskRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sprint> getSprints() {
        return sprintService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sprint getSprint(@PathParam("id") Long id) {
        return sprintService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Sprint createSprints(@Valid Sprint sprint) {
        return sprintService.save(sprint);
    }

    @POST
    @Path("/{id}/tasks")
    public Response addTaskToSprint(@PathParam("id") Long id, Task issue) {
        Task task = taskRepository.findById(issue.getId());
        Sprint sprint = sprintService.findById(id);
        sprint.addTaskToSprint(task);
        sprintService.save(sprint);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteSprint(@PathParam("id") Long id) {
        sprintService.delete(id);
    }
}
