package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayRepairTask extends AbstractFlywayTask {

    FlywayRepairTask() {
        description = 'Repairs the Flyway metadata table.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            flyway.repair()
        }
    }
}
