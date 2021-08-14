package scalf;

import javax.swing.SwingUtilities;

import scalf.project2.GUI;

/**
 * 
 * The ShapesGUI program loader.  This class contains only the bare minimum to
 * start the program and load the GUI.
 * 
 * <p><strong>Filename:</strong> ShapesGUI.java
 *
 * @author Samuel Scalf
 * @date 31 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class ShapesGUI {

	/**
	 * Creates and starts an instance of the GUI.
	 * 
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI shapesGui = new GUI();
					shapesGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
