package dev.mcabsan.markdown

class LinkToFootnote(private val fileSystem: FileSystem) {
    fun execute(source: FilePath, destination: FilePath) {
        val content = fileSystem.read(source)
        val markdown = MarkdownText.from(content)
        val transformedMarkdown = markdown.transform()
        TODO("Not yet implemented")
    }
}
