import java.util.ArrayList;

public class hamming {

    public static void main(String[] args) {
        // Using ArrayList instead of array
        ArrayList<Integer> msg = new ArrayList<>();
        int[] messageBits = {2, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};
        for (int bit : messageBits) {
            msg.add(bit);
        }

        int msglength = msg.size();
        int n = 1; // can use Scanner to take input for which hamming bit to calculate
        int sum = 0;

        // take n bits, skip n bits pattern
        for (int i = n; i < msglength; i += (2 * n)) {
            for (int k = 0; k < n && (i + k) < msglength; k++) {
                sum += msg.get(i + k); // use .get() for ArrayList
            }
        }

        System.out.println("Sum for h" + n + " = " + sum);
        int ans = HammingCalculator(sum);
        System.out.println("The Hamming bit h" + n + " = " + ans);
    }

    // This is for calculating parity only
    public static int HammingCalculator(int sum) {
        String type = "odd"; // can use Scanner or manually change
        int r = sum % 2;
        int H = 0;

        if (type.equalsIgnoreCase("odd")) {
            if (r != 0) {
                H = 0;
            } else {
                H = 1;
            }
        }else if (type.equalsIgnoreCase("even")) {
            if (r == 0) {
                H = 0;
            } else {
                H = 1;
            }
        }return H;
    }
}
