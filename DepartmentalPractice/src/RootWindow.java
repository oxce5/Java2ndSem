import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RootWindow extends JFrame {
  final String FILEPATH = "students.txt";
  ArrayList<Student> studentRecords = new FileIO().loadFile(FILEPATH);
  RootWindow() {
    StudentInput inputPanel = new StudentInput();
    StudentButtons buttonPanel = new StudentButtons();
    StudentTable tablePanel = new StudentTable();
    FileIO io = new FileIO();
    tablePanel.setStudents(studentRecords);

    buttonPanel.setStudentActionListener(new StudentListener() {
      @Override
      public void onAdd() {
        Student student = inputPanel.getStudentFormData();
        studentRecords.add(student);
        tablePanel.addStudent(student);
        inputPanel.clearForm();
        io.saveData(studentRecords, FILEPATH);
      }
      @Override
      public void onUpdate() {
        int row = tablePanel.getSelectedRow();
        if (row >= 0) {
            Student updated = inputPanel.getStudentFormData();
            studentRecords.set(row, updated);
            tablePanel.updateStudent(row, updated); 
            inputPanel.clearForm();
            io.saveData(studentRecords, FILEPATH);
        }
      }
      public void onDelete() {
        int row = tablePanel.getSelectedRow();
        if (row >= 0) {
          studentRecords.remove(row);
          tablePanel.removeStudent(row);
          inputPanel.clearForm();
          io.saveData(studentRecords, FILEPATH);
        }
      }
    });
    tablePanel.setStudentSelectListener(student -> inputPanel.setStudentForm(student));

    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(400,800));

    JPanel root = new JPanel(new GridBagLayout());
    root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 0.05;
    root.add(new StudentTitle(), gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.weighty = 0.15;
    root.add(inputPanel, gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1;
    gbc.weighty = 0.1;
    root.add(buttonPanel, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 1;
    gbc.weighty = 0.75;
    root.add(tablePanel, gbc);
    add(root);
    pack();
    setVisible(true);
  }
}
