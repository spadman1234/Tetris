public class Point {
    private int x;
    private int y;
    
    public Point(int dx, int dy){
        x = dx;
        y = dy;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int dx){
        x = dx;
    }
    public void setY(int dy){
        y = dy;
    }
    
    public void add(Point p){
        this.x += p.x;
        this.y += p.y;
    }
    
    public boolean equals(Point p){
        return (p.getX() == this.x && p.getY() == this.y);
    }
    
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}