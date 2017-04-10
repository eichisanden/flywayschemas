package com.github.flywaySchemas

import org.flywaydb.gradle.task.MyFlywayMigrateTask
import org.flywaydb.gradle.task.MyFlywayRepairTask
import org.flywaydb.gradle.task.MyFlywayBaselineTask
import org.flywaydb.gradle.task.MyFlywayCleanTask
import org.flywaydb.gradle.task.MyFlywayInfoTask
import org.flywaydb.gradle.task.MyFlywayValidateTask
import groovy.sql.Sql
import org.flywaydb.gradle.FlywayExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.flywaydb.core.Flyway

class FlywaySchemasPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("flyway", FlywayExtension)
        project.tasks.create("flywayClean", MyFlywayCleanTask)
        project.tasks.create("flywayBaseline", MyFlywayBaselineTask)
        project.tasks.create("flywayMigrate", MyFlywayMigrateTask)
        project.tasks.create("flywayValidate", MyFlywayValidateTask)
        project.tasks.create("flywayInfo", MyFlywayInfoTask)
        project.tasks.create("flywayRepair", MyFlywayRepairTask)
    }

    static List<String> getSchemas(Flyway flyway) {
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
