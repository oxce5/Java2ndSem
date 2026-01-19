import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  private int ChoiceHandler(int min, int max) {
    int choice;
    do {
      System.out.print("Choice: ");
      choice = scan.nextInt();
      if (choice < min || choice > max) {
        System.err.printf("Invalid choice. Please select a number between %d and %d.%n", min, max);
        continue;
      }
    } while (choice < min || choice > max);
    return choice;
  }

  private int[] CreateBookArray() {
    System.out.print("How many books to inspect? ");
    int[] bookConditions = new int[scan.nextInt()];

    for (int i = 0; i < bookConditions.length; i++) {
      bookConditions[i] = ChoiceHandler(0, 100);
    }
    return bookConditions;
  }

  private String EvaluateCategory(int condition) {
    if (condition > 85) return "Excellent condition";
    else if (60 < condition && condition <= 85) return "Good condition";
    else return "Needs repair";
  }

  private String FormatData(int[] bookArray, int increment) {
    StringBuilder sb = new StringBuilder();
    int counter = 1 + increment;
    for (int i : bookArray) {
      sb.append(String.format("Book %d: %d — %s%n", counter, i, EvaluateCategory(i)));
      counter++;
    }
    return sb.toString();
  }

  private void WriteToFile(String fileName,String data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.write(data);
      writer.close();
    } catch (Exception e) {
      //TODO: handle exception
    }
  }

  private int IncrementIndex(String fileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line, lastLine = "";
      while ((line = reader.readLine()) != null) {
        lastLine = line;
      }
      String[] tempArray = lastLine.split("\\s|:");
      // for (String string : tempArray) {
      //   System.out.println(string);
      //
      // }
      return Integer.parseInt(tempArray[1]);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
  public static void main(String[] args) {
    Main main = new Main();
    int[] bookArray = main.CreateBookArray();
    final String fileName = "book_condition_report.txt";
    
    main.WriteToFile(fileName, main.FormatData(bookArray, main.IncrementIndex(fileName)));
  }
}
