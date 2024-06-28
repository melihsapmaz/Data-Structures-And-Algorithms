import java.sql.Timestamp;
import java.util.*;

/**
 * Represents a person in the social network.
 */
public class Main {
    /**
     * Main method to run the social network analysis program.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);

        // Adding some people for demonstration
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));
        System.out.println("John Doe (Timestamp: " + network.people.get("John Doe").timestamp + ")");
        System.out.println("Jane Smith (Timestamp: " + network.people.get("Jane Smith").timestamp + ")");
        System.out.println("Alice Johnson (Timestamp: " + network.people.get("Alice Johnson").timestamp + ")");
        System.out.println("Bob Brown (Timestamp: " + network.people.get("Bob Brown").timestamp + ")");
        System.out.println("Emily Davis (Timestamp: " + network.people.get("Emily Davis").timestamp + ")");
        System.out.println("Frank Wilson (Timestamp: " + network.people.get("Frank Wilson").timestamp + ")");

        // Adding friendships for demonstration
        network.addFriendship("John Doe", network.people.get("John Doe").timestamp,"Jane Smith", network.people.get("Jane Smith").timestamp);
        network.addFriendship("John Doe", network.people.get("John Doe").timestamp,"Alice Johnson", network.people.get("Alice Johnson").timestamp);
        network.addFriendship("Jane Smith", network.people.get("Jane Smith").timestamp,"Bob Brown", network.people.get("Bob Brown").timestamp);
        network.addFriendship("Emily Davis", network.people.get("Emily Davis").timestamp,"Frank Wilson", network.people.get("Frank Wilson").timestamp);

        // Finding the shortest path for demonstration
        network.findShortestPath("John Doe", network.people.get("John Doe").timestamp, "Bob Brown", network.people.get("Bob Brown").timestamp);
        network.findShortestPath("Jane Smith", network.people.get("Jane Smith").timestamp,"Alice Johnson", network.people.get("Alice Johnson").timestamp);

        // Counting clusters for demonstration
        network.countClusters();

        // Suggesting friends for demonstration
        network.suggestFriends("John Doe", network.people.get("John Doe").timestamp, 4);

        while(true){
            userInterface();
            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // clear the input buffer
                continue; // go to the next iteration of the loop
            }

            switch(option){
                case 1:
                    System.out.print("Enter person's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter person's age: ");
                    try {
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter person's hobbies (comma-separated): ");
                        List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));
                        network.addPerson(name, age, hobbies);
                        System.out.println("Person added: " + name + " (Timestamp: " + network.people.get(name).timestamp + ")");
                        System.out.println();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer for age.");
                        scanner.nextLine(); // clear the input buffer
                    }
                    break;
                case 2:
                    System.out.print("Enter person's name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter person's timestamp: ");
                    try {
                        Timestamp timestamp = Timestamp.valueOf(scanner.nextLine());
                        network.removePerson(name, timestamp);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid timestamp format. Please enter a timestamp in the format yyyy-mm-dd hh:mm:ss.");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter first person's name: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter first person's timestamp: ");
                    try {
                        Timestamp timestamp1 = Timestamp.valueOf(scanner.nextLine());
                        System.out.print("Enter second person's name: ");
                        String name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp: ");
                        Timestamp timestamp2 = Timestamp.valueOf(scanner.nextLine());
                        network.addFriendship(name1, timestamp1, name2, timestamp2);
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid timestamp format. Please enter a timestamp in the format yyyy-mm-dd hh:mm:ss.");
                    }
                    break;
                case 4:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter first person's timestamp: ");
                    try {
                        Timestamp timestamp1 = Timestamp.valueOf(scanner.nextLine());
                        System.out.print("Enter second person's name: ");
                        String name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp: ");
                        Timestamp timestamp2 = Timestamp.valueOf(scanner.nextLine());
                        network.removeFriendship(name1, timestamp1, name2, timestamp2);
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid timestamp format. Please enter a timestamp in the format yyyy-mm-dd hh:mm:ss.");
                    }
                    break;
                case 5:
                    System.out.print("Enter first person's name: ");
                    String source = scanner.nextLine();
                    System.out.println("Enter first person's timestamp: ");
                    try {
                        Timestamp sourceTimestamp = Timestamp.valueOf(scanner.nextLine());
                        System.out.print("Enter second person's name: ");
                        String destination = scanner.nextLine();
                        System.out.print("Enter second person's timestamp: ");
                        Timestamp destinationTimestamp = Timestamp.valueOf(scanner.nextLine());
                        network.findShortestPath(source, sourceTimestamp, destination, destinationTimestamp);
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid timestamp format. Please enter a timestamp in the format yyyy-mm-dd hh:mm:ss.");
                    }
                    break;
                case 6:
                    System.out.print("Enter person's name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter person's timestamp: ");
                    try {
                        Timestamp timestamp = Timestamp.valueOf(scanner.nextLine());
                        System.out.print("Enter number of friends to suggest: ");
                        int numFriends = scanner.nextInt();
                        scanner.nextLine();
                        network.suggestFriends(name, timestamp, numFriends);
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid timestamp format. Please enter a timestamp in the format yyyy-mm-dd hh:mm:ss.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer for the number of friends.");
                        scanner.nextLine(); // clear the input buffer
                    }
                    break;
                case 7:
                    System.out.println("Counting clusters in the social network...");
                    network.countClusters();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.out.println();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
            }
        }
    }

    /**
     * Displays the user interface for the social network analysis program.
     */
    private static void userInterface() {
        System.out.println("===== Social Network Analysis Menu =====");
        System.out.println("1. Add person");
        System.out.println("2. Remove person");
        System.out.println("3. Add friendship");
        System.out.println("4. Remove friendship");
        System.out.println("5. Find shortest path");
        System.out.println("6. Suggest friends");
        System.out.println("7. Count clusters");
        System.out.println("8. Exit");
        System.out.print("Please select an option: ");
    }
}
