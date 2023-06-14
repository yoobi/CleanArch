import org.junit.Assert
import org.junit.Test

class GeneratorTest {

    private val javaPackagePath = "$SRC_PATH$APPLICATION_ID_PATH"

    @Test
    fun toModuleNameTest() {
        val str = "CommonViewWithModule"
        Assert.assertEquals(toModuleName(str), "common-view-with-module")
    }

    private data class ModuleTest(
        val moduleName: String,
        val featureType: FeatureType,
        val featureLayer: FeatureLayer?
    ) {
        val rootPkgName = "${featureType.packageTree}${toModuleName(moduleName)}"
            .plus(if(featureLayer != null) "/${featureLayer.name.lowercase()}" else "")

        val srcPkgName = "${featureType.packageTree.replace("-", "/")}${toModuleName(moduleName).replace("-", "/")}"
            .plus(if(featureLayer != null) "/${featureLayer.name.lowercase()}" else "")
    }

    @Test
    fun checkGenerateModuleSrc() {
        val moduleList = listOf(
            ModuleTest("TopVideos", FeatureType.FEATURE, FeatureLayer.DATA),
            ModuleTest("TopVideos", FeatureType.FEATURE, FeatureLayer.UI),
            ModuleTest("TopVideos", FeatureType.FEATURE, FeatureLayer.DOMAIN),
            ModuleTest("FragmentUi", FeatureType.CORE, null),
        )
        moduleList.forEach {
            val moduleName = toModuleName(it.moduleName)
            moduleRootFile = generateModuleRootFile(it.featureType, it.featureLayer, moduleName)
            val srcFile = generateModuleSrcFile(it.featureType, it.featureLayer, moduleName)
            println("root: $moduleRootFile")
            println("src: $srcFile")
            Assert.assertEquals(moduleRootFile.path, it.rootPkgName)
            Assert.assertEquals(srcFile.path, "${it.rootPkgName}/$javaPackagePath/${it.srcPkgName}")
            println("------------------------")
        }
    }

}