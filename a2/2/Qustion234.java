/**
*Creat By T.Cage
*
*
*/
import java.io.*;
import java.util.ArrayList;
import java.util.*;
public class Qustion234{
    public static int question2(String file_path)throws IOException{
        char[][] ca=new char[8][8];
        int max=0;
        BufferedReader in= new BufferedReader(new FileReader(file_path));
        for(int i=0;i<8;i++)
            ca[i]=in.readLine().toCharArray();
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(ca[i][j]=='1'){
                    int n=1;
                    for(int a=i+1;a<8&&ca[a][j]=='1';a++)
                        n++;
                    if(n>max)
                        max=n;

                    n=1;
                    for(int b=j+1;b<8&&ca[i][b]=='1';b++)
                        n++;
                    if(n>max)
                        max=n;

                    n=1;
                    for(int a=i+1,b=j+1;a<8&&b<8&&
                        ca[a][b]=='1';a++,b++)
                        n++;
                    if(n>max)
                        max=n;

                    n=1;
                    for(int a=i+1,b=j-1;a<8&&b>=0&&
                        ca[a][b]=='1';a++,b--)
                        n++;
                    if(n>max)
                        max=n;

            }
                }
        }
        in.close();
        return max;
    }

    public static void question3(int num)throws IOException{
        BufferedWriter out= new BufferedWriter(new FileWriter("out.txt"));
        int flag=0;
        for(int i=2;i<num;i++){
            flag=1;
            for(int j=2;j<=(int)Math.sqrt(i);j++)
                if(i%j==0){
                    flag=0;
                    break;
                }
            if(1==flag){
                out.write(String.valueOf(i));
                out.newLine();
                out.flush();
            }
        }
        out.close();
    }


    public static int judge(String num,int length,int start,List<Integer> list){
        //int length=num.length();
        int flag=1;
        if(1==length&&list.contains(Integer.parseInt(num)))
            return 0;
        else{
            for(int i=start+1;i<=length;i++){
                if (flag==0)
                    return flag;
                if(num.charAt(start)!='0'&&list.contains(Integer.parseInt(num.substring(start,i)))){
                    String sub_num=num.substring(i,num.length());
                    if(sub_num.length()==0&&start!=0)
                        return 0;
                    else
                        flag=judge(num,length,i,list);
                }
            }
        }
        return flag;
    }
    public static int question4_method2(int num)throws IOException{
        BufferedWriter out= new BufferedWriter(new FileWriter("out3.txt"));
        List<Integer>list=new ArrayList<Integer>();
        int total=0;
        int flag;
        for(int i=2;i<num;i++){
            flag=1;
            for(int j=2;j<=(int)Math.sqrt(i);j++)
                if(i%j==0){
                    flag=0;
                    break;
                }
            if(1==flag){
                list.add(i);
                String str_num=String.valueOf(i);
                if(str_num.length()>1)
                    flag=judge(str_num,str_num.length(),0,list);
                if(0==flag){
                    total++;
                    out.write(String.valueOf(i));
                    out.newLine();
                    out.flush();
                }
            }
        }
        out.close();
        return total;
    }
    public static void main(String[] args){
        try{
            // System.out.println(question2("a.txt"));
            System.out.println(question4_method2(10000));
            // question3(10000);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Hello World!");
    }
}