package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayBaselineTask extends AbstractFlywayTask {

    FlywayBaselineTask() {
        description = 'Baselines an existing database, excluding all migrations up to and including baselineVersion.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            flyway.baseline()
        }
    }
}
