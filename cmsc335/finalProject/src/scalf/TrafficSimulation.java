package scalf;

import javax.swing.SwingUtilities;

import scalf.cmsc335.finalProject.TrafficController;
import scalf.cmsc335.finalProject.View;

/**
 * 
 * Starts the Traffic Simulator program.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class TrafficSimulation {

	/**
	 * Initializes the primary components and starts the program.
	 * 
	 * @param args Ignored and not used in this program.
	 */
	public static void main(String[] args) {
		View view = new View();
		TrafficController controller = new TrafficController(view);
		view.setViewListener(controller);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.start();
			}
		});
	}

}
