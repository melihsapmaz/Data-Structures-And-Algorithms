import java.sql.Timestamp;
import java.util.*;

/**
 * Represents a social network graph.
 */
public class SocialNetworkGraph {

    /**
     * Represents a person in the social network.
     */
    Map<Person, List<Person>> friendships;
    /**
     * Represents a map of people in the social network.
     */
    Map<String, Person> people;

    /**
     * Constructor to initialize the social network graph.
     */
    public SocialNetworkGraph() {
        //Initialize the people and friendships maps
        people = new HashMap<>();
        friendships = new HashMap<>();
    }

    /**
     * Method to add a person to the social network.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param hobbies The list of hobbies of the person.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        //Create a new person object and add it to the people map
        Person person = new Person(name, age, hobbies);
        people.put(name, person);
        //Add the person to the friendships map
        friendships.put(person, new ArrayList<>());
    }

    /**
     * Method to remove a person from the social network.
     * @param name The name of the person to be removed.
     * @param timestamp The timestamp of the person to be removed.
     */
    public void removePerson(String name, Timestamp timestamp) {
        //Remove the person from the people map and also remove the person from the friendships map
        Person person = people.get(name);
        //Check if the person exists in the network
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }
        //Check if the timestamp is valid
        if (person.timestamp.equals(timestamp)) {
            people.remove(name);
            friendships.remove(person);
            //Remove the person from the friends list of all other people
            for (List<Person> friends : friendships.values()) {
                friends.remove(person);
            }
            System.out.println("Person removed: " + person);
        } else {
            System.out.println("Invalid timestamp.");
        }
    }

    /**
     * Method to add a friendship between two people.
     * @param name1 The name of the first person.
     * @param timestamp1 The timestamp of the first person.
     * @param name2 The name of the second person.
     * @param timestamp2 The timestamp of the second person.
     */
    public void addFriendship(String name1, Timestamp timestamp1, String name2, Timestamp timestamp2) {
        //Add the friendship between two people
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        //Check if both persons exist in the network
        if (person1 != null && person2 != null) {
            if(person1.timestamp.equals(timestamp1) && person2.timestamp.equals(timestamp2)) {
                //Add the friendship in both directions
                friendships.get(person1).add(person2);
                friendships.get(person2).add(person1);
                System.out.println("Friendship added between " + person1.name + " and " + person2.name);
            } else {
                System.out.println("Invalid timestamps.");
            }
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Method to remove a friendship between two people.
     * @param name1 The name of the first person.
     * @param timestamp1 The timestamp of the first person.
     * @param name2 The name of the second person.
     * @param timestamp2 The timestamp of the second person.
     */
    public void removeFriendship(String name1, Timestamp timestamp1 ,String name2, Timestamp timestamp2) {
        //Remove the friendship between two people
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        //Check if both persons exist in the network
        if (person1 != null && person2 != null) {
            if(person1.timestamp.equals(timestamp1) && person2.timestamp.equals(timestamp2)) {
                //Remove the friendship in both directions
                friendships.get(person1).remove(person2);
                friendships.get(person2).remove(person1);
                System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
            } else {
                System.out.println("Invalid timestamps.");
            }
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Method to find the shortest path between two people.
     * @param startName The name of the start person.
     * @param timestamp1 The timestamp of the start person.
     * @param endName The name of the end person.
     * @param timestamp2 The timestamp of the end person.
     */
    public void findShortestPath(String startName, Timestamp timestamp1, String endName, Timestamp timestamp2) {
        //Find the shortest path between two people
        Person start = people.get(startName);
        Person end = people.get(endName);
        //Check if both persons exist in the network
        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network.");
            return;
        }
        //Check if the timestamps are valid
        if (!start.timestamp.equals(timestamp1) || !end.timestamp.equals(timestamp2)) {
            System.out.println("Invalid timestamps.");
            return;
        }
        //Implement BFS to find the shortest path
        //Initialize the visited set, prev map, and queue
        Set<Person> visited = new HashSet<>();
        Map<Person, Person> prev = new HashMap<>();
        Queue<Person> queue = new LinkedList<>();
        //Add the start person to the queue and visited set
        queue.add(start);
        visited.add(start);

        //BFS
        while (!queue.isEmpty()) {
            //Remove the current person from the queue
            Person current = queue.poll();
            //Check if the current person is the end person
            if (current == end) {
                //Print the path
                printPath(end, prev);
                //Return after finding the shortest path
                return;
            }
            //Add the neighbors of the current person to the queue and visited set
            for (Person neighbor : friendships.get(current)) {
                //Check if the neighbor has not been visited
                if (!visited.contains(neighbor)) {
                    //Add the neighbor to the queue and visited set
                    queue.add(neighbor);
                    visited.add(neighbor);
                    //Update the prev map
                    prev.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Method to print the shortest path between two people.
     * @param end The end person.
     * @param prev The map containing the previous person for each person in the path.
     */
    private void printPath(Person end, Map<Person, Person> prev) {
        //Create a list to store the path
        List<Person> path = new ArrayList<>();
        //Add the end person to the path list
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        //Reverse the path list
        Collections.reverse(path);
        System.out.println("Shortest path: ");
        //Print the path
        for(int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).name);
            //Print an arrow if it is not the last person in the path
            if(i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    /**
     * Method to count the number of clusters in the social network.
     */
    public void countClusters() {
        //A set to store the visited persons
        Set<Person> visited = new HashSet<>();
        //A variable to store the number of clusters
        int clusters = 0;
        //A list to store the clusters
        List<List<Person>> clustersList = new ArrayList<>();
        //Iterate through all the people in the network
        for (Person person : people.values()) {
            //Check if the person has not been visited
            if (!visited.contains(person)) {
                //Create a new cluster
                List<Person> cluster = new ArrayList<>();
                //Perform BFS to find all the people in the cluster
                bfs(person, visited, cluster);
                //Increment the number of clusters
                clusters++;
                //Add the cluster to the list of clusters
                clustersList.add(cluster);
            }
        }

        System.out.println("Number of clusters found: " + clusters);

        //Print the clusters and the people in each cluster
        for (int i = 0; i < clustersList.size(); i++) {
            System.out.println("Cluster " + (i + 1) + ": ");
            for(int j = 0; j < clustersList.get(i).size(); j++) {
                System.out.println(clustersList.get(i).get(j).name);
            }
        }

    }

    /**
     * Method to suggest friends for a person.
     * @param name The name of the person.
     * @param timestamp The timestamp of the person.
     * @param numOfSuggestion The number of friends to suggest.
     */
    public void suggestFriends(String name, Timestamp timestamp, int numOfSuggestion){
        Person person = people.get(name);
        //Check if the person exists in the network
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }
        //Check if the timestamp is valid
        if (!person.timestamp.equals(timestamp)) {
            System.out.println("Invalid timestamp.");
            return;
        }

        //Create a priority queue to store the suggested friends
        //The priority queue is sorted based on the score calculated for each person
        PriorityQueue<Person> pq = new PriorityQueue<>((p1, p2) -> Double.compare(calculateScore(p2, person), calculateScore(p1, person)));

        //Add all the people who are not friends with the person to the priority queue
        for (Person other : people.values()) {
            if (other != person && !friendships.get(person).contains(other)) {
                pq.add(other);
            }
        }

        System.out.println("Suggestions for " + name + ": ");
        //Print the suggested friends
        for (int i = 0; i < numOfSuggestion; i++) {
            if (!pq.isEmpty()) {
                //Get the person with the highest score from the priority queue
                Person suggested = pq.poll();
                System.out.println(suggested.name + " (Score: " + calculateScore(suggested, person) + ")");
            } else {
                break;
            }
        }
    }

    /**
     * Method to calculate the score between two people.
     * @param person1 The first person.
     * @param person2 The second person.
     * @return The score between the two people.
     */
    private double calculateScore(Person person1, Person person2) {
        int mutualFriends = countMutualFriends(person1, person2);
        int commonHobbies = countCommonHobbies(person1, person2);
        return mutualFriends + 0.5 * commonHobbies;
    }

    /**
     * Method to count the number of mutual friends between two people.
     * @param person1 The first person.
     * @param person2 The second person.
     * @return The number of mutual friends between the two people.
     */
    private int countMutualFriends(Person person1, Person person2) {
        Set<Person> friendsSet = new HashSet<>(friendships.get(person1));
        friendsSet.retainAll(friendships.get(person2));
        return friendsSet.size();
    }

    /**
     * Method to count the number of common hobbies between two people.
     * @param person1 The first person.
     * @param person2 The second person.
     * @return The number of common hobbies between the two people.
     */
    private int countCommonHobbies(Person person1, Person person2) {
        Set<String> hobbiesSet = new HashSet<>(person1.hobbies);
        hobbiesSet.retainAll(person2.hobbies);
        return hobbiesSet.size();
    }

    /**
     * Method to perform BFS to find all the people in a cluster.
     * @param start The start person.
     * @param visited The set of visited persons.
     * @param cluster The list to store the people in the cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
