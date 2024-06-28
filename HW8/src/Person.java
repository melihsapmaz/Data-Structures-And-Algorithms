import java.sql.Timestamp;
import java.util.*;

/**
 * Represents a person in the social network.
 */
public class Person {
    /**
     * Name of the person.
     */
    String name;
    /**
     * Age of the person.
     */
    int age;
    /**
     * Hobbies of the person.
     */
    List<String> hobbies;
    /**
     * Timestamp of when the person was added to the network.
     */

    Timestamp timestamp;

    /**
     * Creates a new person with the given name, age, and hobbies.
     *
     * @param name    name of the person
     * @param age     age of the person
     * @param hobbies hobbies of the person
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the person.
     *
     * @return name of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }
}
