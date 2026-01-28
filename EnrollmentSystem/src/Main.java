import java.awt.Component;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame {
  static final Insets margins = new Insets(5, 5, 5, 5);

  private String formatData(String name, String address, String course, String age, String last) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("5s | %s | %s | %s | %s%n", name, address, course,age, last));
    return sb.toString();
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

  public static void main(String[] args) {
    new Main();
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

    JButton enroll = new JButton("ENROLL");
    add(enroll).setBounds(50 + margins.left, 500, 100, 40);
    enroll.addActionListener(e -> extracted(fieldArray));
  }

  Main() {
    setLayout(null);
    createLabelAndFields(getContentPane());


    setVisible(true);
    setTitle("Student Enrollment System");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 600);
    setLocationRelativeTo(null);
    setResizable(true);
  }

  private void extracted(JTextField[] fields) {
    StringBuilder sb =  new StringBuilder();
    for (int i = 0; i < fields.length; i++) {
      sb.append(fields[i].getText()).append(" | ");
    }
    sb.append("\n");

    writeFile("EnrolledStudents.txt", sb.toString());
    for (int i = 0; i < fields.length; i++) {
      fields[i].setText("");
    }
  }
}
