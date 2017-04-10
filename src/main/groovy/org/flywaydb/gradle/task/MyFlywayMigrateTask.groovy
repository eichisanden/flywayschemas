package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway

class MyFlywayMigrateTask extends AbstractFlywayTask {

    MyFlywayMigrateTask() {
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
