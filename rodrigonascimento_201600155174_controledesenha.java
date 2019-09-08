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
    Organ   organ;
    int     number;
    int     priority;

    public Person(String name, int age, Organ organ, int number, int priority) {
        this.name       = name;
        this.age        = age;
        this.organ      = organ;
        this.number     = number;
        this.priority   = priority;
    }
}

public class rodrigonascimento_201600155174_controledesenha {

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}