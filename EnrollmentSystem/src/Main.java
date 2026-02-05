import java.awt.Component;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
  // TODO: Use class Rectangle or migrate to a layout manager
  static final Rectangle positions = new Rectangle(35, 35, 35, 35);
  static final Insets margins = new Insets(5, 5, 5, 5);

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Main().setVisible(true));
  }

  Main() {
    setLayout(null);
    Component parent = getContentPane();

    String[] labels = {
        "Student name",
        "Student address",
        "Student course",
        "Student age",
        "Last school attended"
    };

    String[] columnLabels = {
        "Name",
        "Address",
        "Curse",
        "Age",
        "Last school"
    };

    var tableModel = new DefaultTableModel();
    createFields(parent, null, labels, columnLabels);
    refreshTable(parent, "EnrolledStudents.txt", tableModel, false);

    setVisible(true);
    setTitle("Student Enrollment System");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(950, 600);
    setLocationRelativeTo(null);
    setResizable(true);
  }

  private void writeFile(String filename, String content) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.write(content);
      writer.close();
      JOptionPane.showMessageDialog(null, String.format("Data saved to %s.", filename));
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error writing to file.");
    }
  }

  private void createFields(Component parent, DefaultTableModel tableModel, String[] labels, String[] columnHeader) {
    int y = 35;
    var labelArray = new JLabel[labels.length];
    var fieldArray = new JTextField[labels.length];
    for (int i = 0; i < labels.length; i++) {
      labelArray[i] = new JLabel(labels[i]);
      labelArray[i].setBounds(50, y, 150, 50);
      add(labelArray[i]);

      fieldArray[i] = new JTextField();
      fieldArray[i].setBounds(50, y + 40, 150, 40);
      add(fieldArray[i]);

      y += 70;
    }

    createTable(parent, tableModel, columnHeader);
    JButton enroll = new JButton("ENROLL");
    add(enroll).setBounds(50 + margins.left, 500, 100, 40);
    enroll.addActionListener(e -> extracted(fieldArray, parent, tableModel));
  }

  private void extracted(JTextField[] fields, Component parent, DefaultTableModel tableModel) {
    StringBuilder sb = new StringBuilder();
    var content = Arrays.stream(fields)
        .map(JTextField::getText)
        .toArray(String[]::new);

    sb.append(String.join(" | ", content)).append('\n');

    writeFile("EnrolledStudents.txt", sb.toString());
    for (var field : fields)
      field.setText("");
    refreshTable(parent, "EnrolledStudents.txt", tableModel, true);
  }

  private void createTable(Component parent, DefaultTableModel tableModel, String[] columnHeader) {
    int y = 35;
    var table = new JTable(tableModel);
    var scroll = new JScrollPane(table);
    add(scroll).setBounds(250, y, 650, 500);
  }

  private void refreshTable(Component parent, String fileName,
      DefaultTableModel model, boolean getLastLine) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      String[] lastLine = null;
      while ((line = reader.readLine()) != null) {
        String[] columns = line.split("\\s\\|\\s");
        if (!getLastLine) {
          model.addRow(columns);
          continue;
        }

        lastLine = columns;
      }
      if (getLastLine && lastLine != null)
        model.addRow(lastLine);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
