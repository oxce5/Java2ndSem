import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {
  public void WriteToFile(ArrayList<Expense> records, Path path) {
    // Streams are pointless here, minimal gains
    try (BufferedWriter writer = Files.newBufferedWriter(
        path,
        java.nio.file.StandardOpenOption.CREATE,
        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING)) {
      writer.write(String.join(",", "id","store_name","total_cost","tax","final_amount"));
      writer.newLine();
      for (Expense expenses : records) {
        writer.write(FormatData(
          expenses.getReceiptID(), 
          expenses.getStoreName(), 
          expenses.getTotalCost(),
          0.12,
          expenses.getFinalAmount())
        );
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String FormatData(int id, String storeName, double totalCost, double finalAmount) {
    return String.join(",",
      String.valueOf(id),
      storeName,
      String.valueOf(totalCost),
      String.valueOf(finalAmount)
    );
  }

  public String FormatData(int id, String storeName, double totalCost, double tax, double finalAmount) {
    return String.join(",",
      String.valueOf(id),
      storeName,
      String.valueOf(totalCost),
      String.valueOf(0.12),
      String.valueOf(finalAmount)
    );
  }

  /* This method demonstrates Java Streams,
   *    a functional way of processing data,
   *    introduced in Java 8.
   *
   * @param path 
   * @return ArrayList<Expense> ArrayList of Object Expense
   * @throws IOException
   * 
  */
  public ArrayList<Expense> ReadFile(Path path) {
    try (Stream<String> stream = Files.lines(Path.of("expenses.txt"))) {
      return stream.skip(1)
      .map(line -> line.split(","))
      .map(parts -> new Expense(
        Double.parseDouble(parts[2]),
        Double.parseDouble(parts[4]),
        parts[1],
        Integer.parseInt(parts[0]))
      )
      .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      System.err.print("Error reading file: " + e.getLocalizedMessage());
      return new ArrayList<>();
    }
  }
}
