package shoot;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Plane extends Polygon{


	int gBWidth = Board.boardWidth;
	int gBHeight = Board.boardHeight;
	
	int planeWidth = 20, planeHeight= 5;
	static ArrayList<Plane> planes = new ArrayList<Plane>();
	
	public boolean onScreen = true;
	
	
	
	private double xVelocity = 2, yVelocity = 0;
	
	public static int[] polyXArray = {-20,20,20,-20,-20};
	public static int[] polyYArray = {-5,-5,5,5,-5};
	
	private double centerX = 0, centerY = gBHeight-550;
	
	private double uLeftXPos = getCenterX() + this.polyXArray[0];
	private double uLeftYPos = getCenterY() + this.polyYArray[0];
	
	public Plane(){
		super(polyXArray, polyYArray, 5);
		
	}

	
	
	public double getCenterX() {return centerX;}
	public double getCenterY() {return centerY;}
	public void setCenterX(double centerX) {this.centerX = centerX;}
	public void setCenterY(double centerY) {this.centerY = centerY;}
	
	//**********************************
	
	
	public double getuLeftXPos(){ return uLeftXPos; }
	public double getuLeftYPos(){ return uLeftYPos; }
	
	public void setuLeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
	public void setuLeftYPos(double yULPos){ this.uLeftYPos = yULPos; }
	
	//**************************************
	public int getWidth() {	return planeWidth;}
	public int getHeight() {	return planeHeight;}
    //***********************************************
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
	public void setXVelocity(double xVel){ this.xVelocity = xVel; }
	public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	
	//******************************************
	public void changeXPos(double incAmt) { this.centerX += incAmt; }
	public void changeYPos(double incAmt) { this.centerY += incAmt; }
	
	public Rectangle getBounds(){
		
		return new Rectangle((int) (getCenterX() - 20), (int) (getCenterX() - 5), getWidth(), getHeight());
			
	}
	public void move(){
		if(this.onScreen){
			
			this.changeXPos(this.getXVelocity());
			if(this.getCenterX() < 0){
			
				this.onScreen = false;
			
			} else
				if (this.getCenterX() > gBWidth){
				
					this.setCenterX(0);
				
				}
		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
