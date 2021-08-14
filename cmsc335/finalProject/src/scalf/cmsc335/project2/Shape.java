package scalf.cmsc335.project2;

import javax.swing.JOptionPane;

/**
 * 
 * An abstract class that provides the common fields and methods of all Shapes.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Shape}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">05 SEP 2020</p>
 * <ul>
 * <li>Removed the Scanner import and field.</li>
 * <li>Changed validateInput(String) to getInput(String).</li>
 * <li>Updated getInput to utilize JOptionPanes instead of the console.</li>
 * </ul>
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
 * @updated 05 SEP 2020
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
	 * Used to distinguish between 2- and 3-dimensional Shapes.
	 */
	private int numberOfDimensions;
	
	/**
	 * Creates a new {@code Shape} with the specified number of dimensions.
	 * 
	 * @param numberOfDimensions Self-explanatory
	 */
	public Shape(int numberOfDimensions) {
		this.numberOfDimensions = numberOfDimensions;
	}
	
	/**
	 * Returns a deep-copy of the {@code int} value of {@code
	 * NumberofDimensions}. This is accomplished by first storing the value in
	 * a temporary variable and returning the temporary variable.
	 * 
	 * @return an {@code int} representing the number of dimensions
	 */
	public int getDimensions() {
		int output = this.numberOfDimensions;
		return output;
	}
	
	/**
	 * Calculates the calculated area or volume of the Shape. This method is
	 * polymorphic.
	 */
	public abstract void calculate();
	
	/**
	 * Prompts the user for a value.  This loops until a double value is
	 * entered. JOptionPanes are used for requesting input and displaying an
	 * error in the event invalid input is entered.
	 * 
	 * @param variable The name of the requested variable, like radius.
	 * @return double value of user input
	 */
	double getInput(String variable) {
		while (true) {
			String prompt = String.format("What is the %s?", variable);
			String input = JOptionPane.showInputDialog(prompt);
			try {
				double result = Double.parseDouble(input);
				return result;
			} catch (Exception e) {
				String message = String.format(
					"\"%s\" is not a decimal value." +
						"%nPlease enter a decimal value",
					input);
				JOptionPane.showMessageDialog(null, message,
					"Invalid Input", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}
	}
}
