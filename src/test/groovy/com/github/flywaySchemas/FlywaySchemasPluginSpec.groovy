package com.github.flywaySchemas

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class FlywaySchemasPluginSpec extends Specification {

    def "apply() should load the plugin"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.with {
            apply plugin: 'com.github.flyway-schemas'
        }

        then:
        project.plugins.hasPlugin(FlywaySchemasPlugin)
    }

}
