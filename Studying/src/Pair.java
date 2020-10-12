public class Pair <E> {
    private E o1;
    private E o2;
    
    public Pair ( E o11, E o21)
    {
        this.o1 = o11;

        this.o2 = o21;
    }

    public E getO1() {
        return o1;
    }

    public void setO1(E o1) {
        this.o1 = o1;
    }

    public E getO2() {
        return o2;
    }

    public void setO2(E o2) {
        this.o2 = o2;
    }
    public String toString()
    {
        return o1 + ", " + o2;
    }
}
