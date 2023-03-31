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
        val destination = Path("test-footnote.md")
        Files.deleteIfExists(source)
        Files.deleteIfExists(destination)
    }

    @Test
    fun `read the content of a file`() {
        val content = sut.read(FilePath("test.md"))

        assertThat(content).isEqualTo("# Test file")
    }

    @Test
    fun `write the content of a file`() {
        val destination = FilePath("test-footnote.md")
        val content = "# Test file"

        sut.write(destination, content)

        assertThat(Files.readString(Path(destination.value))).isEqualTo(content)
    }
}