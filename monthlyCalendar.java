import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class monthlyCalendar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = sc.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = sc.nextInt();

        LocalDate firstDay = LocalDate.of(year, month, 1);
        int lengthOfMonth = firstDay.lengthOfMonth(); 
        DayOfWeek startDay = firstDay.getDayOfWeek(); 


        int dayValue = (startDay.ordinal()) ;
        //System.out.print(dayValue);

        // Print header
        System.out.println("\nCalendar for the month of " + firstDay.getMonth() + ", " + year);
        System.out.println("Mo  Tu  We  Th  Fr  Sa  Su");

        // Print initial spaces before the first day
        for (int i = 0; i < dayValue; i++) {
            System.out.print("    ");
        }

        // Print all days of the month
        int currentPos = dayValue;
        for (int day = 1; day <= lengthOfMonth; day++) {
            System.out.printf("%2d  ", day);
            currentPos++;
            if (currentPos % 7 == 0) {
                System.out.println(); // start new week
            }
        }

        System.out.println();
        sc.close();
    }
}

