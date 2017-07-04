/**
*Creat By T.Cage
*
*
*/
public class Line{
    private Point a;
    private Point b;
    Line(Point a,Point b){
        if(a.equals(b)){
            this.a=null;
            this.b=null;
        }
        else{
            this.a=a;
            this.b=b;
        }
    }
    @Override
    public String toString(){
        return "line[a=("+a.getX()+","+a.getY()+"),b=("+b.getX()+","+b.getY()+")]";
    }
    public static void main(String[] args){
        Point p1=new Point(1,2);
        Point p2=new Point(2,3);
        Point p3=new Point(2,3);
        System.out.println(new Line(p1,p2));
        try{
            System.out.println(new Line(p3,p2));
        }catch (Exception e){
            System.out.println("two same point erro");
        }

    }
}