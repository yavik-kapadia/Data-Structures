import java.util.Arrays;

/**
 * A generic stack using Array
 */
public class GenericStack<E> {


    private int currentlyFilled = 0;
    private int capacity = 10;
    private E[] list = (E[]) new Object[capacity];

    public static void main(String[] args) {
        GenericStack<String> stack1 = new GenericStack<>();
        stack1.push("London");
        stack1.push("Paris");
        stack1.push("Berlin");
        stack1.push("Tokyo");
        stack1.push("San Francisco");
        stack1.push("New York");
        stack1.push("Mumbai");
        stack1.push("Vancouver");
        stack1.push("Montreal");
        stack1.push("Moscow");
        stack1.push("Venice");
        stack1.push("Jordan");
        System.out.println(stack1.count());
        System.out.println(stack1.pop());
        stack1.push("Virginia");
        System.out.println(stack1.peek());
        System.out.println(stack1.toString());
    }

    /**
     * returns size of array
     *
     * @return size of array
     */
    public int getSize() {
        return list.length;
    }

    /**
     * gets the top element of stack
     *
     * @return the top element in the stack
     */
    public E peek() {
        return list[currentlyFilled - 1];
    }

    /**
     * Add element to the top of stack
     *
     * @param o object that is being added to stack
     */
    public void push(E o) {

        if (currentlyFilled == getSize()) {
            // create new array
            E[] temp = Arrays.copyOf(list, capacity * 2);
            list = temp;

        }
        list[currentlyFilled] = o;
        currentlyFilled++;
    }

    public E pop() {
        E o = list[currentlyFilled - 1];
        list[currentlyFilled - 1] = null;
        currentlyFilled--;
        return o;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }


    /**
     * converts array into string
     * @return Array in string format
     * @Override the original arrays method
     */
    public String toString() {
        return "stack: " + Arrays.toString(list);
    }

    /**
     * counts all non null values
     * @return all non null values
     */
    public int count() {
        int count = 0;
        for (E o : list
        ) {
            if (o != null) {
                count++;
            }

        }
        return count;
    }


}