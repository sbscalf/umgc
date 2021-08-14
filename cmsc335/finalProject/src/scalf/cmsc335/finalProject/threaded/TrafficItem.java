package scalf.cmsc335.finalProject.threaded;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.concurrent.locks.Lock;

import javax.swing.ImageIcon;

import scalf.cmsc335.finalProject.Synchronizer;

/**
 * 
 * Parent class of all Cars and TrafficLights.  Contains methods and variables
 * common to all.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public abstract class TrafficItem implements Runnable {
	// Member Variables
	protected Image image;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Synchronizer _sync;
	protected Lock _lock;
	
	/**
	 * Constructor. Initializes all variables.
	 * 
	 * @param x the starting horizontal position of this TrafficItem
	 * @param y the starting vertical position of this TrafficItem
	 * @param imageName the filepath for this TrafficItem's image
	 * @param sync the Synchronizer for this thread
	 */
	public TrafficItem(int x, int y, String imageName, Synchronizer sync) {
		this.x = x;
		this.y = y;
		loadImage(imageName);
		_sync = sync;
		_lock = sync.getLock();
	}
	
	/**
	 * Sets the graphical representation of this TrafficItem.
	 * Gets the width and height of the image, or defaults to 10 pixels.
	 * 
	 * @param imageName the filepath for this TrafficItem's image
	 */
	protected void loadImage(String imageName) {
		java.net.URL url = TrafficItem.class.getResource(imageName);
		try {
			image = new ImageIcon(url).getImage();
			width = image.getWidth(null);
			height = image.getHeight(null);
		} catch (Exception e) {
			image = null;
			width = 10;
			height = 10;
		}
	}
	
	/**
	 * Returns this TrafficItem's graphical representation
	 * @return Image for this TrafficItem
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Returns this TrafficItem's upper-left x-coordinate.
	 * 
	 * @return int of x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns this TrafficItem's upper-left y-coordinate.
	 * 
	 * @return int of y-coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the rectangular "hit-box" of this TrafficItem.
	 * 
	 * @return Rectangle of hit-box
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Checks if the Synchronizer has been paused. If paused, waits until
	 * notified. 
	 * 
	 * @throws InterruptedException
	 */
	protected void checkPaused() throws InterruptedException {
		while (_sync.isPaused()) {
			synchronized (_lock) {
				_lock.wait();
			}
		}
	}
}
