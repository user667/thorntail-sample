package ch.elmundi.rest;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.inject.Inject;

public class CustomCountingMetric {

    @Inject
    @Metric
    Long counter = 666L;

    @Counted(name = "countMe", absolute = true, reusable = true, tags = {"tag1=value1"})
    public void countMeA() {
    }
}
