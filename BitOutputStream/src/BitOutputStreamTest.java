import java.io.*;
/**
 * A class to test outputting a string of bits to a file using FileOutputStreams.
 *
 * @author Yavik Kapadia
 * @version 09-15-2020
 */
public class BitOutputStreamTest {
    /**
     * Main method to test the BitOutputStream class
     *
     * @throws Exception if file does not exist, unable able to write to stream or unable to close stream.
     */
    public static void main(String[] args) throws Exception {
        BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
        output.writeBit("010000100100001001101");
        output.close();
        System.out.println("Done");

    }

    /**
     * A class to handle data stream output to a file.
     */
    static class BitOutputStream {
        private int bitsHolder;
        private final FileOutputStream output;
        private int occupiedBits;

        /**
         * Creates a BitOutputStream to write bits to file
         *
         * @param file where to write bits to
         * @throws FileNotFoundException if file does not exist
         */
        public BitOutputStream(File file) throws FileNotFoundException {
            this.output = new FileOutputStream(file);
        }

        /**
         * Writes a bit '0' or '1' to the output stream
         *
         * @param bit that is being written to this file
         * @throws IOException if unable to write to file
         */
        void writeBit(char bit) throws IOException {
            // shifts bits to the right to make room
            bitsHolder = bitsHolder << 1;

            // if the char is 1 then bitsHolder = the bit value of itself or 1
            if (bit == '1') {
                bitsHolder = bitsHolder | 1;
            }
            // if 8 bits are occupied then bitsHolder will be written to the file
            if (++occupiedBits == 8) {
                output.write(bitsHolder);
                occupiedBits = 0;
            }

        }

        /**
         * writes a string of bits to the output stream
         *
         * @param bitString of bits being written to output stream
         * @throws IOException if unable to write bits
         */
        public void writeBit(String bitString) throws IOException {
            for (int i = 0; i < bitString.length(); i++)
                writeBit(bitString.charAt(i));
        }

        /**
         * method must be invoked to close the stream,
         * will write the last byte and add remaining bits as 0s if necessary.
         * @throws IOException if unable to write bits to stream or if stream is already closed.
         */
        void close() throws IOException {
            if (occupiedBits > 0) {
                bitsHolder = bitsHolder << 8 - occupiedBits;
                output.write(bitsHolder);
            }
            output.close();

        }


    }


}
