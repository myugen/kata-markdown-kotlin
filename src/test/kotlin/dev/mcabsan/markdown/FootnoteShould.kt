package dev.mcabsan.markdown

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FootnoteShould {
    @Test
    fun `create a footnote text with shared anchors per URL`() {
        val codigoSostenibleLink = Link("Código Sostenible", "https://www.codigosostenible.com")
        val codigoSostenibleLink2 = Link("Otro Código Sostenible", "https://www.codigosostenible.com")
        val savvilyLink = Link("Savvily", "https://www.savvily.es")
        val footnote = Footnote.from(listOf(codigoSostenibleLink, codigoSostenibleLink2, savvilyLink))

        val actual = footnote.asMarkdownText()

        Assertions.assertThat(actual).isEqualTo(
            """
            [^anchor1]: https://www.codigosostenible.com
            [^anchor2]: https://www.savvily.es
        """.trimIndent()
        )
    }

    @Test
    fun `get relevant anchor tag for link`() {
        val codigoSostenibleLink = Link("Código Sostenible", "https://www.codigosostenible.com")
        val codigoSostenibleLink2 = Link("Otro Código Sostenible", "https://www.codigosostenible.com")
        val savvilyLink = Link("Savvily", "https://www.savvily.es")
        val footnote = Footnote.from(listOf(codigoSostenibleLink, codigoSostenibleLink2, savvilyLink))

        val actual = footnote.anchorTagFor(codigoSostenibleLink.url)

        Assertions.assertThat(actual).isEqualTo("[^anchor1]")
    }

    @Test
    fun `throw an exeption when trying to get anchor tag for a link that is not in the footnote`() {
        val linkNotInFootnote = Link("Otro libro", "https://www.otrolibro.com")

        val codigoSostenibleLink = Link("Código Sostenible", "https://www.codigosostenible.com")
        val codigoSostenibleLink2 = Link("Otro Código Sostenible", "https://www.codigosostenible.com")
        val savvilyLink = Link("Savvily", "https://www.savvily.es")

        val footnote = Footnote.from(listOf(codigoSostenibleLink, codigoSostenibleLink2, savvilyLink))

        Assertions.assertThatThrownBy { footnote.anchorTagFor(linkNotInFootnote.url) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Link is not in the footnote")
    }
}