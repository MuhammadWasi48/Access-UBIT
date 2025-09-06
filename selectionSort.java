import java.util.Scanner;

public class selectionSort {
    
    public static void SelectionSort(String[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareToIgnoreCase(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter names separated by comma:");
            String input = sc.nextLine();
            
            String[] names = input.split(",");
            
            System.out.println("Original list:");
            printArray(names);
            
            SelectionSort(names);
            System.out.println("Sorted list:");
            printArray(names);
        }
    }
}
