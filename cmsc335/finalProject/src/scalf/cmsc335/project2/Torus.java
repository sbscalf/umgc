package scalf.cmsc335.project2;

import javax.swing.JOptionPane;

/**
 * 
 * Provides calculations related to Tori.
 * <p>This is a revised version of {@link scalf.cmsc335.project1.Torus}.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">06 SEP 2020</p>
 * <ul>
 * <li>Changed calls to {@code validateInput(String)} to
 * 		{@code getInput(String)}.</li>
 * <li>Added import for {@code javax.swing.JOptionPane}.</li>
 * <li>Modified {@code calculate()} to display a JOptionPane for errors instead 
 * 		of displaying the message in the console.</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Torus.java
 *
 * @author Samuel Scalf
 * @date 19 AUG 2020
 * @updated 06 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Torus extends ThreeDimensionalShape {
	/**
	 * The distance from the center of a slice to any point along its
	 * circumference. A slice is cross-section of the torus and is equivalent
	 * to a circle.
	 */
	double minorRadius;
	/**
	 * The distance from the center of the torus (in the "hole") to the center
	 * of a slice. The center of a slice can also be described as the halfway
	 * point between the inner and outer rings along a line drawn from the
	 * center of the torus.
	 */
	double majorRadius;
	
	/**
	 * Default Constructor.
	 * Simply calls the {@code ThreeDimensionalShape} constructor.
	 */
	public Torus() {
		super();
	}

	/**
	 * Calculates the volume of a torus.
	 * <br><strong>Formula:</strong> V = (&pi;r<sup>2</sup>)(2&pi;R);
	 * r = Minor Radius, R = Major Radius
	 * <p><strong>NOTE:</strong> &pi; is estimated to be 3.14159 for
	 * calculations. See {@link Shape#PI}.
	 */
	@Override
	public void calculate() {
		while (true) {
			// Get minor radius
			minorRadius = getInput("minor radius");
			
			// Get major radius
			majorRadius = getInput("major radius");
			
			if (majorRadius > minorRadius) {
				break;
			} else {
				String message = "The major radius must be larger than" +
					" the minor radius.";
				JOptionPane.showMessageDialog(null, message,
					"Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		}
			
		// Calculate volume
		double sliceArea = PI * minorRadius * minorRadius;
		double rotation = 2 * PI * majorRadius;
		volume = sliceArea * rotation;
	}

}
