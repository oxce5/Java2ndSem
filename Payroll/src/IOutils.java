import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class IOutils {
  public double[] computePay(double hourlyRate, double hoursWorked, double taxRate) {
    double regularPay = 0.00;
    double overtimePay = 0.00;

    if (hoursWorked <= 40) {
      regularPay = hourlyRate * hoursWorked;
    } else {
      regularPay = 40 * hourlyRate;
      overtimePay = (hoursWorked - 40) * (hourlyRate * 1.5);
    }

    double grossPay = regularPay + overtimePay;
    double tax = applyTax(taxRate, grossPay);
    double netPay = grossPay - tax;

    return new double[] { regularPay, overtimePay, grossPay, tax, netPay };
  }

  private double applyTax(double taxRate, double tentativePay) {
    return tentativePay * taxRate;
  }

  public ArrayList<Employee> parseFile(String DATA_FILE, DefaultTableModel tableModel) {
    File file = new File(DATA_FILE);
    if (!file.exists())
      return null;

    ArrayList<Employee> records = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(DATA_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 8) {
          tableModel.addRow(parts);
          records.add(new Employee(
            parts[0],
            Double.parseDouble(parts[1]),
            Double.parseDouble(parts[2]))
          );
        }
      }
      return records;

    } catch (IOException e) {
      e.printStackTrace();
      return records;
    }
  }

  public void saveDataToFile(String DATA_FILE, DefaultTableModel tableModel, ArrayList<Employee> records) {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DATA_FILE))) {
      for (int i = 0; i < tableModel.getRowCount(); i++) {
        ArrayList<String> row = new ArrayList<>();
        for (int j = 0; j < tableModel.getColumnCount(); j++) {
          row.add((tableModel.getValueAt(i, j).toString()));
        }
        writer.write(String.join(",", row));
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
