object AppConfig {
    const val applicationId = "com.goofy.goober.composelab"
    const val compileSdk = 32
    const val minSdk = 24
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "30.0.3"
    const val detektVersion = "1.20.0"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules = "consumer-rules.pro"
    const val proguardFile = "proguard-android.txt"
    const val detektConfig = "detekt-config.yml"
}

object Project {
    private const val agpVersion = "7.4.0-alpha03"
    const val jvmTarget = "1.8"
    const val agp = "com.android.tools.build:gradle:$agpVersion"

    object Kotlin {
        const val version = "1.6.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }
}
