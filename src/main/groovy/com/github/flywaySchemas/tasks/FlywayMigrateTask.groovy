package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayMigrateTask extends AbstractFlywayTask {

    FlywayMigrateTask() {
        description = 'Migrates the schema to the latest version.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            didWork = flyway.migrate() > 0
        }
    }
}
