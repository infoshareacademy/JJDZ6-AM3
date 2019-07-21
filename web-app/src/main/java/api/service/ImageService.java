package api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Path("/images")
public class ImageService {

    private static final int MAX_SIZE_IN_MB = 1;
    private static final java.nio.file.Path BASE_DIR = Paths
            .get(System.getProperty("user.home"), "Documents", "Saved Images");

    @POST
    @Consumes({"image/jpeg", "image/png"})
    public Response uploadImage(
            InputStream in,
            @HeaderParam("Content-Type") String fileType,
            @HeaderParam("Content-Length") long fileSize) throws IOException {

        if (fileSize > 1024 * 1024 * MAX_SIZE_IN_MB) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Image is larger than " + MAX_SIZE_IN_MB + "MB").build());
        }

        String fileName = "" + System.currentTimeMillis();

        if (fileType.equals("image/jpeg")) {
            fileName += ".jpg";
        } else {
            fileName += ".png";
        }

        Files.copy(in, BASE_DIR.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        return Response.status(Response.Status.CREATED).location(URI.create("/" + fileName)).build();
    }
}
