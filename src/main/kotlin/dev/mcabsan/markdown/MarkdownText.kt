package dev.mcabsan.markdown

data class MarkdownText private constructor(
    private val content: Content,
    private val links: List<Link>,
    private val footnotes: Footnote
) {

    fun transform(): String {
        if (links.isEmpty()) return content.value
        val transformedText = replaceLinksForAnchors()
        return includeFootnoteIn(transformedText)
    }

    private fun replaceLinksForAnchors() = links.fold(content.value) { acc, link ->
        acc.replace(link.asMarkdownText(), "${link.label.value}${footnotes.anchorTagFor(link.url)}")
    }

    private fun includeFootnoteIn(transformedText: String) = "$transformedText\n\n${footnotes.asMarkdownText()}"

    companion object {
        fun from(text: String): MarkdownText {
            val content = Content.from(text)
            val links = content.extractLinks()
            val footnotes = Footnote.from(links)
            return MarkdownText(content, links, footnotes)
        }
    }
}
