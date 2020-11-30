package bullets;

import utilities.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class Missile extends Bullet{
	private Image currentImage, bullet, bulletLeft;
	private Direction direction;
	private int speed, power;
	public Missile(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/missile.png").getHeight(null));
		setWidth(getImage("../data/missile.png").getWidth(null));
		direction = d;
		speed = 7;
		power = 1;
		bullet = ImageTransparent.makeTransparent(getImage("../data/missile.png"));
		bulletLeft = ImageTransparent.makeTransparent(getImage("../data/missile_left.png"));
		toggleImage(d);
	}
	@Override
	public void toggleImage(Direction d) {
		if(d == Drawable.Direction.RIGHT) {
			currentImage = bullet;
		}
		else {
			currentImage = bulletLeft;
		}
	}

	@Override
	public void move() {
		move(direction, speed);
	}

	@Override
	public int power() {
		return power;
	}

	@Override
	public Direction direction() {
		return direction;
	}

	@Override
	public void paint(Graphics2D g) {
        g.drawImage(currentImage, getLocation().x, getLocation().y, null);		

	}

}
