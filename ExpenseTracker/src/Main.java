import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new RootWindow());
    // try { 
    //   ArrayList<Expense> expenses = new FileIO().ReadFile(Path.of("expenses.txt"));
    //   System.out.println(expenses.get(0).getStoreName());
    //
    // } catch (IOException e) {
    //   e.getCause();
    // }
  }
}
