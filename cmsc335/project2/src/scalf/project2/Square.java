package scalf.project2;

/**
 * 
 * Provides calculations related to Squares.
 * <p>This is a revised version of {@link scalf.project1.Square}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Square.java
 *
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @updated 06 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Square extends TwoDimensionalShape {
	/**
	 * The length of any given side of the square.
	 */
	private double length;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code TwoDimensionalShape} constructor.
	 */
	public Square() {
		super();
	}

	/**
	 * Calculates the area of a square.
	 * <br><strong>Formula:</strong> A = l<sup>2</sup>; l = length of one side
	 */
	@Override
	public void calculate() {
		// Get length
		length = getInput("length of a side");
		
		// Calculate area
		area = length * length;
	}

}
