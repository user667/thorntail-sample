package ch.elmundi.sample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello2")
@ApplicationScoped
@OpenAPIDefinition(tags = {
        @Tag(name = "Hello universe service")
})
public class HelloUniverseEndpointImpl implements HelloWorldEndpoint {

    @GET
    @Override
    @Path("/{who}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(tags = {"Hello world"})
    public HelloWorld hello(@PathParam("who") String who) {
        HelloWorld helloWorld = new HelloWorld.Builder()
                .withData("who", who)
                .build();
        return helloWorld;
    }
}
