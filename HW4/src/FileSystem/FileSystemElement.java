package FileSystem;

import java.sql.Timestamp;

/**
 * Represents a file system element.
 */
public abstract class FileSystemElement {
    /**
     * The name of the file system element.
     */
    protected String name;

    /**
     * The date the file system element was created.
     */
    protected Timestamp dateCreated;

    /**
     * The parent directory of the file system element.
     */
    protected FileSystemElement parent;

    /**
     * Initializes a new file system element with the given name and parent directory.
     *
     * @param name the name of the file system element
     * @param parent the parent directory
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the file system element.
     *
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date the file system element was created.
     *
     * @return the date the file system element was created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent directory of the file system element.
     *
     * @return the parent directory of the file system element
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent the parent directory of the file system element
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Prints the file system element with the given prefix.
     *
     * @param prefix the prefix to print before the file system element
     */
    public abstract void print(String prefix);
}
