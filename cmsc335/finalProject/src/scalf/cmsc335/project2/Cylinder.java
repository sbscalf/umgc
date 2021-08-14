package scalf.cmsc335.project2;

/**
 * 
 * Provides calculations related to Cylinders.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Cylinder}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Cylinder.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @updated 06 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Cylinder extends ThreeDimensionalShape {
	/**
	 * The distance from the center of the base to any point along its
	 * circumference. This is similar to {@link Circle#radius}. 
	 */
	double radius;
	/**
	 * The distance from one end of the cylinder to the other.
	 */
	double height;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code ThreeDimensionalShape} constructor.
	 */
	public Cylinder() {
		super();
	}

	/**
	 * Calculates the volume of a cylinder.
	 * <br><strong>Formula:</strong> V = &pi;r<sup>2</sup>h;
	 * r = radius, h = height
	 * <p><strong>NOTE:</strong> &pi; is estimated to be 3.14159 for
	 * calculations. See {@link Shape#PI}.
	 */
	@Override
	public void calculate() {
		// Get radius
		radius = getInput("radius");
		
		// Get height
		height = getInput("height");
		
		// Calculate volume
		volume = PI * radius * radius * height;
	}

}
