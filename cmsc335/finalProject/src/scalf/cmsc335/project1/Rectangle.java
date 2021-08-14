package scalf.cmsc335.project1;

/**
 * 
 * Provides calculations related to Rectangles.
 * 
 * <p><strong>Filename:</strong> Rectangle.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Rectangle extends TwoDimensionalShape {
	/**
	 * The horizontal side of the rectangle.
	 */
	private double length;
	
	/**
	 * The vertical side of the rectangle.
	 */
	private double width;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Rectangle() {
		super();
	}

	/**
	 * Calculates the area of a rectangle.
	 * <br><strong>Formula:</strong> A = lw; l = length, w = width
	 */
	@Override
	public void calculate() {
		// Get Length
		length = validateInput("\nWhat is the length?\n");
		
		// Get Width
		width = validateInput("\nWhat is the Width?\n");
		
		// Calculate Area
		area = length * width;
	}

}
