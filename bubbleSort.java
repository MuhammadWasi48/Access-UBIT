import java.util.Arrays;
import java.util.Scanner;

public class bubbleSort {
    public static void main(String[] args) {
        
        try (Scanner sc = new Scanner(System.in)) {
    
            System.out.println("Enter numbers separated by comma :");
            String input = sc.nextLine();

            String[] strNums = input.split(",");
            int[] arr = new int[strNums.length];

            for (int i = 0; i < strNums.length; i++) {
                arr[i] = Integer.parseInt(strNums[i]); 
            }
            
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // Swap elements
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }System.out.println(Arrays.toString(arr));
                }
            }
            
            System.out.println("Sorted array:");
            for (int num : arr) {
                System.out.print(num + " ");
            }
        }
    }
}
