package scalf.project1;

/**
 * 
 * Provides calculations related to Squares.
 * 
 * <p><strong>Filename:</strong> Square.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Square extends TwoDimensionalShape {
	/**
	 * The length of any given side of the square.
	 */
	private double length;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Square() {
		super();
	}

	/**
	 * Calculates the area of a square.
	 * <br><strong>Formula:</strong> A = l<sup>2</sup>; l = length of one side
	 */
	@Override
	public void calculate() {
		// Get length
		length = validateInput("\nWhat is the length of a side?\n");
		
		// Calculate area
		area = length * length;
	}

}
