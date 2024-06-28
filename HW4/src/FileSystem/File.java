package FileSystem;

/**
 * File class that extends FileSystemElement
 */
public class File extends FileSystemElement{

    /**
     * Constructor to create a new file
     * @param name name of the file
     * @param parent parent directory of the file
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }

    /**
     * Method to print the name of the file
     * @param prefix prefix to be printed before the name
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + getName());
    }
}
