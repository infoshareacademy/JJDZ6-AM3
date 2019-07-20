package api.resource;

import api.domain.Status;
import api.domain.Task;
import api.domain.Type;
import api.domain.User;
import api.service.TaskService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/tasks")
public class TaskResource {

    @Inject
    TaskService taskService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTask() {
        return taskService.findAll();
    }

    @GET
    @Path("/types")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getIssueTypes() {
        return Stream.of(Type.values()).map(Type::getName).collect(Collectors.toList());
    }

    @GET
    @Path("/statuses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getIssueStatuses() {
        return Stream.of(Status.values()).map(Status::getName).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTask(@PathParam("id") Long id) {
        return taskService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Task createTasks(@Valid Task task) {
        return taskService.saveTask(task);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Task updateTasks(@PathParam("id") Long id, @Valid Task task) {
        task.setId(id);
        return taskService.update(task);
    }

    @PUT
    @Path("/{id}/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Task assignUser(@PathParam("id") Long id, @Valid User user) {
        return taskService.assignUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteTask(@PathParam("id") Long id) {
        taskService.delete(id);
    }
}
