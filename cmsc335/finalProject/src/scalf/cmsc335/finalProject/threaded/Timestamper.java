package scalf.cmsc335.finalProject.threaded;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;

import scalf.cmsc335.finalProject.Synchronizer;
import scalf.cmsc335.finalProject.TimeListener;

/**
 * 
 * Notifies listener of the new time every second.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class Timestamper implements Runnable {
	// Member variables
	private final SimpleDateFormat sdf;
	private TimeListener timeListener;
	private Date date;
	private Synchronizer _sync;
	private Lock _lock;
	
	/**
	 * Constructor. Initializes all variables, except the date.
	 * 
	 * @param timeListener the object listening for updated timestamps
	 * @param sync the Synchronizer for this thread
	 */
	public Timestamper(TimeListener timeListener, Synchronizer sync) {
		this.timeListener = timeListener;
		_sync = sync;
		_lock = _sync.getLock();
		sdf = new SimpleDateFormat("EEE, d MMM yyy @ HH:mm:ss");
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
	
	/**
	 * Gets the current date.  Creates a timestamp and passes it to the
	 * TimeListener, if any.
	 */
	private void getCurrentTime() {
		date = new Date();
		String time = sdf.format(date);
		if (timeListener != null)
			timeListener.timeChanged(time);
	}
	
	/**
	 * The main process of this Thread.  Continuously loops once per second 
	 * until either cancelled or stopped.
	 */
	public void run() {
		while(!_sync.isStopped()) {
			try {
				checkPaused();
				getCurrentTime();
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}
