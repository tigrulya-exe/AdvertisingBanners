plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.flywaydb.flyway") version "6.3.0"
    java
}

group = "ru.manasyan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.flywaydb:flyway-core")

    implementation("io.konform:konform-jvm:0.2.0")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks {
    flyway {
        url = "jdbc:mysql://localhost:3306/advertising_banners"
        user = "banners_user"
        password = "banners_user_password"
    }
    test {
        useJUnitPlatform()
    }
}