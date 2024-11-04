import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("java")
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

val specFile = "$projectDir/sub-module/spec/main-spec.yml".replace("\\", "/")

tasks.register<GenerateTask>("generateServer") {
    generatorName.set("spring")
    inputSpec.set(specFile)
    outputDir.set("$projectDir/build/generated/server")
    apiPackage.set("openapi.server.api")
    invokerPackage.set("openapi.server.invoker")
    modelPackage.set("openapi.server.model")
    configOptions.set(mapOf(
        "interfaceOnly" to "true",
        "dateLibrary" to "java8",
        "useJakartaEe" to "true",
        "useTags" to "true",
        "generateConstructorWithAllArgs" to "true",
        "serializableModel" to "true"
    ))
}

tasks.register<GenerateTask>("generateClient") {
    generatorName.set("java")
    inputSpec.set(specFile)
    outputDir.set("$projectDir/build/generated/client")
    apiPackage.set("openapi.client.api")
    invokerPackage.set("openapi.client.invoker")
    modelPackage.set("openapi.client.model")
    library.set("webclient")
    configOptions.set(mapOf(
        "hideGenerationTimestamp" to "true",
        "useRuntimeException" to "true",
        "useJakartaEe" to "true",
        "serializableModel" to "true"
    ))
}

tasks.named("build") {
    dependsOn("generateServer", "generateClient")
}

