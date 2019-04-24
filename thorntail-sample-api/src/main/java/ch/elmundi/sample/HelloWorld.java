package ch.elmundi.sample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Hello world object is a trivial object to showcase JSON deserialization and serialization.
 */
public class HelloWorld {

    private Map<String, Object> data = new HashMap<>();

    private HelloWorld() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * Simple builder for {@link HelloWorld}.
     */
    public static class Builder {

        private HelloWorld helloWorld = new HelloWorld();

        public Builder withData(String id, Object object) {
            helloWorld.data.put(id, object);
            return this;
        }

        public HelloWorld build() {
            Objects.requireNonNull(helloWorld);
            helloWorld.data = Collections.unmodifiableMap(helloWorld.data);
            return helloWorld;
        }
    }
}
