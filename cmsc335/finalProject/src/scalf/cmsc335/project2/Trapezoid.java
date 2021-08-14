package scalf.cmsc335.project2;

/**
 * 
 * Provides calculations related to Trapezoids.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Trapezoid}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Trapezoid.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @updated 06 SEP 2020
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
	 * Calculates the area of a trapezoid.
	 * <br><strong>Formula:</strong> A = ((a + b) * h) / 2;
	 * a = shorter parallel, b = longer parallel, h = height;
	 */
	@Override
	public void calculate() {
		// Get base a (shorter parallel)
		baseA = getInput("length of the shorter parallel");
		
		// Get base b (longer parallel)
		baseB = getInput("length of the longer parallel");
		
		// Get height
		height = getInput("height");
		
		// Calculate area
		area = ( (baseA + baseB) * height) / 2;
	}

}
