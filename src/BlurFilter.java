import java.awt.image.BufferedImage;

/**
 * 
 * @author Sam Maynard
 * Filter to blur the image.  Averages the color values of all the pixels in a square around it.
 *
 */
public class BlurFilter extends Filter {

	int blurRadius;
	
	public BlurFilter(int blurRadius) {
		this.blurRadius = blurRadius;
	}
	
	public BlurFilter() {
		this(5);
	}
	
	@Override
	public BufferedImage filter(BufferedImage img) {
		BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for(int i=0;i<filteredImage.getWidth();i++){
			for(int j=0;j<filteredImage.getHeight();j++){
				
				int rgb = 0;
				int r = 0;
				int g = 0;
				int b = 0;
				int num = 0;
				for(int y=-blurRadius;y<=blurRadius;y++){
					for(int z=-blurRadius;z<=blurRadius;z++){
						try{
							rgb = img.getRGB(i+y, j+z);
							r += (int) ((rgb & 0xFF0000) >> 16);
							g += (int) ((rgb & 0x00FF00) >> 8);
							b += (int) (rgb & 0x0000FF);
							
							num++;
						}catch(ArrayIndexOutOfBoundsException e){
						}
					}
				}
				r /= num;
				g /= num;
				b /= num;

				r = r << 16;
				g = g  << 8;
				int newRGB = r + b + g;
				filteredImage.setRGB(i, j, newRGB);
			}
		}
		return filteredImage;
	}

}
