package enemies;

import utilities.*;
import bullets.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class IceBerg extends Bullet{
	private Image img;
	private Direction direction = Direction.DOWN;
	private int speed= 10, power = 2;
	private int waitTime = 40;
	
	public IceBerg(Point p) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/ice_berg.png").getHeight(null));
		setWidth(getImage("../data/ice_berg.png").getWidth(null));
		img = ImageTransparent.makeTransparent(getImage("../data/ice_berg.png"));
	}
	
	@Override
	public void toggleImage(Direction d) {
		// no animation
	}

	@Override
	public void move() {
		if(waitTime > 0) {
			waitTime --;
		}
		else {
			this.move(direction, speed);
		}
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
        g.drawImage(img, getLocation().x, getLocation().y, null);				
	}
}
