package scalf.cmsc335.finalProject;

import scalf.cmsc335.finalProject.threaded.Car;
import scalf.cmsc335.finalProject.threaded.TrafficLight;

/**
 * 
 * Provides methods required by any GUI that can be used by the
 * TrafficController.  Abstracts away the implementation of the TrafficView.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public interface TrafficView {
	public void addLight(TrafficLight light);
	public void reset();
	public void setViewListener(ViewListener a);
	public void addCar(Car car);
}
