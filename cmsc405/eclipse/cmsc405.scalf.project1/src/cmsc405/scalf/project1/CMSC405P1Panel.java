package cmsc405.scalf.project1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CMSC405P1Panel extends JPanel {

  private static final long serialVersionUID = 5151401693160166408L;
  private static final int PANEL_HEIGHT = 600;
  private static final int PANEL_WIDTH = 800;
  private static final int OFFSET = 60;
  private final BufferedImage[] images;
  private int frameNumber;
  private int translateX;
  private int translateY;
  private double scaleX;
  private double scaleY;
  private double rotation;
  private AffineTransform savedTransform;
  private Graphics2D g2;
  
  CMSC405P1Panel() {
    setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    images = new BufferedImage[] {
      PixelImage.PIKACHU.getImage(),
      PixelImage.ALPACA.getImage(),
      PixelImage.NOODLES.getImage()
    };
  }

  @Override
  protected void paintComponent(Graphics g) {
    configureGraphics(g);
    setTransformation();
    transformImages();
    frameNumber = (frameNumber + 1) % 5;
  }

  private void configureGraphics(Graphics g) {
    g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setPaint(Color.WHITE);
    g2.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
    applyWindowToViewportTransformation(g2, -130, 150, 130, -60, true);
    savedTransform = g2.getTransform();
  }
  
  // Method modified from AnimationStarter.java Code
  // Last three lines for pixelSize were removed
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
    }

  private void setTransformation() {
    System.out.printf("Frame is %d%n", frameNumber);
    switch(frameNumber) {
      case 1:
        translateX += -5;
        translateY += 7;
        break;
      case 2:
        rotation += -45 * Math.PI / 180.0;
        break;
      case 3:
        rotation += 90 * Math.PI / 180.0;
        break;
      case 4:
        scaleX *= 2;
        scaleY *= 0.5;
        break;
      default:
        translateX = 0;
        translateY = 0;
        scaleX = 1.0;
        scaleY = 1.0;
        rotation = 0.0;
    }
  }
    
    private void transformImages() {
      for (int i = 0; i < images.length; i++) {
        transformImage(images[i], ((images.length / 2) - i) * OFFSET);
      }
    }
    
    private void transformImage(BufferedImage image, int offset) {
      g2.translate(translateX + offset, translateY);
        g2.rotate(rotation);
        g2.scale(scaleX, scaleY);
        g2.drawImage(image, 0, 0, this);
        g2.setTransform(savedTransform);
    }

}
