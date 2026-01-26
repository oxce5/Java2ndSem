import java.util.ArrayList;
public class Main {
  private static final int[] arr1 = {
      235, 537, 355, 206, 399, 746, 593, 763, 997, 916, 415, 972, 4, 691, 386, 494, 760, 28, 347, 38, 623, 447,
      445, 164, 129, 358, 418, 89, 251, 738, 603, 687, 765, 851, 19, 288, 144, 237, 687, 702, 926, 870, 159, 900,
      749, 115, 50, 255, 932, 744, 67, 882, 93, 813, 12, 764, 772, 526, 111, 456, 524, 515, 530, 944, 858, 479,
      233, 924, 524, 222, 789, 980, 349, 662, 583, 678, 791, 502, 182, 438, 368, 667, 507, 186, 505, 601, 477,
      623, 650, 818, 258, 320, 404, 352, 870, 225, 491, 909, 319, 815, 734, 252, 959, 806, 844, 880, 43, 576,
      888, 163, 988, 579, 288, 59, 937, 26, 59, 771, 451, 905, 878, 608, 930, 876, 793, 628, 347, 18, 844, 518,
      122, 787, 131, 328, 879, 64, 842, 893, 511, 261, 116, 311, 448, 363, 952, 56, 191, 953, 419, 352, 199,
      979, 818, 462, 991, 634, 154, 49, 296, 924, 255, 759, 653, 535, 894, 520, 206, 100, 8, 218, 811, 129, 215, 782,
      125, 13, 319, 601, 573, 559, 408, 685, 360, 626, 331, 489, 335, 797, 153, 937, 489, 942, 5, 415, 47,
      354, 984, 400, 644, 264, 354, 598, 310, 393, 337, 349, 322, 993, 565, 648, 828, 183, 914, 49, 409, 629,
      392, 381, 791, 252, 474, 65, 888, 759, 973, 313, 822, 904, 379, 716, 759, 937, 509, 110, 380, 167, 917,
      934, 641, 508, 452, 282, 344, 401, 474, 613, 929, 520, 727, 825, 997, 195, 208, 539, 686, 311, 759, 986,
      472, 283, 446, 162, 10, 909, 159, 445, 363, 379, 568, 858, 103, 260, 182, 897, 872, 317, 1, 174, 884, 75,
      12, 741, 911, 856, 601, 678, 908, 97, 981, 688, 464, 871, 538, 872, 61, 305, 683, 251, 291, 773
  };
  private static final int[] arr2 = {
      235, 537, 355, 206, 399, 746, 593, 763, 997, 916, 415, 972, 4, 691, 386, 494, 760, 28, 347, 38, 623, 447,
      445, 164, 129, 358, 418, 89, 251, 738, 603, 687, 765, 851, 19, 288, 144, 237, 687, 702, 926, 870, 159, 900,
      749, 115, 50, 255, 932, 744, 67, 882, 93, 813, 12, 764, 772, 526, 111
  };
  private static final int[] arr3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

  private int[] removeAndSort(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == target)
        array[i] = 0;
    }
    for (int i = 0; i < array.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < array.length; j++)
        if (array[j] < array[min])
          min = j;
      int temp = array[i];
      array[i] = array[min];
      array[min] = temp;
    }
    return array;
  }

  private void replaceAllInstances(int[] array, int target, int replace) {
    int[] instances = findAllInstances(array, target);
    for (int j : instances) {
      array[j] = replace;
      System.out.printf("%d at index %d replaced with %d.%n", target, j, 402);
      System.out.printf("Verify: Index %d is %d", j, array[j]);
    }
  }

  private int[] findAllInstances(int[] array, int target) {
    ArrayList<Integer> temp = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      if (array[i] == target)
        temp.add(i);
    }
    int[] result = new int[temp.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = temp.get(i);
      System.out.println(i);
    }
    return result;
  }

  private void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.printf("Index %d: %d%n", i, array[i]);
    }
  }

  private void printInstances(int[] array, int[] indices) {
    int count = 1;
    for (int i : indices) {
      System.out.printf("Instance %d with value %d found at index %d.%n", count, array[i], i);
      count++;
    }
  }

  public static void main(String[] args) {
    Main main = new Main();
    System.out.println("ACTIVITY 1");
    for (int i : arr3) {
      if (i == 80)
        System.out.println(i);
    }

    System.out.println("\nACTIVITY 2");
    main.printInstances(arr3, main.findAllInstances(arr3, 984));
    System.out.println("\nACTIVITY 3");
    main.replaceAllInstances(arr1, 400, 402);

    System.out.println("\nACTIVITY 4");
    main.printArray(main.removeAndSort(arr3.clone(), 50));

    System.out.println("\nACTIVITY 5");
    double sum = 0;
    for (int i : arr2)
      sum += i;
    System.out.printf("Average: %.2f%n", sum / arr2.length);
  }
}
