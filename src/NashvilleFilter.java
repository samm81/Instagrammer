import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * 
 * @author Sam Maynard
 * Filter that applys the Nashville effect.  High exposure, low contrast, warm temperature.
 *
 */
public class NashvilleFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		RescaleOp rescaler = new RescaleOp(.6f, 255/5f, null);
		((Graphics2D)(filteredImage.getGraphics())).drawImage(img, rescaler, 0, 0);
		for(int i=0;i<filteredImage.getWidth();i++){
			for(int j=0;j<filteredImage.getHeight();j++){
				int rgb = filteredImage.getRGB(i, j);
				int r = (int) ((rgb & 0xFF0000) >> 16);
				int g = (int) ((rgb & 0x00FF00) >> 8);
				int b = (int) (rgb & 0x0000FF);
				int yellowR = (int) (r * 1.15);
				if( yellowR > 255)
					yellowR = 255;
				yellowR = yellowR << 16;
				
				int yellowG = (int) (g * 1.15);
				if( yellowG > 255)
					yellowG = 255;
				yellowG = yellowG << 8;
				
				int yellowB = b;
				int yellowRGB = yellowR + yellowG + yellowB;
				filteredImage.setRGB(i, j, yellowRGB);
			}
		}
		return filteredImage;
	}

}
