import java.util.Scanner;
public class Main {
  static Scanner scan = new Scanner(System.in);
  static String name, gender, roomType;
  static int age, nightsStatic;

  static final String[] consumables = {"Coke", "Rice", "Chicken Adobo",  "Beef Steak", "Halo-Halo"};
  static final String[] rooms = {"Small Room", "Medium Room", "Large Room"};
  static final double[] roomPrices = {500.00, 700.00, 1000.00};
  static final double[] foodPrices = {50.00, 5.00, 100.00, 175.00, 250.00};

  private int ChoiceHandler(int min, int max) {
    int choice;

    do {
      choice = scan.nextInt();

      if (choice < min || choice > max) {
        System.err.printf("Invalid choice. Please select a number between %d and %d: ", min, max);
      }
    } while (choice < min || choice > max);
    return choice;
  }

  private void GetCustomerDetails() {
    System.out.print("Enter customer name: ");
    name = scan.nextLine().trim();
    System.out.print("Enter customer age: ");
    age = scan.nextInt(); scan.nextLine();
    System.out.print("Enter customer gender: ");
    gender = scan.nextLine().trim();
  }

  private double RoomBill() {
    int nights;
    int choice = 0;

    System.out.println("\nSelect the Room type: ");
    for (int i = 0; i < roomPrices.length; i++) {
      System.out.printf("%s (%.2f per night)%n", rooms[i], roomPrices[i]);
    }
    System.out.print("Choice (1/2/3): ");
    choice = ChoiceHandler(1, 3);

    roomType = rooms[choice-1];
    System.out.print("How many nights? ");
    nights = scan.nextInt();
    nightsStatic = nights;
    return roomPrices[choice-1] * nights;
  }

  private double ComputeMenuBill() {
    System.out.println("Please select Food and Beverages");
    double bill = 0.00;

    while (true) {
      System.out.printf("%nFood and Drinks bill: %.2f%n", bill);

      for (int i = 0; i < consumables.length; i++) {
        System.out.printf("%d. %s (%.2f)%n", i + 1, consumables[i], foodPrices[i]);
      }
      System.out.println("6. Exit");
      System.out.print("Choice: ");
      int choice = ChoiceHandler(1, 6);

      if (choice == 6) {
        if (bill <= 0.00) {
          System.err.println("Please select at least 1 food and drink.");
          continue;
        }
        return bill;
      }

      bill += ComputeFood(choice);
    }
}

  private double ComputeFood(int choice) {
    double foodBill = 0.00;
    System.out.print("Enter quantity of item: ");
    int quantity = scan.nextInt();
    foodBill = foodPrices[choice-1] * quantity;
    return foodBill;
  }

  private void ReceiptPrinter(int nights, double roomBill, double menuBill, double change) {
    System.out.printf("%nName: %s%n", name);
    System.out.printf("Room type: %s%n", roomType);
    System.out.printf("Number of nights: %d%n", nights);
    System.out.printf("Food and Drinks: %.2f%n", menuBill);
    System.out.printf("Room Cost: %.2f%n", roomBill);
    System.out.printf("Amount due: %.2f%n", (roomBill + menuBill));
    System.out.printf("Change: %.2f%n", change);
  }

  private void AskForPayment(int nights, double roomBill, double menuBill) {
    double total = roomBill + menuBill;

    System.out.printf("%nAmount due: %.2f%n", total);
    System.out.print("Payment: ");

    double balance = total - scan.nextDouble();

    while (balance > 0) {
      System.out.printf("Payment insufficient. You are %.2f short.%nPayment: ", balance);
      balance -= scan.nextDouble();
    }

    ReceiptPrinter(nights, roomBill, menuBill, Math.abs(balance));
    System.out.println("\nThank you for staying with us! We hope to see you again!");
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.GetCustomerDetails();
    double roomBill = main.RoomBill();
    double menuBill = main.ComputeMenuBill();
    main.AskForPayment(nightsStatic, roomBill, menuBill);
  }
}
