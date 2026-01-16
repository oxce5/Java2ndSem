import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  static String name, gender, roomType;
  static int age, nightsStatic;

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
    boolean roomPicked = false;
    while (!roomPicked) {
      System.out.println("Select the Room type: ");
      System.out.println("Small (500 per night)");
      System.out.println("Medium (700 per night)");
      System.out.println("Large (1000 per night)");
      System.out.print("Choice (1/2/3): ");
      choice = scan.nextInt();
      switch (choice) {
        case 1:
          roomType = "Small Room";
          roomPicked = true;
          break;

        case 2:
          roomType = "Medium Room";
          roomPicked = true;
          break;

        case 3:
          roomType = "Large Room";
          roomPicked = true;
          break;

        default:
          System.err.println("Invalid choice!");
      }
    }
    System.out.print("How many nights? ");
    nights = scan.nextInt();
    nightsStatic = nights;
    return ComputeRoomBill(choice, nights);
  }

  private double ComputeRoomBill(int choice, int nights) {
    double bill = 0.00;

    if (choice == 1) {
      bill = 500.00 * nights; 
    } else if (choice == 2) {
      bill = 700.00 * nights;
    } else {
      bill = 1000.00 * nights; 
    }

    return bill;
  }

  private double ComputeMenuBill() {
    System.out.println("Please select Food and Beverages");
    double bill = 0.00;
    
    while (true) {
      System.out.println("1. Coke (50.00)\t4. Beef Steak (175.00)");
      System.out.println("2. Rice (5.00)\t5. Halo-Halo (250.00)");
      System.out.println("3. Chicken Adobo (100.00)\t");
      System.out.println("\n6. Exit");
      System.out.print("Choice: ");
      int choice = scan.nextInt();
      if (choice == 6) return bill;
      else if (choice < 6) bill += ComputeFoodBill(choice);
      else System.err.println("Invalid selection! ");
    }  
  }

  private double ComputeFoodBill(int choice) {
    double foodBill = 0.00;
    System.out.print("Enter quantity of item: ");
    int quantity = scan.nextInt();
    switch (choice) {
      case 1:
        foodBill += 50.00 * quantity;
        break;

      case 2:
        foodBill += 5.00 * quantity;
        break;

      case 3:
        foodBill += 100.00 * quantity;
        break;

      case 4:
        foodBill += 175.00 * quantity;
        break;

      case 5:
        foodBill += 250.00 * quantity;
        break;

      default:
        break;
    }
    return foodBill;
  }

  private void ReceiptPrinter(int nights, double roomBill, double menuBill, double change) {
    System.out.printf("Name: %s%n", name);
    System.out.printf("Room type: %s%n", roomType);
    System.out.printf("Number of nights: %d%n", nights);
    System.out.printf("Food and Drinks: %.2f%n", menuBill);
    System.out.printf("Room Cost: %.2f%n", roomBill);
    System.out.printf("Amount due: %.2f%n", (roomBill + menuBill));
    System.out.printf("Change: %.2f%n", change);
  }

  private void AskForPayment(int nights, double roomBill, double menuBill) {
    System.out.printf("Amount due: %.2f%n", (roomBill + menuBill));
    System.out.print("Payment: ");
    double payment = scan.nextDouble();
    double finalBill = (roomBill + menuBill) - payment;
    while (finalBill > 0) {
      System.out.printf("Payment insufficient. You are %.2f short.", finalBill);
      finalBill -= scan.nextDouble();
    }
    ReceiptPrinter(nights, roomBill, menuBill, Math.abs(finalBill));
    System.out.println("Thank you for staying with us! We hope to see you again!");
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.GetCustomerDetails();
    double roomBill = main.RoomBill();
    double menuBill = main.ComputeMenuBill();
    main.AskForPayment(nightsStatic, roomBill, menuBill);
  }
}
