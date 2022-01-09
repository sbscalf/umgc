package cmsc405.scalf.project1;

import javax.swing.JFrame;

public class CMSC405P1Frame extends JFrame {

  private static final long serialVersionUID = 3181896601838002034L;
  private CMSC405P1Panel panel = new CMSC405P1Panel();
  
  public CMSC405P1Frame() {
    super("CMSC405 | Samuel Scalf | Project 1");
    setContentPane(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setResizable(false);
    setLocationRelativeTo(null);
  }
  
  public void advanceFrame() {
    panel.repaint(); 
  }

}
