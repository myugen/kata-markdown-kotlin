package dev.mcabsan.markdown

data class Footnote private constructor(private val anchors: Map<Tag, URL>) {
    fun asMarkdownText(): String {
        return anchors.entries.joinToString("\n") { (tag, url) ->
            "${tag.value}: ${url.value}"
        }
    }

    fun anchorTagFor(url: URL): String {
        return anchors.entries.find { (_, u) -> u == url }?.key?.value
            ?: throw IllegalArgumentException("Link is not in the footnote")
    }

    companion object {
        fun from(links: List<Link>): Footnote {
            val anchors = links.map { it.url }.distinct().mapIndexed { index, url ->
                val tag = Tag("[^anchor${index + 1}]")
                tag to url
            }.toMap()
            return Footnote(anchors)
        }
    }
}
