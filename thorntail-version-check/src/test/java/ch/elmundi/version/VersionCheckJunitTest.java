package ch.elmundi.version;

import io.smallrye.config.inject.ConfigExtension;
import org.eclipse.microprofile.health.HealthCheck;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.wildfly.swarm.microprofile.health.deployment.HealthExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WeldJunit5Extension.class)
public class VersionCheckJunitTest {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(
            WeldInitiator.createWeld()
                    .addExtensions(ConfigExtension.class, HealthExtension.class)
                    .addBeanClass(VersionCheck.class)
    ).build();

    private VersionCheck versionCheck;

    @BeforeEach
    public void setup() {
        versionCheck = weld.select(VersionCheck.class).get();
    }

    @Test
    public void verifyVersionCheck() {
        // GIVEN
        // WHEN
        HealthCheck healthCheck = versionCheck.call();
        // THEN
        assertNotNull(healthCheck);
        assertEquals("version-check", healthCheck.call().getName());
        assertEquals("1.0.0-SNAPSHOT", healthCheck.call().getData().get().get("version"));
    }
}
