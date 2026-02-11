# Common gradle build script wrapped up into gradle plugins 
Project encapsulating common gradle build scripts to bootstrap application quickly

## Kotlin
Simple plugin to get you up and running with Kotlin with single plugin.  Perfect for quick kotlin prototyping. We will 
attempt to keep up with all the latest long term support (LTS) releases of Kotlin and the JVM.
If you wish to use this build script for production use then we recommend three approaches.  

1. Just copy the script from here ```src/main/kotlin/io.contractfirst.kotlin.gradle.kts``` for use in your own project
2. If we are behind your need to bump Kotlin or the JVM then we are happy to do this just create an issue, and we should 
   service this quickly.
3. Fork the project yourself and publish your own internal version of the plugin that you can manipulate at your 
   discretion. 

Usage: https://plugins.gradle.org/plugin/io.contractfirst.kotlin 
```
plugins {
    id("io.contractfirst.kotlin") version "1.0.0"
}
```
| Plugin version | Kotlin version | JVM version | JUnit |
|----------------|----------------|-------------|-------|
| 1.0.0          | 1.9.22         | 21          | 5     |
