package dev.mcabsan.markdown

fun main(args: Array<String>) {
    val fileSystem = FileSystem()
    val linkToFootnoteCommand = LinkToFootnoteCommand(fileSystem)
    
    val source = FilePath(args[1])
    val destination = FilePath(args[2])
    linkToFootnoteCommand.execute(source, destination)
}