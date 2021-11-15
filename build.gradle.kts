buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Project.agp)
        classpath(Project.Kotlin.gradlePlugin)
        classpath("com.android.tools.build:gradle:7.2.0-alpha04")
        classpath("app.cash.molecule:molecule-gradle-plugin:0.1.0")
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
