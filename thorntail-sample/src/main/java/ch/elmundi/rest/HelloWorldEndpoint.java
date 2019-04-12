package ch.elmundi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Path("/{id}")
    public Response getConfigurationById(@PathParam("id") Integer id){
        return Response.status(javax.ws.rs.core.Response.Status.OK).entity(id).build();
    }
}
