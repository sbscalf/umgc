package scalf.project2;

/**
 * 
 * Provides calculations related to Spheres.
 * <p>This is a revised version of {@link scalf.project1.Sphere}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Sphere.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @updated 06 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Sphere extends ThreeDimensionalShape {
	/**
	 * The distance from the center of the sphere to any point along its
	 * surface.
	 */
	private double radius;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code ThreeDimensionalShape} constructor.
	 */
	public Sphere() {
		super();
	}

	/**
	 * Calculates the volume of a sphere.
	 * <br><strong>Formula:</strong> V = (4&pi;r<sup>3</sup>)/3; r = radius
	 * <p><strong>NOTE:</strong> &pi; is estimated to be 3.14159 for
	 * calculations. See {@link Shape#PI}.
	 */
	@Override
	public void calculate() {
		// Get radius
		radius = getInput("radius");
		
		// Calculate volume
		double r3 = radius * radius * radius;
		volume = (4 * PI * r3) / 3;
	}

}
