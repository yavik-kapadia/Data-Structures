import java.util.Scanner;

/**
 * Test 1
 * A program to count character occurrence using recursion
 *
 * @author Yavik
 * @version 10-1-2020
 */
public class CharacterCounter {
    /**
     * Main method/ Driver program
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] test = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 's', 't', 'r', 'i', 'n', 'g'};
        System.out.print("Enter a character: ");
        char ch = input.next().charAt(0);
        int charCount = charCount(test, 0, ch);
        if (charCount == 0)
            System.out.printf("The character \"%s\" was not found.", ch);
        else if (charCount == 1)
            System.out.printf("The character \"%s\" was found once.", ch);
        else
            System.out.printf("The character \"%s\" was found %d times.", ch, charCount);
    }

    /**
     * nTail-Recursive method to search through an array and count occurrence of a character
     *
     * @param array in which character occurrence is being counted
     * @param start where to start checking in array
     * @param ch    character that is being searched for
     * @return count of the occurrence of a specified character
     */
    public static int charCount(char[] array, int start, char ch) {
        if (start < array.length) if (ch == array[start])
            return 1 + charCount(array, start + 1, ch);
        else
            return charCount(array, start + 1, ch);
        else
            return 0;
    }
}
