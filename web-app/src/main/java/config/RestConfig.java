package config;

import api.resource.ProjectResource;
import api.resource.SprintResource;
import api.resource.TaskResource;
import api.resource.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(TaskResource.class);
        resources.add(UserResource.class);
        resources.add(SprintResource.class);
        resources.add(ProjectResource.class);
        resources.add(ConstraintViolationMapper.class);
        resources.add(CorsFilter.class);
        return resources;
    }
}
