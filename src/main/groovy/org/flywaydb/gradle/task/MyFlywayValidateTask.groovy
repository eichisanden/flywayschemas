package org.flywaydb.gradle.task

import com.github.flywaySchemas.FlywaySchemasPlugin
import org.flywaydb.core.Flyway

class MyFlywayValidateTask extends AbstractFlywayTask {

    MyFlywayValidateTask() {
        description = 'Validates the applied migrations against the ones available on the classpath.'
    }

    @Override
    run(Flyway flyway) {
        FlywaySchemasPlugin.getSchemas(flyway).each { schema ->
            println "<${schema}>"
            Flyway.configure().schemas(schema)
            flyway.validate()
        }
    }
}
