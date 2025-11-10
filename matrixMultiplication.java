import java.util.Scanner;

public class matrixMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dimensions of first matrix
        System.out.print("Enter number of rows in Matrix A: ");
        int rowsA = sc.nextInt();
        System.out.print("Enter number of columns in Matrix A: ");
        int colsA = sc.nextInt();

        // Input dimensions of second matrix
        System.out.print("Enter number of rows in Matrix B: ");
        int rowsB = sc.nextInt();
        System.out.print("Enter number of columns in Matrix B: ");
        int colsB = sc.nextInt();

        // Check matrix multiplication condition
        if (colsA != rowsB) {
            System.out.println("Matrix multiplication not possible. Columns of A must equal rows of B.");
            return;
        }

        // Declare matrices
        int[][] A = new int[rowsA][colsA];
        int[][] B = new int[rowsB][colsB];
        int[][] C = new int[rowsA][colsB]; // Result matrix

        // Input elements of matrix A
        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // Input elements of matrix B
        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // Perform matrix multiplication
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // Display result matrix
        System.out.println("Resultant Matrix C (A x B):");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
