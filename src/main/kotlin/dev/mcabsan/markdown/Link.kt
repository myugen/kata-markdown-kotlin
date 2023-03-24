package dev.mcabsan.markdown

data class Link(val label: String, val url: String) {
    fun asMarkdownText(): String {
        return "[$label]($url)"
    }
}
