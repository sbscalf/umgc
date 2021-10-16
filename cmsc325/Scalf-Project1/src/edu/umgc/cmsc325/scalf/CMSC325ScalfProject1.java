package edu.umgc.cmsc325.scalf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class CMSC325ScalfProject1 extends JPanel {
	
	public CMSC325ScalfProject1() {
		setBackground(new Color(0, 219, 219));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Call methods to draw
		drawGround(g);
	}
	
	private void drawGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GeneralPath ground = new GeneralPath();
		ground.moveTo(0, 240);
		ground.curveTo(100, 220, 300, 260, 400, 240);
		ground.lineTo(400, 400);
		ground.lineTo(0, 400);
		ground.closePath();
		g2d.setColor(new Color(0, 128, 0));
		g2d.fill(ground);
		g2d.draw(ground);
	}
	
}
