import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.0"
	kotlin("plugin.spring") version "1.8.0"
}

group = "br.com.dillmann.shirotest"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	// Spring
	api("org.springframework.boot:spring-boot-starter-web")
	api("org.springframework.boot:spring-boot-starter-validation")

	// Kotlin support
	api("com.fasterxml.jackson.module:jackson-module-kotlin")
	api("org.jetbrains.kotlin:kotlin-reflect")
	api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Shiro
	api("org.apache.shiro:shiro-spring-boot-starter:2.0.0-SNAPSHOT:jakarta") {
		exclude(group = "org.apache.shiro", module = "shiro-spring")
		exclude(group = "org.apache.shiro", module = "shiro-web")
	}
	api("org.apache.shiro:shiro-spring:2.0.0-SNAPSHOT:jakarta") {
		exclude(group = "org.apache.shiro", module = "shiro-web")
		isForce = true
	}
	api("org.apache.shiro:shiro-web:2.0.0-SNAPSHOT:jakarta") {
		isForce = true
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}
