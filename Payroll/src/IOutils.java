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
              Double.parseDouble(parts[2])));
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
      if (records != null) {
        for (Employee emp : records) {
          double[] payroll = computePay(emp.getHourlyRate(), emp.getHoursWorked(), 0.12);
          String line = String.format("%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f",
              emp.getName(),
              emp.getHourlyRate(),
              emp.getHoursWorked(),
              payroll[0],
              payroll[1],
              payroll[2],
              payroll[3],
              payroll[4]);
          writer.write(line);
          writer.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String formatPayResult(String name, double hourlyRate, double hoursWorked, double[] breakdown) {
    return String.format(
        "Employee name: %s\n" +
            "Employee hourly rate: %.2f\n" +
            "Employee hours worked: %.2f\n" +
            "─────────────────\n" +
            "Regular Pay: %.2f\n" +
            "Overtime Pay: %.2f\n" +
            "Gross Pay: %.2f\n" +
            "Tax (12%%): %.2f\n" +
            "─────────────────\n" +
            "Net Pay: %.2f",
        name, hourlyRate, hoursWorked, breakdown[0], breakdown[1], breakdown[2], breakdown[3], breakdown[4]);
  }

}
