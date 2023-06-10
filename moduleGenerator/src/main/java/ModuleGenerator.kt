import java.io.File
import java.nio.file.Files

internal const val SRC_PATH = "src/main/java/"
internal const val APPLICATION_ID_PATH = "io/yoobi/poc/cleanarch"

lateinit var moduleRootFile: File
lateinit var moduleSrcFile: File

fun main() {
    val type = Question.askFeatureType()
    val layer: FeatureLayer? = if(type == FeatureType.FEATURE) Question.askFeatureLayer() else null
    val moduleName = toModuleName(Question.askFeatureName())
    moduleRootFile = generateModuleRootFile(type, layer, moduleName).createFolder()
    moduleSrcFile = generateModuleSrcFile(type, layer, moduleName).createFolder()


    when(type) {
        FeatureType.FEATURE -> {
            if(layer != FeatureLayer.ALL) {
                generateFeatureFiles(type, layer!!, moduleName)
            } else {
                val layerToGenerate = FeatureLayer.values().toMutableList().apply { remove(FeatureLayer.ALL) }
                layerToGenerate.forEach { generateFeatureFiles(type, it, moduleName) }
            }
        }
        FeatureType.CORE -> generateCoreFiles(type, moduleName)
        FeatureType.COMMON -> generateCoreFiles(type, moduleName)
    }

    println("Sync gradle to reload project modules :)")
}

private fun generateCoreFiles(type: FeatureType, moduleName: String) {
    copyGradleFile(type, moduleRootFile).log().addToGit().replacePlaceholder(
        mapOf("{{featureName}}" to toGradleName(moduleName))
    )
}

private fun generateFeatureFiles(type: FeatureType, layer: FeatureLayer, moduleName: String) {
    copyGradleFile(type, moduleRootFile, layer).log().addToGit().replacePlaceholder(
        mapOf(
            "{{featureName}}" to toGradleName(moduleName),
            "{{featureLayer}}" to layer.name.lowercase()
        )
    )
//    File(moduleRootFile, "README.md").createFileAndLog()
    if(layer == FeatureLayer.UI) {
        File(moduleSrcFile, "${moduleName.replaceFirstChar { it.uppercase() }}Fragment.kt")
            .createFileAndLog().addToGit()
    }
}

internal fun copyGradleFile(featureType: FeatureType, moduleRootFile: File, featureLayer: FeatureLayer? = null): File {
    val newBuildGradle = File(moduleRootFile, "build.gradle")
    Files.copy(
        featureType.getTemplateGradleFile(featureLayer).toPath(),
        newBuildGradle.toPath()
    )
    return newBuildGradle
}

internal fun generateModuleRootFile(type: FeatureType, layer: FeatureLayer?, name:String): File {
    val layerPath = "/${layer?.name?.lowercase()}"
    val fullPath = "${type.packageTree}${name.lowercase()}"
        .plus(if(layer != null) layerPath else "")
    return File(fullPath).ifExistsThrow()
}

internal fun generateModuleSrcFile(type: FeatureType, layer: FeatureLayer?, name:String): File {
    val featureTypePath = type.packageTree
        .replace("features/", "")
        .replace("-", "/")
    val layerPath = "/${layer?.name?.lowercase()}"
    val fullPath = "${SRC_PATH}/$APPLICATION_ID_PATH/$featureTypePath/"
        .plus(name.lowercase().replace("-", "/"))
        .plus(if(layer != null) layerPath else "")
    return File(moduleRootFile, fullPath).ifExistsThrow()
}

internal fun toModuleName(name: String): String {
    var transformed = name.replaceFirstChar { it.lowercase() }
    transformed.asIterable().iterator().forEachRemaining {
        if (it.isUpperCase()) {
            val index = transformed.indexOf(it)
            transformed = transformed.addCharAtIndex('-', index)
        }
    }
    return transformed.lowercase()
}

internal fun toGradleName(moduleName: String) =
    moduleName.lowercase().replace("-", ".")
