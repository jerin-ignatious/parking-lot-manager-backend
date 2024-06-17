plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	id("org.openapi.generator") version "7.4.0"
}

group = "jerin.ignatious"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("javax.validation:validation-api:2.0.1.Final")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.0")
}

allprojects {

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
