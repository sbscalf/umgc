package scalf.cmsc335.finalProject;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import scalf.cmsc335.finalProject.threaded.Car;
import scalf.cmsc335.finalProject.threaded.LeftCar;
import scalf.cmsc335.finalProject.threaded.RightCar;
import scalf.cmsc335.finalProject.threaded.TrafficLight;

/**
 * 
 * The GUI for the program that displays all the graphical elements of the
 * program.
 * <p>This class implements multiple listeners to update graphical elements,
 * such as traffic lights and timestamps.  To manage the state of the control
 * buttons, an internal enum was used.
 *
 * @author Samuel Scalf
 * @date 10 OCT 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class View
extends JFrame
implements TrafficView, TimeListener, ActionListener {
	/**
	 * Serialization for JFrame.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Available states of the program.  This is mainly used to manage the state
	 * of the control buttons.
	 */
	private enum State { STOPPED, RUNNING, PAUSED };
	
	// Components of GUI used by multiple methods and subclasses.
	private Container mainPane;
	private TrafficPanel trafficPanel;
	protected JButton startBtn, pauseBtn, stopBtn, addCarBtn, exitBtn;
	private JScrollPane scrollbar;
	private JTextArea timestamps;
	private ViewListener listener;
	private State state = State.STOPPED;
	
	/**
	 * Defines how many cars can be added to GUI at any given time.
	 */
	private static final int MAXIMUM_CARS = 3;
	/**
	 * Keeps track of how many cars are currently in the GUI.
	 */
	private static int numberOfCars = 0;
	
	/**
	 * Default Constructor. Sets basic window options
	 */
	public View() {
		super("Traffic Simulator 2020");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	/**
	 *  Calls methods to fill and display the GUI.
	 */
	public void start() {
		addPanels();
		pack();
		setVisible(true);
	}
	
	/**
	 * Adds the panels by calling respective methods.
	 */
	private void addPanels() {
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.add(controlPanel());
		rightPanel.add(trafficPanel());
		
		mainPane = getContentPane();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.LINE_AXIS));
		mainPane.add(timestampPanel());
		mainPane.add(rightPanel);
	}
	
	/**
	 * Creates elements for the timestamp panel area.
	 * 
	 * @return a scrollable text area in a JPanel
	 */
	private JPanel timestampPanel() {
		JPanel timestampPanel = new JPanel();
		timestampPanel.setPreferredSize(new Dimension(215, 100));
		timestampPanel.setMinimumSize(new Dimension(215, 100));
		timestampPanel.setLayout(
			new BoxLayout(timestampPanel, BoxLayout.PAGE_AXIS));
		TitledBorder title = BorderFactory.createTitledBorder("Timestamps");
		timestampPanel.setBorder(title);
		timestamps = new JTextArea();
		scrollbar = new JScrollPane(timestamps);
		timestampPanel.add(scrollbar);
		return timestampPanel;
	}
	
	/**
	 * Creates elements for the control panel area.
	 * 
	 * @return the control buttons for the program in a JPanel
	 */
	private JPanel controlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		TitledBorder title = BorderFactory.createTitledBorder("Controls");
		controlPanel.setBorder(title);
		
		initializeButtons();
		
		controlPanel.add(startBtn);
		controlPanel.add(pauseBtn);
		controlPanel.add(stopBtn);
		controlPanel.add(addCarBtn);
		controlPanel.add(exitBtn);
		return controlPanel;
	}
	
	/**
	 * Sets the Button names and default state.
	 */
	private void initializeButtons() {
		startBtn = new JButton("START");
		pauseBtn = new JButton("PAUSE");
		pauseBtn.setEnabled(false);
		stopBtn = new JButton("STOP");
		stopBtn.setEnabled(false);
		addCarBtn = new JButton("ADD CAR");
		addCarBtn.setEnabled(false);
		exitBtn = new JButton("EXIT");
		addButtonListeners();
	}
	
	/**
	 * Adds relevant ActionListeners to the respective buttons.
	 */
	private void addButtonListeners() {
		startBtn.addActionListener(listener);
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(listener);
		pauseBtn.addActionListener(this);
		stopBtn.addActionListener(listener);
		stopBtn.addActionListener(this);
		addCarBtn.addActionListener(trafficPanel);
		addCarBtn.addActionListener(listener);
		addCarBtn.addActionListener(this);
		exitBtn.addActionListener(this);
	}
	
	/**
	 * Initializes the traffic panel.  While this method does not do much, I
	 * separated it to match the other panels.
	 * 
	 * @return a new TrafficPanel.
	 */
	private JPanel trafficPanel() {
		trafficPanel = new TrafficPanel();
		return trafficPanel;
	}
	
	/**
	 * Sets the GUI's listener, which can listen to ActionEvents.
	 */
	public void setViewListener(ViewListener a) {
		listener = a;
	}
	
	/**
	 * Changes the current state of the program. Exits the program if "EXIT" is
	 * pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "START":
				state = State.RUNNING;
				break;
			case "PAUSE": case "RESUME":
				if (state == State.RUNNING) {
					state = State.PAUSED;
					pauseBtn.setText("RESUME");
					break;
				}
				if (state == State.PAUSED) {	
					state = State.RUNNING;
					pauseBtn.setText("PAUSE");
					break;
				}
			case "STOP":
				state = State.STOPPED;
				pauseBtn.setText("PAUSE");
			case "ADD CAR":
				break;
			case "EXIT":
				System.exit(0);
		}
		toggleButtons();
	}
	
	/**
	 * Enables and disables buttons depending on the current state of the
	 * program.
	 */
	private void toggleButtons() {
		switch (state) {
			case STOPPED:
				startBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				stopBtn.setEnabled(false);
				addCarBtn.setEnabled(false);
				break;
			case RUNNING:
				startBtn.setEnabled(false);
				pauseBtn.setEnabled(true);
				stopBtn.setEnabled(true);
				addCarBtn.setEnabled(true);
				break;
			case PAUSED:
				startBtn.setEnabled(false);
				pauseBtn.setEnabled(true);
				stopBtn.setEnabled(true);
				addCarBtn.setEnabled(false);
				break;
		}
	}
	
	/**
	 * Updates the timestamp panel. Appends the timestamp to the end of the
	 * JTextArea.  Scrolls to the bottom of the area so the most recent
	 * timestamp is visible.
	 * <p>Required by TimeListener
	 * 
	 * @param timestamp The current date and time in a String.
	 */
	public void timeChanged(String timestamp) {
		timestamps.append(timestamp);
		timestamps.append("\n");
		JScrollBar vertical = scrollbar.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
	
	/**
	 * Adds a traffic light to the traffic panel.
	 * <p>Required by TrafficView
	 * 
	 * @param light the TrafficLight to be added to the GUI
	 */
	public void addLight(TrafficLight light) {
		trafficPanel.addLight(light);
	}
	
	/**
	 * Adds a car to the traffic panel.
	 * 
	 * @param car the Car to be added to the GUI
	 */
	public void addCar(Car car) {
		trafficPanel.addCar(car);
	}
	
	/**
	 * Sets all values back to default.
	 */
	public void reset() {
		trafficPanel.reset();
		timestamps.setText("");
		numberOfCars = 0;
	}
	
	/**
	 * 
	 * Nested class to manage the traffic elements.
	 *
	 * @author Samuel Scalf
	 * @date 10 OCT 2020
	 * @school University of Maryland Global Campus
	 * @prof Dr. Osama A. Morad, Ph.D.
	 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
	 *
	 */
	class TrafficPanel extends JPanel implements ActionListener {
		/**
		 * Serialization for JPanel.
		 */
		private static final long serialVersionUID = 1L;

		// Components of this TrafficPanel used by multiple methods.
		private BufferedImage background;
		private Dimension dimension;
		private int width, height;
		private ArrayList<TrafficLight> lights;
		private List<Car> cars;
		private Timer timer;
		private CarListener carListener;

		/**
		 * Default Constructor. Sets background, initializes data structures,
		 * and starts a timer.
		 */
		TrafficPanel() {
			java.net.URL path =
					this.getClass().getResource("/images/background.png");
			try {
				background = ImageIO.read(path);
				width = background.getWidth();
				height = background.getHeight();
				dimension = new Dimension(width, height);
			} catch (Exception e) {
				background = null;
				dimension = null;
			}
			lights = new ArrayList<TrafficLight>();
			cars = Collections.synchronizedList(new ArrayList<Car>());
			timer = new Timer(15, this);
			setFocusable(true);
			timer.start();
		}

		/**
		 * Sets the CarListener responsible for removing car threads.
		 * 
		 * @param c the CarListener
		 */
		public void setCarListener(CarListener c) {
			carListener = c;
		}

		/**
		 * Adds a TrafficLight to the TrafficLight ArrayList.
		 * 
		 * @param light TrafficLight to be added to the GUI.
		 */
		public void addLight(TrafficLight light) {
			lights.add(light);
		}

		/**
		 * Adds a car to the Car ArrayList iff the number of cars is less than
		 * the maximum allowed.
		 * <p>While it is not expected to be encountered, a
		 * message will appear to inform the user the maximum number of cars
		 * has been reached.  This would only happen if the user is somehow able
		 * to click "ADD CAR" before it gets disabled.
		 * 
		 * @param car The Car to be added to the GUI.
		 */
		public void addCar(Car car) {
			if (numberOfCars < MAXIMUM_CARS) {
				cars.add(car);
				numberOfCars++;
				if (numberOfCars >= MAXIMUM_CARS)
					addCarBtn.setEnabled(false);
			} else {
				 JOptionPane.showMessageDialog(
					 this, "There can be no more than 3 cars.",
					 "Too Many Cars", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		/**
		 * Removes the car from the GUI.
		 * <p> The Car object is removed from the ArrayList, the number of cars
		 * is decreased, and, if the number is less than the maximum, enables
		 * the "ADD CAR" button.
		 * <p> If a carListener has been added, it is informed of the removal.
		 * 
		 * @param car The Car that has gone out of the panel.
		 */
		private void removeCar(Car car) {
			if (carListener != null)
				carListener.removeCar(car);
			cars.remove(car);
			numberOfCars--;
			if (numberOfCars < MAXIMUM_CARS)
				addCarBtn.setEnabled(true);
		}

		/**
		 * Clears the ArrayLists for lights and cars.
		 */
		public void reset() {
			lights.clear();
			cars.clear();
		}

		/**
		 * Displays the traffic elements. This first draws the background,
		 * checks for collisions, then draws the updated traffic.
		 * 
		 * @param g The panel's Graphics
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (background != null) {
				int x = (getWidth() - width) / 2;
				int y = (getHeight() - height) / 2;
				g.drawImage(background, x, y, this);
			}
			try { checkCollisions(); } catch (Exception e) {}
			drawTraffic(g);
		}

		/**
		 * Checks the positioning of cars compare to the panel, lights, and
		 * other cars.  Removes cars that have left the panel.
		 */
		private void checkCollisions() {
			for (Car car : cars) {
				if (car.getX() < 0 || car.getX() > width)
					removeCar(car);
				else {
					checkLights(car);
					checkCars(car);
				}
			}
		}

		/**
		 * If a car is at a red light, stops the car until the light changes.
		 * 
		 * @param car The car under collision inspection.
		 */
		private void checkLights(Car car) {
			Rectangle carRect = car.getBounds();
			for (TrafficLight light : lights) {
				Rectangle lightRect = light.getBounds();
				Rectangle carR, lightR;
				if (car instanceof LeftCar) {
					carR = new Rectangle(
							carRect.x - 5, carRect.y, 
							10, carRect.height);
					lightR = new Rectangle(
							lightRect.x + lightRect.width - 5, lightRect.y, 
							10, lightRect.height);
				} else {
					carR = new Rectangle(
							carRect.x + carRect.width - 5, 
							carRect.y, 10, carRect.height);
					lightR = new Rectangle(
							lightRect.x - 5, 
							lightRect.y, 10, lightRect.height);
				}
				synchronized (car) {
					if (carR.intersects(lightR)) {
						if (light.isRed())
							car.atRed(true);
						else
							car.atRed(false);
					}
					car.notify();
				}
			}
		}
		
		/**
		 * If a car is behind another car, stops it from passing through.
		 * Cars with higher velocity end up going slower if behind a slower car.
		 * 
		 * @param car The car under collision inspection
		 */
		private void checkCars(Car car) {
			Rectangle carRect = car.getBounds();
			boolean behindCar = false;
			for (Car two : cars) {
				Rectangle twoRect = two.getBounds();
				if (!two.equals(car)) {
					if (car instanceof LeftCar &&
							two instanceof LeftCar) {
						if (carRect.x > twoRect.x) {
							if (carRect.intersects(twoRect))
								behindCar = true;
						} else {
							behindCar = false;
						}
					} else if (car instanceof RightCar &&
							two instanceof RightCar) {
						if (carRect.x < twoRect.x) {
							if (carRect.intersects(twoRect))
								behindCar = true;
						} else {
							behindCar = false;
						}
					}
					synchronized (car) {
						car.behindCar(behindCar);
						car.notify();
					}
				}
			}
		}

		/**
		 * Draws the current state of lights and cars.
		 * 
		 * @param g The panel's Graphics
		 */
		private void drawTraffic(Graphics g) {
			for (TrafficLight light : lights)
				g.drawImage(light.getImage(), light.getX(), light.getY(), this);
			for (Car car : cars)
				g.drawImage(car.getImage(), car.getX(), car.getY(), this);
		}

		/**
		 * Returns the dimensions of the background image, if present.
		 * Otherwise, it returns JPanel's defaults.
		 * 
		 * @return the preferred dimensions of the panel
		 */
		@Override
		public Dimension getPreferredSize() {
			if (dimension == null)
				return super.getPreferredSize();
			else
				return dimension;
		}

		/**
		 * Returns the dimensions of the background image, if present.
		 * Otherwise, it returns JPanel's defaults.
		 * 
		 * @return the minimum dimensions of the panel
		 */
		@Override
		public Dimension getMinimumSize() {
			if (dimension == null)
				return super.getPreferredSize();
			else
				return dimension;
		}

		/**
		 * Repaints this JPanel every time the timer updates this JPanel.
		 * 
		 * @param e The event sent to this ActionListener
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	} // end of TrafficPanel class

} // end of View class
