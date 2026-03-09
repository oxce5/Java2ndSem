import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controllers {
  static FileIO io = new FileIO();
  private static final Path FILEPATH = Path.of("expenses.txt");
   Controllers(
      LabeledField[] fields,
      JButton[] buttons,
      ArrayList<Expense> expenses
  ) {
    buttons[0].addActionListener(e -> invokeRecord(
      fields,
      expenses
    ));
    buttons[1].addActionListener(e -> invokeView(expenses));
    buttons[2].addActionListener(e -> invokeSumFinalAmounts(expenses));
    buttons[3].addActionListener(e -> invokeDelete(expenses));
  }

  public void invokeRecord(
    LabeledField[] fields,
    ArrayList<Expense> expenses
  ) {
    try {
      int id = 1;
      if (!expenses.isEmpty()) id = expenses.getLast().getReceiptID() + 1;
     
      String storeName = fields[1].getText();
      double totalCost = Double.parseDouble(fields[2].getText());
      double finalAmount = computeFinal(totalCost);
      expenses.add(new Expense(totalCost, finalAmount, storeName, id));

      System.out.println("DEBUG: Data: " + io.FormatData(id, storeName, totalCost, finalAmount));
      io.WriteToFile(expenses, FILEPATH);
      System.out.println("DEBUG: Data written to file.");
      JOptionPane.showMessageDialog(null, String.format("Data saved to %s.", FILEPATH));
      fields[1].setText("");
      fields[2].setText("");
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null,  "Numbers only allowed in Total Cost input field.", "Invalid input", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void invokeView(ArrayList<Expense> expenses) {
    StringBuilder sb = new StringBuilder();
    for (Expense expense : expenses) {
      String id =  String.valueOf(expense.getReceiptID());
      String storeName =  expense.getStoreName();
      String totalCost = String.valueOf(expense.getTotalCost());
      String tax = String.valueOf(0.12);
      String finalAmount = String.valueOf(expense.getFinalAmount());
      String entry = String.join(",", id, storeName, totalCost, tax, finalAmount);
      String message = String.format("Expense %s%n", entry);
      sb.append(message).append("\n");
    }
    JOptionPane.showMessageDialog(null, sb);
  }

  public void invokeSumFinalAmounts(ArrayList<Expense> expenses) {
    double sum = 0.00;
    for (Expense expense : expenses) {
      sum += expense.getFinalAmount();
    }
    JOptionPane.showMessageDialog(null, String.format("Total expenses: %.2f", sum));
  }

  public void invokeDelete(ArrayList<Expense> expenses) {
    int choice = JOptionPane.showConfirmDialog(null, "DATA WILL BE DELETED. DO YOU CONFIRM?", "WARNING!", JOptionPane.WARNING_MESSAGE);

    if (choice == JOptionPane.YES_OPTION) {
      expenses.clear();
      io.WriteToFile(expenses, FILEPATH);
      JOptionPane.showMessageDialog(null, "DATA DELETED.");
    }
  }

  private double computeTax(double totalCost, double tax) {
    return totalCost * tax;
  }

  private double computeFinal(double totalCost) {
    return totalCost + computeTax(totalCost, 0.12);
  }
}
