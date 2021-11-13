import java.util.Scanner;
public class days_from_today {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Monday = 0");
        System.out.println("Tuesday = 1");
        System.out.println("Wednesday = 2");
        System.out.println("Thursday = 3");
        System.out.println("Friday = 4");
        System.out.println("Saturday = 5");
        System.out.println("Sunday = 6");
        System.out.print("Select a day: ");
        int day = in.nextInt();
        System.out.print("Enter number of days: ");
        int days_from_now = in.nextInt();;


        int out = (days_from_now+day) % 7;
        switch (out) {
            case 0 -> System.out.println("Monday");
            case 1 -> System.out.println("Tuesday");
            case 2 -> System.out.println("Wednesday");
            case 3 -> System.out.println("Thursday");
            case 4 -> System.out.println("Friday");
            case 5 -> System.out.println("Saturday");
            case 6 -> System.out.println("Sunday");
        }
        System.out.println(out);
    }

}
