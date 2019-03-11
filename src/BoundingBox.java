

public class BoundingBox implements BoundingArea {
	
	// MEMBER VARIABLES
	
	private Point LR; //xmax, ymin
	private Point UL; //xmin, ymax

	// CONSTRUCTORS
	
	public BoundingBox(double x1, double y1, double x2, double y2) {
		this.LR = new Point(Math.max(x1, x2), Math.min(y1, y2));
		this.UL = new Point(Math.min(x1, x2), Math.max(y1, y2));
	}
	
	public BoundingBox(Point p1, Point p2) {
		if(p1.getX() > p2.getX()) {
		this.LR = p1;
		this.UL = p2;
	} else {
		this.LR = p2;
		this.UL = p1;
		}
   }

	// GETTERS & SETTERS

	public Point getUL() { return UL; }

	public void setUL(Point p1) { this.UL = p1; }

	public Point getLR() { return LR; }

	public void setLR(Point p2) { this.LR = p2; }
	
	public String getType(){ return "BOUNDINGBOX"; }

	
	// METHODS
		
	public String toString() { return this.getType()+ " ("+UL+", "+LR+")"; }
	
	public boolean isInside(Point p) {
		if (UL.getX() > p.getX() || LR.getX() < p.getX()) return false;
		if (UL.getY() < p.getY() || LR.getY() > p.getY()) return false;
		return true;
	}
	
	public double getArea() { 
		return Math.abs(UL.getX() - LR.getX()) * Math.abs(UL.getY() - LR.getY());
	}
	
	public double getLength() {
		return Math.abs(LR.getX() - UL.getX())*2 + Math.abs(UL.getY() - LR.getY())*2;
	}
}
