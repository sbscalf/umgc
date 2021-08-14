package scalf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import scalf.cmsc335.project1.*;

/**
 * A command line-driven program that calculates the area or volume of a
 * two-dimensional or three-dimensional shape, respectively (See 
 * <a href="{@docRoot}/scalf/project1/package-summary.html">scalf.package1</a>).
 * 
 * <p> Users are presented with a menu for selecting the different shapes. User
 * input will determine which shape is created or to exit the program.
 * 
 * <p style="font-size:120%; font-weight: bold;">Changes</p>
 * <p style="font-size:105%; font-weight: bold;">18 AUG 2020</p>
 * <ul>
 * <li>Modified the logic in the {@code getSelection()} method to use the length
 * of {@code choices} as the upper limit.
 *    <ul>
 *       <li>Since {@code choices} is shifted to align the entries with the menu
 *       number, the length of {@code choices} is equal to the last option
 *       (exit program), since "Exit the program" will always be the last
 *       menu option.</li>
 *    </ul>
 * </li>
 * <li>Modified the string for the last menu item to use the length of
 * {@code choices} as the number. All previous numbers are used in building the
 * earlier options.
 * </li>
 * </ul>
 * 
 * <p style="font-size:105%; font-weight: bold;">17 AUG 2020</p>
 * <ul>
 * <li>Added {@code runSelection()}, {@code getContinue()}, and {@code exit()}
 * methods.
 *    <ul>
 *    <li>{@code runSelection()} is a work in progress. Only case 1 is completed
 *     at this time.</li>
 *    </ul>
 * </li>
 * </ul>
 * 
 * <p style="font-size:105%; font-weight: bold;">16 AUG 2020</p>
 * <ul>
 * <li>Added documentation to fields and methods</li>
 * <li>Created the {@code showMenu()} and {@code getSelection()} methods to
 * reduce the line-count of {@code main}</li>
 * </ul>
 * 
 * <p><strong>Filename:</strong> Shapes.java
 * 
 * @author Samuel Scalf
 * @version 1.0
 * @date 13 AUG 2020
 * @updated 17 AUG 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Shapes {
	/**
	 * An array of the different available shapes used in the user interface.
	 * The first item is a blank string to line up the choices with the
	 * appropriate menu item. For instance, "Circle" is menu item 1 not 0.
	 */
	 private static String[] choices = {"",
			 "Circle","Rectangle","Square","Triangle","Trapezoid",
			 "Sphere","Cube","Cone","Cylinder","Torus"};
	 
	 private static Shape[] shapes = {null,
			 new Circle(), new Rectangle(), new Square(), new Triangle(),
			 	new Trapezoid(),
			 new Sphere(), new Cube(), new Cone(), new Cylinder(), new Torus()};
	 
	 private static Scanner cin = new Scanner(System.in);
	 
	/**
	 * Provides the starting point of the program. Makes calls to other methods
	 * and/or classes as necessary.
	 * @param args {@code String} array of command line arguments
	 */
	public static void main(String[] args) {
		while(true) {
			try {
				showMenu();
				getSelection();
				if (!getContinue()) {
					exit();
				}
			} catch (Exception e) {
				System.out.println();
				System.err.println(
					e.getClass().getSimpleName() +
					": " + e.getMessage());
				System.out.println("\nPress \"ENTER\" to continue...\n");
				cin.nextLine();
				continue;
			}
		}
	}
	
	/**
	 * Displays the user selection menu.
	 */
	private static void showMenu() {
		System.out.println(
				"\n*********Welcome to the Java OO Shapes Program*********" +
				"\n\nSelect from the menu below:\n");
		for (int i = 1; i < choices.length; i++) {
			System.out.print("\t");
			if (i < 10)
				System.out.print(" ");
			System.out.printf("%d" + ". Construct a %s%n",
					i, choices[i]);
		}
		System.out.printf("\t%d. Exit the program\n\n", choices.length);
	}
	
	/**
	 * Obtains and validates the user input.
	 * 
	 * @throws Exception 
	 * <br><ul>
	 * <li>{@code InvalidSelection} - User input was either not within the
	 * desired integer range or was not an integer.</li>
	 * <li>{@code InstantiationException} - Temporary for Testing purposes.</li>
	 * </ul>
	 */
	private static void getSelection() throws Exception {
		try {
			String input = cin.nextLine();
			int selection = Integer.parseInt(input);
			if (selection < 1 || selection > choices.length) {
				throw new InvalidSelection(1,choices.length);
			} 
			if (selection == choices.length)
				exit();
			else
				System.out.printf("\nYou have selected a %s.\n",
						choices[selection]);
				runSelection(selection);
		} catch (Exception e) {
			throw new InvalidSelection(1,choices.length);
		}
	}
	
	/**
	 * Creates the appropriate Shape depending on user selection.
	 * 
	 * @param selection Validated user-selection of menu item
	 */
	private static void runSelection(int selection) {
		Shape currentShape = shapes[selection];
		currentShape.calculate();
		System.out.println(currentShape);
	}
	
	/**
	 * Obtains and validates whether the user wants to continue.
	 * 
	 * @return {@code boolean} representation of user choice 
	 */
	private static boolean getContinue() {
		System.out.println("\nWould you like to continue? (Y or N)\n");
		while (true) {
			String input = cin.nextLine().toLowerCase();
			Character first = input.charAt(0);
			if (first.equals('y')) {
				return true;
			} else if (first.equals('n')) {
				return false;
			} else {
				System.out.println(
						"\nSorry I do not understand. Enter Y or N\n");
				continue;
			}
		}
	}

	/**
	 * Outputs a message and exits the program.
	 * Output message includes the current date and time.
	 */
	private static void exit() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d 'at' H:mm a");
		System.out.printf(
				"\nThanks for using the program. Today is %s%n%n",
				sdf.format(date));
		System.exit(0);
	}
}
