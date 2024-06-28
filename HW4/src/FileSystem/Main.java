package FileSystem;

import java.util.Scanner;

/**
 * Main class to manage the file system.
 * The user can perform the following operations:
 * 1. Change directory
 * 2. List directory contents
 * 3. Create file
 * 4. Create directory
 * 5. Delete file
 * 6. Delete directory
 * 7. Move file/directory
 * 8. Search file/directory
 * 9. Print directory tree
 * 10. Sort contents by date
 * 11. Exit
 */
public class Main {

    /**
     * Scanner object to read input from the user.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * FileSystem object to manage the file system.
     */
    private static final FileSystem fileSystem = new FileSystem();

    /**
     * Directory object to store the current directory.
     */
    private static Directory currentDirectory;

    /**
     * Main method to manage the file system.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        currentDirectory = fileSystem.getRoot();

        while(true){
            System.out.println();
            System.out.println("Current directory: " + fileSystem.getFullPath(currentDirectory));
            System.out.println("========== File System Management ==========");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file");
            System.out.println("4. Create directory");
            System.out.println("5. Delete file");
            System.out.println("6. Delete directory");
            System.out.println("7. Move file/directory");
            System.out.println("8. Search file/directory");
            System.out.println("9. Print directory tree");
            System.out.println("10. Sort contents by date");
            System.out.println("11. Exit");
            System.out.print("Please select an option:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            switch(choice) {
                case 1 -> changeDirectory();//
                case 2 -> listContents();//
                case 3 -> createFile();//
                case 4 -> createDirectory();//
                case 5 -> deleteFile();//
                case 6 -> deleteDirectory();//
                case 7 -> moveElement();
                case 8 -> search();
                case 9 -> printDirectoryTree();
                case 10 -> sortDirectoryByDate();
                case 11 -> System.exit(0);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Method to change the current directory.
     */
    private static void changeDirectory(){
        System.out.print("Current directory: ");
        System.out.println(fileSystem.getFullPath(currentDirectory));
        System.out.print("Enter new directory path: ");
        String path = scanner.nextLine();
        Directory newDirectory = fileSystem.changeDirectory(path);
        if(newDirectory == null){
            System.out.println("Directory not found.");
        } else {
            currentDirectory = newDirectory;
            System.out.print("Current directory changed to ");
            System.out.println(fileSystem.getFullPath(currentDirectory));
        }
    }

    /**
     * Method to list the contents of the current directory.
     */
    private static void listContents(){
        fileSystem.listContents(currentDirectory);
    }

    /**
     * Method to create a file.
     */
    private static void createFile(){
        System.out.print("Enter the name of the file: ");
        String name = scanner.nextLine();
        fileSystem.createFile(name, currentDirectory);
    }

    /**
     * Method to create a directory.
     */
    private static void createDirectory(){
        System.out.print("Enter the name of the directory: ");
        String name = scanner.nextLine();
        fileSystem.createDirectory(name, currentDirectory);
    }

    /**
     * Method to delete a file.
     */
    private static void deleteFile(){
        System.out.print("Enter the name of the file: ");
        String name = scanner.nextLine();
        fileSystem.deleteFile(name, currentDirectory);
    }

    /**
     * Method to delete a directory.
     */
    private static void deleteDirectory(){
        System.out.print("Enter the name of the directory: ");
        String name = scanner.nextLine();
        fileSystem.deleteDirectory(name, currentDirectory);
    }

    /**
     * Method to move a file/directory to a new parent directory.
     */
    private static void moveElement(){
        System.out.print("Enter the name of the file/directory: ");
        String name = scanner.nextLine();
        System.out.print("Enter the path of the new parent directory: ");
        String path = scanner.nextLine();
        Directory newParent = fileSystem.changeDirectory(path);
        if(newParent == null){
            System.out.println("Directory not found.");
        } else {
            fileSystem.moveElement(name, newParent);
        }
    }

    /**
     * Method to search for a file/directory.
     */
    private static void search(){
        System.out.print("Enter the name of the file/directory: ");
        String name = scanner.nextLine();
        if(!fileSystem.search(name)){
            System.out.println("File/directory not found.");
        }
    }

    /**
     * Method to print the directory tree.
     */
    private static void printDirectoryTree(){
        fileSystem.printDirectoryTree(currentDirectory, "");
    }

    /**
     * Method to sort the contents of the current directory by date.
     */
    private static void sortDirectoryByDate(){
        fileSystem.sortDirectoryByDate(currentDirectory);
    }
}