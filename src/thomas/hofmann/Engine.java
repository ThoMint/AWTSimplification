package thomas.hofmann;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//Interface for User input and draw/loop functions
public interface Engine extends KeyListener, MouseMotionListener, MouseListener {
	// function in which the user can determine what to draw
	// by the GraphicsEngine
	// For details about the capability's of Graphics2D see
	// https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
	public void draw(Graphics2D g);

	// function in which the user can lock the frame rate and redraw every frame
	// Handled by an independent Thread
	public void loop(GraphicsEngine ge);
}
