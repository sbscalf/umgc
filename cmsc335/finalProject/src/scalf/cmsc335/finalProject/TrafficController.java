package scalf.cmsc335.finalProject;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;

import scalf.cmsc335.finalProject.threaded.Car;
import scalf.cmsc335.finalProject.threaded.LeftCar;
import scalf.cmsc335.finalProject.threaded.RightCar;
import scalf.cmsc335.finalProject.threaded.Timestamper;
import scalf.cmsc335.finalProject.threaded.TrafficLight;

/**
 * 
 * The thread manager of the program. This class communicates with the other
 * objects in the program to handle the start, suspension, resumption, and
 * stopping of threads.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class TrafficController
implements Synchronizer, ViewListener, CarListener {
	
	/**
	 * Used by threads to obtain common lock without locking this entire object.
	 */
	private Lock lock;
	
	// Thread-safe booleans to track the state of the program
	private volatile boolean paused = true;
	private volatile boolean stopped = true;
	private volatile boolean started = false;
	
	/**
	 * The primary thread manager.
	 */
	private ExecutorService executor;
	
	/**
	 * All non-Car tasks, such as lights and timestampers
	 */
	private ArrayList<Runnable> tasks;
	
	/**
	 * Car threads.  This was separated to better handle the removal of cars.
	 */
	private ArrayList<Car> cars;

	/**
	 *  Used to alternate between left and right Cars
	 */
	private boolean leftCar = false;
	
	/**
	 * The GUI for the program.
	 */
	private TrafficView view;
	
	/**
	 * Default Constructor.  Initializes the lock and data structures.
	 * 
	 * @param view The GUI for the program.
	 */
	public TrafficController(TrafficView view) {
		lock = new ReentrantLock();
		this.view = view;
		tasks = new ArrayList<Runnable>();
		cars = new ArrayList<Car>();
	}

	/**
	 * @return boolean whether paused or not
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * @return boolean whether stopped or not
	 */
	public boolean isStopped() {
		return stopped;
	}

	/**
	 * @return lock This object's stand-alone lock.
	 */
	public Lock getLock() {
		return lock;
	}
	
	/**
	 * Creates, but does not start, threads.
	 * This will clear out any tasks, if they exist, then get the lights and
	 * add a Timestamper.
	 */
	private void createTasks() {
		if (tasks.size() > 0)
			tasks.clear();
		createLights();
		tasks.add(new Timestamper((TimeListener)view, this));
	}
	
	/**
	 * Creates the traffic lights.  These lights are added to both the
	 * Runnable ArrayList and the TrafficView.
	 */
	private void createLights() {
		TrafficLight light = newLight(105);
		tasks.add(light);
		view.addLight(light);
		light = newLight(345);
		tasks.add(light);
		view.addLight(light);
		light = newLight(585);
		tasks.add(light);
		view.addLight(light);
	}
	
	/**
	 * Creates a traffic light starting on red with an offset time for changing
	 * lights.
	 * 
	 * @param x The horizontal position of the light
	 * @return a new TrafficLight object
	 */
	private TrafficLight newLight(int x) {
		String light = "/images/redLight.png";
		Random random = new Random();
		double multiplier = (random.nextInt(5) + 8) * 0.1;
		return new TrafficLight(multiplier, x, 55, light, this);
	}
	
	/**
	 * Creates and starts tasks, if not already started. Sets the stopped and
	 * paused values to false.
	 * <p>A CachedThreadPool was used to allow variable numbers of cars.
	 */
	private void start() {
		if (!started) {
			started = true;
			stopped = paused = false;
			executor = Executors.newCachedThreadPool();
			createTasks();
			for (Runnable task : tasks)
				executor.execute(task);
		}
	}
	
	/**
	 * Toggles the paused boolean and notifies all threads waiting on lock.
	 */
	private void togglePause() {
		paused = !paused;
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	/**
	 * Resets the controller. Sets booleans to default value, shuts down the
	 * CachedThreadPool, clears the data structures, and tells the TrafficView
	 * to reset, as well.
	 */
	private void stop() {
		if (started) {
			stopped = paused = true;
			started = false;
			executor.shutdownNow();
			tasks.clear();
			cars.clear();
			view.reset();
		}
	}
	
	/**
	 * Creates and starts a new Car thread with random velocity.
	 * Cars generated alternate between LeftCar and RightCar.
	 */
	private void addCar() {
		Car car;
		int id = cars.size();
		Random random = new Random();
		int velocity = (random.nextInt(4) + 6) * 10;
		if (leftCar)
			car = new LeftCar(velocity, this, id);
		else
			car = new RightCar(velocity, this, id);
		view.addCar(car);
		cars.add(car);
		executor.execute(car);
		leftCar = !leftCar;
	}
	
	/**
	 * Cancels and removes the specific car from the ArrayList.
	 * <p>It does not matter if the thread takes a moment longer to fully stop, 
	 * because a CachedThreadPool is being used. The Car will be removed from 
	 * the ThreadPool as soon as it finishes.
	 * 
	 * @param car the car to be removed
	 */
	public void removeCar(Car car) {
		cars.get(car.getId()).cancel();
		cars.remove(car.getId());
	}
	
	/**
	 * Performs the action for the relevant JButton.
	 * 
	 * @param e the JButton pressed in the TrafficView
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			switch (((JButton)e.getSource()).getText()) {
				case "START":
					this.start();
					break;
				case "PAUSE": case "RESUME":
					this.togglePause();
					break;
				case "STOP":
					this.stop();
					break;
				case "ADD CAR":
					this.addCar();
					break;
				default:
					break;
			}
		}
	}

}
