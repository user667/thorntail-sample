package ch.elmundi.rest;

import ch.elmundi.sample.HelloWorld;
import ch.elmundi.sample.HelloWorldEndpoint;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class HelloWorldEndpointImpl implements HelloWorldEndpoint {

    @GET
    @Override
    @Path("/{who}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorld hello(@PathParam("who") String who) {
        HelloWorld helloWorld = new HelloWorld.Builder()
                .withData("who", who)
                .build();
        return helloWorld;
    }
}
