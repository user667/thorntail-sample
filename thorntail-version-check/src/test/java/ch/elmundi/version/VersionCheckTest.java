package ch.elmundi.version;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class VersionCheckTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(VersionCheck.class)
                .addAsManifestResource(new StringAsset(
                        "version=1.0.0"
                ), "microprofile-config.properties")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @RunAsClient
    @Test
    public void givenAVersion_whenCallingHealthCheck_thenVersionIsReturned() throws Exception {
        // GIVEN

        // WHEN
        HttpResponse response = Request.Post("http://localhost:8080/health")
                .setHeader("Accept", MediaType.TEXT_PLAIN)
                .execute()
                .returnResponse();

        // THEN
        assertEquals(200, response.getStatusLine().getStatusCode());

        JsonElement healthCheckResponse = getHealthCheckResponse(response);

        assertEquals(HealthCheckResponse.State.UP.name(), healthCheckResponse.getAsJsonObject().get("status"));
    }

    private JsonElement getHealthCheckResponse(HttpResponse httpResponse) throws IOException {
        try (InputStream inputStream = httpResponse.getEntity().getContent()) {
            final BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            return new JsonParser().parse(bufferedReader);
        }
    }
}
