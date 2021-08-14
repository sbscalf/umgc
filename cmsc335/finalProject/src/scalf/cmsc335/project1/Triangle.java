package scalf.cmsc335.project1;

/**
 * 
 * Provides calculations related to Triangles.
 * 
 * <p><strong>Filename:</strong> Triangle.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Triangle extends TwoDimensionalShape {
	/**
	 * The side of the triangle from which the height is measured.
	 */
	private double base;
	/**
	 * The distance from the center of the base of the triangle to the opposite
	 * angle.
	 */
	private double height;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Triangle() {
		super();
	}

	/**
	 * Calculates the area of a square.
	 * <br><strong>Formula:</strong> A = &#189;bh; b = base, h = height
	 */
	@Override
	public void calculate() {
		// Get length of base
		base = validateInput("\nWhat is the length of the base?\n");
		
		// Get the height
		height = validateInput("\nWhat is the height?\n");
		
		// Calculate area
		area = 0.5 * base * height;
	}

}
