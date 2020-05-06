plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}

group = "cash.andrew.ktor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(npm("abort-controller", "3.0.0")) // required by ktor but not pulled in
}

kotlin {
    target {
        nodejs
        browser {
            dceTask {
                dceOptions.devMode = true
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
        useCommonJs()
    }
}

tasks.register<Exec>("runWebpackedNode") {
    group = "build"
    description = "Package code with webpack then run it on nodejs"
    dependsOn(tasks.named("browserDevelopmentWebpack"))
    commandLine("node", "$buildDir/distributions/kotlin-ktor-issue.js")
}
