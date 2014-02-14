import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author Sam Maynard
 * Canvas class that stores and draws the image
 *
 */
public class ImageCanvas extends Canvas {

	BufferedImage img;
	
	/**
	 * Instantiator
	 * @param width
	 * @param height
	 * @param img
	 */
	public ImageCanvas(int width, int height, BufferedImage img) {
		this.setBounds(0, 0, width, height);
		this.img = img;
	}
	
	/**
	 * updates the image being drawn on the canvas
	 * @param img
	 */
	public void updateImage(BufferedImage img){
		this.img = img;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
	}
}
