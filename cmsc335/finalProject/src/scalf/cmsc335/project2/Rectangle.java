package scalf.cmsc335.project2;

/**
 * 
 * Provides calculations related to Rectangles.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Rectangle}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Rectangle.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @updated 06 SEP 2020
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
		length = getInput("length");
		
		// Get Width
		width = getInput("width");
		
		// Calculate Area
		area = length * width;
	}

}
