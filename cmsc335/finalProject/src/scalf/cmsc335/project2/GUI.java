package scalf.cmsc335.project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * The primary user interface.  This class contains all the elements required
 * for user interaction.
 * 
 * <p><strong>Filename:</strong> GUI.java
 *
 * @author Samuel Scalf
 * @date 05 SEP 2020
 * @school University of Maryland Global Campus
 * @prof Dr. Osama A. Morad, Ph.D.
 * @course CMSC 335 6380 Object-Oriented and Concurrent Programming (2208)
 *
 */
public class GUI extends JFrame implements ActionListener {
	/**
	 * Placeholder for the image of the selected Shape.
	 */
	private JLabel imageLbl;
	
	/**
	 * The actual image for the selected Shape. Used to set the {@code icon}
	 * property of {@code imageLbl}.
	 */
	private ImageIcon icon;
	
	/**
	 * Label for the measurement type, either Area or Volume in this program.
	 */
	private JLabel measureTypeLbl;
	
	/**
	 * The calculated amount of the area of volume of the selected Shape.
	 */
	private JLabel resultLbl;
	
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -2263622955303932232L;
	
	/**
	 * Default Constructor. Configures the window and adds the main JPanel.
	 */
	public GUI() {
		super("Shapes");
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(getMain());
	}
	
	/**
	 * Creates a layout for the interactive and output JPanels.
	 * 
	 * @return fully configured JPanel
	 */
	private JPanel getMain() {
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		// Configure Selection Panel
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		main.add(getTabs(), constraints);
		// Configure Output Panel
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		main.add(getOutputPanel(), constraints);
		return main;
	}
	
	/**
	 * User-interactive area of GUI. Tabs separate the different types of
	 * Shapes: Two- and Three-Dimensional. Updates the measurement type JLabel
	 * in the output panel to reflect the chosen shape type: area and volume,
	 * respectively. Resets the components of the output panel to their
	 * defaults, respective of the selected tab.
	 * 
	 * @return fully configured JTabbedPane
	 */
	private JTabbedPane getTabs() {
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("2D Shapes",get2dPanel());
		tabs.addTab("3D Shapes", get3dPanel());
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = tabs.getSelectedIndex();
				changeIcon("blank");
				if (index == 0) {
					measureTypeLbl.setText("Area");
				} else {
					measureTypeLbl.setText("Volume");
				}
				resultLbl.setText("0.00");
			}
		});
		return tabs;
	}
	
	/**
	 * Creates the buttons for the available 2D Shapes.
	 * 
	 * @return JPanel with buttons
	 */
	private JPanel get2dPanel() {
		String[] shapes2D = {
				"Circle","Rectangle","Square","Triangle","Trapezoid"};
		JPanel twoD = new JPanel(new GridBagLayout());
		twoD.setBorder(new EmptyBorder(4,4,4,4));
		JPanel buttonPanel = new JPanel(new GridLayout(2,2,20,20));
		for (String shape: shapes2D) {
			JButton button = new JButton(shape);
			button.setActionCommand(shape);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		twoD.add(buttonPanel);
		return twoD;
	}
	
	/**
	 * Creates the buttons for the available 3D Shapes.
	 * 
	 * @return JPanel with buttons
	 */
	private JPanel get3dPanel() {
		String[] shapes3D = {"Sphere","Cube","Cone","Cylinder","Torus"};
		JPanel threeD = new JPanel(new GridBagLayout());
		threeD.setBorder(new EmptyBorder(4,4,4,4));
		JPanel buttonPanel = new JPanel(new GridLayout(2,2,20,20));
		for (String shape: shapes3D) {
			JButton button = new JButton(shape);
			button.setActionCommand(shape);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		threeD.add(buttonPanel);
		return threeD;
	}
	
	/**
	 * Configures the layout and components for the output panel. Sets default
	 * values for the imageLbl, measureTypeLbl, and resultLbl.
	 * 
	 * @return fully configured JPanel
	 */
	private JPanel getOutputPanel() {
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.PAGE_AXIS));
		TitledBorder title = BorderFactory.createTitledBorder("Output");
		outputPanel.setBorder(title);
		imageLbl = getLabel("", false);
		outputPanel.add(imageLbl);
		measureTypeLbl = getLabel("Area", true);
		outputPanel.add(measureTypeLbl);
		outputPanel.add(Box.createRigidArea(new Dimension(0,10)));
		resultLbl = getLabel("0.00", false);
		outputPanel.add(resultLbl);
		changeIcon("blank");
		return outputPanel;
	}
	
	/**
	 * Creates a centered JLabel. An option to add an underline is provided with
	 * the {@code isUnderlined} parameter.
	 * 
	 * @param content The text to be displayed in the JLabel
	 * @param isUnderlined Whether or not to add an underline
	 * @return a centered JLabel with desired text with or without underline
	 */
	private JLabel getLabel(String content, boolean isUnderlined) {
		JLabel label = new JLabel(content);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		if (isUnderlined)
			label.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		return label;
	}
	
	/**
	 * Changes the icon of the {@code imageLbl} in the output panel.
	 * This could possibly throw an exception if the image is not located. The
	 * required image files are part of the source package. Since the only known
	 * effect of this is a blank {@code imageLbl} and the stack trace being
	 * output to the error stream, any exceptions are caught internally and the
	 * {@code imageLbl}'s icon is set to {@code null}. This prevents the error
	 * stream from being filled with expected "fails".
	 * 
	 * @param name The base name of the image file. This does not include the
	 * extension, which is expected to be ".png".
	 */
	private void changeIcon(String name) {
		try {
			String path = String.format("/images/%s.png", name);
			java.net.URL imgURL = GUI.class.getResource(path);
			icon = new ImageIcon(imgURL);
			imageLbl.setIcon(icon);
		} catch (Exception e) {
			imageLbl.setIcon(null);
		}
	}
	
	/**
	 * Performs GUI updates based on the clicked button.
	 */
	public void actionPerformed (ActionEvent e) {
		String name = e.getActionCommand();
		changeIcon(name);
		Shape shape = getShape(name);
		shape.calculate();
		resultLbl.setText(shape.toString());
	}
	
	/**
	 * Creates a new Shape instance of the selected Shape. A switch statement is
	 * used to achieve this, with the default returning a Circle object. While
	 * this could cause some "unexpected" results, it was better than returning
	 * {@code null}. In order for the default case to be invoked, the user would
	 * need to somehow select a button for a Shape not in the switch statement.
	 * 
	 * @param name the name of the selected Shape
	 * @return a new Shape instance of the selected Shape
	 */
	private Shape getShape(String name) {
		switch(name) {
			case "Circle": return new Circle();
			case "Rectangle": return new Rectangle();
			case "Square": return new Square();
			case "Triangle": return new Triangle();
			case "Trapezoid": return new Trapezoid();
			case "Sphere": return new Sphere();
			case "Cube": return new Cube();
			case "Cone": return new Cone();
			case "Cylinder": return new Cylinder();
			case "Torus": return new Torus();
			default: return new Circle();
		}
	}
}
