import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JPanel;

//To do:
// ellipse orbits
// fix lock onto moon
//   !!! maybe issue with drawing is system time passes between the lock, move, and the draw
//      perhaps solution is to save system time for the draw

public class DrawingFrame extends JFrame {
	
	public static final int VIEW_WIDTH = 1050;
	public static final int VIEW_HEIGHT = 1050;
	public static final int ACTUAL_WIDTH = VIEW_WIDTH*3;
	public static final int ACTUAL_HEIGHT = VIEW_HEIGHT*3;
	
	public static DrawingCanvas canvas;
	
	@SuppressWarnings("unchecked")
	public DrawingFrame () {
		super("Centered Drawing");
		setSize(VIEW_WIDTH, VIEW_HEIGHT);
		setLocation(350,0);
		
		canvas = new DrawingCanvas(VIEW_WIDTH, VIEW_HEIGHT, ACTUAL_WIDTH, ACTUAL_HEIGHT);
		((Component)canvas).setFocusable(true);
		
		getContentPane().add(canvas);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		DrawingFrame go = new DrawingFrame();
	}
}