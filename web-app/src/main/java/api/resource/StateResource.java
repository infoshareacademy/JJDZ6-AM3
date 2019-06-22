package api.resource;

import api.domain.State;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/states")
public class StateResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public State getStates() {
        return null;
    }
}
