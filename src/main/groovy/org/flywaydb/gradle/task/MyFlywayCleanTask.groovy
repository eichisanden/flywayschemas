package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway

class MyFlywayCleanTask extends AbstractFlywayTask {

    MyFlywayCleanTask() {
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
