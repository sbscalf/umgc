

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Modified by Samuel Scalf
 * @author jim
 */
public class CMSC325Animate extends JPanel {

    // A counter that increases by one in each frame.
    private int frameNumber;
    // The time, in milliseconds, since the animation started.
    private long elapsedTimeMillis;
    // This is the measure of a pixel in the coordinate system
    // set up by calling the applyLimits method.  It can be used
    // for setting line widths, for example.
    private float pixelSize;

    static int translateX = 0;
    static int translateY = 0;
    static double rotation = 0.0;
    static double scaleX = 1.0;
    static double scaleY = 1.0;
    PixelImage myImages = new PixelImage();
    BufferedImage pikachu = myImages.getImage(PixelImage.pikachu);
    BufferedImage alpaca = myImages.getImage(PixelImage.alpaca);
    BufferedImage noodles = myImages.getImage(PixelImage.noodles);
    BufferedImage triforce = myImages.getImage(PixelImage.triforce);
    BufferedImage megaman = myImages.getImage(PixelImage.megaman);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window;
        window = new JFrame("Java Animation");  // The parameter shows in the window title bar.
        final CMSC325Animate panel = new CMSC325Animate(); // The drawing area.
        window.setContentPane(panel); // Show the panel in the window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
        window.pack();  // Set window size based on the preferred sizes of its contents.
        window.setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth()) / 2,
                (screen.height - window.getHeight()) / 2);
        Timer animationTimer;  // A Timer that will emit events to drive the animation.
        final long startTime = System.currentTimeMillis();
        // Taken from AnimationStarter
        // Modified to change timing and allow for recycling
        animationTimer = new Timer(1600, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	panel.frameNumber = (panel.frameNumber + 1) % 5;
                panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
                panel.repaint();
            }
        });
        window.setVisible(true); // Open the window, making it visible on the screen.
        animationTimer.start();  // Start the animation running.
    }

    public CMSC325Animate() {
        // Size of Frame
        setPreferredSize(new Dimension(800, 600));
    }

 // This is where all of the action takes place
    // Code taken from AnimationStarter.java but modified to add the specific Images
    // Also added looping structure for Different transformations
    protected void paintComponent(Graphics g) {

        /* First, create a Graphics2D drawing context for drawing on the panel.
         * (g.create() makes a copy of g, which will draw to the same place as g,
         * but changes to the returned copy will not affect the original.)
         */
        Graphics2D g2 = (Graphics2D) g.create();

        /* Turn on antialiasing in this graphics context, for better drawing.
         */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Fill in the entire drawing area with white.
         */
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight()); // From the old graphics API!

        /* Here, I set up a new coordinate system on the drawing area, by calling
         * the applyLimits() method that is defined below.  Without this call, I
         * would be using regular pixel coordinates.  This function sets the value
         * of the global variable pixelSize, which I need for stroke widths in the
         * transformed coordinate system.
         */
        // Controls your zoom and area you are looking at
        applyWindowToViewportTransformation(g2, -130, 150, 130, -60, true);
        
        /*
         * Required transformations:
         * a. Translate -10 in x direction, Translate +12 in the y direction.
         * b. Rotate 55 degrees counter clockwise.
         * c. Rotate 75 clockwise
         * d. Scale 3 times for the x component, scale 1.5 times for the y component
         */

        AffineTransform savedTransform = g2.getTransform();
        System.out.println("Frame is " + frameNumber);
        switch (frameNumber) {
    		// First transformation: translate -10 in x, +12 in y
        	case 1:
                translateX += -10;
                translateY += 12;
                break;
            // Second transformation: Rotate 55 degrees counter clockwise
        	case 2:
                rotation += -55 * Math.PI / 180.0;
                break;
            // Third transformation: Rotate 75 degrees clockwise
        	case 3:
                rotation += 75 * Math.PI / 180.0;
                break;
            // Fourth transformation: Scale 3 times X, 1.5 times Y
            case 4:
            	scaleX *= 3;
            	scaleY *= 1.5;
            	break;
            // Initial State
            default:
            	translateX = 0;
        		translateY = 0;
        		scaleX = 1.0;
        		scaleY = 1.0;
        		rotation = 0;
        		break;
        } // End switch
        
        
        int offset = 60;
        
        // Alpaca
        g2.translate(translateX, translateY); // Move image.
        g2.translate(-offset, -offset); // To offset translate again
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(alpaca, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Megaman
        g2.translate(translateX, translateY); // Move image.
        g2.translate(-offset, offset); // To offset translate again
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(megaman, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Triforce
        g2.translate(translateX, translateY); // Move image.
        g2.translate(0,0); // To offset translate again
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(triforce, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Pikachu
        g2.translate(translateX, translateY); // Move image.
        g2.translate(offset, -offset); // To offset translate again
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(pikachu, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Noodles
        g2.translate(translateX, translateY); // Move image.
        g2.translate(offset, offset); // To offset translate again
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(noodles, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);

// You can add more shapes/images as needed
// 
        
       

    }

    // Method taken directly from AnimationStarter.java Code
    private void applyWindowToViewportTransformation(Graphics2D g2,
            double left, double right, double bottom, double top,
            boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double) height / width);
            double requestedAspect = Math.abs((bottom - top) / (right - left));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
                bottom += excess / 2;
                top -= excess / 2;
            } else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
                double excess = (right - left) * (requestedAspect / displayAspect - 1);
                right += excess / 2;
                left -= excess / 2;
            }
        }
        g2.scale(width / (right - left), height / (bottom - top));
        g2.translate(-left, -top);
        double pixelWidth = Math.abs((right - left) / width);
        double pixelHeight = Math.abs((bottom - top) / height);
        pixelSize = (float) Math.max(pixelWidth, pixelHeight);
    }

}
