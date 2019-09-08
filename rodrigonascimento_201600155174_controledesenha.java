import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Organ {

    String  name;
    int     register;

    public Organ(String name, int register) {
        this.name       = name;
        this.register   = register;
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

    public static Organ makeOrgan(String line) {
        int whitespace = line.indexOf(" ");
        String organName = line.substring(0, whitespace);
        int cashiers = Integer.parseInt(line.substring(whitespace + 1));
        return new Organ(organName, cashiers);
    }

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

            int numOfOrgans = Integer.parseInt(reader.readLine());
            Organ[] organs = new Organ[numOfOrgans];

            for(int i = 0; i < numOfOrgans; i++) {
                organs[i] = makeOrgan(reader.readLine());
            }

            int numOfPeople = Integer.parseInt(reader.readLine());
            Person[] people = new Person[numOfPeople];

            for(int i = 0; i < numOfPeople; i++) {
                people[i] = makePerson(reader.readLine(), i);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}