package com.github.flywaySchemas

import com.github.flywaySchemas.tasks.FlywayMigrateTask
import com.github.flywaySchemas.tasks.FlywayRepairTask
import com.github.flywaySchemas.tasks.FlywayBaselineTask
import com.github.flywaySchemas.tasks.FlywayCleanTask
import com.github.flywaySchemas.tasks.FlywayInfoTask
import com.github.flywaySchemas.tasks.FlywayValidateTask
import groovy.sql.Sql
import org.flywaydb.gradle.FlywayExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.flywaydb.core.Flyway

class FlywaySchemasPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("flyway", FlywayExtension);
        project.tasks.create("flywayClean", FlywayCleanTask)
        project.tasks.create("flywayBaseline", FlywayBaselineTask)
        project.tasks.create("flywayMigrate", FlywayMigrateTask)
        project.tasks.create("flywayValidate", FlywayValidateTask)
        project.tasks.create("flywayInfo", FlywayInfoTask)
        project.tasks.create("flywayRepair", FlywayRepairTask)
    }

    public static List<String> getSchemas(Flyway flyway) {
        def db = Sql.newInstance(flyway.getDataSource())
        def arr = []
        if (flyway.schemas.size() > 0) {
            db.eachRow("SELECT nspname FROM pg_namespace WHERE nspname ~* ? AND nspname != 'information_schema'", [flyway.schemas[0]]) {
                arr.add((it."nspname"))
            }
        }
        db.close()
        arr
    }

}
