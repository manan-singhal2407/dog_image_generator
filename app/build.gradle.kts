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
            buildConfigField("String", "BASE_URL", project.properties["base_url"].toString())
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            versionNameSuffix = "-${System.currentTimeMillis()}"
            buildConfigField("String", "BASE_URL", project.properties["base_url"].toString())
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
        implementation("androidx.compose.ui:ui:1.1.1")
        implementation("androidx.compose.material3:material3:1.0.0-alpha15")
        implementation("androidx.compose.material:material-icons-extended:1.2.0")
        implementation("androidx.navigation:navigation-compose:2.5.1")

        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation("com.squareup.moshi:moshi:1.13.0")
        implementation("com.squareup.okhttp3:okhttp:4.9.3")
        debugImplementation("com.squareup.leakcanary:leakcanary-android:2.8.1")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
        kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

        implementation("com.github.bumptech.glide:glide:4.15.0")
        kapt("com.github.bumptech.glide:compiler:4.15.0")

        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0-RC1")
        detektPlugins("ru.kode:detekt-rules-compose:1.0.1")

        implementation("androidx.room:room-runtime:2.4.2")
        implementation("androidx.room:room-ktx:2.4.2")
        kapt("androidx.room:room-compiler:2.4.2")

        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
        implementation("com.google.dagger:hilt-android:2.43")
        kapt("com.google.dagger:hilt-compiler:2.43")

        implementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
    config =
        files("$projectDir/config/detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
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