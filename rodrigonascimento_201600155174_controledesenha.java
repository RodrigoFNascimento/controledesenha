import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Organ {

    String      name;
    int         register;
    Person[]    queue;
    int         queueSize;

    public Organ(String name, int register) {
        this.name       = name;
        this.register   = register;
        this.queue      = null;
        this.queueSize  = 0;
    }
}

class Person {

    String  name;
    int     age;
    String   organ;
    int     number;
    int     priority;

    public Person(String name, int age, String organ, int number, int priority) {
        this.name       = name;
        this.age        = age;
        this.organ      = organ;
        this.number     = number;
        this.priority   = priority;
    }
}

public class rodrigonascimento_201600155174_controledesenha {

    /**
     * Instantiates a new {@link Organ} object with the
     * provided content.
     * 
     * @param line  Organ's info.
     * @return      New {@link Organ} instance.
     */
    public static Organ makeOrgan(String line) {
        int whitespace = line.indexOf(" ");
        String organName = line.substring(0, whitespace);
        int cashiers = Integer.parseInt(line.substring(whitespace + 1));
        return new Organ(organName, cashiers);
    }

    /**
     * Makes a new {@link Person} object with the informations
     * provided.
     * 
     * @param line      Input line containing the Person's information.
     * @param number    Person's number in the queue.
     * @return          New {@link Person} instance.
     */
    public static Person makePerson(String line, int number) {
        int firstPole = line.indexOf("|");
        int secondPole = line.indexOf("|", firstPole + 1);
        String organ = line.substring(0, firstPole);
        String name = line.substring(firstPole + 1, secondPole);
        int age = Integer.parseInt(line.substring(secondPole + 1));
        int priority;
        if (age >= 60)
            priority = 1;
        else
            priority = 0;
        return new Person(name, age, organ, number, priority);
    }

    /**
     * Adds a person to an organ.
     * 
     * @param organ     Organ to be added to.
     * @param person    Person to add.
     */
    public static void addPerson(Organ organ, Person person) {
        organ.queue[organ.queueSize] = person;
        organ.queueSize++;
    }

    /**
     * Adds person to the corresponding Organ in the array.
     * 
     * @param organs    Avaiable organs.
     * @param person    Person to be added.
     */
    public static void sendPerson(Organ[] organs, Person person) {
        for (int i = 0; i < organs.length; i++) {
            if (person.organ.equals(organs[i].name)) {
                addPerson(organs[i], person);
                break;
            }
        }
    }

    /**
     * Removes from the organ's queue n persons corrensponding
     * to the number of registers avaiable.
     * 
     * @param organ Organ to be run.
     * @return      Organ and persons' names.
     */
    public static StringBuilder run(Organ organ) {

        StringBuilder output = new StringBuilder();

        if (organ.queueSize > 0) {
            
            output.append(organ.name).append(":");
            int lastRootIndex;

            for (int i = 0; i < organ.register && organ.queueSize > 0; i++) {

                // Removes from queue
                output.append(organ.queue[0].name).append(",");
                remove(organ.queue, organ.queueSize - 1);
                organ.queueSize--;

                // Heapifies
                lastRootIndex = getLastRoot(organ.queueSize);
                for (int j = lastRootIndex; j >= 0; j--)
                    organ.queue = heapifyMax(organ.queue, j, organ.queueSize);
            }

            if (output.length() > 0)
                output.setCharAt(output.length() - 1, '\n');
        }

        return output;
    }

    /**
     * Runs every organ in a array.
     * 
     * @param organs    Organs to be run.
     * @return          Organs and persons' names.
     */
    public static StringBuilder runAll(Organ[] organs) {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < organs.length; i++) {
            output.append(run(organs[i]));
        }

        return output;
    }

    /**
     * Swaps two elements of an array.
     * 
     * @param array         Array to have it's elements swaped.
     * @param firstIndex    First index to be swaped.
     * @param secondInex    Second index to be swaped.
     */
    private static void swap(Person[] array, int firstIndex, int secondInex) {

        Person aux = array[firstIndex];
        array[firstIndex] = array[secondInex];
        array[secondInex] = aux;
    }
    
    /**
     * Gets the index of the left child of a heap tree.
     * 
     * @param index Index of the root node.
     * @return      Index of the left child.
     */
    private static int getLeft(int index) {
        return 2 * index + 1;
    }

    /**
     * Gets the index of the right child of a heap tree.
     * 
     * @param index Index of the root node.
     * @return      Index of the right child.
     */
    private static int getRight(int index) {
        return 2 * index + 2;
    }

    /**
     * Gets last root of the tree.
     * 
     * @param size  Number of elements in the tree.
     * @return      Index of the last root.
     */
    private static int getLastRoot(int size) {
        return ((size - 1) - 1) / 2;
    }

    /**
     * Turns a binary tree into a max heap.
     * 
     * @param array     Tree to be heapfied.
     * @param index     Target index.
     * @param length    Total number of non null elements in the array.
     * @return          Heapified array.
     */
    private static Person[] heapifyMax(Person[] array, int index, int length) {

        int root = index;
        int left = getLeft(index);
        int right = getRight(index);

        if (left < length) {

            if (array[left].priority > array[root].priority)
                root = left;
            else if (array[left].priority == array[root].priority && array[left].number < array[root].number)
                root = left;

        }

        if (right < length) {

            if (array[right].priority > array[root].priority)
                root = right;
            else if (array[right].priority == array[root].priority && array[right].number < array[root].number)
                root = right;

        }

        if (root != index) {
            swap(array, root, index);
            array = heapifyMax(array, root, length);
        }

        return array;
    }

    /**
     * Replaces the node at the first position in the array
     * with the one on the last position and sets the last to null.
     * 
     * @param array         Array to be used.
     * @param lastKeyIndex  Index of the last non null node.
     * @return              Array without the first node.
     */
    private static Person[] remove(Person[] array, int lastKeyIndex) {

        if (lastKeyIndex == 0) {
            array[0] = null;
        } else {
            array[0] = array[lastKeyIndex];
            array[lastKeyIndex] = null;
        }
        
        return array;
    }

    /**
     * Writes content to file.
     * 
     * @param fileName  Name of the file (with extension) to be writen.
     * @param content   Content to be writen on the file.
     * @throws FileNotFoundException
     */
    private static void writeToFile(String fileName, String content) throws FileNotFoundException {

        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
        try (FileInputStream inputStream = new FileInputStream(args[0])) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Reads the organs
            int numOfOrgans = Integer.parseInt(reader.readLine());
            Organ[] organs = new Organ[numOfOrgans];

            for(int i = 0; i < numOfOrgans; i++)
                organs[i] = makeOrgan(reader.readLine());

            // Reads the people
            int numOfPeople = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numOfOrgans; i++)
                organs[i].queue = new Person[numOfPeople];

            Person auxPerson;
            for(int i = 0; i < numOfPeople; i++) {
                auxPerson = makePerson(reader.readLine(), i);
                sendPerson(organs, auxPerson);
            }

            // Heapifies
            int lastRootIndex;
            for (int i = 0; i < numOfOrgans; i++) {
                lastRootIndex = getLastRoot(organs[i].queueSize);
                for (int j = lastRootIndex; j >= 0; j--)
                    organs[i].queue = heapifyMax(organs[i].queue, j, organs[i].queueSize);
            }

            // Runs the Organs
            for (int i = 0; i < numOfPeople; i++) {

                StringBuilder output = runAll(organs);
                if (output != null && output.length() > 0)
                    System.out.print(output);
                else
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}