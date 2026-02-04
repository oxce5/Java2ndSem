import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
  // TODO: Avoid statics
  static private DefaultTableModel tableModel;
  static private JTable table;
  static private JScrollPane scroll;
  // TODO: Use class Rectangle or migrate to a layout manager
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

  private void extracted(JTextField[] fields, Component parent) {
    StringBuilder sb = new StringBuilder();
    String[] content = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
      content[i] = fields[i].getText();
    }
    sb.append(String.format("%s | %s | %s | %s | %s%n", content[0], content[1], content[2], content[3], content[4]));

    writeFile("EnrolledStudents.txt", sb.toString());
    for (int i = 0; i < fields.length; i++) {
      fields[i].setText("");
    }
    refreshTable(parent, "EnrolledStudents.txt", tableModel, true);
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
