import java.io.*;
/**
 * A simple class that allows a user to write a stream of bits to a file.
 * You should make sure you .flush() the BitWriter when you have finished writing.
 * Otherwise there will be bits and pieces left in the writer. :)
 * Once you HAVE flushed this writer, you can no longer write bits to it.
 * This is because it would make the algorithm a little more difficult and I am lazy. (it is
 * also not needed)
 *
 * For those that are interested:
 * The bots are stored in sections of bytes. The last byte in the file stores
 * the number of bits in the last byte that were filled in to make up a whole byte.
 *
 *
 * @author Shane Paul
 * 7/10/2003
 * @
 */
public class BitWriter {
	//For writing to a file
	FileOutputStream output;
	//Stores 8 bits, before writing them to the FileWriter
	int buffer;
	//Count the number of bits
	int count;
	//Whether or not this Writer has been closed yet
	boolean closed = false;
	/**
	 * Constuct a BitWriter using a String filename. The file will be overwritten.
	 * @param filename The name of the file.
	 * @exception IOException is thrown if the file does not exist or is not writable
	 */
	public BitWriter(String filename) throws IOException{
		this(new File(filename)); //uses "this" to call the file constructor
	}
	/**
	 * Construct a BitWriter using a File.
	 * @param file The name of the file.
	 * @exception IOException is thrown if the file does not exist or is not writable
	 */
	public BitWriter(File file) throws IOException{
		//Test to see file is actually a file and can have data written to it
		file.createNewFile();
		if(!file.canWrite())
			throw new IOException("Oh My!! This file cannot be written to.....whatever shall I do? \nfile: "+file);
		output = new FileOutputStream(file);
	}
	/**
	 * Writes a single bit to a file. The bit is defined by the boolean it receives
	 * @param bit TRUE = 1, FALSE = 0
	 * @exception IOException Throws an IOException if there were any problems writing to this file
	 */
	public void writeBit(boolean bit)throws Exception{
		//Add in the specified bit by using the other method
		if(bit)
			writeBit(1);
		else
			writeBit(0);
	}
	/**
	 * Flushes the buffer and closes this writer for good. The writer will accept no further bits after this method
	 * has been called. You cannot "flush" this buffer because you cannot write single bits to the file.
	 * Once you have "flushed" this buffer, it is then closed for good. (see class descrip. for why)
	 */
	public void close()throws Exception{
		if(closed)
			throw new BitWriterClosedAlreadyException();
		output.flush();
		if(count!=0){
			int leftOverBits = 8-count;
			buffer = buffer<<leftOverBits;
			output.write(buffer);
			output.write(leftOverBits);
		}
		else
			output.write(0); //no extra bits
		output.close();
	}
	/**
	 * Another method that writes a single bit to a file.
	 * This time it takes in an integer (or you could just pass a byte and it will be upcast)
	 * only values 0 and 1 are acceptable.
	 * <b>You need not do anything special with these exceptions, they are simply to inform you of the type
	 * of error you have so you can debuig easier.</b>
	 * If you try passing it another value, it will throw an exception. (to help debugging)
	 * @param bit
	 * @exception IOException Throws an IOException if there were any problems writing to this file
	 * @exception InvalidBitException An exception I made up to indicate that you are not using this method correctly..
	 */
	public void writeBit(int bit)throws Exception{
		//can't write to a closed bitwriter
		if(closed)
			throw new BitWriterClosedAlreadyException();
		if(bit <0 || bit>1)
			throw new InvalidBitException();
		count++;
		buffer=(buffer<<1);
		//Add in the specified bit
		if(bit==1)
			buffer|=1;
		//empty the buffer and reset the count
		if(count==8){
			output.write(buffer);
			count = 0;buffer = 0;
		}
	}
	/**
	 * This is a simple Exception class that is thrown when you attempt to incorrectly call the writeBit method.
	 * You do not need to do anything with this exception, they are just to make debugging easier
	 * @author Shane Paul
	 * 7/10/2003
	 * @
	 */
	public class InvalidBitException extends Exception{

	}
	/**
	 * This is an exception to inform you that you are trying to write to a bitwriter that has already been closed.
	 * @author Shane Paul
	 * 7/10/2003
	 * @
	 */
	public class BitWriterClosedAlreadyException extends Exception{

	}

	/**
	 * Testing...and an example of how to use this class.
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		BitWriter bw = new BitWriter("writetest.txt");
		for(int j=0;j<12;j++){
			int num=(int)(Math.random()*30);
            if (num % 2 == 0) {
            	System.out.println("0");
             	bw.writeBit(0);
          	}
          	else {
            	System.out.println("1");
             	bw.writeBit(1);
       		}
		}
		bw.close();
	}
}
