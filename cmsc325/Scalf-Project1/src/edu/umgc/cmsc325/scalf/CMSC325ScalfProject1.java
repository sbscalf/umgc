package edu.umgc.cmsc325.scalf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

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
		drawHouseBase(g);
		drawHouseRoof(g);
		drawDoor(g);
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
	}
	
	private void drawHouseBase(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D base = new Rectangle2D.Double(135, 200, 130, 80);
		g2d.setColor(new Color(124, 89, 61));
		g2d.fill(base);
	}
	
	private void drawHouseRoof(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GeneralPath roof = new GeneralPath();
		roof.moveTo(105, 200);
		roof.lineTo(200, 150);
		roof.lineTo(295, 200);
		roof.closePath();
		g2d.setColor(new Color(124, 89, 61));
		g2d.fill(roof);
	}
	
	private void drawDoor(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D door = new Rectangle2D.Double(213, 238, 26, 42);
		g2d.setColor(Color.WHITE);
		g2d.fill(door);
	}
	
}
