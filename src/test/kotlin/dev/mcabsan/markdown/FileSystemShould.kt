package dev.mcabsan.markdown

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import kotlin.io.path.Path

class FileSystemShould {
    private val sut = FileSystem()

    @BeforeEach
    fun `create test files`() {
        val source = Path("test.md")
        Files.writeString(source, "# Test file")
    }

    @AfterEach
    fun `delete test files`() {
        val source = Path("test.md")
        Files.deleteIfExists(source)
    }

    @Test
    fun `read the content of a file`() {
        val content = sut.read(FilePath("test.md"))

        assertThat(content).isEqualTo("# Test file")
    }
}