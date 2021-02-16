buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Project.agp)
        classpath(Project.Kotlin.gradlePlugin)
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
