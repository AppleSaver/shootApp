package shoot;


import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;



@SuppressWarnings("serial")
public class Bullet extends Polygon{

	
	int gBWidth = Board.boardWidth;
	int gBHeight = Board.boardHeight;
		
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private double centerX = 0, centerY = 0;
	
	public static int[] polyXArray = {-10,10,10,-10,-10};
	public static int[] polyYArray = {-10,-10,10,10,-10};
	
	
	private int bulletWidth = 10, bulletHeight = 10;
	
	public boolean onScreen = false;
	
	
	private double movingAngle = 0;
	
	private double xVelocity = 2, yVelocity = 2;
	private int speed = 4;
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}


	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int  increaseSpeed(){
		int x=0;
		x= this.getSpeed() + 1;
		return x;
	}
	
	public Bullet(double centerX, double centerY , double movingAngle){
		
		super(polyXArray, polyYArray, 5);
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.movingAngle = movingAngle;
		
		this.onScreen = true;
		
		this.setXVelocity(this.bulletXMoveAngle(movingAngle)*5);
		this.setYVelocity(this.bulletYMoveAngle(movingAngle)*5);
	}
	
	
	public double getXCenter(){ return centerX; }
	
	public double getYCenter(){ return centerY; }
		
	public void setXCenter(double xCent){ this.centerX = xCent; }
		
	public void setYCenter(double yCent){ this.centerY = yCent; }
		
	public void changeXPos(double incAmt) { this.centerX += incAmt; }
		
	public void changeYPos(double incAmt) { this.centerY += incAmt; }
	
	//****************************************************
	public double getXVelocity(){ return xVelocity; }
	
	public double getYVelocity(){ return yVelocity; }
		
	public void setXVelocity(double xVel){ this.xVelocity = xVel; }
		
	public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	//*******************************************************
	public void increaseXVelocity(){ this.xVelocity += 1; }
	
	public void increaseYVelocity(){ this.yVelocity += 1; }
	
	public void decreaseXVelocity(){ this.xVelocity -= 1; }
	
	public void decreaseYVelocity(){ this.yVelocity -= 1; }
	//***********************************************
	public int getWidth(){ return bulletWidth; }
	
	public int getHeight(){ return bulletHeight; }
	//***************************************************
	public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
	
	public double getMovingAngle(){ return movingAngle; }
	
	
	public Rectangle getBounds(){
			
		return new Rectangle((int) (getXCenter() - 10), (int) (getXCenter() - 10), getWidth(), getHeight());
			
	}
	
	public double bulletXMoveAngle(double xMoveAngle){
		
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
			
	}
		
		
	public double bulletYMoveAngle(double yMoveAngle){
			
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
			
	}
	
	public void move(){
		
		if(this.onScreen){
			
			
			this.changeXPos(this.getXVelocity());
		
		
		if(this.getXCenter() < 0){
			
			this.onScreen = false;
			
		} else
			if (this.getXCenter() > gBWidth){
				
				this.onScreen = false;
				
			}
		
		
		this.changeYPos(this.getYVelocity());
		
		
		if(this.getYCenter() < 0){
			
			this.onScreen = false;
			
		} else
			if (this.getYCenter() > gBHeight){
				
				this.onScreen = false;
				
			}
		
		} 
		/*
		for(Bullet bul : bullets){
			if(bul.onScreen){
				Rectangle otherBull = bul.getBounds();
				Rectangle pla = plane.getBounds();
				if(otherBull.intersects(pla)){
					bul.onScreen = false;
					plane.onScreen = false;
					
					System.out.println("HIT");
				}
			}
		}
		*/
		
		
	}

	
}
