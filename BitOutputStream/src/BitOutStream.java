import java.io.FileNotFoundException;
import java.io.File;
import java.io.*;
/**
 * A class to handle bit stream output
 */
public class BitOutputStream {
    private File file;
    private byte buffer;
    private FileOutputStream fileOutStream;
    private int occupiedBits;
    /**
     * Creates a BitOutputStream to write bits to file
     * @param file where to write bits to
     */
   public BitOutputStream(File file)
   {
       this.file = file;
       occupiedBits = 0;
       // try to open a new FileOutStream using file locaton
       try
       {
           fileOutStream  =  new FileOutputStream(this.file);
       }
       // if error throw error
       catch (FileNotFoundException exception)
       {
           exception.printStackTrace();
       }

   }
    /**
     * Writes a bit '0' or '1' to the output stream
     * @param bit that is being written to this file
     */

    public void writeBit(char bit) throws IOException {   System.out.println("Buffer:" + buffer);
        // when you write a bit to the buffer you shift the right most bit to the right
        //convert char to byte by casting byte and then stored in a byte variable
        buffer = (byte) (bit << 1);
        if (bit == '1')
        {
            buffer = (byte)(buffer | 1);
        }
        occupiedBits ++;
        if(occupiedBits == 8)
        {
            try
            {
                fileOutStream.write(buffer);
            }
            catch(FileNotFoundException exception)
            {
                exception.printStackTrace();
            }
        }

    }

    /**
     * writes a string of bits to the output stream
     * @param bit string of bits being written to output stream
     */

    public void writeBit(String bit)
    {

    }

    /**
     * method must be invoked to close the stream
     */
    public void close()
    {

    }

}

static void main(String[] args)
{
    File file = new File("testOutput.dat");
    BitOutputStream out = new BitOutputStream(file);
    out.writeBit('1');
    out.writeBit("0101");

    out.writeBit( "010000100100001001101" );
    out.close();
}