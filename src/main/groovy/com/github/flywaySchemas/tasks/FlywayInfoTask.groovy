package com.github.flywaySchemas.tasks

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.core.internal.info.MigrationInfoDumper
import org.flywaydb.gradle.task.AbstractFlywayTask

class FlywayInfoTask extends AbstractFlywayTask {

    FlywayInfoTask() {
        description = 'Prints the details and status information about all the migrations.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            flyway.setSchemas(schema)
            println MigrationInfoDumper.dumpToAsciiTable(flyway.info().all())
        }
    }
}
