import java.util.Scanner;

public class binarySearch{

    public static int binary(int[] arr, int target) {
       
        int low =  0;
        int high = arr.length - 1;

         while (low <= high){
            int mid = (low + high) / 2; 

            if (arr[mid] == target)
                return mid;
            
            else if (arr[mid] < target) 
                low = mid + 1;
                
            else 
                high = mid - 1;

        
        }return -1;
    }

    
    public static void main(String[] args) {

    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. :");
        int target = sc.nextInt();
        int[] arr = {15,33,35,38,44,56,78,82,97};
        sc.close();
        int a = binary(arr, target);

        if (a  != -1)
            System.out.println("Target found on index " + a);
        else
            System.out.println("Target not found :( ");

    }
}

