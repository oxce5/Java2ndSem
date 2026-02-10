import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
  static final Rectangle positions = new Rectangle(35, 35, 35, 35);
  static final Insets margins = new Insets(5, 5, 5, 5);

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Main().setVisible(true));
  }

  Main() {
    setLayout(null);
    pack();
    Component parent = getContentPane();
    final String[] labels = { "Student name", "Student address", "Student course", "Student age", "Last school attended" };
    final String[] columnLabels = { "Name", "Address", "Curse", "Age", "Last school" };
    var tableModel = new DefaultTableModel(columnLabels, 0);

    rootComponent(tableModel, labels, columnLabels);
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
      JOptionPane.showMessageDialog(null, "Error writing to file.");
      throw new RuntimeException(e);
    }
  }

  private void rootComponent(DefaultTableModel tableModel, String[] labels, String[] columnHeader) {
    JPanel rootPanel = new JPanel();
    rootPanel.setLayout(new BorderLayout(10,10));
    var labelArray = new JLabel[labels.length];
    var fieldArray = new JTextField[labels.length];
    for (int i = 0; i < labels.length; i++) {
      JPanel innerPanel = new JPanel();
      innerPanel.setLayout(new GridLayout(2, 1, 0, 20));
      labelArray[i] = new JLabel(labels[i]);
      innerPanel.add(labelArray[i]);

      fieldArray[i] = new JTextField();
      innerPanel.add(fieldArray[i]);
    }
    JButton enroll = new JButton("ENROLL");
    rootPanel.add(enroll).setBounds(50 + margins.left, 500, 100, 40);
    enroll.addActionListener(e -> extracted(fieldArray, tableModel));
    add(rootPanel);
  }

  private void createLabelField(String[] labels, String[] columnHeader) {
    JPanel fieldsPanel = new JPanel(new LayoutManager((new GridLayout(2, 1, 0, 20)));
  }

  private void extracted(JTextField[] fields, DefaultTableModel tableModel) {
    StringBuilder sb = new StringBuilder();
    var content = Arrays.stream(fields)
        .map(JTextField::getText)
        .toArray(String[]::new);

    sb.append(String.join(" | ", content)).append('\n');

    writeFile("EnrolledStudents.txt", sb.toString());
    for (var field : fields) {
      field.setText("");
    }
  }

  private void createTable(Component parent, DefaultTableModel tableModel, String[] columnHeader) {
    int y = 35;
    var table = new JTable(tableModel);
    var scroll = new JScrollPane(table);
    add(scroll).setBounds(250, y, 650, 500);
    refreshTable(parent, "EnrolledStudents.txt", tableModel, true);
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
      throw new RuntimeException(e);
    }
  }
}
