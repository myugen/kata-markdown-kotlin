package dev.mcabsan.markdown

data class Link(val label: Label, val url: URL) {
    fun asMarkdownText(): String {
        return "[${label.value}](${url.value})"
    }
}
