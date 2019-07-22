package api.resource;

import api.domain.ActivityEntry;
import api.repository.ActivityReportRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activity")
public class ActivityReportResource {

    @Inject
    ActivityReportRepository activityReportRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActivityEntry> getActivities(){
        return activityReportRepository.getActivities();
    }

    @POST
    public Response userLogin(@Valid ActivityEntry entry) {
        activityReportRepository.saveUserLastLoginTime(entry);
        return Response.ok().build();
    }
}
