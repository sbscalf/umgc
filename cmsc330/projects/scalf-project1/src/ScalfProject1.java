

import java.io.FileReader;
import java.io.Reader;

import javax.swing.JOptionPane;

import scalf.project1.util.Parser;

public class ScalfProject1 {

	public static void main(String[] args) {
		
		try {
			Reader reader;
			if (args.length == 0) {
				JOptionPane.showMessageDialog(null,"No file path provided as command line argument.\nUsing internal configuration.","No Filepath Notice",JOptionPane.INFORMATION_MESSAGE);
			  	reader = new FileReader("calculator");
			} else {
				reader = new FileReader(args[0]);
			}
			
			Parser parser = new Parser(reader);
			
			//parser.showTokens();
			parser.parse();
		} catch (Exception e) {
	 		JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	 	}
	 	
		
	} // end main() method

}
