import java.awt.image.BufferedImage;

/**
 * Abstract class Filter.  Contains one method, filter, which will take an image,
 * apply a specific filter, and return it
 * @author Sam Maynard
 *
 */
public abstract class Filter {

	/**
	 * takes an image and applies a specific filter.
	 * @param img
	 * @return filteredImg
	 */
	public abstract BufferedImage filter(BufferedImage img);
	
}
