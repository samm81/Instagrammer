import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * 
 * @author Sam Maynard
 * Filter to brighten the image by half.
 *
 */
public class BrighterFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		RescaleOp rescaler = new RescaleOp(.5f, 255/2f, null);
		((Graphics2D)(filteredImage.getGraphics())).drawImage(img, rescaler, 0, 0);
		return filteredImage;
	}

}
