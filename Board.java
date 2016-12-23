package shoot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import shoot.Gun;

public class Board extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public static int boardWidth = 800;
	public static int boardHeight = 600;
	
	public static boolean keyHeld = false;
	
	public static int keyHeldCode;
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Random rnd = new Random();
	
	static Plane thePlane = new Plane();
	
	
	 
	public static void main(String [] args)
	{
			
            new Board(); 
            new BackPan();
    }
	
	
	
	public Board()
    {
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Shooting Plane      ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==38)
			    {
					System.out.println("Speed Up");
					
					keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
					
			    } 
				
					else if (e.getKeyCode()==40){
			    	System.out.println("Speed Down");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
				
			    else if (e.getKeyCode()==39){
			    	System.out.println("Rotate Right");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
			     
			    else if (e.getKeyCode()==37){
			    	System.out.println("Rotate Left");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    }
				
			    else if (e.getKeyCode()==KeyEvent.VK_SPACE){
			    	System.out.println("Shoot");
			    	bullets.add(new Bullet(GameDrawingPanel.theGun.getShipNoseX(),
			    			GameDrawingPanel.theGun.getShipNoseY(),
			    			GameDrawingPanel.theGun.getRotationAngle()));
			    		
			    }
				
			}

			
			public void keyReleased(KeyEvent e) {
		
				keyHeld = false;
				
			}
        	
        });
        
        GameDrawingPanel gamePanel = new GameDrawingPanel();
        
        this.add(gamePanel, BorderLayout.CENTER);
        
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
		executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);
		
		this.setVisible(true);
    }
	
}

@SuppressWarnings("serial")
class BackPan extends JFrame{
	
	
	private JLayeredPane layers;
	private JPanel down;
	static int width = Board.boardWidth;
	static int height = Board.boardHeight;
	Random rnd = new Random();
	
	public BackPan(){
		
		   layers = new JLayeredPane();
	       rnd =new Random(); 
	       down = new JPanel(){
	       public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D)g; 
			//***************************************************************
	 		int low = 50; 
	 		int high = 255;
	 		for(int i = 0; i<= width; i+=50){
	 			g2d.setColor(new Color(rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low));     		
	     		g2d.fillRect(i, 50, 50, 50);
	     		for(int j = 0; j<= height; j += 50 ){
	     			g2d.setColor(new Color(rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low));
	         		g2d.fillRect(i, j, 50, 50);         		
	     		}
	 		}
	       }
	       };
	       //****************************************************************
	       down.setBounds(0, 0, width, height);
	       layers.add(down, new Integer(1));
	       getContentPane().add(layers, BorderLayout.CENTER);
	      
	
	}
	
}

class RepaintTheBoard2 implements Runnable{

	Board theBoard;
	
	public RepaintTheBoard2(Board theBoard){
		this.theBoard = theBoard;
	}

	@Override
	public void run() {
		
		theBoard.repaint();
		
	}
	
}

@SuppressWarnings("serial")

class GameDrawingPanel extends JComponent { 
	
	
	static int width = Board.boardWidth;
	static int height = Board.boardHeight;
	Random rnd = new Random();
	static Gun  theGun = new Gun();
	static Plane  thePlane = new Plane();
	static Bullet theBullet;
	public static ArrayList<Plane> planes = new ArrayList<Plane>();
	
	
	
	public GameDrawingPanel() { 
		planes.add(new Plane());
		

	} 
	
	@Override
	public void paint(Graphics g) { 
		
		//drawBackground(g);
		checkCollision();
		Graphics2D g2d = (Graphics2D)g; 
		AffineTransform identity = new AffineTransform();
		
		
		
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		g2d.setColor(Color.BLACK);
		
		if(Board.keyHeld == true && Board.keyHeldCode == 39){
			theGun.increaseRotationAngle();
		}else
			
		if(Board.keyHeld == true && Board.keyHeldCode == 37){
			theGun.decreaseRotationAngle();
		}else
		
		if(Board.keyHeld == true && Board.keyHeldCode == 38){
			theBullet.increaseSpeed();
			
		}
		theGun.move();
		g2d.setTransform(identity);
		g2d.translate(theGun.getXCenter(), theGun.getYCenter());
		g2d.rotate(Math.toRadians(theGun.getRotationAngle()));
		g2d.fill(theGun);
		g2d.draw(theGun);
		
		
		
		
		thePlane.move();
		g2d.setTransform(identity);
		g2d.translate(thePlane.getCenterX(), thePlane.getCenterY());
		g2d.fill(thePlane);
		g2d.draw(thePlane);
				
		
		g2d.setColor(Color.BLACK);
		for(Bullet bullet: Board.bullets){
			
			bullet.move();
			if(bullet.onScreen){
				g2d.setTransform(identity);
				g2d.translate(bullet.getXCenter(), bullet.getYCenter());
				g2d.draw(bullet);
			}
		}
			
	}
	
	public void checkCollision(){

		Rectangle pln = thePlane.getBounds();
		
		for(Bullet blt : Board.bullets){
			Rectangle b = blt.getBounds();
			if(b.intersects(pln)){
				blt.onScreen = false;
				thePlane.onScreen = false;
				System.out.println("HIT");
				System.exit(0);
				
			}
		}
	}/*
	public void drawBackground(Graphics g){
		Graphics2D g2d = (Graphics2D)g; 
	
  		int low = 50; 
  		int high = 255;
  		for(int i = 0; i<= width; i+=50){
  			g2d.setColor(new Color(rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low));
          	
      		
      		g2d.fillRect(i, 50, 50, 50);
      		for(int j = 0; j<= height; j += 50 ){
      			g2d.setColor(new Color(rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low,rnd.nextInt(high-low)+low));
          		g2d.fillRect(i, j, 50, 50);
          		
      		}
      	}
      	
  	
  	
	}*/
	
	
}
