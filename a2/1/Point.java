/**
*Creat By T.Cage
*
*
*/
public class Point{
    private double x;
    private double y;
    Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    public double getX(){return this.x;}
    public double getY(){return this.y;}
    @Override
    public boolean equals(Object o){
        if(o instanceof Point){
            Point p=(Point)o;
            return p.getX()==x&&
                    p.getY()==y;
            }
        return false;
    }
    // public static void main(String[] args){
    //     System.out.println("Hello World!");
    // }
}