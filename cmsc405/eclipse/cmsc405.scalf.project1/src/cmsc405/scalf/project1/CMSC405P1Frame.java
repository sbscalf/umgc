package cmsc405.scalf.project1;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CMSC405P1Frame extends JFrame {

	private static final long serialVersionUID = 3181896601838002034L;
	private CMSC405P1Panel panel = new CMSC405P1Panel();
	private int frameNumber = 0;
	
	public CMSC405P1Frame() {
		super("CMSC405 Project 1");
		panel.setPreferredSize(new Dimension(800, 600));
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void advanceFrame() {
		frameNumber = (frameNumber + 1) % 5;
		panel.repaint(); 
	}

}
