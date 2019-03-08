import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Point implements Serializable{

	//Attributes
	private double x, y;
	
	//Constructors
	public Point(double x, double y){this.x = x; this.y = y;}
	public Point(                  ){ this.x = 0.0; this.y = 0.0;}
	
	//Getters & Setters 
	public double getX(){ return x; }
	public double getY(){ return y; }
	
	public void   setX(double x){ this.x = x;}
	public void   setY(double y){ this.y = y; }
	
	//Methods 
	public double distance(Point pointB){
		return  Math.sqrt(Math.pow((this.x - pointB.x), 2) + Math.pow((this.y - pointB.y), 2));  
	}

	public void draw (Graphics g) {
		g.fillOval((int) (this.getX() - 5), (int) (this.getY() - 5), 10, 10);
	}
	
	public boolean isEqual(Point p) {
	 return this.getX() == p.getX() && this.getY() == p.getY();
	}
}
