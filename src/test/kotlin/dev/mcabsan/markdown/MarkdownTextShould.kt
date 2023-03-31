package dev.mcabsan.markdown

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarkdownTextShould {

    @Test
    fun `retrieve same content when there is no any link`() {
        val content = "# Test file"
        val markdownText = MarkdownText.from(content)

        val actual = markdownText.transform()

        assertThat(actual).isEqualTo(content)
    }

    @Test
    fun `retrieve transformed content turning links into footnote anchors`() {
        val content = """
            # Test file
            
            El libro de [Código Sostenible](https://www.codigosostenible.com)
            es un librazo. ¡Cómpralo!
            
            De verdad, [Código Sostenible](https://www.codigosostenible.com) es un libro
            muy recomendable.
            
            Buscadlo en [Savvily](https://www.savvily.es)
        """.trimIndent()
        val markdownText = MarkdownText.from(content)

        val actual = markdownText.transform()

        assertThat(actual).isEqualTo(
            """
            # Test file
            
            El libro de Código Sostenible[^anchor1]
            es un librazo. ¡Cómpralo!
            
            De verdad, Código Sostenible[^anchor1] es un libro
            muy recomendable.
            
            Buscadlo en Savvily[^anchor2]
            
            [^anchor1]: https://www.codigosostenible.com
            [^anchor2]: https://www.savvily.es
            """.trimIndent()
        )
    }
}