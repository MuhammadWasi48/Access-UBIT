import java.util.Scanner;

public class linearSearch {

    public static int LinearSearch(int[] arr, int target) {
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; 
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            
            // Input array
            System.out.println("Enter numbers separated by comma:");
            String input = sc.nextLine();
            String[] strNums = input.split(",");
            int[] arr = new int[strNums.length];
            
            for (int i = 0; i < strNums.length; i++) {
                arr[i] = Integer.parseInt(strNums[i].trim());
            }
            
            System.out.print("Enter the number you want to search: ");
            int target = sc.nextInt();
            
            int result = LinearSearch(arr, target);
            
            if (result != -1) {
                System.out.println("Element " + target + " found at index " + result);
            } else {
                System.out.println("Element " + target + " not found in the list.");
            }
        }
    }
}
