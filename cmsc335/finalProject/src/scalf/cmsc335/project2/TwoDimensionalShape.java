package scalf.cmsc335.project2;

/**
 * 
 * An abstract class that provides additional fields for 2D Shapes.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.TwoDimensionalShape}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Updated the toString method to return a String representation of the
 * 		area.</li>
 * </ul>
 * 
 * <p style="font-size:105%; font-weight: bold;">17 AUG 2020</p>
 * <ul>
 * <li>Added the {@code toString()} method.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> ThreeDimensionalShape.java
 *
 * @author Samuel Scalf
 * @date 16 AUG 2020
 * @updated 06 SEP 2020
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
	 * Converts the double value of the area to a String. Rounds to two decimal
	 * places.
	 *  
	 * @return a string representation of the area of this object
	 */
	@Override
	public String toString() {
		return String.format("%,.2f", area);
	}
}
