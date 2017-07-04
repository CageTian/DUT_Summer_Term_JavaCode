/**
*Creat By T.Cage
*
*
*/
import java.io.*;
import java.util.*;
public class MyFile extends File{
    MyFile(String pathname){
        super(pathname);
        file_name=getName();
        file_path=getAbsolutePath();
        file_size=length();
    }
    private String file_name;
    private String file_path;
    private long file_size;

    @Override
    public boolean equals(Object o){
        if(o instanceof MyFile){
            MyFile tmp_file=(MyFile)o;
            return tmp_file.file_name.equals(file_name)
                    &&tmp_file.file_size==(file_size);
        }
        return false;
    }
    public static void isDuplicated(MyFile file,List<MyFile> list,List<MyFile> duplist){
        if(file.isDirectory()){
            for(String str_file:file.list()){
                // System.out.println(new MyFile(file.file_path+"\\"+str_file));
                isDuplicated(new MyFile(file.file_path+"\\"+str_file),list,duplist);
            }
        }
        else if(file.isFile()){
            if(list.contains(file)){
                if(duplist.contains(file))
                    duplist.add(file);
                else{
                    duplist.add(list.get(list.indexOf(file)));
                    duplist.add(file);
                }
            }
            else
                list.add(file);
        }

        }


    public static void main(String[] args){
        if(args.length!=1)
            System.out.println("Usage: input the absolute file_path!");
        else{
            MyFile main_dir=new MyFile(args[0]);
            if(main_dir.isDirectory()){
                List<MyFile> ls=new ArrayList<MyFile>();
            MyFile.isDuplicated(main_dir,new ArrayList<MyFile>(),ls);
            for(MyFile f:ls)
                System.out.println("Duplicated:"+f.toString());
            }
            else{
                System.out.println("please input the absolute file_path!");
            }
            
            // for(File file:main_dir.listFiles())
            // System.out.println(file.getName()+String.valueOf(file.length()));
            
        }
    }
}