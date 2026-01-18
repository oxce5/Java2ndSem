import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Item IOUtils = new Item();
    ArrayList<Item> record = IOUtils.ParseFile("data.txt");
    Item modify = record.get(4);
    modify.SetValue(1000);
    IOUtils.WriteToFile(record);
  }
}
