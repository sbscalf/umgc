package scalf.cmsc335.project2;

/**
 * 
 * Provides calculations related to Cubes.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Cube}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Cube.java
 * 
 * @author Samuel Scalf
 * @date 18 AUG 2020
 * @updated 06 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Cube extends ThreeDimensionalShape {
	/**
	 * The length of any given edge.
	 * Each surface of a cube is a square, so all edges are equally long.
	 */
	private double length;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code ThreeDimensionalShape} constructor.
	 */
	public Cube() {
		super();
	}

	/**
	 * Calculates the volume of a cube.
	 * <br><strong>Formula:</strong> V = l<sup>3</sup>; l = length of an edge.
	 */
	@Override
	public void calculate() {
		// Get Length of Edge
		length = getInput("length of an edge");
		
		// Calculate volume
		volume = length * length * length;
	}

}
