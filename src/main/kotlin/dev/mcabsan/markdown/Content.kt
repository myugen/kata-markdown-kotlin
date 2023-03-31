package dev.mcabsan.markdown

data class Content private constructor(val value: String) {
    fun extractLinks(): List<Link> {
        val matches = linksRegex.findAll(value)
        return matches.map { match ->
            match.groups.size.takeIf { it >= 3 }.let {
                val labelText = match.groups[1]!!.value.replace("[", "").replace("]", "")
                val urlText = match.groups[2]!!.value.replace("(", "").replace(")", "")
                Link(Label(labelText), URL(urlText))
            }
        }.toList()
    }

    companion object {
        private val linksRegex = "(\\[.+])(\\((https?)://.+\\))".toRegex()
        fun from(value: String): Content {
            return Content(value)
        }
    }
}
