plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
    id("io.gitlab.arturbosch.detekt").version("1.21.0-RC1")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.simpleviralgames"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDefault = true
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isCrunchPngs = false
            extra["alwaysUpdateBuildId"] = false
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            versionNameSuffix = "-${System.currentTimeMillis()}"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.2.0"
        }
        packagingOptions {
            resources.excludes.addAll(
                listOf(
                    "META-INF/DEPENDENCIES",
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1"
                )
            )
        }
        namespace = "com.example.simpleviralgames"
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.7.0")
        implementation("androidx.appcompat:appcompat:1.4.1")
        implementation("androidx.constraintlayout:constraintlayout:2.1.3")
        implementation("androidx.compose.ui:ui-tooling:1.1.1")
        implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
        implementation("androidx.navigation:navigation-ui-ktx:2.4.1")
        implementation("androidx.compose.ui:ui:1.1.1")
        implementation("androidx.compose.ui:ui-util:1.1.1")
        implementation("androidx.compose.foundation:foundation:1.2.0")
        implementation("androidx.compose.material3:material3:1.0.0-alpha15")
        implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha15")
        implementation("androidx.compose.material:material-icons-core:1.2.0")
        implementation("androidx.compose.material:material-icons-extended:1.2.0")
        implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
        implementation("androidx.navigation:navigation-compose:2.5.1")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
        implementation("androidx.activity:activity-compose:1.5.1")
        implementation("androidx.room:room-runtime:2.4.2")
        implementation("androidx.room:room-ktx:2.4.2")
        implementation("androidx.datastore:datastore-preferences:1.0.0")
        implementation("androidx.paging:paging-compose:1.0.0-alpha14")
        implementation("com.google.dagger:hilt-android:2.43")

        implementation("io.coil-kt:coil-compose:2.0.0-rc02")
        implementation("net.zetetic:android-database-sqlcipher:4.5.0")

        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.0")

        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation("com.squareup.moshi:moshi:1.13.0")
        implementation("com.squareup.okhttp3:okhttp:4.9.3")
        debugImplementation("com.squareup.leakcanary:leakcanary-android:2.8.1")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

        implementation("com.google.accompanist:accompanist-pager:0.24.12-rc")

        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0-RC1")
        detektPlugins("ru.kode:detekt-rules-compose:1.0.1")

        kapt("com.google.dagger:hilt-compiler:2.43")
        kapt("androidx.room:room-compiler:2.4.2")
        kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        testImplementation("org.junit.platform:junit-platform-suite-engine:1.8.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        testImplementation("io.mockk:mockk:1.12.4")

        implementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    }
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    autoCorrect = true // automatically formats your code
    config =
        files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(false) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(false) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(false) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<Test> { useJUnitPlatform() }

tasks {
    create<Copy>(name = "InstallGitHook") {
        this.group = "help"
        this.from("${rootProject.rootDir}/scripts/")
            .into("${rootProject.rootDir}/.git/hooks/").eachFile {
                fileMode = 1100000111
            }
    }
}

tasks.getByPath(":app:preBuild").dependsOn("InstallGitHook")