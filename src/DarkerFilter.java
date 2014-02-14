import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * 
 * @author Sam Maynard
 * Filter to make and image darker by half.
 *
 */
public class DarkerFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		RescaleOp rescaler = new RescaleOp(.5f, 0f, null);
		((Graphics2D)(filteredImage.getGraphics())).drawImage(img, rescaler, 0, 0);
		return filteredImage;
	}

}
