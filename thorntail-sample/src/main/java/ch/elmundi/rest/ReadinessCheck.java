package ch.elmundi.rest;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("simple-health-check")
                .up()
                .build();
    }
}
