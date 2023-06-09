import java.io.File

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

internal fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()