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

  Main() {
    setLayout(null);

    JLabel name = new JLabel("Student name");
    JLabel address = new JLabel("Student address");
    JLabel course = new JLabel("Student course");
    JLabel age = new JLabel("Student age");
    JLabel last = new JLabel("Last school attended");

    add(name).setBounds(50 + margins.left, 20 * 1 + margins.top, 200, 50);
    add(address).setBounds(50 + margins.left, 5 * 20 + margins.top, 200, 50);
    add(course).setBounds(50 + margins.left, 10 * 20 + margins.top, 200, 50);
    add(age).setBounds(50 + margins.left, 15 * 20 + margins.top, 200, 50);
    add(last).setBounds(50 + margins.left, 20 * 20 + margins.top, 200, 50);

    JTextField nameField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField courseField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField lastField = new JTextField();

    add(nameField).setBounds(50 + margins.left, 70 * 1 + margins.top, 200, 40);
    add(addressField).setBounds(50 + margins.left, 70 * 2 + margins.top, 200, 40);
    add(courseField).setBounds(50 + margins.left, 80 * 3 + margins.top, 200, 40);
    add(ageField).setBounds(50 + margins.left, 85 * 4 + margins.top, 200, 40);
    add(lastField).setBounds(50 + margins.left, 110 * 4 + margins.top, 200, 40);

    JButton enroll = new JButton("ENROLL");
    add(enroll).setBounds(50 + margins.left, 500, 100, 40);

    enroll.addActionListener(e -> extracted(nameField, addressField, courseField, ageField, lastField));

    setVisible(true);
    setTitle("Student Enrollment System");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 600);
    setLocationRelativeTo(null);
    setResizable(true);
  }

  private void extracted(JTextField nameField, JTextField addressField, JTextField courseField, JTextField ageField, JTextField lastField) {
    String name = nameField.getText();
    String address = addressField.getText();
    String course = courseField.getText();
    String age = ageField.getText();
    String last = lastField.getText();

    writeFile("EnrolledStudents.txt", formatData(name, address, course, age, last));
    emptyFields(nameField, addressField, courseField, ageField, lastField);
  }
  
  private void emptyFields(JTextField nameField, JTextField addressField, JTextField courseField, JTextField ageField, JTextField lastField) {
    nameField.setText("");
    addressField.setText("");
    courseField.setText("");
    ageField.setText("");
    lastField.setText("");
  }
}
