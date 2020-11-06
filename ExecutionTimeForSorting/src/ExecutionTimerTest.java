import java.util.ArrayList;

public class ExecutionTimerTest {

    public static void main(String[] args) {
        System.out.print("|Array size    |Selection Sort|Merge Sort    |Quick Sort    |Heap Sort     |Radix Sort    |");
        for (int i = 50000; i <= 300000; i += 50000) {
            printValue(i);
        }
    }

    public static void printValue(int arraySize) {
        int width = 14;

        int[] list = new int[arraySize];
        for (int i = 0; i < list.length; i++) {
            list[i] = (int)(Math.random() * 1000000);
        }
        System.out.print("\n|");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("-");
            }
            System.out.print("|");
        }

        System.out.printf("\n|%" + width + "d|", arraySize);

        int[] list2 = new int[arraySize];
        System.arraycopy(list, 0, list2, 0, list.length);
        long startTime = System.currentTimeMillis();
        selectionSort(list2);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "d|", executionTime);


        list2 = new int[arraySize];
        System.arraycopy(list, 0, list2, 0, list.length);
        startTime = System.currentTimeMillis();
        mergeSort(list2);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.printf("%" + width + "d|", executionTime);

        list2 = new int[arraySize];
        System.arraycopy(list, 0, list2, 0, list.length);
        startTime = System.currentTimeMillis();
        quickSort(list2);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.printf("%" + width + "d|", executionTime);

        list2 = new int[arraySize];
        System.arraycopy(list, 0, list2, 0, list.length);
        startTime = System.currentTimeMillis();
        heapSort(list2);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.printf("%" + width + "d|", executionTime);

        list2 = new int[arraySize];
        System.arraycopy(list, 0, list2, 0, list.length);
        startTime = System.currentTimeMillis();
        radixSort(list2, 1000000);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.printf("%" + width + "d|", executionTime);




    }

    /**
     * selection sort a collection
     * @param list
     */
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
    /**
     * Merges two sorted lists
     *
     * @param firstHalf
     * @param secondHalf
     * @param temp
     */
    public static void merge(int[] firstHalf, int[] secondHalf, int[] temp) {
        int current1 = 0; // current index in firstHalf
        int current2 = 0; // current index in secondHalf
        int current3 = 0; // current index in temp

        while (current1 < firstHalf.length && current2 < secondHalf.length) {
            if (firstHalf[current1] < secondHalf[current2])
                temp[current3++] = firstHalf[current1++];
            else
                temp[current3++] = secondHalf[current2++];
        }
        while (current1 < firstHalf.length)
            temp[current3++] = firstHalf[current1++];
        while (current2 < secondHalf.length)
            temp[current3++] = secondHalf[current2++];
    }

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;      // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;      // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot)
            high--;    // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }


    public static void mergeSort(int[] list) {
        if (list.length > 1) {
            // merge sort the first half
            int firstHalfLength = list.length / 2;
            int[] firstHalf = new int[firstHalfLength];
            System.arraycopy(list, 0,
                    firstHalf, 0, firstHalfLength);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - firstHalfLength;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, firstHalfLength,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            //merge first half with second half into array
            merge(firstHalf, secondHalf, list);

        }
    }

    public static void heapSort(int[] list) {
        // Create a Heap of integers
        Heap<Integer> heap = new Heap<Integer>();

        // Add elements to the heap
        for (int i = 0; i < list.length; i++)
            heap.add(list[i]);

        // Remove elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }
    public static class Heap<E extends Comparable<E>> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

        /** Create a default heap */
        public Heap() {

        }

        /** Create a heap from an array of objects */
        public Heap(E[] objects) {
            for (int i = 0; i < objects.length; i++)
                add(objects[i]);
        }

        /** Add a new object into the heap */
        public void add(E newObject) {
            list.add(newObject); // Append to the heap
            int currentIndex = list.size() - 1; // The index of the last node

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                // Swap if the current object is greater than its parent
                if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else
                    break; // the tree is a heap now

                currentIndex = parentIndex;
            }
        }

        /** Remove the root from the heap */
        public E remove() {
            if (list.size() == 0)
                return null;

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                // Find the maximum between two children
                if (leftChildIndex >= list.size())
                    break; // The tree is a heap
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }

                // Swap if the current node is less than the maximum
                if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else
                    break; // The tree is a heap
            }

            return removedObject;
        }

        /** Get the number of nodes in the tree */
        public int getSize() {
            return list.size();
        }
    }


    public static void radixSort(int[] list, int maxOrder) {
        for (int order = 1; order < maxOrder; order *= 10) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] bucket = new ArrayList[10];

            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new java.util.ArrayList<>();
            }

            for (int i = 0; i < list.length; i++) {
                bucket[(list[i] / order) % 10].add(list[i]);
            }

            int k = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != null) {
                    for (int j = 0; j < bucket[i].size(); j++)
                        list[k++] = bucket[i].get(j);
                }
            }
        }
    }


}


