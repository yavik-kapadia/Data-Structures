
/**
 * Write a description of class Welcome here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class nPrintln
{

    public static void nPrintln(String message, int times)
    {
        if(times>=1)
        {
            System.out.println(message);
            nPrintln(message, times - 1);
        }// base case is times == 1
    }
    public static void main(String[] args){
        nPrint("hi",1);
    }
}
