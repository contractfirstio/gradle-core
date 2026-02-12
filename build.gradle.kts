plugins {
    `kotlin-dsl`
    `maven-publish`
    signing
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.contractfirst"
version = (project.findProperty("buildVersion") ?: "1.0.0").toString()

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
}

gradlePlugin {
    website.set("https://github.com/contractfirstio")
    vcsUrl.set("https://github.com/contractfirstio/gradle-core.git")
    plugins.all {
        if(this.id == "io.contractfirst.kotlin"){
            displayName = "Kotlin Code Bootup"
            description = "Plugin that encapsulates a common Kotlin build script"
            tags.set(listOf("build"))
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {

        pom {
            name.set("ContractFirst Gradle Plugin")
            description.set("Common Gradle bootstrap plugin for ContractFirst projects")
            url.set("https://github.com/contractfirstio/gradle-core")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }

            developers {
                developer {
                    id.set("contractfirstio")
                    name.set("Lee Cooper")
                    email.set("lee@contractfirst.io")
                }
            }

            scm {
                connection.set("scm:git:https://github.com/contractfirstio/gradle-core.git")
                developerConnection.set("scm:git:ssh://git@github.com/contractfirstio/gradle-core.git")
                url.set("https://github.com/contractfirstio/gradle-core")
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://central.sonatype.com/api/v1/publisher")

            credentials {
                username = providers.environmentVariable("SONATYPE_USERNAME").orNull
                password = providers.environmentVariable("SONATYPE_PASSWORD").orNull
            }
        }
    }
}

signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY")
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE")

    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        sign(publishing.publications)
    }
}
