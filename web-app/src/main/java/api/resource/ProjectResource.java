package api.resource;

import api.domain.Project;
import api.repository.ProjectRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/projects")
public class ProjectResource {

    @Inject
    ProjectRepository projectRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProject(@PathParam("id") String id) {
        return projectRepository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project createProjects(Project project) {
        return projectRepository.save(project);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProject(@PathParam("id") String id) {
        projectRepository.delete(id);
    }
}
