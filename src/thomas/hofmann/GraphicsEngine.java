package thomas.hofmann;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

@SuppressWarnings("serial")
//Handles drawing and user input
public class GraphicsEngine extends Thread {
	public int width, height;
	private double wait, diff, start;
	private boolean stop = false;
	// Interface
	private Engine e;
	private JPanel p;

	// Constructor
	public GraphicsEngine(Window w, Engine e) {
		super();
		p = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				super.paintComponent(g);
				// Draw the background
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLACK);
				// Create another graphics object and flip the coordinate System
				Graphics2D g2 = (Graphics2D) g.create();
				AffineTransform tform = AffineTransform.getTranslateInstance(0, this.getHeight());
				tform.scale(1, -1);
				g2.setTransform(tform);
				// Call the draw function from the interface determined by the user
				e.draw(g2);
			}
		};
		// Add the JPanel to the JFrame (Window)
		w.add(p);
		p.validate();
		w.validate();
		// Focus is needed for user input
		p.setFocusable(true);
		p.requestFocus();
		p.setIgnoreRepaint(false);
		// Add listeners for user input
		p.addKeyListener(e);
		p.addMouseMotionListener(e);
		p.addMouseListener(e);
		this.e = e;
	}

	// To get a stable frame rate
	public void capFrameRate(double fps) {
		// Calculate standard waiting time in milliseconds
		wait = 1000 / fps;
		// The time passed since the last
		diff = System.currentTimeMillis() - start;
		// If the computer can keep up with the frame rate lock it to the
		// desired rate
		if (diff < wait) {
			try {
				Thread.sleep((long) (wait - diff));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Set new starting time to measure time
		start = System.currentTimeMillis();
	}

	// Extra Thread for loop
	@Override
	public void run() {
		super.run();
		while (!stop) {
			// Call the loop function through the interface
			this.e.loop(this);
			this.width = p.getWidth();
			this.height = p.getHeight();
		}
	}

	public void kill() {
		stop = true;
	}

	// Calls the repaint function of the JPanel
	public void redraw() {
		p.repaint();
	}
	
	public void redrawImmediatly() {
		p.paintImmediately(p.getBounds());
	}
}
