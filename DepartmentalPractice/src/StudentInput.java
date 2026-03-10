import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class StudentInput extends JPanel {
  private final LabeledField nField = new LabeledField("Full Name", 200);
  private final LabeledField eField = new LabeledField("Email", 200);
  private final LabeledField gField = new LabeledField("Final Grade");
  StudentInput() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.insets = new Insets(0, 0, 8, 0);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    gbc.gridy = 0;
    add(nField, gbc);
    gbc.gridy = 1;
    add(eField, gbc);
    gbc.gridy = 2;
    add(gField, gbc);
  }

  public Student getStudentFormData() {
    return new Student(
      nField.getText(),
      eField.getText(),
      Double.parseDouble(gField.getText())
    );
  }

  public void setStudentForm(Student student) {
    nField.setText(student.getName());
    eField.setText(student.getEmail());
    gField.setText(String.valueOf(student.getFinalGrade()));
  }

  public void clearForm() {
    nField.setText("");
    eField.setText("");
    gField.setText("");
  }
}

