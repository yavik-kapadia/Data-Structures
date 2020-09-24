import java.util.Scanner;

/**
 * A program to display a user provided string in reverse order using recursion
 * and to print all permutation of a string.
 *
 * @author Yavik Kapadia
 * @version 09/20/2020
 */
public class ReverseString {
    /**
     * Main method to test the program
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Testing Reverse order recursion!");
        System.out.println();
        System.out.print("Enter your string: ");
        String value = input.nextLine();
        reverseDisplay(value);
        System.out.println();

    }

    /**
     * a method that print string in reverse order
     *
     * @param value string to be reversed
     */
    public static void reverseDisplay(String value) {

        reverseDisplay(value, value.length() - 1);
    }

    /**
     * helper method for reversing a string
     *
     * @param value string to be processed
     * @param high  index of the substring
     */
    public static void reverseDisplay(String value, int high) {
        if (high >= 0) {
            System.out.print(value.charAt(high));
            reverseDisplay(value, high - 1);
        }

    }
}
