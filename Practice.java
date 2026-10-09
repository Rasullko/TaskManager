package TaskManager;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Practice {
    public static void main(String args[]){
       String[] fruits = {"apple", "banana", "cherry", "mango"};
       try(FileWriter writer = new FileWriter("fruits.txt")){
           for(String f : fruits){
               writer.write(f + "\n");
           }
       }
       catch(IOException e){
           System.out.println("Something went wrong");
       }
       try(BufferedReader reader = new BufferedReader(new FileReader("fruits.txt"))){
           String line;
           while((line = reader.readLine()) != null){
               System.out.println(line);
           }
       }
       catch(IOException e){
           System.out.println("Something went wrong");
       }
    }

}
