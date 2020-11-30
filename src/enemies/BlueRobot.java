package enemies;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class BlueRobot extends Robot{
	private Image blueRobotA, blueRobotB, blueRobotLeftA, blueRobotLeftB, currentImage;
	MegaMan player;
	
	public BlueRobot(Point p, MegaMan player) {
		super();
		this.player = player;
		setX(p.x);
		setY(p.y);
		setWidth(getImage("../data/red_robot.png").getWidth(null));
		setHeight(getImage("../data/red_robot.png").getHeight(null));
		blueRobotA = ImageTransparent.makeTransparent(getImage("../data/blue_robotA.png"));
		blueRobotB = ImageTransparent.makeTransparent(getImage("../data/blue_robotB.png"));
		blueRobotLeftA = ImageTransparent.makeTransparent(getImage("../data/blue_robot_leftA.png"));
		blueRobotLeftB = ImageTransparent.makeTransparent(getImage("../data/blue_robot_leftB.png"));
		toggleImage();
	}
	
	@Override
	public void paint(Graphics2D g) {
		if (visible)
	        g.drawImage(currentImage, getLocation().x, getLocation().y, null);		
	}

	@Override
	public void toggleImage() {
		if (this.getLocation().x > player.getLocation().x) {
			if(currentImage == blueRobotLeftA)
				currentImage = blueRobotLeftB;
			else
				currentImage = blueRobotLeftA;
		}
		else {
			if(currentImage == blueRobotA)
				currentImage = blueRobotB;
			else
				currentImage = blueRobotA;
		}
	}
}
