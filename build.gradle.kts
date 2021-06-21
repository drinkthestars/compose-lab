buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Project.agp)
        classpath(Project.Kotlin.gradlePlugin)
        classpath("com.android.tools.build:gradle:7.0.0-beta01")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
