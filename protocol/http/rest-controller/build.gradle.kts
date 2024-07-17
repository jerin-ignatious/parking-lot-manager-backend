plugins {
    java
    id("org.springframework.boot") version "3.3.0"
}

dependencies {
    // internal dependencies
    implementation(project(":protocol:http:server-stub"))

    // external dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.cloud:spring-cloud-openfeign-core")

    // BOM imports
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.1"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.0"))
}
