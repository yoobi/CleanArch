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
includeModulesDir(settingsDir.path)

fun includeModulesDir(vararg dirs: String) {
    val foundModules = dirs
            .map(::File)
            .flatMap(::findGradleModules)
            .map { it.path.replace(File.separatorChar, ':') }
            .sorted()

    println("foundModules: $foundModules")
    include(*foundModules.toTypedArray())
}

fun findGradleModules(file: File): List<File> {
    val mutableList = mutableListOf<File>()
    file.walkTopDown().forEach {
        if (it.name == "build.gradle" && it.parentFile != rootDir) {
            mutableList.add(File(it.parent.substringAfter(settingsDir.name, "")))
        }
    }
    return mutableList
}
