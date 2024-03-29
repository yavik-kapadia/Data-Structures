import java.io.File;
import java.util.TreeMap;
import java.util.Scanner;

/**
 * A program to count the occurrences of words in a text file
 * which is passed in through command line
 *
 * @author Yavik Kapadia
 * @version 10-20-2020
 */
public class CountWords {
    /**
     * Main driver code
     *
     * @throws Exception if file does not exist.
     */
    public static void main(String[] args) throws Exception {
        File file = null;
        if (0 < args.length) {
            file = new File(args[0]);
        } else {
            System.err.println("No file was provided.");
            System.exit(1);

        }
        //opens file if it exists

        System.out.printf(" %-15s %15s %n", "Word", "Count");
        System.out.printf("----------------------------------%n");
        countWords(file).forEach((k, v) -> System.out.printf(" %-15s %15d %n", k, v));
        System.out.printf("----------------------------------%n");
    }


    /**
     * A method to count the occurrence of each word in a file and save to a treemap for sorting key/value pairs in
     * alphabetical order
     *
     * @param file whose contents are being read
     * @return count of occurrence for each word
     * @throws Exception if file does not exist
     */
    public static TreeMap<String, Integer> countWords(File file) throws Exception {
        Scanner input = new Scanner(file);
        TreeMap<String, Integer> wordMap = new TreeMap<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();

            String[] words = line.toLowerCase().split("[\\s+\\p{P}]");

            for (String word : words) {
                if (word.trim().length() > 0 && word.trim().matches("[a-z]+")) {
                    if (wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }

                }
            }

        }
        return wordMap;
    }

}
