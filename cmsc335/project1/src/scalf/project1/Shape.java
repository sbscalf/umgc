package scalf.project1;

import java.util.Scanner;

/**
 * 
 * An abstract class that provides the common fields and methods of all Shapes.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">17 AUG 2020</p>
 * <ul>
 * <li>Created the {@code validateInput(String)} method for use in all Shape
 *   objects.
 *    <ul>
 *    <li>Since the area/volume for all Shapes is stored as a {@code double},
 *      I determined that it would be best to consolidate it in one method in
 *      the common ancestor class.</li>
 *    </ul>
 * </li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Shape.java
 *
 * @author Samuel Scalf
 * @date 16 AUG 2020
 * @updated 17 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public abstract class Shape {
	/**
	 * Constant value of &pi;, rounded to the fifth decimal place.
	 */
	static final double PI = 3.14159;
	/**
	 * Reads console input.
	 * Available to all Shape objects and Shape descendants.
	 */
	static  Scanner shapeInput = new Scanner(System.in);
	/**
	 * Used to distinguish between 2- and 3-dimensional Shapes.
	 */
	private int NumberofDimensions;
	
	/**
	 * Creates a new {@code Shape} with the specified number of dimensions.
	 * 
	 * @param NumberofDimensions Self-explanatory
	 */
	public Shape(int NumberofDimensions) {
		this.NumberofDimensions = NumberofDimensions;
	}
	
	/**
	 * Returns a deep-copy of the {@code int} value of {@code
	 * NumberofDimensions}. This is accomplished by first storing the value in
	 * a temporary variable and returning the temporary variable.
	 * 
	 * @return an {@code int} representing the number of dimensions
	 */
	public int getDimensions() {
		int output = this.NumberofDimensions;
		return output;
	}
	
	/**
	 * Calculates the calculated area or volume of the Shape. This method is
	 * polymorphic.
	 */
	public abstract void calculate();
	
	double validateInput(String prompt) {
		while (true) {
			try {
				System.out.println(prompt);
				String input = shapeInput.nextLine();
				double result = Double.parseDouble(input);
				return result;
			} catch (Exception e) {
				System.err.println(
					e.getClass().getSimpleName() +
					": " + "Please enter a decimal value.");
				System.out.println("Press \"ENTER\" to continue...");
				shapeInput.nextLine();
				continue;
			}
		}
	}
}
