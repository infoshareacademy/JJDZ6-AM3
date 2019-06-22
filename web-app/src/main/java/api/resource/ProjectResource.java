package api.resource;

import api.domain.Project;
import api.domain.Sprint;
import api.domain.Task;
import api.domain.User;
import api.service.ProjectService;

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

@Path("/projects")
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProject(@PathParam("id") Long id) {
        return projectService.findById(id);
    }

    @GET
    @Path("/{id}/sprint")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sprint> getAllSprintsForProject(@PathParam("id") Long id) {
        return projectService.getAllSprintsForProject(id);
    }

    @GET
    @Path("/{id}/backlog")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getBacklogForProject(@PathParam("id") Long id) {
        return projectService.getBacklog(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project createProjects(@Valid Project project) {
        return projectService.save(project);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project updateProjectName(@PathParam("id") Long id, Project project) {
        project.setId(id);
        return projectService.update(project);
    }

    @PUT
    @Path("/{id}/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUserToProject(@PathParam("id") Long id, User user) {
        projectService.addUserToProject(id, user);
    }

    @POST
    @Path("/{id}/task")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTaskToProject(@PathParam("id") Long id, Task task) {
        projectService.addTaskToProject(id, task);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProject(@PathParam("id") Long id) {
        projectService.delete(id);
    }
}
