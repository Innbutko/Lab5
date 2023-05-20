import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.21"
	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.jpa") version "1.8.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework:spring-webmvc:6.0.6")
	// Thymeleaf
	implementation("org.thymeleaf:thymeleaf-spring6:3.1.1.RELEASE")
	// Jetty & Servlet API
	implementation("org.eclipse.jetty:jetty-webapp:11.0.14")
	compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")

	// Spring Data
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
	implementation("org.springframework.data:spring-data-jpa:3.0.3")

	// Hibernate
	implementation("org.hibernate:hibernate-core:6.1.7.Final")

	// In Memory DB
	implementation("org.hsqldb:hsqldb:2.7.1")

	// Jackson
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

	// Security
	implementation("org.springframework.security:spring-security-config:6.0.3")
	implementation("org.springframework.security:spring-security-web:6.0.3")

	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

noArg {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
	invokeInitializers = true
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}
