import java.io.*;
import java.util.*;

/**
 可以为这个类添加额外的方法及数据成员.
 ID就是指学号, 下面的作者一定要写上你的名字和学号
 作业中出现的示范数据abdc001需要改成学生的学号数据
 @author  	T.Cage and 201592465
 @version	THE DATE
 **/

public class TextZip {

    //ID, 该学号的值需要修改!
    private static final String ID = "201592465";


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
        Boolean b;
        TreeNode root=huffman;
        while(br.hasNext()){
            b=br.next();
            if(!b){
//				System.out.print("0");
                if(root.getLeft()!=null){
                    root=root.getLeft();
                    if(root.isLeaf()){
                        char c=((CharFreq)root.getItem()).getChar();
                        fw.write(c);
                        root=huffman;
                    }
                }
                else root=huffman;
            }
            else {
//				System.out.print("1");
                if(root.getRight()!=null){
                    root=root.getRight();
                    if(root.isLeaf()){
                        char c=((CharFreq)root.getItem()).getChar();
                        fw.write(c);
                        root=huffman;
                    }
                }
                else root=huffman;
            }
        }
        fw.close();

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
    public static HashMap<Character,char[]> traverse
    (TreeNode t, String code,HashMap<Character,char[]>hashMap) {
        String code1=code+'0';
        String code2=code+'1';
        if (!t.isLeaf()){
            traverse(t.getLeft(),code1,hashMap);
            traverse(t.getRight(),code2,hashMap);
        }
        else {
            CharFreq cf=(CharFreq)t.getItem();
            System.out.println(cf.getChar()+" : "+code);
            hashMap.put(cf.getChar(),code.toCharArray());
        }

        // IMPLEMENT THIS METHOD
        return hashMap;
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
        ArrayList<CharFreq>list=new ArrayList<>();
        for(char i=0;i<127;i++){
            list.add(new CharFreq(i, 0));
        }
        int ch=fr.read();
        while(ch!=-1){
            if(ch>=0&&ch<127){
                CharFreq cf=list.get(ch);
                cf.setFreq(cf.getFreq()+1);
            }
            ch=fr.read();
        }

        Iterator<CharFreq> iterator=list.iterator();
        CharFreq cf;
        while (iterator.hasNext()){
            cf=iterator.next();
            if (cf.getFreq()==0) {
                list.remove(cf);
                iterator=list.iterator();
            }
        }
        iterator=list.iterator();
        while(iterator.hasNext()){
            cf=iterator.next();
            pw.write(cf.getChar()+" "+cf.getFreq()+"\n");
        }
        Collections.sort(list);
        //pw.close();
        return list;

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
        List<TreeNode> tn=new ArrayList<>();
        Iterator<CharFreq>iterator=trees.iterator();
        while (iterator.hasNext()){
            CharFreq cf0=iterator.next();
            tn.add(new TreeNode(cf0));
        }
        Collections.sort(tn, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                CharFreq cft1=(CharFreq) o1.getItem();
                CharFreq cft2=(CharFreq) o2.getItem();
                return cft1.getFreq()-cft2.getFreq();
            }
        });
//		TreeNode root=new TreeNode(new CharFreq('\u0000',0));
        while(tn.size()>=2){
//
//			CharFreq cft=new CharFreq('\u0000',cf0.getFreq()+cf1.getFreq());
            TreeNode n0 = tn.get(0);
            TreeNode n1 = tn.get(1);

            CharFreq cf0=(CharFreq)n0.getItem();
            CharFreq cf1=(CharFreq)n1.getItem();
            TreeNode nt = new TreeNode(new CharFreq('\u0000', cf0.getFreq()+cf1.getFreq()), n0, n1);
            tn.remove(0);
            tn.remove(0);
            tn.add(nt);
            Collections.sort(tn, new Comparator<TreeNode>() {
                @Override
                public int compare(TreeNode o1, TreeNode o2) {
                    CharFreq cft1=(CharFreq) o1.getItem();
                    CharFreq cft2=(CharFreq) o2.getItem();
                    return cft1.getFreq()-cft2.getFreq();
                }
            });
        }
        return tn.get(0);
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
        Map<Character,char[]> map=traverse(huffman, "",new HashMap<>());
        int ch=fr.read();
        while (ch!=-1){
            if(ch>=0&&ch<127){
                char[] ch_tmp=map.get((char)ch);
                for(char c:ch_tmp)
                    bw.writeBit(c!='0');
            }
            ch=fr.read();
        }

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
        BufferedReader br=new BufferedReader(new FileReader(inputFreqFile));
        ArrayList<CharFreq>list=new ArrayList<>();
        String s=br.readLine();
        while (s!=null&&s.length()>2){
            list.add(new CharFreq(s.charAt(0),Integer.parseInt(s.substring(2))));
            s=br.readLine();
        }
        Collections.sort(list);
        // IMPLEMENT THIS METHOD
        return list;
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
        //program param:-a
        if (args[0].equals("-a")) {
            BitReader br = new BitReader("a.txz");
            FileWriter fw = new FileWriter("b.txt");

            // Get the default prefix code tree
            TreeNode tn = abracadbraTree();

            // Decompress the default file "a.txz"
            decompress(br, tn, fw);

            // Close the ouput file
            fw.close();
            // Output the compression ratio
            long a=new File("a.txz").length();
            long b=new File("b.txt").length();

            System.out.println("a.txz decompressed by T.Cage\n" +
                    "Size of compressed file is:"+a+" bytes\n" +
                    "Size of original file is:"+b+" bytes\n" +
                    "compression ratio is:"+String.format("%.14f", ((double)a*100/(double)b))+"%");
            // Write your own implementation here.

        }
        //program param: -f a.txt a.freq
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
            traverse(n, "",new HashMap<>());
        }

        //program param: -c file.txt file.freq file.txz
        else if (args[0].equals("-c")) {

            FileReader fr = new FileReader(args[1]);
            PrintWriter pw = new PrintWriter(new FileWriter(args[2]));
            ArrayList trees = countFrequencies(fr, pw);

            fr.close();
            pw.close();



            TreeNode n = buildTree(trees);


            fr = new FileReader(args[1]);
            BitWriter bw = new BitWriter(args[3]);
            // IMPLEMENT NEXT
            compress(fr,n,bw);
            fr.close();
            bw.close();
            // Finish the compress function here



            // then output the compression ratio
            // Write your own implementation here.
            long a=new File(args[3]).length();
            long b=new File(args[1]).length();

            System.out.println(args[1]+" compressed by T.Cage\n" +
                    "Size of compressed file is:"+a+" bytes\n" +
                    "Size of original file is:"+b+" bytes\n" +
                    "compression ratio is:"+String.format("%.14f", ((double)a*100/(double)b))+"%");



        }
        //program param: -d file.txz file.freq file2.txt
        else if (args[0].equals("-d")) {
            ArrayList a = readFrequencies(args[2]);
            TreeNode tn = buildTree(a);
            BitReader br = new BitReader(args[1]);
            FileWriter fw = new FileWriter(args[3]);
            decompress(br, tn, fw);
            fw.close();

            // Output the compression ratio
            // Write your own implementation here.
            long a1=new File(args[1]).length();
            long b1=new File(args[3]).length();

            System.out.println(args[1]+" decompressed by T.Cage\n" +
                    "Size of compressed file is:"+a1+" bytes\n" +
                    "Size of original file is:"+b1+" bytes\n" +
                    "compression ratio is:"+String.format("%.14f", ((double)a1*100/(double)b1))+"%");



        }
    }
}
