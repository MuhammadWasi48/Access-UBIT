import java.io.*;
import java.util.*;

public class ProductSalesTotal {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Wasi\\java\\EVE01Sales.txt";  // Your file name
        Map<String, Double> productSales = new HashMap<>();
        double grandTotal = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                // Split using tab delimiter (\t)
                String[] parts = line.split("\\t");
                //System.out.println(Arrays.toString(parts));

                if (parts.length < 6) continue; // Skip invalid lines

                String product = parts[3].trim();
                int qty = Integer.parseInt(parts[4].trim());
                double unitPrice = Double.parseDouble(parts[5].trim());

                double amount = qty * unitPrice;

                // Add to product-wise total
                productSales.put(product, productSales.getOrDefault(product, 0.0) + amount);

                grandTotal += amount;
            }

            // Display results
            System.out.println("Product-wise Sales Amount Total:");
            for (String product : productSales.keySet()) {
                System.out.println(product + " : " + productSales.get(product));
            }

            System.out.println("Grand Total: " + grandTotal);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file.");
        }
    }
}