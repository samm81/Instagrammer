import java.awt.image.BufferedImage;

/**
 * 
 * @author Sam Maynard
 * Filter that applys no effect.
 *
 */
public class NoFilter extends Filter {

	@Override
	public BufferedImage filter(BufferedImage img) {
		return img;
	}

}
