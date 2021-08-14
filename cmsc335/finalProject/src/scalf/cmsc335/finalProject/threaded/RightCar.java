package scalf.cmsc335.finalProject.threaded;

import scalf.cmsc335.finalProject.Synchronizer;

/**
 * 
 * Defines cars moving from left to right.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class RightCar extends Car {
	
	/**
	 * Minimum Constructor. Sets the image for the proper facing car.
	 * 
	 * @param velocity the integral speed of this Car
	 * @param sync the Synchronizer for this thread
	 * @param id an arbitrary ID used for managing this thread
	 */
	public RightCar(int velocity, Synchronizer sync, int id) {
		this(velocity, 0, 100, "/images/rightcar.png", sync, id);
	}

	/**
	 * Constructor required by Car.
	 * 
	 * @param velocity the integral speed of this Car
	 * @param x the starting horizontal position of this Car
	 * @param y the starting vertical position of this Car
	 * @param imageName the filepath for this Car's image
	 * @param sync the Synchronizer for this thread
	 * @param id an arbitrary ID used for managing this thread
	 */
	public RightCar(
			int velocity, int x, int y,
			String imageName, Synchronizer sync, int id) {
		super(velocity, x, y, imageName, sync, id);
	}

	/**
	 * Moves the car right 10 pixels.
	 */
	@Override
	public void move() {
		x += 10;
	}

}
