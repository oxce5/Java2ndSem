import java.awt.Component;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
  static private DefaultTableModel tableModel;
  static private JTable table;
  static private JScrollPane scroll;
  static final Insets margins = new Insets(5, 5, 5, 5);

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

  private void createLabelAndFields(Component parent) {
    String[] labels = {
        "Student name",
        "Student address",
        "Student course",
        "Student age",
        "Last school attended"
    };

    int y = 35;
    JLabel[] labelArray = new JLabel[labels.length];
    JTextField[] fieldArray = new JTextField[labels.length];
    for (int i = 0; i < labels.length; i++) {
      labelArray[i] = new JLabel(labels[i]);
      labelArray[i].setBounds(50, y, 150, 50);
      add(labelArray[i]);

      fieldArray[i] = new JTextField();
      fieldArray[i].setBounds(50, y + 40, 150, 40);
      add(fieldArray[i]);

      y += 70;
    }

    createTable(parent);
    JButton enroll = new JButton("ENROLL");
    add(enroll).setBounds(50 + margins.left, 500, 100, 40);
    enroll.addActionListener(e -> extracted(fieldArray, parent));

  }

  private void createTable(Component parent) {
    String[] colHeader = {
        "Name",
        "Address",
        "Course",
        "Age",
        "Last school"
    };

    int y = 35;
    tableModel = new DefaultTableModel(colHeader, 0);
    table = new JTable(tableModel);
    scroll = new JScrollPane(table);
    add(scroll).setBounds(250, y, 650, 500);
  }

  private void refreshTable(Component parent, String fileName, DefaultTableModel model, boolean getLastLine) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      String[] lines = null;
      while ((line = reader.readLine()) != null) {
        lines = line.split("\s\\|\s");
        if (!getLastLine) {
          model.addRow(lines);
          continue;
        }
      }
      model.addRow(lines);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  private void extracted(JTextField[] fields, Component parent) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < fields.length; i++) {
      sb.append(fields[i].getText()).append(" | ");
    }
    sb.append("\n");

    writeFile("EnrolledStudents.txt", sb.toString());
    for (int i = 0; i < fields.length; i++) {
      fields[i].setText("");
    }
    refreshTable(parent, "EnrolledStudents.txt", tableModel, true);
  }

  Main() {
    setLayout(null);
    Component parent = getContentPane();
    createLabelAndFields(parent);
    refreshTable(parent, "EnrolledStudents.txt", tableModel, false);

    setVisible(true);
    setTitle("Student Enrollment System");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(950, 600);
    setLocationRelativeTo(null);
    setResizable(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Main().setVisible(true));
  }

}
