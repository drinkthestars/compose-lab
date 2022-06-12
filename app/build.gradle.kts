plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt") version AppConfig.detektVersion
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.proguardFile),
                AppConfig.proguardConsumerRules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }

    android.sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
}

detekt {
    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
    config = files(AppConfig.detektConfig)
    buildUponDefaultConfig = true
}

dependencies {
    debugImplementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Compose.tooling)

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)
    implementation(Libs.Coroutines.core)
    implementation(Libs.material)

    implementation(Libs.Accompanist.insets)
    implementation(Libs.Accompanist.systemUiController)

    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(Libs.AndroidX.Lifecycle.viewModelSavedState)

    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.iconsExtended)

    implementation(Libs.AndroidX.ConstraintLayout.compose)
    implementation(Libs.AndroidX.Hilt.navCompose)
    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.Coil.gif)
    implementation(Libs.Coil.compose)
    implementation(Libs.Giphy.ui)
    implementation(Libs.Glide.core)

    androidTestImplementation(Libs.junit)
    androidTestImplementation(Libs.AndroidX.Test.core)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Compose.test)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = Project.jvmTarget
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.Experimental"
        freeCompilerArgs =
            freeCompilerArgs + "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
    }
}
