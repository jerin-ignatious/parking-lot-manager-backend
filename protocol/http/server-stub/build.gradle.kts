import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.openapi.generator") version "7.4.0"
    java
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }

    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.1"))
}

val serverPath = "$rootDir/protocol/http/server-stub"

val preGenerateCleanup by tasks.registering(Delete::class) {
    delete(fileTree(serverPath) {
        setExcludes(listOf("build.gradle.kts", "build", "templates")) // Gradle's cache, helps in faster re-compiles
    })
    doLast {
        println("Cleaned server-stub dir")
    }
}

val generateServerStub by tasks.registering(GenerateTask::class) {
    dependsOn(preGenerateCleanup)

    inputSpec.set("$rootDir/protocol/http/specs.yaml")
    outputDir.set(serverPath)

    generatorName.set("spring")
    apiPackage.set("jerin.ignatious.parking.lot.protocol.http.api")
    modelPackage.set("jerin.ignatious.parking.lot.protocol.http.model")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "useTags" to "true",
            "interfaceOnly" to "true",
            "gradleBuildFile" to "false",
            "exceptionHandler" to "false",
            "enumPropertyNaming" to "UPPERCASE",
            "useBeanValidation" to "false"
        )
    )
}

val postGenerateCleanup by tasks.registering(Delete::class) {
    dependsOn(generateServerStub)

    doLast {
        println("Generated files exist: ${fileTree(serverPath).files.isNotEmpty()}")
    }

    // Customize the cleanup to avoid deleting necessary generated files
    delete(fileTree(serverPath) {
        include("**/*")
        exclude("build.gradle.kts", "build", "src/**", "templates/**")
    })
    doLast {
        println("Cleaned unnecessary files post generation")
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn(postGenerateCleanup)
}
