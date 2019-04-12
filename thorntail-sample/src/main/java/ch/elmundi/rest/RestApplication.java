package ch.elmundi.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/test")
public class RestApplication extends Application {

    private final Set<Object> singletons = new HashSet<Object>();
    private final Set<Class<?>> empty = new HashSet<Class<?>>();

    public RestApplication() {
        singletons.add(new HelloWorldEndpoint());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
