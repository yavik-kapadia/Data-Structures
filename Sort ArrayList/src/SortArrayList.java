import java.util.ArrayList;

/**
 * Program to sort an ArrayList using the interface Comparable
 *
 * @author Yavik Kapadia
 * @version 09-26-2020
 */
public class SortArrayList {
    /**
     * Main method to test the program
     * @param args supplies command-line arguments as an array of String objects
     */
    public static void main(String[] args) {

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(4);
        integers.add(3);
        sort(integers);
        System.out.println(integers);

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(3.4);
        doubles.add(1.2);
        doubles.add(-12.3);
        sort(doubles);
        System.out.println(doubles);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Bob");
        strings.add("Alice");
        strings.add("Ted");
        strings.add("Carol");
        sort(strings);
        System.out.println(strings);
    }
    /**
     * when using the comparable interface the CompareTo method will return 0 if the same,
     * returns positive if first object is greater than the other,
     * returns negative if first object is lesser than the other.
     *
     * @param list which is being sorted
     * @param <E> formal generic type allows us to pass any type of object
     */
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {

        // variables to hold the CurrentMin value and it's index
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.size(); i++) {
            //variables initialize the the first elements of the ArrayList
            currentMin = list.get(i);
            currentMinIndex = i;

            //j will always be ahead of i, thus i != j ever
            for (int j = i + 1; j < list.size(); j++) {
                // if the currentMin is greater than the value at
                // then the value of j will be the new currentMin and currentMinIndex will be j
                if (currentMin.compareTo(list.get(j)) > 0) {
                    currentMin = list.get(j);
                    currentMinIndex = j;

                }
            }
            // swap values
            list.set(currentMinIndex, list.get(i));
            list.set(i, currentMin);

        }

    }



}
