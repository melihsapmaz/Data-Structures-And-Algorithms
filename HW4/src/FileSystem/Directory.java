package FileSystem;

import java.util.LinkedList;

/**
 * Directory class extends FileSystemElement.
 * It represents a directory in a file system, which can contain other FileSystemElements (files or directories).
 */
public class Directory extends FileSystemElement{

    /**
     * A list of FileSystemElements that are contained in this directory.
     */
    private final LinkedList<FileSystemElement> children;

    /**
     * Constructor for Directory.
     * @param name The name of the directory.
     * @param parent The parent directory of this directory.
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * Adds a FileSystemElement to the list of children of this directory.
     * @param element The FileSystemElement to be added.
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes a FileSystemElement from the list of children of this directory.
     * @param element The FileSystemElement to be removed.
     */
    public void removeElement(FileSystemElement element) {

        children.remove(element);
    }

    /**
     * Returns the list of children of this directory.
     * @return The list of children of this directory.
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Prints the name of this directory and all of its children.
     * @param prefix The prefix to be printed before the name of this directory.
     */
    @Override
    public void print(String prefix) {
        for(FileSystemElement element : children) {
            element.print(prefix + "  ");
        }
    }
}
