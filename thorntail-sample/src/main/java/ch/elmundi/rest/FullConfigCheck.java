package ch.elmundi.rest;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Custom health check that adds a dump of the full config to the return payload.
 */
@Liveness
@ApplicationScoped
public class FullConfigCheck implements HealthCheck {

    @Inject
    private Config config;

    public HealthCheckResponse call() {
        final HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse
                .named("full-config-check")
                .up();

        for (String propertyName : config.getPropertyNames()) {
            healthCheckResponseBuilder.withData(propertyName, config.getOptionalValue(propertyName, String.class).orElse(""));
        }

        return healthCheckResponseBuilder.build();
    }
}
