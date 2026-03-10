import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentTable extends JPanel {
  private StudentSelectionListener listener;


  DefaultTableModel model = new DefaultTableModel();
  JTable table;
  ArrayList<Student> students = new ArrayList<>();
  StudentTable() {
    setLayout(new GridLayout());
    table = new JTable(model);
    JScrollPane scroll = new JScrollPane(table);

    model.addColumn("Full Name");
    model.addColumn("Email");
    model.addColumn("Final Grade");
    add(scroll);

    table.getSelectionModel().addListSelectionListener(e -> {
      if (e.getValueIsAdjusting() && listener != null) {
        int row = table.getSelectedRow();
        if (row >= 0) listener.onStudentSelect(students.get(row));
      }
    });
  }

  public int getSelectedRow() {
    return table.getSelectedRow();
  }

  public void setStudents(ArrayList<Student> source) {
    students.clear();
    students.addAll(source);
    model.setRowCount(0);
    for (Student student : source) {
      model.addRow(new Object[]{student.getName(), student.getEmail(), student.getFinalGrade()});
    }
  }

  public void addStudent(Student student) {
    students.add(student);
    model.addRow(new Object[]{student.getName(), student.getEmail(), student.getFinalGrade()});
  }

  public void updateStudent(int row, Student s) {
      students.set(row, s);
      model.setValueAt(s.getName(), row, 0);
      model.setValueAt(s.getEmail(), row, 1);
      model.setValueAt(s.getFinalGrade(), row, 2);
  }
  public void removeStudent(int row) {
      students.remove(row);
      model.removeRow(row);
  }

  public void setStudentSelectListener(StudentSelectionListener listener) {
    this.listener = listener;
  }
}

