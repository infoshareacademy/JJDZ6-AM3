package config;

import api.resource.AuthenticationResource;
import api.resource.ProjectResource;
import api.resource.SprintResource;
import api.resource.StateResource;
import api.resource.TaskResource;
import api.resource.UserResource;
import errors.Unauthorized;
import filter.AuthorizationFilter;

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
        resources.add(StateResource.class);
        resources.add(ConstraintViolationMapper.class);
        resources.add(CorsFilter.class);
        resources.add(AuthenticationResource.class);
        resources.add(AuthorizationFilter.class);
        resources.add(Unauthorized.class);
        return resources;
    }
}
