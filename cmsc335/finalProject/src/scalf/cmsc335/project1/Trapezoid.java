package scalf.cmsc335.project1;

/**
 * 
 * Provides calculations related to Trapezoids.
 * 
 * <p><strong>Filename:</strong> Trapezoid.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Trapezoid extends TwoDimensionalShape {
	/**
	 * The length of the shorter of the two parallel lines of this
	 * {@code Trapezoid}.
	 */
	private double baseA;
	/**
	 * The length of the longer of the two parallel lines of this
	 * {@code Trapezoid}.
	 */
	private double baseB;
	/**
	 * The distance between the two parallel lines of this {@code Trapezoid}.
	 */
	private double height;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Trapezoid() {
		super();
	}

	/**
	 * Calculates the area of a circle.
	 * <br><strong>Formula:</strong> A = ((a + b) * h) / 2;
	 * a = shorter parallel, b = longer parallel, h = height;
	 */
	@Override
	public void calculate() {
		// Get base a (shorter parallel)
		baseA = validateInput("\nWhat is the length of the shorter parallel?\n");
		
		// Get base b (longer parallel)
		baseB = validateInput("\nWhat is the length of the longer parallel?\n");
		
		// Get height
		height = validateInput("\nWhat is the height?\n");
		
		// Calculate area
		area = ( (baseA + baseB) * height) / 2;
	}

}
