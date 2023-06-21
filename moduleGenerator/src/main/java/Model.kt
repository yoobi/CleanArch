import java.io.File
import java.lang.IllegalArgumentException

internal enum class FeatureType(val string: String, val packageTree: String) {
    FEATURE("Feature", "features/feature-"),
    CORE("Core", "core/"),
//    LIBRARY_ANDROID("Library (Android)"),
//    LIBRARY_KOTLIN_ONLY("Library (Kotlin Only)"),
}

internal enum class FeatureLayer {
    ALL,
    DOMAIN,
    DATA,
    UI,
}

internal fun FeatureType.getTemplateGradleFile(featureLayer: FeatureLayer? = null) = when(this) {
    FeatureType.FEATURE -> {
        when(featureLayer) {
            FeatureLayer.DOMAIN -> File(".gradleTemplate/template-feature-domain.gradle")
            FeatureLayer.DATA -> File(".gradleTemplate/template-feature-data.gradle")
            FeatureLayer.UI -> File(".gradleTemplate/template-feature-ui.gradle")
            else -> throw IllegalArgumentException()
        }
    }
    FeatureType.CORE -> File(".gradleTemplate/template-core.gradle")
//    FeatureType.LIBRARY_ANDROID -> TODO()
//    FeatureType.LIBRARY_KOTLIN_ONLY -> TODO()
}