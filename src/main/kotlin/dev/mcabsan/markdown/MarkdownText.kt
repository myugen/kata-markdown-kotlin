package dev.mcabsan.markdown

data class MarkdownText(private val content: String) {
    fun transform(): String {
        val links = findLinks()
        val allUrls = links.map { it.url }.distinct()
        var contentToTransform = content
        for (link in links) {
            val index = allUrls.indexOf(link.url)
            val anchor = "[^anchor${index + 1}]"
            val transformedContent = contentToTransform.replaceFirst(link.asMarkdownText(), "${link.label}$anchor")
            contentToTransform = transformedContent
        }
        var footnotes = "\n"
        for (url in allUrls) {
            val index = allUrls.indexOf(url)
            val anchor = "[^anchor${index + 1}]"
            footnotes = "$footnotes\n$anchor: $url"
        }
        return "$contentToTransform$footnotes"
    }

    private fun findLinks(): List<Link> {
        // FIXME: This regex is not perfect, it does not support links with parenthesis
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
