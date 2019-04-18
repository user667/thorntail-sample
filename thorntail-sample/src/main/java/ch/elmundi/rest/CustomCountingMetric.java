package ch.elmundi.rest;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.atomic.AtomicLong;

@Path("/test-counter")
@ApplicationScoped
public class CustomCountingMetric {

    private AtomicLong atomicLong = new AtomicLong();

    @GET
    @Counted(name = "test-counter-counted",
            absolute = true,
            monotonic = true,
            displayName = "hello get",
            description = "Monitor how many times helloGet method was called")
    @Timed(name = "test-counter-timed",
            description = "Monitor the time helloMessageProcessed Method takes",
            unit = MetricUnits.MILLISECONDS,
            absolute = true)
    public Long count() {
        return atomicLong.getAndIncrement();
    }
}
