package cmsc405.scalf.project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class CMSC405P1 {
  
  private static final CMSC405P1 program = new CMSC405P1();
  
  private final CMSC405P1Frame window;
  private final Timer animationTimer;
  
  private CMSC405P1() {
    window = new CMSC405P1Frame();
    animationTimer = new Timer(1600, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.advanceFrame();
      }
    });
  }

  public static CMSC405P1 getInstance() {
    return program;
  }
  
  public void start() {
    window.setVisible(true);
    animationTimer.start();
  }

  public static void main(String[] args) {
    CMSC405P1 program = CMSC405P1.getInstance();
    program.start();
  }

}
