package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayValidateTask extends AbstractFlywayTask {

    FlywayValidateTask() {
        description = 'Validates the applied migrations against the ones available on the classpath.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            flyway.validate()
        }
    }
}
