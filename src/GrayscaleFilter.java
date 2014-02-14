import java.awt.image.BufferedImage;

/**
 * 
 * @author Sam Maynard
 * Filter to make an image grayscale.  Rudimentary algorithm that merely averages R, G, and B.
 *
 */
public class GrayscaleFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for(int i=0;i<img.getWidth();i++){
			for(int j=0;j<img.getHeight();j++){
				int rgb = img.getRGB(i, j);
				int r = (int) ((rgb & 0xFF0000) >> 16);
				int g = (int) ((rgb & 0x00FF00) >> 8);
				int b = (int) (rgb & 0x0000FF);
				int gray = (r + g + b) / 3;
				int grayR = gray << 16;
				int grayG = gray << 8;
				int grayB = gray;
				int grayRGB = grayR + grayG + grayB;
				filteredImage.setRGB(i, j, grayRGB);
			}
		}
		return filteredImage;
	}

}
