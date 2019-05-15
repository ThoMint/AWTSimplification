package thomas.hofmann;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//Interface for user input and draw/loop functions
public interface ContextInterface extends KeyListener, MouseMotionListener, MouseListener {
	// Function in which the user can determine what to draw
	// by the ContextPane
	// This function has 0/0 at the bottom left
	// For details about the capability's of Graphics2D see
	// https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
	public void drawRev(Graphics2D g);

	// Function in which the user can determine what to draw
	// This function has 0/0 at the top left
	public void draw(Graphics2D g);

	// Function in which the user can lock the frame rate and redraw every frame
	// Called by an independent Thread
	public void loop(ContextPane ge);
}
