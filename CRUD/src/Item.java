import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Item {
  private String name;
  private int value;

  public Item() {}

  public void SetName(String name) { this.name = name; }
  public void SetValue(int value) { this.value = value; }

  public String GetName() { return this.name; }
  public int GetValue() { return this.value; }

  public ArrayList<Item> ParseFile(String fileName) {
    ArrayList<Item> records = new ArrayList<Item>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while((line = reader.readLine()) != null) {
        String[] tempArray = line.split("\\s-\\s");
        Item item = new Item();
        item.SetName(tempArray[0]);
        item.SetValue(Integer.parseInt(tempArray[1]));
        records.add(item);
      }
      return records;
    } catch (Exception e) {
      e.printStackTrace();
      return records;
    }
  }

  public void WriteToFile(ArrayList<Item> items) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
      for ( Item item : items) {
        writer.write(item.GetName() + " - " + item.GetValue());
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
