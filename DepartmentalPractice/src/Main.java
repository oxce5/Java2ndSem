import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {
  private static FileIO io = new FileIO();

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new RootWindow());
    // ArrayList<Student> studentRecords = io.loadFile("students.txt");
    // for (Student student : studentRecords) {
    //   System.out.println(student.getName());
    // }
    //
  }
}
