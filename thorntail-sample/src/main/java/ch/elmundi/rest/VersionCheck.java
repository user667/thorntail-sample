package ch.elmundi.rest;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class VersionCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "version")
    String versionNumber;

    @Inject
    Config config;

    public HealthCheckResponse call() {
        final HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse
                .named("version-check")
                .withData("version", versionNumber)
                .up();

        for (String propertyName : config.getPropertyNames()) {
            healthCheckResponseBuilder.withData(propertyName, config.getValue(propertyName, String.class));
        }

        return healthCheckResponseBuilder.build();
    }
}
