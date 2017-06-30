
import java.io.*;
/**
 * This class is designed to read in bits from a file that is stored in the same format
 * as specified by the BitWriter
 * It is based on the iterator model, with similar names. It has different return types though.
 * @author Shane Paul
 * 7/10/2003
 * @
 */
public class BitReader {

	//The inputStream
	FileInputStream input;
	//Stores the next 3 bytes in the file, to check for EOF and deal with the last byte being potentially half full
	int current, plus1, plus2;
	//The number of bits that have been read so far in the current byte
	int count;
	//Have we reached the end of the file? If so, count must be ==0 also before we are finally done
	boolean finished;

	/**
	 * Construct a new BitReader from a file.
	 */
	public BitReader(String file) throws Exception{
		this(new File(file));
	}
	public BitReader(File file) throws Exception{
		file.createNewFile();
		if(!file.canRead())
			throw new IOException("Oh My!! This file cannot be read from.....whatever shall I do? \nfile: "+file);
		input = new FileInputStream(file);
		//Read in the first two bytes. This assumes that the list contains at least one bit and therefore two bytes...
		plus1 = input.read();
		plus2 = input.read();
	}
	/**
	 * Returns the next available bit in the file
	 * @return the next boolean in the file
	 */
	public boolean next()throws Exception{
		if(!hasNext())
			throw new NoBitsLeftToReturn();
		//Get the next bit
		if(count==0){ //We have no more bits in this particular byte
			//Get the next byte from the file
			current=plus1;
			plus1=plus2;
			plus2 = input.read(); //-1 is returned if we reach the EOF
			count=8;
			if(plus2<0){
				finished = true;
				count = 8-plus1;  //only need to read the leftover bits
			}
		}
		count--;
		//get the leftmost bit and shift to right most
        int bit = current&0x80;
        bit = bit>>7;
        //shift current to left for one bit
        current = current<<1;
		if(bit==0)
			return false;
		return true;
	}
	/**
	 * returns whether or not there are any bits left to read.
	 */
	public boolean hasNext(){
		return !(finished && count==0);
	}
	/**
	 * Thiis an informative exception that is thrown when you forget to check hasNext()
	 * @author Shane Paul
	 * 7/10/2003
	 * @
	 */
	public class NoBitsLeftToReturn extends Exception{}
	/**
	 * Testing
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		BitReader br = new BitReader("writetest.txt");
		while(br.hasNext()){
			if(br.next())
				System.out.println("1");
			else
				System.out.println("0");
		}
	}
}
