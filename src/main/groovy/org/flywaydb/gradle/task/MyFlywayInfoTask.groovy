package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway
import org.flywaydb.core.internal.info.MigrationInfoDumper

class MyFlywayInfoTask extends AbstractFlywayTask {

    MyFlywayInfoTask() {
        description = 'Prints the details and status information about all the migrations.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            Flyway.configure().schemas(schema)
            println MigrationInfoDumper.dumpToAsciiTable(flyway.info().all())
        }
    }
}
