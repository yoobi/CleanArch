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

internal fun FeatureType.getTemplateGradleFile() = when(this) {
    FeatureType.FEATURE -> File(".gradleTemplate/template-feature.gradle")
    FeatureType.CORE -> File(".gradleTemplate/template-core.gradle")
    FeatureType.COMMON -> TODO()
//    FeatureType.LIBRARY_ANDROID -> TODO()
//    FeatureType.LIBRARY_KOTLIN_ONLY -> TODO()
}