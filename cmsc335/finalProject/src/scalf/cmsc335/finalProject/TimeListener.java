package scalf.cmsc335.finalProject;

/**
 * 
 * Abstracts away the listener for the Timestampers.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public interface TimeListener {
	public void timeChanged(String timestamp);
}
