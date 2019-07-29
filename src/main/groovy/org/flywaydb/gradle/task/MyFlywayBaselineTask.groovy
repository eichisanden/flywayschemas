package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway

class MyFlywayBaselineTask extends AbstractFlywayTask {

    MyFlywayBaselineTask() {
        description = 'Baselines an existing database, excluding all migrations up to and including baselineVersion.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { def schema ->
            println "<${schema}>"
            Flyway.configure().schemas(schema)
            flyway.baseline()
        }
    }
}
