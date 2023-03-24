package dev.mcabsan.markdown

data class MarkdownText(private val content: String) {
    fun transform(): String {
        val links = findLinks()
        var contentToTransform = content
        for ((index, link) in links.withIndex()) {
            val anchor = "[^anchor${index + 1}]"
            val transformedContent = contentToTransform.replace(link.asMarkdownText(), "${link.label}$anchor")
            val footnoteDefinition = """
                $anchor: ${link.url}
            """.trimIndent()
            contentToTransform = "$transformedContent$footnoteDefinition"
        }

        return contentToTransform
    }

    private fun findLinks(): List<Link> {
        // FIXME: This regex is not perfect, it does not support links with parenthesis
        val linksRegex = Regex.fromLiteral("(\\[.+\\])(\\((https?)://.+\\))")
        val matches = linksRegex.findAll(content)
        return matches.map { match ->
            val label = match.groupValues[1].replace("[", "").replace("]", "")
            val url = match.groupValues[2].replace("(", "").replace(")", "")
            Link(label, url)
        }.toList()
    }
}
