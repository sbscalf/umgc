package edu.umgc.cmsc325.scalf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		drawDoorknob(g);
		drawWindow(g);
		drawSun(g);
		drawCloud(g);
		drawBird(g, 40, 160);
		drawBird(g, 200, 80);
		drawBird(g, 300, 130);
		drawGrassTuft(g, 65, 260);
		drawGrassTuft(g, 170, 310);
		drawGrassTuft(g, 240, 285);
		drawGrassTuft(g, 330, 330);
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
		
		Line2D eves = new Line2D.Double(135, 200, 264, 200);
		g2d.setStroke(new BasicStroke(1.25f));
		g2d.setColor(new Color(74, 53, 36));
		g2d.draw(eves);
	}
	
	private void drawDoor(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D door = new Rectangle2D.Double(213, 238, 26, 42);
		g2d.setColor(Color.WHITE);
		g2d.fill(door);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.BLACK);
		g2d.draw(door);
	}
	
	private void drawDoorknob(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D doorknob = new Ellipse2D.Double(229.25, 259, 6.5, 6.5);
		g2d.setColor(Color.BLACK);
		g2d.fill(doorknob);
	}
	
	private void drawWindow(Graphics g) {
		drawWindowPane(g, 150, 220);
		drawWindowPane(g, 150, 235);
		drawWindowPane(g, 170, 220);
		drawWindowPane(g, 170, 235);
	}
	
	private void drawWindowPane(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D pane = new Rectangle2D.Double(x, y, 20, 15);
		g2d.setColor(Color.WHITE);
		g2d.fill(pane);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.BLACK);
		g2d.draw(pane);
	}
	
	private void drawSun(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D sun = new Ellipse2D.Double(300, -100, 200, 200);
		g2d.setColor(Color.YELLOW);
		g2d.fill(sun);
		g2d.drawLine(290, 5, 245, 10);
		g2d.drawLine(295, 25, 248, 42);
		g2d.drawLine(305, 50, 262, 75);
		g2d.drawLine(325, 75, 290, 110);
		g2d.drawLine(350, 95, 325, 135);
		g2d.drawLine(375, 105, 360, 152);
	}
	
	private void drawCloud(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		List<Ellipse2D> clouds = new ArrayList<>();
		clouds.add(new Ellipse2D.Double(-50, 0, 100, 100));
		clouds.add(new Ellipse2D.Double(0, -100, 200, 200));
		clouds.add(new Ellipse2D.Double(125, -50, 100, 100));
		g2d.setColor(Color.LIGHT_GRAY);
		for (Ellipse2D cloud : clouds)
			g2d.fill(cloud);
	}
	
	private void drawBird(Graphics g, int x, int y) {
		int wingspan = 30;
		Graphics2D g2d = (Graphics2D) g;
		Arc2D leftWing = new Arc2D.Double(x, y, wingspan, 10, 0, 90, Arc2D.OPEN);
		Arc2D rightWing = new Arc2D.Double(x + wingspan, y, wingspan, 10, 180, -90, Arc2D.OPEN);
		g2d.setStroke(new BasicStroke(1.5f));
		g2d.setColor(Color.BLACK);
		g2d.draw(leftWing);
		g2d.draw(rightWing);
	}
	
	private void drawGrassTuft(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		Arc2D[] blades = new Arc2D[8];
		blades[0] = new Arc2D.Double(x, y, 10, 20, 0, 90, Arc2D.OPEN);
		blades[1] = new Arc2D.Double(x - 5, y, 20, 15, 0, 90, Arc2D.OPEN);
		blades[2] = new Arc2D.Double(x + 5, y - 3, 10, 15, 0, 90, Arc2D.OPEN);
		blades[3] = new Arc2D.Double(x + 5, y - 3, 13, 15, 0, 90, Arc2D.OPEN);
		blades[4] = new Arc2D.Double(x + 15, y, 20, 20, 180, -90, Arc2D.OPEN);
		blades[5] = new Arc2D.Double(x + 20, y, 10, 20, 180, -90, Arc2D.OPEN);
		blades[6] = new Arc2D.Double(x + 20, y + 5, 10, 10, 180, -90, Arc2D.OPEN);
		blades[7] = new Arc2D.Double(x + 25, y + 5, 5, 10, 180, -90, Arc2D.OPEN);
		g2d.setStroke(new BasicStroke(1.2f));
		g2d.setColor(Color.BLACK);
		for (Arc2D blade : blades)
			if (blade != null)
				g2d.draw(blade);
	}
	
}
