package bullets;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class WingBullet extends Bullet {
	private Image img;
	private Direction direction;
	private int speed, power;
	
	public WingBullet(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/wing_weapon.png").getHeight(null));
		setWidth(getImage("../data/wing_weapon.png").getWidth(null));
		img = ImageTransparent.makeTransparent(getImage("../data/wing_weapon.png"));
		speed = 7;
		power = 1;
		direction = d;
	}
	
	public void move() {
		this.move(direction, speed);
	}
	
	public int power() {
		return power;
	}
	
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(img, getLocation().x, getLocation().y, null);		
	}

	public Direction direction() {
		return direction;
	}

	@Override
	public void toggleImage(Direction d) {}

}
