package com.assigment.monitor.flyway;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Shmarlouski
 */
@Component
public class FlywayRepairRunner implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(FlywayRepairRunner.class);
    private final Flyway flyway;

    public FlywayRepairRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Flyway repair started...");
        flyway.repair();
        log.info("Flyway migrate started...");
        flyway.migrate();
    }
}
