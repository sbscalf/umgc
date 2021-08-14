package scalf.project2;

/**
 * 
 * Provides calculations related to Triangles.
 * <p>This is a revised version of {@link scalf.project1.Triangle}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Triangle.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @updated 06 SEP 2020
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
	 * Calculates the area of a triangle.
	 * <br><strong>Formula:</strong> A = &#189;bh; b = base, h = height
	 */
	@Override
	public void calculate() {
		// Get length of base
		base = getInput("length of the base");
		
		// Get the height
		height = getInput("height");
		
		// Calculate area
		area = 0.5 * base * height;
	}

}
