plugins {
    alias(libs.plugins.openapi.generator.gradlePlugin)
}

repositories {
    mavenCentral()
}

tasks.register("initSubmodule") {
    doLast {
        val submoduleDir = file("sub-module")
        if (submoduleDir.exists()) {
            println("sub-module already exists")
        } else {
            exec {
                commandLine("git", "submodule", "update", "--init", "--recursive")
            }
        }

    }
}