import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * 
 * @author Sam Maynard
 * Filter that applys the Lo-Fi effect.  High saturation, warm temperature.
 *
 */
public class LoFiFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		RescaleOp rescaler = new RescaleOp(1.5f, -255/4f, null);
		((Graphics2D)(filteredImage.getGraphics())).drawImage(img, rescaler, 0, 0);
		for(int i=0;i<filteredImage.getWidth();i++){
			for(int j=0;j<filteredImage.getHeight();j++){
				int rgb = filteredImage.getRGB(i, j);
				int r = (int) ((rgb & 0xFF0000) >> 16);
				int g = (int) ((rgb & 0x00FF00) >> 8);
				int b = (int) (rgb & 0x0000FF);
				int warmR = (int) (r * 1.07);
				if( warmR > 255)
					warmR = 255;
				warmR = warmR << 16;
				int warmG = g << 8;
				int warmB = b;
				int warmRGB = warmR + warmG + warmB;
				filteredImage.setRGB(i, j, warmRGB);
			}
		}
		return filteredImage;
	}

}
