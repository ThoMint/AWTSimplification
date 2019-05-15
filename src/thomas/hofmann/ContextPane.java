package thomas.hofmann;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContextPane {
	public int width, height;
	private double wait, diff, start;
	private boolean stop;
	private ContextInterface ci;
	private JPanel p;
	private Graphics2D g2;
	private Thread t;
	private ContextPane cp;

	// Constructor
	public ContextPane(Window w, ContextInterface i) {
		super();
		// Setting 'cp' the reference to this object in order to use it in the thread
		cp = this;
		ci = i;
		p = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				super.paintComponent(g);
				// Draw the background
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLACK);
				// Create another graphics object
				g2 = (Graphics2D) g.create();
				// Call the draw function with non reversed coordinate system
				ci.draw(g2);
				// flip the coordinate System
				AffineTransform tform = AffineTransform.getTranslateInstance(0, this.getHeight());
				tform.scale(1, -1);
				g2.setTransform(tform);
				// Call the draw function from the interface determined by the user
				ci.drawRev(g2);
			}
		};
		stop = false;
		t = new Thread() {
			@Override
			public void run() {
				while (!stop) {
					// Call the loop function through the interface
					ci.loop(cp);
					width = p.getWidth();
					height = p.getHeight();
				}
				super.run();
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
		p.addKeyListener(ci);
		p.addMouseMotionListener(ci);
		p.addMouseListener(ci);
	}

	// To cap the logic of the game at a fixed rate
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

	public void stop() {
		stop = true;
	}

	public void start() {
		stop = false;
		t.start();
	}

	// Calls the repaint function of the JPanel
	public void redraw() {
		p.repaint();
	}

	public void redrawImmediatly() {
		p.paintImmediately(p.getBounds());
	}
}
