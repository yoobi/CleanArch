internal object Question {

    fun askFeatureType(): FeatureType {
        val typeStringBuilder = StringBuilder("Select type of module:").appendLine()
        FeatureType.values().forEachIndexed { index, featureType ->
            typeStringBuilder.appendLine("- [${index+1}] ${featureType.string}")
        }
        println(typeStringBuilder.trimEnd().toString())
        val featureType = readLine()?.toIntOrNull() ?: throw RuntimeException("Should be a number")
        if(featureType !in 1..FeatureType.values().size) {
            throw RuntimeException("Should be between 1 and ${FeatureType.values().size+1}")
        }
        return FeatureType.values()[featureType-1]
    }

    fun askFeatureLayer(): FeatureLayer {
        val typeStringBuilder = StringBuilder("Select layer of module:").appendLine()
        FeatureLayer.values().forEachIndexed { index, featureType ->
            typeStringBuilder.appendLine("- [${index+1}] ${featureType.name}")
        }
        println(typeStringBuilder.trimEnd().toString())
        val layer = readLine()?.toIntOrNull() ?: throw RuntimeException("Should be a number")
        if(layer !in 1..FeatureLayer.values().size+1) {
            throw RuntimeException("Should be between 1 and ${FeatureType.values().size+1}")
        }
        return FeatureLayer.values()[layer-1]
    }

    fun askFeatureName(): String {
        println("Enter module name in PascaleCase (only alphabet)")
//        println("Hint: To place the module in a sub folder use the syntax: :subfolder:module-name for example :libraries:lib")
        val moduleName = readLine()!!
        if(moduleName.isBlank() || !moduleName.matches("^[A-Z][a-zA-Z]+".toRegex())) {
//        if(moduleName.isBlank() || moduleName.last() == ':' || moduleName.contains(":{2,}".toRegex())) {
            throw RuntimeException("Please follow naming convention")
        }
        return moduleName
    }

}