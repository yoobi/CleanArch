import java.io.File
import java.nio.file.Files

private const val SRC_PATH = "src/main/java/"
private val applicationIdPath by lazy {
    val projectDir = File("app/$SRC_PATH").walkTopDown().first { it.extension == "kt" }.parentFile.path
    projectDir.substringAfter("app/$SRC_PATH")
}

private val applicationId by lazy {
    applicationIdPath.replace("/", ".")
}

lateinit var moduleRootFile: File
lateinit var moduleSrcFile: File

fun main() {
    val type = Question.askFeatureType()
    val layer: FeatureLayer? = if(type == FeatureType.FEATURE) Question.askFeatureLayer() else null
    val moduleName = Question.askFeatureName()
    moduleRootFile = generateModuleRootFile(type, layer, moduleName)
    moduleSrcFile = File(moduleRootFile, SRC_PATH).ifExistsThrow().createFolder()

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
    copyGradleFile(type, moduleRootFile).log().replacePlaceholder(
        mapOf("{{featureName}}" to moduleName.lowercase())
    )
}

private fun generateFeatureFiles(type: FeatureType, layer: FeatureLayer, moduleName: String) {
    copyGradleFile(type, moduleRootFile).log().replacePlaceholder(
        mapOf(
            "{{featureName}}" to moduleName.lowercase(),
            "{{featureLayer}}" to layer.name.lowercase()
        )
    )
    File(moduleRootFile, "README.md").createFileAndLog()
    if(layer == FeatureLayer.UI) {
        File(moduleSrcFile, "${moduleName}Fragment.kt").createFileAndLog()
    }
}

private fun copyGradleFile(featureType: FeatureType, moduleRootFile: File): File {
    val newBuildGradle = File(moduleRootFile, "build.gradle")
    Files.copy(
        featureType.getTemplateGradleFile().toPath(),
        newBuildGradle.toPath()
    )
    return newBuildGradle
}

private fun generateModuleRootFile(type: FeatureType, layer: FeatureLayer?, name:String): File {
    val layerPath = "/${layer?.name?.lowercase()}"
    val fullPath = "${type.packageTree}${name.lowercase()}"
    return File(fullPath.plus(if(layer != null) layerPath else ""))
        .ifExistsThrow()
        .createFolder()
}
