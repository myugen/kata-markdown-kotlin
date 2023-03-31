package dev.mcabsan.markdown

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ContentShould {

    @Test
    fun `extract links in a text`() {
        val content = """
            # Test file
            
            El libro de [Código Sostenible](https://www.codigosostenible.com)
            es un librazo. ¡Cómpralo!
            
            De verdad, [Código Sostenible](https://www.codigosostenible.com) es un libro
            muy recomendable.
            
            Buscadlo en [Savvily](https://www.savvily.es)
        """.trimIndent()

        val actual = Content.from(content).extractLinks()

        assertThat(actual).isEqualTo(
            listOf(
                Link(Label("Código Sostenible"), URL("https://www.codigosostenible.com")),
                Link(Label("Código Sostenible"), URL("https://www.codigosostenible.com")),
                Link(Label("Savvily"), URL("https://www.savvily.es"))
            )
        )
    }
}