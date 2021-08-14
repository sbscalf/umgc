package scalf.project1;

/**
 * 
 * Custom exception class for menu selection validation. This class is only used
 * for returning a more meaningful exception name and message when an invalid
 * menu selection is made. Invalid selections are non-integer values and integer
 * values not listed in the menu (1 .. "last choice").
 * 
 * <p><strong>Filename:</strong> InvalidSelection.java
 *
 * @author Samuel Scalf
 * @date 16 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class InvalidSelection extends Exception {
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -1860821970783796808L;

	/**
	 * Creates a checked Exception for the user's menu selection.
	 * 
	 * @param low The lowest available option on the menu. This is usually '1'.
	 * @param high The highest available option on the menu.
	 */
	public InvalidSelection(int low, int high) {
		super("Selection must be an integer between " + low +"-" + high + ".");
	}
}
