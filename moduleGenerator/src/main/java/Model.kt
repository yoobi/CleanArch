import java.io.File

internal enum class FeatureType(val string: String, val packageTree: String) {
    FEATURE("Feature", "features/feature-"),
    COMMON("Common", "common/"),
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
        if(featureLayer == FeatureLayer.UI) File(".gradleTemplate/template-feature-ui.gradle")
        else File(".gradleTemplate/template-feature.gradle")
    }
    FeatureType.CORE -> File(".gradleTemplate/template-core.gradle")
    FeatureType.COMMON -> TODO()
//    FeatureType.LIBRARY_ANDROID -> TODO()
//    FeatureType.LIBRARY_KOTLIN_ONLY -> TODO()
}