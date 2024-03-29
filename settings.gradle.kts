pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://api.mapbox.com/downloads/v2/releases/maven")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://api.mapbox.com/downloads/v2/releases/maven")
    }
}


rootProject.name = "UniRoute"
include(":app")
