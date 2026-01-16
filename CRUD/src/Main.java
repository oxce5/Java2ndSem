import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
       
  }

  private ArrayList<Item> ReadFile(String fileName) {
    ArrayList<Item> records = new ArrayList<>();
    Item tempItem = new Item();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while((line = reader.readLine()) != null) {
        String[] tempArray = line.split("\\s-\\s");
        
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
