import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmployeeControllers {

  static final String DATA_FILE = "payroll_data.txt";
  static final Insets defaultInnerMargin = new Insets(10, 10, 10, 10);
  static IOutils utils = new IOutils();

  public void invokeCompute(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput) {
    String name = nameInput.getText();
    double hourlyRate = Double.parseDouble(hourlyRateInput.getText());
    double hoursWorked = Double.parseDouble(hoursWorkedInput.getText());

    double[] payArray = utils.computePay(hourlyRate, hoursWorked, 0.12);
    String message = utils.formatPayResult(name, hourlyRate, hoursWorked, payArray);

    JOptionPane.showMessageDialog(null, message, "Pay Calculation", JOptionPane.INFORMATION_MESSAGE);
  }

  public void invokeAddToTable(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput,
      ArrayList<Employee> parsedFile, DefaultTableModel tableModel, JTable table) {
    String name = nameInput.getText();
    double hourlyRate = Double.parseDouble(hourlyRateInput.getText());
    double hoursWorked = Double.parseDouble(hoursWorkedInput.getText());

    double[] payArray = utils.computePay(hourlyRate, hoursWorked, 0.12);

    Object[] rowData = {
        name,
        String.format("%.2f", hourlyRate),
        String.format("%.2f", hoursWorked),
        String.format("%.2f", payArray[0]),
        String.format("%.2f", payArray[1]),
        String.format("%.2f", payArray[2]),
        String.format("%.2f", payArray[3]),
        String.format("%.2f", payArray[4])
    };

    parsedFile.add(new Employee(name, hourlyRate, hoursWorked));
    tableModel.addRow(rowData);
    utils.saveDataToFile(DATA_FILE, tableModel, parsedFile);
    invokeClear(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel, parsedFile);
  }

  public void invokeGet(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput, JTable table,
      DefaultTableModel tableModel) {
    int selectedRow = table.getSelectedRow();

    if (selectedRow != -1) {
      nameInput.setText(tableModel.getValueAt(selectedRow, 0).toString());
      hourlyRateInput.setText(tableModel.getValueAt(selectedRow, 1).toString());
      hoursWorkedInput.setText(tableModel.getValueAt(selectedRow, 2).toString());
    }
  }

  public void invokeUpdate(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput, JTable table,
      DefaultTableModel tableModel, ArrayList<Employee> parsedFile) {
    int selectedRow = table.getSelectedRow();

    if (selectedRow != -1) {
      tableModel.setValueAt(nameInput.getText(), selectedRow, 0);
      tableModel.setValueAt(hourlyRateInput.getText(), selectedRow, 1);
      tableModel.setValueAt(hoursWorkedInput.getText(), selectedRow, 2);

      double payArray[] = utils.computePay(Double.parseDouble(hourlyRateInput.getText()),
          Double.parseDouble(hoursWorkedInput.getText()), 0.12);
      for (int i = 0; i < payArray.length; i++) {
        int finalIndex = 3 + i;
        if (finalIndex < table.getColumnCount())
          tableModel.setValueAt(payArray[i], selectedRow, (finalIndex));
      }

      Employee employee = parsedFile.get(selectedRow);
      employee.setName(nameInput.getText());
      employee.setHourlyRate(Double.parseDouble(hourlyRateInput.getText()));
      employee.setHoursWorked(Double.parseDouble(hoursWorkedInput.getText()));
      utils.saveDataToFile(DATA_FILE, tableModel, parsedFile);
    invokeClear(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel, parsedFile);
    }
  }

  public void invokeDelete(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput, JTable table,
      DefaultTableModel tableModel, ArrayList<Employee> parsedFile) {
    int selectedRow = table.getSelectedRow();

    if (selectedRow != -1) {
      parsedFile.remove(selectedRow);
      tableModel.removeRow(selectedRow);
      utils.saveDataToFile(DATA_FILE, tableModel, parsedFile);
    }
  }

  public void invokeClear(JTextField nameInput, JTextField hourlyRateInput, JTextField hoursWorkedInput, JTable table,
      DefaultTableModel tableModel, ArrayList<Employee> parsedFile) {
    nameInput.setText("");
    hourlyRateInput.setText("");
    hoursWorkedInput.setText("");
    nameInput.requestFocus();
  }
}
