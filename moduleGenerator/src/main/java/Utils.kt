import java.io.File
import java.util.concurrent.TimeUnit

internal fun File.log(): File = apply { println("generated: $this") }

internal fun File.createFileAndLog(): File = apply {
    createNewFile()
    log()
}

internal fun File.createFolder(): File = apply { mkdirs() }

@Throws(RuntimeException::class)
internal fun File.ifExistsThrow(): File =
    if(exists()) throw RuntimeException("folder $path already exists") else this


internal fun File.replacePlaceholder(placeholders: Map<String, String>) {
    var text = readText()
    placeholders.forEach { (placeholder, value) ->
        text = text.replace(placeholder, value)
    }
    writeText(text)
}

internal fun File.addToGit(): File {
    "git add $this".runCommand(File("."))
    return this
}

internal fun String.toFragmentFileName(): String {
    val transformed = replace("(\\-)([a-z])".toRegex()) { it.groupValues[2].uppercase() }
    return transformed.replaceFirstChar { it.uppercase() }.plus("Fragment.kt")
}

internal fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()

internal fun String.runCommand(workingDir: File) {
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(60, TimeUnit.MINUTES)
}