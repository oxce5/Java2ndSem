import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static final Insets defaultInnerMargin = new Insets(10, 10, 10, 10);
  static final String DATA_FILE = "payroll_data.txt";

  private JTextField nameInput;
  private JTextField hourlyRateInput;
  private JTextField hoursWorkedInput;
  private DefaultTableModel tableModel;

  private void createWindow() {
    JFrame root = new JFrame("Payroll Calculator");

    root.add(createRootComponent());

    root.pack();
    root.setSize(950, 650);
    root.setLocationRelativeTo(null);
    root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    root.setVisible(true);
  }

  private JPanel createRootComponent() {
    JPanel rootPanel = new JPanel(new BorderLayout());
    rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    rootPanel.add(createSplits(), BorderLayout.CENTER);
    return rootPanel;
  }

  private JPanel createSplits() {
    JPanel splitPanel = new JPanel();
    splitPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    JPanel left = createForm();
    JPanel right = createTable();

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.6;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(0, 0, 0, 5);
    splitPanel.add(left, c);

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.4;
    c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(0, 5, 0, 0);
    splitPanel.add(right, c);

    return splitPanel;
  }

  private JPanel createTable() {
    JPanel tablePanel = new JPanel();
    tablePanel.setLayout(new BorderLayout());
    tablePanel.setBorder(new EmptyBorder(defaultInnerMargin));

    String[] columns = { "Name", "Hourly Rate", "Hours Worked", "Regular Pay", "Overtime Pay", "Gross Pay", "Tax",
        "Net Pay" };
    tableModel = new DefaultTableModel(columns, 0);

    JTable table = new JTable(
        tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumnModel().getColumn(0).setPreferredWidth(120);
    for (int i = 1; i < columns.length; i++) {
      table.getColumnModel().getColumn(i).setPreferredWidth(100);
    }

    JScrollPane scrollPane = new JScrollPane(table);
    tablePanel.add(scrollPane, BorderLayout.CENTER);

    loadDataFromFile();

    return tablePanel;

  }

  private JPanel createForm() {
    JPanel rootForms = new JPanel();
    rootForms.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    JPanel fields = createFields();
    JPanel buttons = new JPanel();
    createButtons(buttons);

    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1.0;
    c.weighty = 0.7;
    c.fill = GridBagConstraints.BOTH;
    rootForms.add(fields, c);

    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1.0;
    c.weighty = 0.3;
    c.fill = GridBagConstraints.BOTH;
    rootForms.add(buttons, c);

    return rootForms;
  }

  private JPanel createFields() {
    JPanel fields = new JPanel();
    fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
    fields.setBorder(new EmptyBorder(defaultInnerMargin));

    nameInput = createLabeledField("Employee name", fields, 15);
    fields.add(Box.createVerticalStrut(20));
    hourlyRateInput = createLabeledField("Employee hourly rate", fields, 15);
    fields.add(Box.createVerticalStrut(20));
    hoursWorkedInput = createLabeledField("Employee hours worked", fields, 15);
    fields.add(Box.createVerticalStrut(20));

    fields.add(Box.createVerticalGlue());
    return fields;
  }

  private JTextField createLabeledField(String field, JPanel parent, int maxChar) {
    JPanel labeledField = new JPanel();
    labeledField.setLayout(new BoxLayout(labeledField, BoxLayout.Y_AXIS));
    labeledField.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel label = new JLabel(field);
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    labeledField.add(label);
    labeledField.add(Box.createVerticalStrut(10));

    JTextField input = new JTextField(maxChar);
    input.setAlignmentX(Component.LEFT_ALIGNMENT);
    input.setMaximumSize(new Dimension(Integer.MAX_VALUE, input.getPreferredSize().height));
    labeledField.add(input);

    parent.add(labeledField);

    return input;
  }

  private void createButtons(JPanel parent) {
    parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
    parent.setBorder(new EmptyBorder(defaultInnerMargin));

    JButton calculate = new JButton("Calculate");
    calculate.setAlignmentX(Component.CENTER_ALIGNMENT);
    calculate.setMaximumSize(new Dimension(Integer.MAX_VALUE, calculate.getPreferredSize().height));

    JButton addToTable = new JButton("Add to Table");
    addToTable.setAlignmentX(Component.CENTER_ALIGNMENT);
    addToTable.setMaximumSize(new Dimension(Integer.MAX_VALUE, addToTable.getPreferredSize().height));

    JButton clear = new JButton("Clear");
    clear.setAlignmentX(Component.CENTER_ALIGNMENT);
    clear.setMaximumSize(new Dimension(Integer.MAX_VALUE, clear.getPreferredSize().height));

    parent.add(Box.createVerticalGlue());
    parent.add(calculate);
    parent.add(Box.createVerticalStrut(10));
    parent.add(addToTable);
    parent.add(Box.createVerticalStrut(10));
    parent.add(clear);
    parent.add(Box.createVerticalGlue());

    calculate.addActionListener(e -> invokeCompute());
    addToTable.addActionListener(e -> invokeAddToTable());
    clear.addActionListener(e -> invokeClear());
  }

  private void invokeCompute() {
    String name = nameInput.getText();
    double hourlyRate = Double.parseDouble(hourlyRateInput.getText());
    double hoursWorked = Double.parseDouble(hoursWorkedInput.getText());

    double[] payArray = computePay(hourlyRate, hoursWorked, 0.12);
    String message = formatPayResult(name, hourlyRate, hoursWorked, payArray);

    JOptionPane.showMessageDialog(null, message, "Pay Calculation", JOptionPane.INFORMATION_MESSAGE);
  }

  private void invokeAddToTable() {
    String name = nameInput.getText();
    double hourlyRate = Double.parseDouble(hourlyRateInput.getText());
    double hoursWorked = Double.parseDouble(hoursWorkedInput.getText());

    double[] payArray = computePay(hourlyRate, hoursWorked, 0.12);

    Object[] rowData = {
        name,
        String.format("$%.2f", hourlyRate),
        String.format("%.2f", hoursWorked),
        String.format("$%.2f", payArray[0]),
        String.format("$%.2f", payArray[1]),
        String.format("$%.2f", payArray[2]),
        String.format("$%.2f", payArray[3]),
        String.format("$%.2f", payArray[4])
    };

    tableModel.addRow(rowData);
    saveDataToFile();
    invokeClear();
  }

  private void invokeClear() {
    nameInput.setText("");
    hourlyRateInput.setText("");
    hoursWorkedInput.setText("");
    nameInput.requestFocus();
  }

  private String formatPayResult(String name, double hourlyRate, double hoursWorked, double[] breakdown) {
    return String.format(
        "Employee name: %s\n" +
            "Employee hourly rate: $%.2f\n" +
            "Employee hours worked: %.2f\n" +
            "─────────────────\n" +
            "Regular Pay: $%.2f\n" +
            "Overtime Pay: $%.2f\n" +
            "Gross Pay: $%.2f\n" +
            "Tax (12%%): $%.2f\n" +
            "─────────────────\n" +
            "Net Pay: $%.2f",
        name, hourlyRate, hoursWorked, breakdown[0], breakdown[1], breakdown[2], breakdown[3], breakdown[4]);
  }

  private double[] computePay(double hourlyRate, double hoursWorked, double taxRate) {
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

  private void saveDataToFile() {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DATA_FILE))) {
      for (int i = 0; i < tableModel.getRowCount(); i++) {
        List<String> row = new ArrayList<>();
        for (int j = 0; j < tableModel.getColumnCount(); j++) {
          row.add(tableModel.getValueAt(i, j).toString());
        }
        writer.write(String.join(",", row));
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadDataFromFile() {
    File file = new File(DATA_FILE);
    if (!file.exists())
      return;

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(DATA_FILE))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 8) {
          tableModel.addRow(parts);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Main().createWindow());
  }
}
