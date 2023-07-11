plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.poc.project.generator"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven {
		url = uri("https://repo.spring.io/snapshot")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
    compileOnly("org.projectlombok:lombok:1.18.28")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.spring.initializr:initializr-web")
	implementation("io.spring.initializr:initializr-generator-spring")
	implementation("javax.servlet:javax.servlet-api:3.0.1")
	implementation("org.springframework.boot:spring-boot-starter-mustache:3.1.0")
	annotationProcessor("org.projectlombok:lombok:1.18.28")
	implementation("javax.cache:cache-api")
	implementation("org.ehcache:ehcache:3.1.0")
}

dependencyManagement {
	imports {
		mavenBom("io.spring.initializr:initializr-bom:0.13.0")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	enabled = true
	// Remove `plain` postfix from jar file name
	archiveClassifier.set("")
}