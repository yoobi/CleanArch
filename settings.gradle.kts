enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CleanArch"

includeModulesDir()

fun findGradleModules(file: File): List<File> {
    val mutableList = mutableListOf<File>()
    file.walkTopDown().forEach {
        if (it.name == "build.gradle" && it.parentFile != rootDir) {
            mutableList.add(File(it.parent.substringAfter(settingsDir.name, "")))
        }
    }
    return mutableList
}

fun includeModulesDir() {
    val foundModules = findGradleModules(rootDir)
        .map { it.path.replace(File.separatorChar, ':') }
        .sorted()

    println("foundModules: $foundModules")
    include(foundModules)
}
