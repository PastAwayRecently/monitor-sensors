package com.assigment.monitor.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Shmarlouski
 */
@Component
public class FlywayRepairRunner implements CommandLineRunner{

    private final Flyway flyway;

    public FlywayRepairRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void run(String... args) throws Exception {
        flyway.repair();
        flyway.migrate();
    }
}
