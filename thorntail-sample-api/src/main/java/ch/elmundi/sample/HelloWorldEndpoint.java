package ch.elmundi.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public interface HelloWorldEndpoint {

    @GET
    @Path("/{who}")
    HelloWorld hello(@PathParam("who") String who);
}
