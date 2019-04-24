package ch.elmundi.version;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Version health check that simply returns the value of property <code>version</code>.
 */
@Health
@ApplicationScoped
public class VersionCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "version")
    private String versionNumber;

    public HealthCheckResponse call() {
        final HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse
                .named("version-check")
                .withData("version", versionNumber)
                .up();

        return healthCheckResponseBuilder.build();
    }
}
