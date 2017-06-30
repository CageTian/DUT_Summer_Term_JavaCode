import java.io.*;
import java.util.*;

/**
	可以为这个类添加额外的方法及数据成员.
	ID就是指学号, 下面的作者一定要写上你的名字和学号
	作业中出现的示范数据abdc001需要改成学生的学号数据
	@author  	YOUR NAME and ID
	@version	THE DATE
**/

public class TextZip {

	//ID, 该学号的值需要修改!
	private static final String ID = "abdc001";	
	
	
  /**
	* This method generates the huffman tree for the text: "abracadabra!"
	*
	* @return the root of the huffman tree
	*/
	
	public static TreeNode abracadbraTree() {
		TreeNode n0 = new TreeNode(new CharFreq('!', 1));
		TreeNode n1 = new TreeNode(new CharFreq('c', 1));
		TreeNode n2 = new TreeNode(new CharFreq('\u0000', 2), n0, n1);
		TreeNode n3 = new TreeNode(new CharFreq('r', 2));
		TreeNode n4 = new TreeNode(new CharFreq('\u0000', 4), n3, n2);
		TreeNode n5 = new TreeNode(new CharFreq('d', 1));
		TreeNode n6 = new TreeNode(new CharFreq('b', 2));
		TreeNode n7 = new TreeNode(new CharFreq('\u0000', 3), n5, n6);
		TreeNode n8 = new TreeNode(new CharFreq('\u0000', '7'), n7, n4);
		TreeNode n9 = new TreeNode(new CharFreq('a', 5));
		TreeNode n10 = new TreeNode(new CharFreq('\u0000', 12), n9, n8);
		return n10;
	}

  /**
	* This method decompresses a huffman compressed text file.  The compressed
	* file must be read one bit at a time using the supplied BitReader, and
	* then by traversing the supplied huffman tree, each sequence of compressed
	* bits should be converted to their corresponding characters.  The
	* decompressed characters should be written to the FileWriter
	*
	* @param  br      the BitReader which reads one bit at a time from the
	*                 compressed file
	*         huffman the huffman tree that was used for compression, and
	*                 hence should be used for decompression
	*         fw      a FileWriter for storing the decompressed text file
	*/
	public static void decompress(BitReader br, TreeNode huffman, FileWriter fw) throws Exception {
		
		// IMPLEMENT THIS METHOD

		

	}
	
   /**
	* This method traverses the supplied huffman tree and prints out the
	* codes associated with each character
	*
	* @param  t    the root of the huffman tree to be traversed
	*         code a String used to build the code for each character as
	*              the tree is traversed recursively
	*/
	public static void traverse(TreeNode t, String code) {

		// IMPLEMENT THIS METHOD
	
	}
	
  /**
	* This method removes the TreeNode, from an ArrayList of TreeNodes,  which
	* contains the smallest item.  The items stored in each TreeNode must
	* implement the Comparable interface.
	* The ArrayList must contain at least one element.
	*
	* @param  a an ArrayList containing TreeNode objects
	*
	* @return the TreeNode in the ArrayList which contains the smallest item.
	*         This TreeNode is removed from the ArrayList.
	*/
	public static TreeNode removeMin(ArrayList a) {
		int minIndex = 0;
		for (int i = 0; i < a.size(); i++) {
			TreeNode ti = (TreeNode)a.get(i);
			TreeNode tmin = (TreeNode)a.get(minIndex);
			if (((Comparable)(ti.getItem())).compareTo(tmin.getItem()) < 0)
				minIndex = i;
		}
		TreeNode n = (TreeNode)a.remove(minIndex);
		return n;
	}

  /**
	* This method counts the frequencies of each character in the supplied
	* FileReader, and produces an output text file which lists (on each line)
	* each character followed by the frequency count of that character.  This
	* method also returns an ArrayList which contains TreeNodes.  The item stored
	* in each TreeNode in the returned ArrayList is a CharFreq object, which
	* stores a character and its corresponding frequency
	*
	* @param  fr the FileReader for which the character frequencies are being
	*            counted
	*         pw the PrintWriter which is used to produce the output text file
	*            listing the character frequencies
	*
	* @return the ArrayList containing TreeNodes.  The item stored in each
	*         TreeNode is a CharFreq object.
	*/
	public static ArrayList countFrequencies(FileReader fr, PrintWriter pw) throws Exception {

		// IMPLEMENT THIS METHOD
		
				
	}

  /**
	* This method builds a huffman tree from the supplied ArrayList of TreeNodes.
	* Initially, the items in each TreeNode in the ArrayList store a CharFreq object.
	* As the tree is built, the smallest two items in the ArrayList are removed,
	* merged to form a tree with a CharFreq object storing the sum of the frequencies
	* as the root, and the two original CharFreq objects as the children.  The right
	* child must be the second of the two elements removed from the ArrayList (where
	* the ArrayList is scanned from left to right when the minimum element is found).
	* When the ArrayList contains just one element, this will be the root of the
	* completed huffman tree.
	*
	* @param  trees the ArrayList containing the TreeNodes used in the algorithm
	*               for generating the huffman tree
	*
	* @return the TreeNode referring to the root of the completed huffman tree
	*/
	public static TreeNode buildTree(ArrayList trees) throws IOException {

		// IMPLEMENT THIS METHOD
	
	}

  /**
	* This method compresses a text file using huffman encoding.  Initially, the
	* supplied huffman tree is traversed to generate a lookup table of codes for
	* each character.  The text file is then read one character at a time, and
	* each character is encoded by using the lookup table.  The encoded bits for
	* each character are written one at a time to the specified BitWriter.
	*
	* @param  fr      the FileReader which contains the text file to be encoded
	*         huffman the huffman tree that was used for compression, and
	*                 hence should be used for decompression
	*         bw      the BitWriter used to write the compressed bits to file
	*/
	public static void compress(FileReader fr, TreeNode huffman, BitWriter bw) throws Exception {

		// IMPLEMENT THIS METHOD
		
	}

  /**
	* This method reads a frequency file (such as those generated by the
	* countFrequencies() method) and initialises an ArrayList of TreeNodes
	* where the item of each TreeNode is a CharFreq object storing a character
	* from the frequency file and its corresponding frequency.  This method provides
	* the same functionality as the countFrequencies() method, but takes in a
	* frequency file as parameter rather than a text file.
	*
	* @param  inputFreqFile the frequency file which stores characters and their
	*                       frequency (one character per line)
	*
	* @return the ArrayList containing TreeNodes.  The item stored in each
	*         TreeNode is a CharFreq object.
	*/
	public static ArrayList readFrequencies(String inputFreqFile) throws Exception {

		// IMPLEMENT THIS METHOD
	}

	/* This TextZip application should support the following command line flags:

	QUESTION 2 PART 1
	=================
		 -a : this uses a default prefix code tree and its compressed
		      file, "a.txz", and decompresses the file, storing the output
		      in the text file, "a.txt".  It should also print out the size
		      of the compressed file (in bytes), the size of the decompressed
		      file (in bytes) and the compression ratio

	QUESTION 2 PART 2
	=================
		 -f : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) this should count the character frequencies in the text file
		      and store these in the frequency file (with one character and its
		      frequency per line).  It should then build the huffman tree based on
		      the character frequencies, and then print out the prefix code for each
		      character

	QUESTION 2 PART 3
	=================
		 -c : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) and the name of the output compressed file (args[3]), this
		      should compress file

	QUESTION 2 PART 4
	=================
		 -d : given a compressed file (args[1]) and its corresponding frequency file
		      (args[2]) and the name of the output decompressed text file (args[3]),
		      this should decompress the file

	*/

	public static void main(String[] args) throws Exception {

		if (args[0].equals("-a")) {
			BitReader br = new BitReader("a.txz");
			FileWriter fw = new FileWriter("a.txt");

			// Get the default prefix code tree
			TreeNode tn = abracadbraTree();

			// Decompress the default file "a.txz"
			decompress(br, tn, fw);

			// Close the ouput file
			fw.close();
			// Output the compression ratio
			// Write your own implementation here.
			
		}

		else if (args[0].equals("-f")) {
			FileReader fr = new FileReader(args[1]);
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));

			// Calculate the frequencies
			ArrayList trees = countFrequencies(fr, pw);

			// Close the files
			fr.close();
			pw.close();

			// Build the huffman tree
			TreeNode n = buildTree(trees);

			// Display the codes
			traverse(n, "");
		}


		else if (args[0].equals("-c")) {

			FileReader fr = new FileReader(args[1]);
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));
			ArrayList trees = countFrequencies(fr, pw);

			fr.close();
			pw.close();

			

			TreeNode n = buildTree(trees);
			
			// IMPLEMENT NEXT 
			// Finish the compress function here

			

			// then output the compression ratio
			// Write your own implementation here.
			



		}

		else if (args[0].equals("-d")) {
			ArrayList a = readFrequencies(args[2]);
			TreeNode tn = buildTree(a);
			BitReader br = new BitReader(args[1]);
			FileWriter fw = new FileWriter(args[3]);
			decompress(br, tn, fw);
			fw.close();

			// Output the compression ratio
			// Write your own implementation here.


		
		}
	}
}
