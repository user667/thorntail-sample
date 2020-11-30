package ch.elmundi.rest;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("simple-health-check")
                .up()
                .build();
    }
}
