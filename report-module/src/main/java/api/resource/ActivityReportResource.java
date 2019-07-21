package api.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/activity")
public class ActivityReportResource {

    @POST
    public Response userLogin() {
        System.out.println("ping");
        return Response.ok().build();
    }
}
