import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DrawingCanvas extends Canvas implements MouseMotionListener, MouseWheelListener, MouseListener, KeyListener, Runnable {
	
	public double zoom;
	public double viewX;
	public double viewY;
	
	public static int VIEW_WIDTH;
	public static int VIEW_HEIGHT;
	
	public static int ACTUAL_WIDTH;
	public static int ACTUAL_HEIGHT;
	
	private BufferedImage back;
	
	public boolean dragging = false;
	private int toX = -1;
	private int toY = -1;
	
	public DrawingCanvas (int viewWidth, int viewHeight, int actualWidth, int actualHeight) {
		setBackground(Color.WHITE);

		VIEW_WIDTH = viewWidth;
		VIEW_HEIGHT = viewHeight;
		ACTUAL_WIDTH = actualWidth;
		ACTUAL_HEIGHT = actualHeight;
		zoom = 1;
		viewX = ACTUAL_WIDTH/2;
		viewY = ACTUAL_HEIGHT/2;
				
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();
	}
	
	public void update (Graphics window) {
		paint(window);
	}
	
	public void paint (Graphics window) {
		
		Graphics2D tdg = (Graphics2D) window;
		
		if(back==null)
			back = (BufferedImage)createImage(ACTUAL_WIDTH, ACTUAL_HEIGHT);
			
		Graphics gtb = back.createGraphics();
		
		gtb.setColor(Color.WHITE);

		Point p = getAverageCenter();
		
		tdg.drawImage(back.getSubimage(p.x - (VIEW_WIDTH/2), p.y - (VIEW_HEIGHT/2), VIEW_WIDTH, VIEW_HEIGHT), null, 0, 0);
		
	}
	
	private Point getAverageCenter() {
		long totalX = 0;
		long totalY = 0;
		int num = 0;
		for (int i = 0; i < back.getWidth() - 1; i+=5) {
			for (int j = 0; j < back.getHeight() - 1; j+=5) {
				//System.out.println("" + i + " " + j);
				int check = back.getRGB(i, j);
				int red =   (check & 0x00ff0000) >> 16;
		        int green = (check & 0x0000ff00) >> 8;
		        int blue =   check & 0x000000ff;
		        
				if (red != 255 && green != 255 && blue != 255) {
					num ++;
					totalX += i;
					totalY += j;
				}
			}
		}
		if (totalX == 0 || totalY == 0) {
			return new Point(ACTUAL_WIDTH/2, ACTUAL_HEIGHT/2);
		}
		return new Point((int)(totalX/num), (int)(totalY/num));
	}

	public void run() {
   		try {
	   		while(true) {
	   		   Thread.currentThread().sleep(5);
	           repaint();
	        }
      	}
      	catch(Exception e) {
      	}
  	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point point = e.getPoint();
		dragging = true;
		toX = e.getX();
		toY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (dragging) {
			int x = e.getX();
			int y = e.getY();
			
			Graphics2D g2 = (Graphics2D) back.createGraphics();
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(10));
			g2.drawLine(toX, toY, x, y);
			//System.out.println("happening");
			toX = x;
			toY = y;
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}