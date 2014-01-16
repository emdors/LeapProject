import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

//NEW COMMENT WHOOOOOOOOO

import javax.swing.JPanel;

public class DA extends JPanel {
	public static int height, width, gestureID;
	private String message;

	public DA(int w, int h) {
		height = h;
		width = w;
		setBackground(Color.GREEN);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void drawSquare(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(100, 100, 100, 100);
	}


	// REQUIRED: the following help fix the size of this JPanel…you will
	// probably have the same
	// methods inside any subclass of JPanel that you create.
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	public Dimension getMinimumSize() {
		return getSize();
	}

	public Dimension getMaximumSize() {
		return getSize();
	}

	public Dimension getPreferredSize() {
		return getSize();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
