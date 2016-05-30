package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayCleanTask extends AbstractFlywayTask {

    FlywayCleanTask() {
        description = 'Drops all objects in the configured schemas.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            flyway.clean()
        }
    }
}
