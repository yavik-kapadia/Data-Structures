import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * A program to display a user provided string in reverse order using recursion
 * and to print all permutation of a string.
 *
 * @author Yavik Kapadia
 * @version 09/20/2020
 */
public class Recursion {
    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        String original;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your string: ");
        original = input.nextLine();
        reverseDisplay(original);


    }

    /**
     * a method to reverse the order of the string
     *
     * @param value string to be reversed
     */
    public static void reverseDisplay(String value) {
        String reversed ="";
        for (int i = value.length(); i >= 0; i--) {
                reverseDisplay(value, i);
        }
    }

    /**
     * helper method for reversing a string
     *
     * @param value
     * @param high
     */
    public static void reverseDisplay(String value, int high) {

    }
}
