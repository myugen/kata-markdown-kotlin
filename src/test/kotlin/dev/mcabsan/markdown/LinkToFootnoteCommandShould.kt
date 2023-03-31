package dev.mcabsan.markdown

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path

internal class LinkToFootnoteCommandShould {
    private val sut = LinkToFootnoteCommand(FileSystem())

    @BeforeEach
    fun `create test files`() {
        val source = Path("test.md")
        Files.writeString(
            source,
            """
            # Test file

            El libro de [Código Sostenible](https://www.codigosostenible.com)
            es un librazo. ¡Cómpralo!
            """.trimIndent()
        )
    }

    @AfterEach
    fun `delete test files`() {
        val source = Path("test.md")
        val destination = Path("test-footnote.md")
        Files.deleteIfExists(source)
        Files.deleteIfExists(destination)
    }

    @Test
    fun `create new markdown file turning links into footnote`() {
        val source = FilePath("test.md")
        val destination = FilePath("test-footnote.md")

        sut.execute(source, destination)
        val content = Files.readString(Paths.get(destination.value))

        assertThat(content).isEqualTo(
            """
            # Test file

            El libro de Código Sostenible[^anchor1]
            es un librazo. ¡Cómpralo!

            [^anchor1]: https://www.codigosostenible.com
            """.trimIndent()
        )
    }
}
