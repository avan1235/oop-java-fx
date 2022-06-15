plugins {
  java
  application
  id("org.openjfx.javafxplugin") version "0.0.13"
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

javafx {
  version = "11"
  modules = listOf("javafx.controls", "javafx.fxml")
}

group = "pl.edu.mimuw"
version = "2022"

application {
  mainClass.set("pl.edu.mimuw.Main")
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
  named<Test>("test") {
    useJUnitPlatform()
  }
}
