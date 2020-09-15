import java.io.*;

class TestFileStream{
     public static void main(String[] args) throws IOException{
          byte buffer = 0;
          try (
                  // create an output stream to the file
                  FileOutputStream output = new FileOutputStream("temp.dat");

          ) {
               //output values to the file
               for(int i = 1; i <= 10; i++)
                    buffer = (byte) (i << 1);
               output.write(buffer);

          }
          try (
                  // create input stream for the file
                  FileInputStream input =  new FileInputStream("temp.dat");
                  ){
               byte[] value;
               value = (byte[])
                    System.out.println(value + " ");
          }
     }
}