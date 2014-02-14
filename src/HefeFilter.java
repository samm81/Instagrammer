import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * 
 * @author Sam Maynard
 * Filter to apply a Hefe effect.  Slight vignette edges, high contrast, high saturation.
 */
public class HefeFilter extends Filter {

	int vignetteRadius = 230;
	int vignetteOffset = 10;
	
	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		RescaleOp rescaler = new RescaleOp(1.25f, -255/8f, null);
		((Graphics2D)(filteredImage.getGraphics())).drawImage(img, rescaler, 0, 0);
		for(int i=0;i<filteredImage.getWidth();i++){
			for(int j=0;j<filteredImage.getHeight();j++){
				double multiplier = 1;
				Point p = new Point(i, j);
				Point center = new Point(filteredImage.getWidth()/2, filteredImage.getHeight()/2);
				Point corner = new Point(0, 0);
				double distance = p.distance(center);
				double maxDistance = corner.distance(center);
				multiplier = 1 - Math.pow((float)(distance) / (float)(maxDistance), 3);
					
				int rgb = filteredImage.getRGB(i, j);
				int r = (int) (((rgb & 0xFF0000) >> 16) * multiplier);
				int g = (int) (((rgb & 0x00FF00) >> 8) * multiplier);
				int b = (int) ((rgb & 0x0000FF) * multiplier);
				int warmR = (int) (r * 1.1);
				if( warmR > 255)
					warmR = 255;
				warmR = warmR << 16;
				int warmG = (int) (g * 1.3);
				if( warmG > 255)
					warmG = 255;
				warmG = g << 8;
				int warmB = b;
				int warmRGB = warmR + warmG + warmB;
				filteredImage.setRGB(i, j, warmRGB);
			}
		}
		return filteredImage;
	}

}
