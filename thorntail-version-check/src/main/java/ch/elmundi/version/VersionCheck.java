package ch.elmundi.version;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Version health check that simply returns the value of property <code>version</code>.
 */
@ApplicationScoped
public class VersionCheck {

    @Inject
    @ConfigProperty(name = "version")
    private String versionNumber;

    @Produces
    @Liveness
    public HealthCheck call() {
        final HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse
                .named("version-check")
                .withData("version", versionNumber)
                .up();

        return () -> healthCheckResponseBuilder.build();
    }
}
