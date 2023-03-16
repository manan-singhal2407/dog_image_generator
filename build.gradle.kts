buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven( "https://jitpack.io" )
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}