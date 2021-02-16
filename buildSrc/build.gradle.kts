import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

sourceSets.all {
    java.srcDir("src/$name/kotlin")
}
