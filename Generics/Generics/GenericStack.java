public class GenericStack<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    public static void main(String[] args)
    {
        
        GenericStack<String> stack1 = new GenericStack<>();
        stack1.push("London");
        stack1.push("Paris");
        stack1.push("Berlin");
        
        GenericStack<Integer> stack2 = new GenericStack<>();
        stack2.push(1); 
        stack2.push(2);
        stack2.push(3);
        /**
         * This is invalid, use upper two
         */
        GenericStack stack3 = new GenericStack<Integer>();
        stack3.push(1); 
        stack3.push(2);
        stack3.push("buttons");
        
        System.out.println(stack1.toString() + "\n"+
        stack2.toString()+ "\n"+
        stack3.toString());
    }

    public int getSize() {
        return list.size();
    }

    public E peek() {
        return list.get(getSize() - 1);
    }

    public void push(E o) {
        list.add(o);
    }

    public E pop() {
        E o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "stack: " + list.toString();
    }

}
 