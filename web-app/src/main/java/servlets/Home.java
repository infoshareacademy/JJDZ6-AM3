package servlets;

import config.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/{path: .*}")
public class Home {

    @Inject
    TemplateProvider templateProvider;
    @Context
    UriInfo uri;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response doGet(@Context HttpServletResponse resp, @Context ServletContext context) throws IOException {

        URI baseUri = uri.getBaseUri();
        Map<String, Object> model = new HashMap<>();
        model.put("baseUrl", baseUri);

        Template template = templateProvider.getTemplate(context, "index.ftl");

        try {
            template.process(model, resp.getWriter());
            return Response.ok().build();
        } catch (TemplateException e) {
            return Response.serverError().build();
        }
    }
}
