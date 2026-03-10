import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {
  public void saveData(ArrayList<Student> studentRecord, String filepath) {
    try (BufferedWriter writer = Files.newBufferedWriter(
        Path.of(filepath),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING))
    {
      writer.write("name,email,grade");
      writer.newLine();
      for (Student student : studentRecord) {
        String formattedData = FormatData(
          student.getName(),
          student.getEmail(),
          student.getFinalGrade()
        );
        writer.write(formattedData);
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error opening file." + e.getMessage());
    }
  }

  private String FormatData(String name, String email, double finalGrade) {
    return String.join(
      ",",
      name,
      email,String.valueOf(finalGrade)
    );
  }

  public ArrayList<Student> loadFile(String filepath) {
    try (Stream<String> stream = Files.lines(Path.of(filepath))) {
      return stream.skip(1)
      .map(line -> line.split(","))
      .map(parsed -> new Student(
          parsed[0],
          parsed[1],
          Double.parseDouble(parsed[2]))
          )
      .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      System.err.println("Error opening file. " + e.getMessage());
      return new ArrayList<>();
    }
  }
}
