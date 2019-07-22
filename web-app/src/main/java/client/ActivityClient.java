package client;

import api.domain.ActivityEntry;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Stateless
public class ActivityClient {
    private static final String URL = "http://localhost:7777/rest/";
    private static final Client client = ClientBuilder.newClient();

    public void createLoginEntry(ActivityEntry entry) {
        client
                .target(URL)
                .path("activity")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(entry, MediaType.APPLICATION_JSON), ActivityEntry.class);
    }
}
