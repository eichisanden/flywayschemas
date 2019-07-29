package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway

class MyFlywayRepairTask extends AbstractFlywayTask {

    MyFlywayRepairTask() {
        description = 'Repairs the Flyway metadata table.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            Flyway.configure().schemas(schema)
            flyway.repair()
        }
    }
}
