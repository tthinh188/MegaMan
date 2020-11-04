import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class RedRobot extends Robot {
	private Image redRobot, redRobotLeft, currentImage;
	private MegaMan player;
	
	public RedRobot(Point p, MegaMan player) {
		super();
		this.player = player;
		setX(p.x);
		setY(p.y);
		setWidth(getImage("data/red_robot.png").getWidth(null));
		setHeight(getImage("data/red_robot.png").getHeight(null));
		redRobot = ImageTransparent.makeTransparent(getImage("data/red_robot.png"));
		redRobotLeft = ImageTransparent.makeTransparent(getImage("data/red_robot_left.png"));
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
			currentImage = redRobotLeft;
		}
		else {
			currentImage = redRobot;
		}
	}

}
