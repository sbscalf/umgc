package scalf.cmsc335.finalProject;

import java.util.concurrent.locks.Lock;

/**
 * 
 * Declares methods required by all objects used for synchronization in the
 * program.  Abstracts away the TrafficController class from the threads.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public interface Synchronizer {
	public boolean isPaused();
	public boolean isStopped();
	public Lock getLock();
}
