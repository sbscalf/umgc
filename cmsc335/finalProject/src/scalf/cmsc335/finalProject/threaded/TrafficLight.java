package scalf.cmsc335.finalProject.threaded;

import scalf.cmsc335.finalProject.Synchronizer;

/**
 * 
 * Simulation of a traffic light.  Changes between green, yellow, and red, while
 * staying on green the longest.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class TrafficLight extends TrafficItem {
	
	/**
	 * Used for checking current state of this TrafficLight.
	 */
	private enum Light { GREEN, YELLOW, RED	};
	
	// Member Variables
	private Light current;
	private double multiplier = 1;
	
	/**
	 * Full constructor. Calls constructor required by TrafficItem.
	 * 
	 * @param mult the timing offset of this TrafficLight
	 * @param x the starting horizontal position of this TrafficLight
	 * @param y the starting vertical position of this TrafficLight
	 * @param imageName the filepath for this TrafficLight's image
	 * @param sync the Synchronizer for this thread
	 */
	public TrafficLight(
			double mult, int x, int y, String imageName, Synchronizer sync) {
		this(x, y, imageName, sync);
		multiplier = mult;
	}
	
	/**
	 * Constructor required by TrafficItem. Sets the starting Light enum value.
	 * 
	 * @param x the starting horizontal position of this TrafficItem
	 * @param y the starting vertical position of this TrafficItem
	 * @param imageName the filepath for this TrafficLight's image
	 * @param sync the Synchronizer for this thread
	 */
	public TrafficLight(int x, int y, String imageName, Synchronizer sync) {
		super(x, y, imageName, sync);
		setLight(imageName);
	}
	
	/**
	 * Sets the Light value based on the image.
	 * 
	 * @param imageName the filepath for this TrafficLight's image
	 */
	private void setLight(String imageName) {
		if (imageName.contains("greenLight"))
			current = Light.GREEN;
		else if (imageName.contains("yellowLight"))
			current = Light.YELLOW;
		else
			current = Light.RED;
	}
	
	/**
	 * Changes the current Light and Image for this TrafficLight and waits for
	 * the delay.
	 * @throws InterruptedException
	 */
	private void changeLight() throws InterruptedException {
		long delay = 100;
		switch (current.name()) {
			case "GREEN":
				current = Light.YELLOW;
				loadImage("/images/yellowLight.png");
				delay = 1000;
				break;
			case "YELLOW":
				current = Light.RED;
				loadImage("/images/redLight.png");
				delay = 2000;
				break;
			case "RED":
				current = Light.GREEN;
				loadImage("/images/greenLight.png");
				delay = 4000;
				break;
		}
		delay *= multiplier;
		Thread.sleep(delay);
	}
	
	/**
	 * Returns whether or not the light is red.
	 * 
	 * @return boolean of whether Light is RED or not
	 */
	public boolean isRed() {
		return current == Light.RED;
	}

	/**
	 * The main process of this Thread.  Continuously loops until either
	 * cancelled or stopped. This checks for paused twice, because the long
	 * delays when changing the light were sometimes messing with the lights
	 * pausing properly.
	 */
	@Override
	public void run() {
		while (!_sync.isStopped()) {
			try {
				checkPaused();
				changeLight();
				checkPaused();
			} catch (InterruptedException e) {
			}
		}
	}

}
