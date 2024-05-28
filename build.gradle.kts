buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        val nav_version = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.4.1" apply false
    id ("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31" apply false
}