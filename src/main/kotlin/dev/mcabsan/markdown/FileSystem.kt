package dev.mcabsan.markdown

import java.nio.file.Files
import kotlin.io.path.Path

class FileSystem {
    fun read(filePath: FilePath): String {
        return Files.readString(Path(filePath.value))
    }

    fun write(filePath: FilePath, content: String) {
        Files.writeString(Path(filePath.value), content)
    }
}
