import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class BlueRobotBullet extends Bullet {
	private Image currentImage, bulletA, bulletB, leftA;
	private Direction direction;
	private int speed, power;
	
	public BlueRobotBullet(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("data/blue_robot_bulletA.png").getHeight(null));
		setWidth(getImage("data/blue_robot_bulletA.png").getWidth(null));
		generateImage();
		direction = d;
		speed = 7;
		power = 1;
		toggleImage(d);
	}
	
	public void move() {
		this.move(direction, speed);
	}
	
	public int power() {
		return power;
	}
	
	public Direction direction() {
		return direction;
	}
	
	@Override
	public void toggleImage(Direction d) {
		if (d == Direction.RIGHT) {
			if(currentImage == bulletA) 		{currentImage = bulletB; }
			else								{currentImage = bulletA; }
		} else {
			if(currentImage == leftA) 		{currentImage =  bulletB; }
			else								{currentImage = leftA; }
		}
	}
	private void generateImage(){
		bulletA = ImageTransparent.makeTransparent(getImage("data/blue_robot_bulletA.png"));
		bulletB = ImageTransparent.makeTransparent(getImage("data/blue_robot_bulletB.png"));
		leftA = ImageTransparent.makeTransparent(getImage("data/blue_robot_bullet_leftA.png"));
	}

	@Override
	public void paint(Graphics2D g) {
        g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}

}
