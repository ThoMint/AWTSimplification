package thomas.hofmann;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	// Rectangle that represents Bounds
	public Rectangle bounds;
	// Resizeable true by default
	public boolean resizeable = true;

	// Constructor where you can set resizeable to false
	public Window(String title, int width, int height, boolean resizeable) {
		super(title);
		bounds = new Rectangle();
		bounds.width = width;
		bounds.height = height;
		this.resizeable = resizeable;
		init();
	}

	// Constructor without setting resizeable so it is true
	public Window(String title, int width, int height) {
		super(title);
		bounds = new Rectangle();
		bounds.width = width;
		bounds.height = height;
		init();
	}

	// To center the window in the middle of the default screen
	void getPosition() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int dWidth = gd.getDisplayMode().getWidth();
		int dHeight = gd.getDisplayMode().getHeight();

		bounds.x = dWidth / 2 - bounds.width / 2;
		bounds.y = dHeight / 2 - bounds.height / 2;
	}

	// The part of the constructor that stays the same for both Variants
	void init() {
		// Set the x,y coordinates to center the window
		getPosition();
		// Sets the position and size of the frame
		super.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(resizeable);
		super.setVisible(true);
	}

	// Update variables when called
	void update() {
		bounds = super.getBounds();
	}
}