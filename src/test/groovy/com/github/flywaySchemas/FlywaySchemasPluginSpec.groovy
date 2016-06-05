package com.github.flywaySchemas

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class FlywaySchemasPluginSpec extends Specification {

    Project project
    def setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.github.flyway-schemas'
    }

    def "project has this plugin"() {
        expect:
        project.plugins.hasPlugin(FlywaySchemasPlugin)
    }

    def "check if task are present"() {
        expect:
        project.tasks.findByName('flywayClean') != null
        project.tasks.findByName('flywayInfo') != null
        project.tasks.findByName('flywayBaseline') != null
        project.tasks.findByName('flywayMigrate') != null
        project.tasks.findByName('flywayRepair') != null
        project.tasks.findByName('flywayValidate') != null
    }
}
