package dev.mcabsan.markdown

data class MarkdownText private constructor(
    private val content: String,
    private val links: List<Link>,
    private val footnotes: Footnote
) {

    fun transform(): String {
        if (links.isEmpty()) return content

        val transformedText = links.fold(content) { acc, link ->
            acc.replace(link.asMarkdownText(), "${link.label}${footnotes.anchorTagFor(link.url)}")
        }
        return "$transformedText\n\n${footnotes.asMarkdownText()}"
    }

    companion object {
        fun from(content: String): MarkdownText {
            val links = findLinks(content)
            val footnotes = Footnote.from(links)
            return MarkdownText(content, links, footnotes)
        }

        private fun findLinks(content: String): List<Link> {
            val linksRegex = "(\\[.+])(\\((https?)://.+\\))".toRegex()
            val matches = linksRegex.findAll(content)
            return matches.map { match ->
                match.groups.size.takeIf { it >= 3 }.let {
                    val label = match.groups[1]!!.value.replace("[", "").replace("]", "")
                    val url = match.groups[2]!!.value.replace("(", "").replace(")", "")
                    Link(label, url)
                }
            }.toList()
        }
    }
}
