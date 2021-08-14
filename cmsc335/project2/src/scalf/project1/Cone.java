package scalf.project1;

/**
 * 
 * Provides calculations related to Cones.
 * 
 * <p><strong>Filename:</strong> Cone.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Cone extends ThreeDimensionalShape {
	/**
	 * The distance from the center of the base to any point along its
	 * circumference. This is similar to {@link Circle#radius}. 
	 */
	private double radius;
	/**
	 * The distance from the center of the base to the peak of the cone.
	 */
	private double height;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code ThreeDimensionalShape} constructor.
	 */
	public Cone() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calculates the volume of a cone.
	 * <br><strong>Formula:</strong> V = (&pi;r<sup>2</sup>h)/3;
	 * r = radius, h = height
	 * <p><strong>NOTE:</strong> &pi; is estimated to be 3.14159 for
	 * calculations. See {@link Shape#PI}.
	 */
	@Override
	public void calculate() {
		// Get radius
		radius = validateInput("\nWhat is the radius?\n");
		
		// Get height
		height = validateInput("\nWhat is the height?\n");
		
		// Calculate volume
		double base = PI * radius * radius;
		volume = (base * height) / 3;
	}

}
