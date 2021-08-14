package scalf.cmsc335.finalProject.threaded;

import scalf.cmsc335.finalProject.Synchronizer;

/**
 * 
 * Provides methods and variables used by all Cars.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public abstract class Car extends TrafficItem {

	// Variables used by all Cars
	protected int velocity = 0;
	protected int id;
	protected volatile boolean cancelled = false;
	protected volatile boolean atRed = false;
	protected volatile boolean behindCar = false;
	
	/**
	 * Minimum Constructor.
	 * <p>Sets the velocity of the car between 100 and 1000. Unlike standard
	 * velocity, a higher number is slower.  This is because it is used as a
	 * length of time to sleep.  This gives the appearance of faster/slower
	 * moving cars. 
	 * 
	 * @param velocity the integral speed of this Car
	 * @param x the starting horizontal position of this Car
	 * @param y the starting vertical position of this Car
	 * @param imageName the filepath for this Car's image
	 * @param sync the Synchronizer for this thread
	 * @param id an arbitrary ID used for managing this thread
	 */
	public Car(
			int velocity, int x, int y,
			String imageName, Synchronizer sync, int id) {
		this(x, y, imageName, sync);
		this.id = id;
		if (velocity < 0)
			velocity = Math.abs(velocity);
		if (velocity < 100)
			this.velocity = 1000 - (velocity * 10);
		else
			this.velocity = 100;
		
	}
	
	/**
	 * Constructor required by TrafficItem.
	 * 
	 * @param x the starting horizontal position of this Car
	 * @param y the starting vertical position of this Car
	 * @param imageName the filepath for this Car's image
	 * @param sync the Synchronizer for this thread
	 */
	public Car(int x, int y, String imageName, Synchronizer sync) {
		super(x, y, imageName, sync);
	}
	
	/**
	 * Returns this Car's ID
	 * @return the ID of this Car
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Enables the internal cancelled boolean for this Car.
	 */
	public void cancel() {
		cancelled = true;
	}
	
	/**
	 * Sets whether this Car is at a red light.
	 * @param atRed boolean value whether or not at red light.
	 */
	public void atRed(boolean atRed) {
		this.atRed = atRed;
	}
	
	/**
	 * Sets whether this Car is behind another Car to avoid collisions.
	 * @param behindCar boolean value whether or not behind another Car.
	 */
	public void behindCar(boolean behindCar) {
		this.behindCar = behindCar;
	}
	
	/**
	 * Checks the internal atRed boolean.  If true, waits until notified.
	 * @throws InterruptedException
	 */
	private void checkAtRed() throws InterruptedException {
		while (atRed) {
			synchronized (this) {
				this.wait();
			}
		}
	}
	
	/**
	 * Checks the internal behindCar boolean.  If true, waits until notified.
	 * @throws InterruptedException
	 */
	private void checkBehindCar() throws InterruptedException {
		while (behindCar) {
			synchronized (this) {
				this.wait();
			}
		}
	}
	
	/**
	 * Moves the car.
	 */
	public abstract void move();
	
	/**
	 * The main process of this Thread.  Continuously loops until either
	 * cancelled or stopped.  This Thread will sleep longer for slow cars.
	 */
	@Override
	public void run() {
		while(!_sync.isStopped() || !cancelled) {
			try {
				checkPaused();
				checkAtRed();
				checkBehindCar();
				move();
				Thread.sleep(velocity);
			} catch (InterruptedException e) {
			}
		}
	}

}
