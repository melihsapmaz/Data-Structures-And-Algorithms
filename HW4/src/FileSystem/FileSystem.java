package FileSystem;

import java.util.*;

/**
 * Represents a file system with directories and files.
 */
public class FileSystem {

    /**
     * The root directory of the file system.
     */
    private final Directory root;

/**
     * The current directory in the file system.
     */

    private Directory currentDirectory;

    /**
     * Initializes a new file system with a root directory.
     */
    public FileSystem() {
        this.root = new Directory("root", null);
        this.currentDirectory = root;
    }

    /**
     * Creates a new file with the given name in the given directory.
     *
     * @param name the name of the file
     * @param dir the parent directory
     */
    public void createFile(String name, Directory dir) {
        File newFile = new File(name, dir);
        dir.addElement(newFile);
    }

    /**
     * Creates a new directory with the given name in the given directory.
     *
     * @param name the name of the directory
     * @param parent the parent directory
     */
    public void createDirectory(String name, Directory parent) {
        Directory newDirectory = new Directory(name, parent);
        parent.addElement(newDirectory);
    }

    /**
     * Deletes the file with the given name from the given directory.
     *
     * @param name the name of the file
     * @param parent the parent directory
     */
    public void deleteFile(String name, Directory parent) {
        for(FileSystemElement element : parent.getChildren()) {
            if(element.getName().equals(name) && element instanceof File) {
                parent.removeElement(element);
                return;
            }
        }
    }

    /**
     * Deletes the directory with the given name from the given directory.
     *
     * @param name the name of the directory
     * @param parent the parent directory
     */
    public void deleteDirectory(String name, Directory parent) {
        deleteDirectoryHelper(name, parent, parent.getChildren().iterator());
    }

    /**
     * Moves the file or directory with the given name to the new parent directory.
     *
     * @param name the name of the file or directory
     * @param newParent the new parent directory
     */
    public void moveElement(String name, Directory newParent) {
        moveElementHelper(name, root, newParent);
    }

    /**
     * Searches for a file or directory with the given name in the file system.
     *
     * @param name the name of the file or directory
     * @return true if the file or directory is found, false otherwise
     */
    public boolean search(String name) {
        return searchHelper(name, root) > 0;
    }

    /**
     * Prints the directory tree of the given directory.
     *
     * @param dir the directory
     * @param prefix the prefix to print before the directory name
     */
    public void printDirectoryTree(Directory dir, String prefix) {
        Stack<Directory> stack = new Stack<>();
        while (dir != null) {
            stack.push(dir);
            dir = (Directory) dir.getParent();
        }
        StringBuilder prefixBuilder = new StringBuilder(prefix);
        while (!stack.isEmpty()) {
            dir = stack.pop();
            if (dir == currentDirectory) {
                System.out.println(prefixBuilder + "* " + dir.getName() + "/ (Current Directory)");
            } else {
                System.out.println(prefixBuilder + "* " + dir.getName() + "/");
            }
            prefixBuilder.append("  ");
        }
        prefix = prefixBuilder.toString();
        assert dir != null;
        for(FileSystemElement element : dir.getChildren()) {
            if(element instanceof Directory) {
                System.out.println(prefix + "* " + element.getName() + "/");
            } else if(element instanceof File) {
                System.out.println(prefix + "  " + element.getName());
            }
        }
    }

    /**
     * Lists the contents of the given directory.
     *
     * @param dir the directory
     */
    public void listContents(Directory dir) {
        for(FileSystemElement element : dir.getChildren()) {
            if(element instanceof Directory) {
                System.out.println("* " + element.getName());
            } else {
                System.out.println(element.getName());
            }
        }
    }

    /**
     * Sorts the contents of the given directory by date and prints them.
     *
     * @param dir the directory
     */
    public void sortDirectoryByDate(Directory dir) {
        dir.getChildren().sort(Comparator.comparing(FileSystemElement::getDateCreated));
        for(FileSystemElement element : dir.getChildren()) {
            String date = element.getDateCreated().toString().split("\\.")[0];
            System.out.println(element.getName() + " " + date);
        }
    }

    /**
     * Returns the full path of the given file or directory.
     *
     * @param element the file or directory
     * @return the full path
     */
    public String getFullPath(FileSystemElement element) {
        StringBuilder path = new StringBuilder();
        for(FileSystemElement current = element; current != null; current = current.getParent()) {
            path.insert(0, "/" + current.getName());
        }
        return path.toString();
    }

    /**
     * Changes the current directory to the directory with the given path.
     *
     * @param path the path of the directory
     * @return the directory with the given path, or null if the directory is not found
     */
    public Directory changeDirectory(String path) {
        if(path.startsWith("/")) {
            currentDirectory = changeDirectoryHelper(path.substring(1), root);
        } else {
            currentDirectory = changeDirectoryHelper(path, root);
        }
        return currentDirectory;
    }

    /**
     * Returns the root directory of the file system.
     *
     * @return the root directory
     */
    public Directory getRoot() {
        return root;
    }

    /**
     * Helper method to change the current directory to the directory with the given path.
     *
     * @param path the path of the directory
     * @param current the current directory
     * @return the directory with the given path, or null if the directory is not found
     */
    private Directory changeDirectoryHelper(String path, Directory current) {
        if(path.isEmpty()) {
            return current;
        }
        String[] names = path.split("/");
        for(int i = 1; i < names.length; i++) {
            boolean found = false;
            for(FileSystemElement element : current.getChildren()) {
                if(element.getName().equals(names[i]) && element instanceof Directory) {
                    current = (Directory) element;
                    found = true;
                    break;
                }
            }
            if(!found) {
                return null;
            }
        }
        return current;
    }

    /**
     * Helper method to move a file or directory with the given name to the new parent directory.
     *
     * @param name the name of the file or directory
     * @param current the current directory
     * @param newParent the new parent directory
     */
    private void moveElementHelper(String name, Directory current, Directory newParent) {
        List<FileSystemElement> toRemove = new ArrayList<>();
        for(FileSystemElement element : new ArrayList<>(current.getChildren())) {
            if(element.getName().equals(name)) {
                toRemove.add(element);
                newParent.getChildren().add(element);
                element.setParent(newParent);
                break; // break the loop once we've found and moved the directory
            }
        }
        current.getChildren().removeAll(toRemove);
    }

    /**
     * Helper method to delete a directory with the given name from the given directory.
     *
     * @param name the name of the directory
     * @param parent the parent directory
     * @param iterator an iterator over the children of the parent directory
     */
    private void deleteDirectoryHelper(String name, Directory parent, Iterator<FileSystemElement> iterator) {
        if (!iterator.hasNext()) {
            return;
        }
        FileSystemElement element = iterator.next();
        if (element.getName().equals(name) && element instanceof Directory directory) {
            deleteDirectoryHelper(name, directory, directory.getChildren().iterator());
            parent.removeElement(directory);
        } else {
            deleteDirectoryHelper(name, parent, iterator);
        }
    }

    /**
     * Helper method to search for a file or directory with the given name in the file system.
     *
     * @param name the name of the file or directory
     * @param directory the directory to search in
     * @return the number of occurrences of the file or directory with the given name
     */
    private int searchHelper(String name, Directory directory) {
        int count = 0;
        for(FileSystemElement element : directory.getChildren()) {
            if(element.getName().equals(name)) {
                if(count == 0)
                    System.out.print("Found : ");
                System.out.println(getFullPath(element.getParent()));
                count++;
            }
            if(element instanceof Directory) {
                count += searchHelper(name, (Directory) element);
            }
        }
        return count;
    }
}
