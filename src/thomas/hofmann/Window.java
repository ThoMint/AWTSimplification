package thomas.hofmann;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public int width;
	public int height;
	public int x;
	public int y;
	public Rectangle bounds;
	// Resizeable true by default
	public boolean resizeable = true;

	// Constructor where you can set resizeable to false
	public Window(String title, int width, int height, boolean resizeable) {
		super(title);
		this.width = width;
		this.height = height;
		this.resizeable = resizeable;
		init();
	}

	// Constructor without setting resizeable so it is true
	public Window(String title, int width, int height) {
		super(title);
		this.width = width;
		this.height = height;
		init();
	}

	// To center the window in the middle of the default screen
	void getPosition() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		this.x = width / 2 - this.width / 2;
		this.y = height / 2 - this.height / 2;
	}

	// The part of the constructor that stays the same for both Variants
	void init() {
		// Set the x,y coordinates to center the window
		getPosition();
		// Sets the position and size of the frame
		super.setBounds(x, y, width, height);
		this.bounds = super.getBounds();
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(resizeable);
	}

	// Update variables when called
	void update() {
		this.bounds = super.getBounds();
		this.width = this.bounds.width;
		this.height = this.bounds.height;
		this.x = this.bounds.x;
		this.y = this.bounds.y;
	}
}
