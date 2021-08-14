package scalf.cmsc335.project1;

/**
 * 
 * An abstract class that provides additional fields for 2D Shapes.
 * 
 * <p><strong>Filename:</strong> ThreeDimensionalShape.java
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">17 AUG 2020</p>
 * <ul>
 * <li>Added the {@code toString()} method.</li>
 * </ul>
 *
 * @author Samuel Scalf
 * @date 16 AUG 2020
 * @updated 17 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public abstract class TwoDimensionalShape extends Shape {
	/**
	 * The amount of space taken up by a two-dimensional shape in the plane.
	 */
	double area;
	
	/**
	 * Default Constructor.
	 * Calls {@link Shape} constructor with an argument of '2', setting the
	 * {@code NumberofDimensions} to that value.
	 */
	public TwoDimensionalShape() {
		super(2);
	}
	
	/**
	 * Returns a deep-copy of the {@code double} value of {@code area}. This is 
	 * accomplished by first storing the value in a temporary variable and
	 * returning the temporary variable.
	 * 
	 * @return an {@code double} representing the area of the Shape.
	 */
	public double getArea() {
		double output = area;
		return output;
	}
	
	// public abstract void calculate() {} defined in concrete classes
	
	/**
	 * Displays information about the area of a {@code TwoDimensionalShape} or
	 * descendant.
	 *  
	 * @return a string representation of the area of this object
	 */
	@Override
	public String toString() {
		String result = String.format("\nThe area of the %s is %.2f.",
				this.getClass().getSimpleName(), area);
		return result;
	}
}
