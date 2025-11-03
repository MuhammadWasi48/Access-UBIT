import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductSalesTotal {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Wasi\\java\\EVE01Sales.txt";  // Your file name
        Map<String, Double> productSales = new HashMap<>();
        double grandTotal = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header line

            Scanner sc = new Scanner(System.in);
            System.out.println("Choose nmuber for each Sales-Wise\n 1:Region\n 2:Rep ID\n 3:Product");
            int  num = sc.nextInt();


            while ((line = br.readLine()) != null) {
                // Split using tab delimiter (\t)
                String[] parts = line.split("\\t");
                //System.out.println(Arrays.toString(parts));

                if (parts.length < 6) continue; // Skip invalid lines

                
                
                String product = parts[num].trim();
                String date = parts[0].trim(); 
                //String day = dateToDay(date);
                String month = extractMonthYear(date);
                // System.out.println(day);
                int qty = Integer.parseInt(parts[4].trim());
                double unitPrice = Double.parseDouble(parts[5].trim());

                double amount = qty * unitPrice;

                // Add to product-wise total
                productSales.put(product, productSales.getOrDefault(product , 0.0) + amount);

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
     // Helper method to extract "Mon-YYYY" from "DD-Mon-YYYY"
    private static String extractMonthYear(String date) {
        try {
            String[] parts = date.split("-");
            if (parts.length == 3) {
                return parts[1] ;
            }
        } catch (Exception e) {
            // Ignore malformed dates
        }
        return "Unknown";
    
    }
    // Helper method to convert date to day
    private static String dateToDay(String dates) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            LocalDate date = LocalDate.parse(dates, formatter);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            Integer a = dayOfWeek.ordinal();
            String dayName = dayOfWeek.toString().substring(0, 1) +
                         dayOfWeek.toString().substring(1).toLowerCase();
            return dayOfWeek.toString(); 
        } catch (Exception e) {
            // Ignore malformed dates
        }
        return "Unknown";
    }
}
