import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Test {
	public static void main(String[] args) {
		JFrame window;
		
		//parseGUI
		window = new JFrame();
		window.setTitle("Calculator");
		window.setSize(200,200);
		
		//parseLayout (Container container) -- window in demo
		window.setLayout(new FlowLayout());
		
		//parse widgets
		//first widget: Textfield
		window.add(new JTextField(20));
		
		//second widget: Panel
			// parsePanel
			JPanel panel = new JPanel();
			//parseLayout
			panel.setLayout(new GridLayout(4,3,5,5));
			//parseWidgets
			panel.add(new JButton("7"));
			panel.add(new JButton("8"));
			panel.add(new JButton("9"));
			panel.add(new JButton("4"));
			panel.add(new JButton("5"));
			panel.add(new JButton("6"));
			panel.add(new JButton("1"));
			panel.add(new JButton("2"));
			panel.add(new JButton("3"));
			panel.add(new JLabel(""));
			panel.add(new JButton("0"));
		//end Panel
		window.add(panel);
		// end GUI
			
		window.setVisible(true);
	}
}
