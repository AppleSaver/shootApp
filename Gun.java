package shoot;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Gun extends Polygon{

	
	int gunHeight = 50, gunWidth = 15;
	
	
	int gBWidth = Board.boardWidth;
	int gBHeight = Board.boardHeight;
	
	public static int[] polyXArray = {-15,15,15,-15,-15};
	public static int[] polyYArray = {-50,-50,50,50,-50};
	
	
	private double centerX = gBWidth/2, centerY = gBHeight -90;
	
	private double rotationAngle = 0, movingAngle = 0;
	
	
	private double uLeftXPos = getXCenter() + this.polyXArray[0];
	private double uLeftYPos = getYCenter() + this.polyYArray[0];
	
	public boolean onScreen = true;
	
	public Gun(){
		super(polyXArray,polyYArray,5);
	}
	
	
	public double getXCenter(){ return centerX; }
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	public void setYCenter(double yCent){ this.centerY = yCent; }
	
	
	public double getuLeftXPos(){ return uLeftXPos; }
	public double getuLeftYPos(){ return uLeftYPos; }
	
	public void setuLeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
	public void setuLeftYPos(double yULPos){ this.uLeftYPos = yULPos; }
	
	
	public int getWidth(){ return gunWidth;}
	public int getHeight(){return gunHeight;}
	
	public void setMovingAngle(double moveAngle){this.movingAngle = moveAngle;}
	public double getMovingAngle(){return movingAngle;}
	public void increaseMovingAngle(double moveAngle){this.movingAngle += moveAngle;}
	
	
	
	
	public double getShipNoseX(){

		return this.getXCenter() + Math.cos(rotationAngle) * 20;
		
	}
	
	public double getShipNoseY(){
		
		return this.getYCenter() + Math.sin(rotationAngle) * 20;
		
	}
	
	public double gunXMoveAngle(double xMoveAngle){
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
	}
	public double gunYMoveAngle(double yMoveAngle){
		return (double)(Math.sin(yMoveAngle * Math.PI /180));
	}
	
	public double getRotationAngle(){return rotationAngle;}
	public void increaseRotationAngle(){
		if(getRotationAngle() >= 355){ rotationAngle = 0; }
		else{ rotationAngle += 5;}
	}
	public void decreaseRotationAngle(){
		if(getRotationAngle() < 0){rotationAngle = 355; }
		else{rotationAngle -= 5; }
	}
	
	
	
	
	
public void move(){
		
		
		if(this.getXCenter() < 0){
			
			this.setXCenter(gBWidth);
			
		} else
			if (this.getXCenter() > gBWidth){
				
				this.setXCenter(0);
				
			}
		
		
		if(this.getYCenter() < 0){
			
			this.setYCenter(gBHeight);
			
		} else
			if (this.getYCenter() > gBHeight){
				
				this.setYCenter(0);
				
			}
		
	}
	

	
	
	
}
