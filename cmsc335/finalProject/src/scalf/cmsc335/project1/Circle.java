package scalf.cmsc335.project1;

/**
 * 
 * Provides calculations related to Circles.
 * 
 * <p><strong>Filename:</strong> Circle.java
 *
 * @author Samuel Scalf
 * @date 17 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Circle extends TwoDimensionalShape {
	/**
	 * The distance from the center of this {@code Circle} to any point along
	 * its circumference.
	 */
	private double radius;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Circle() {
		super();
	}

	/**
	 * Calculates the area of this circle.
	 * <br><strong>Formula:</strong> A = &pi; * r<sup>2</sup>; r = radius
	 * <p><strong>NOTE:</strong> &pi; is estimated to be 3.14159 for
	 * calculations. See {@link Shape#PI}.
	 */
	@Override
	public void calculate() {
		radius = validateInput("\nWhat is the radius?\n");
		area = PI * radius * radius;
	}

}
