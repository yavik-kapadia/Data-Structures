import java.util.Scanner;
/**
 * A program display all permutations of a string.
 *
 * @author Yavik Kapadia
 * @version 09/20/2020
 */
public class Permutations {
    /**
     * Main method to test the program
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Testing Permutations!");
        System.out.print("Enter your string: ");
        String s = input.nextLine();
        displayPermutation(s);
        System.out.println();

    }
    /**
     * Method to print all permutations of a string
     * @param s string whose permutations are being displayed
     */
    public static void displayPermutation(String s){

        displayPermutation("", s);
    }

    /**
     * Helper method for generating per
     * @param s1 is the string where characters from s2 are moved to.
     * @param s2 provides the string whose permutations are being processed and characters are being removed from.
     */
    public static void displayPermutation(String s1, String s2){
        if((s2 == null)|| s2.length() == 0) {

            System.out.println(s1);
        }
        else{
            for (int i = 0; i < s2.length(); i ++) {

                String new_s1 = s1 + s2.charAt(i) ;
                String new_s2 = s2.substring(0, i) + s2.substring(i + 1);
                displayPermutation(new_s1, new_s2);


            }
        }
    }

}
