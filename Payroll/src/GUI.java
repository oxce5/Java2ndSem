import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI {

  static final String DATA_FILE = "payroll_data.txt";
  static final Insets defaultInnerMargin = new Insets(10, 10, 10, 10);
  static JTable table;

  static IOutils utils = new IOutils();
  private final EmployeeControllers controller = new EmployeeControllers();

  private JTextField nameInput;
  private JTextField hourlyRateInput;
  private JTextField hoursWorkedInput;

  private static DefaultTableModel tableModel;
  private static ArrayList<Employee> parsedFile;

  public void createWindow() {
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

    table = new JTable(
        tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumnModel().getColumn(0).setPreferredWidth(120);
    for (int i = 1; i < columns.length; i++) {
      table.getColumnModel().getColumn(i).setPreferredWidth(100);
    }

    JScrollPane scrollPane = new JScrollPane(table);
    tablePanel.add(scrollPane, BorderLayout.CENTER);
    parsedFile = utils.parseFile(DATA_FILE, tableModel);

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

    nameInput = GuiUtils.createLabeledField("Employee name", fields, 15);

    fields.add(Box.createVerticalStrut(20));
    hourlyRateInput = GuiUtils.createLabeledField("Employee hourly rate", fields, 15);
    fields.add(Box.createVerticalStrut(20));
    hoursWorkedInput = GuiUtils.createLabeledField("Employee hours worked", fields, 15);
    fields.add(Box.createVerticalStrut(20));

    fields.add(Box.createVerticalGlue());
    return fields;
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

    JButton update = new JButton("Update row data");
    update.setAlignmentX(Component.CENTER_ALIGNMENT);
    update.setMaximumSize(new Dimension(Integer.MAX_VALUE, clear.getPreferredSize().height));

    JButton getData = new JButton("Get row data");
    getData.setAlignmentX(Component.CENTER_ALIGNMENT);
    getData.setMaximumSize(new Dimension(Integer.MAX_VALUE, clear.getPreferredSize().height));

    JButton delete = new JButton("Delete row");
    delete.setAlignmentX(Component.CENTER_ALIGNMENT);
    delete.setMaximumSize(new Dimension(Integer.MAX_VALUE, clear.getPreferredSize().height));

    parent.add(Box.createVerticalGlue());
    parent.add(calculate);
    parent.add(Box.createVerticalStrut(10));
    parent.add(addToTable);
    parent.add(Box.createVerticalStrut(10));
    parent.add(getData);
    parent.add(Box.createVerticalStrut(10));
    parent.add(update);
    parent.add(Box.createVerticalStrut(10));
    parent.add(clear);
    parent.add(Box.createVerticalStrut(10));
    parent.add(delete);
    parent.add(Box.createVerticalGlue());

    calculate.addActionListener(e -> controller.invokeCompute(nameInput, hourlyRateInput, hoursWorkedInput));
    addToTable.addActionListener(
        e -> controller.invokeAddToTable(nameInput, hourlyRateInput, hoursWorkedInput, parsedFile, tableModel, table));
    clear.addActionListener(
        e -> controller.invokeClear(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel, parsedFile));
    getData
        .addActionListener(e -> controller.invokeGet(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel));
    update.addActionListener(
        e -> controller.invokeUpdate(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel, parsedFile));
    delete.addActionListener(
        e -> controller.invokeDelete(nameInput, hourlyRateInput, hoursWorkedInput, table, tableModel, parsedFile));
  }
}
