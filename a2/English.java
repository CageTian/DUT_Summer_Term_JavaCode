/**
*Creat By T.Cage
*
*
*/
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class English{
    private String word;
    private String trans;
    private static final String WORD_DATABASE="word_databse.txt";

    public void setWord(String word){this.word=word;}
    public void setTrans(String trans){this.trans=trans;}
    public String getWord(){return word;}
    public String getTrans(){return trans;}

    public static void add(English en)throws IOException{
        String s=search(en.getWord());
        if(s.equals("no records.\n")){
            BufferedWriter out= new BufferedWriter
                        (new FileWriter(WORD_DATABASE,true));
            out.write(en.getWord()+":"+en.getTrans());
            out.newLine();
            out.close();
        }
        else{
            BufferedReader in= new BufferedReader(new FileReader(WORD_DATABASE));
            StringBuffer sb=new StringBuffer();
            String str="";
            while(true){
                str=in.readLine();
                if(str==null)
                    break;
                sb.append(str+"\n");
            }
            s=sb.toString();
            s=s.replaceFirst("(?<="+en.getWord()+":).*(?=\n)",en.getTrans());
            BufferedWriter out= new BufferedWriter
                        (new FileWriter(WORD_DATABASE));
            out.write(s);
            out.close();
        }
        
    }
    public static String search(String word)throws IOException{
        BufferedReader in= new BufferedReader(new FileReader(WORD_DATABASE));
        String s=in.readLine();
        while(s!=null){
            if(s.substring(0,s.indexOf(':')).equals(word))
                return s;
            s=in.readLine();
        }
        in.close();
        return "no records.\n";
    }
    public static void main(String[] args){
        English en=new English();
        Scanner s = new Scanner(System.in);
        try{
            while(true){
                System.out.println("1.add\n2.search\n");
                String start=s.next();
                while(start.equals("1")&&start.equals("2"))
                    start=s.next();
                if("1".equals(start)){
                    System.out.println("Please input the word and translation:\n");
                    en.setWord(s.next());
                    en.setTrans(s.next());
                    English.add(en);
                }
                else if("2".equals(start)){
                    System.out.println("Please input the search word:\n");
                    String s_word=s.next();
                    System.out.println("You get:\n"+English.search(s_word));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}