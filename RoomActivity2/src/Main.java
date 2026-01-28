import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int count = 1;
    int[][] matrix = new int[3][3];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print("Input number " + count + ": ");
        matrix[i][j] = scan.nextInt();
        count++;
      }
    }
    scan.close();
  
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();

    int sum = 0;
    for (int i = 0; i < matrix.length; i++) {
      sum = 0;
      for (int j = 0; j < matrix.length; j++) {
        sum += matrix[i][j];
      }
      System.out.println("Row " + (i+1) + " sum: " + sum);
    }
    System.out.println();

    sum = 0;
    for (int i = 0; i < matrix.length; i++) {
      sum = 0;
      for (int j = 0; j < matrix.length; j++) {
        sum += matrix[j][i];
      }
      System.out.println("Column " + (i+1) + " sum: " + sum);
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (matrix[i][j] < min)
          min = matrix[i][j];
      }
    }
    System.out.println("The smallest number is " + min);

    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (matrix[i][j] > max)
          max = matrix[i][j];
      }
    }
    System.out.println("The largest number is " + max);
  }
}
