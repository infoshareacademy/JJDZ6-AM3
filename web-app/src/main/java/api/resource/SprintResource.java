package api.resource;

import annotations.Auth;
import api.domain.Sprint;
import api.domain.State;
import api.dto.Tasks;
import api.service.SprintService;

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
import javax.ws.rs.core.Response;

@Path("/sprints")
@Auth
public class SprintResource {

    @Inject
    SprintService sprintService;

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
    public Response addTasksToSprint(@PathParam("id") Long id, Tasks tasks) {
            sprintService.moveTasksToSprint(id, tasks);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateState(@PathParam("id") Long id, State state) {
        sprintService.updateState(id, state);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSprint(@PathParam("id") Long id) {
        sprintService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
