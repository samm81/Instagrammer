import java.awt.Choice;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Sam Maynard
 * The main Runner class for the Instagram project.  Instantiates all GUI and other aspects
 * of the program and loops infinitely checking if the user changes their filter selection
 *
 */
public class Runner {

	static Choice filterChooser;
	
	/** main() runs the program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Instagram");
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		JFileChooser imageChooser = new JFileChooser(new File("."));
		imageChooser.showOpenDialog(frame);
		
		JLabel filterChoiceLabel = new JLabel("Choose a filter: ");
		filterChoiceLabel.setBounds(5, 5, 100, 20);
		frame.add(filterChoiceLabel);
		
		filterChooser = new Choice();
		filterChooser.add("None");
		filterChooser.add("Blur");
		filterChooser.add("Brighter");
		filterChooser.add("Darker");
		filterChooser.add("Grayscale");
		filterChooser.add("Hefe");
		filterChooser.add("LoFi");
		filterChooser.add("Nashville");
		filterChooser.add("Walden");
		filterChooser.setBounds(105, 5, 150, 40);
		frame.getContentPane().add(filterChooser);

		File imgFile = imageChooser.getSelectedFile();
		BufferedImage img = ImageIO.read(imgFile);
		
		ImageCanvas canvas = new ImageCanvas(frame.getWidth(), frame.getHeight(), img);
		canvas.setBounds(0, 45, img.getWidth(), img.getHeight());
		frame.getContentPane().add(canvas);
		
		frame.setBounds(50, 50, img.getWidth(), img.getHeight()+45);
		frame.setVisible(true);
		
		Filter prevFilter = getCurrentFilter();
		
		while(true){
			Filter filter = getCurrentFilter();
			if(filter.getClass() != prevFilter.getClass()){
				BufferedImage filteredImg = filter.filter(img);
				canvas.updateImage(filteredImg);
				prevFilter = filter;
			}
		}
	}
	
	/** getCurrentFilter() returns the specific filter object based on the selection in the dropdown
	 * 
	 * @return the specific filter object based on the selection in the dropdown
	 */
	private static Filter getCurrentFilter() {
		Filter filter;
		switch(filterChooser.getSelectedItem()){
		case "Blur": filter = new BlurFilter(); break;
		case "Brighter": filter = new BrighterFilter(); break;
		case "Darker": filter = new DarkerFilter(); break;
		case "Grayscale": filter = new GrayscaleFilter(); break;
		case "Hefe": filter = new HefeFilter(); break;
		case "LoFi": filter = new LoFiFilter(); break;
		case "Nashville": filter = new NashvilleFilter(); break;
		case "None": filter = new NoFilter(); break;
		case "Walden": filter = new WaldenFilter(); break;
		default: filter = new NoFilter(); break;
		}
		return filter;
	}
}
