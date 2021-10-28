import javax.swing.JFrame;

public class CMSC325Driver extends JFrame {

	public CMSC325Driver() {

		// Construct Class with Graphics Component
//		CMSC325EX1 myExample = new CMSC325EX1();
		CMSC325ScalfProject1 project1 = new CMSC325ScalfProject1();
		// Add to JFrame
		add(project1);
		// Set the Default Size and title
		setSize(400, 400);
		setTitle("CMSC 325 Scalf Project 1");

		// Frame Default to be able to closd
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center the Frame
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		CMSC325Driver myDriver = new CMSC325Driver();
		myDriver.setVisible(true);

	}
}