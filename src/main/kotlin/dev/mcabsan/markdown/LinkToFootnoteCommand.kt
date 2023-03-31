package dev.mcabsan.markdown

class LinkToFootnoteCommand(private val fileSystem: FileSystem) {
    fun execute(source: FilePath, destination: FilePath) {
        val content = fileSystem.read(source)
        val markdown = MarkdownText.from(content)
        val transformedMarkdown = markdown.transform()
        fileSystem.write(destination, transformedMarkdown)
    }
}
