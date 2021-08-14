package scalf.cmsc335.finalProject;

import scalf.cmsc335.finalProject.threaded.Car;

/**
 * 
 * Created to notify the TrafficContoller to remove a car.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public interface CarListener {
	public void removeCar(Car car);
}
