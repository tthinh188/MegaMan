package bullets;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class TwoGunBullet extends Bullet {
	private Image currentImage, bulletA, bulletB;
	private Direction direction;
	private int speed, power;
	
	public TwoGunBullet(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/two_gun_bulletA.png").getHeight(null));
		setWidth(getImage("../data/two_gun_bulletA.png").getWidth(null));
		generateImage();
		direction = d;
		speed = 7;
		power = 1;
		toggleImage(direction);
	}
	
	public void move() {
		this.move(Direction.DOWN, (int) (speed * Math.cos(Math.PI/4)));
		this.move(direction, (int) (speed * Math.sin(Math.PI/4)));
	}
	
	public int power() {
		return power;
	}
	
	public Direction direction() {
		return direction;
	}
	
	@Override
	public void toggleImage(Direction d) {
		if (currentImage == bulletA) {
			currentImage = bulletB;
		}
		else {
			currentImage = bulletA;
		}
	}
	
	private void generateImage(){
		bulletA = ImageTransparent.makeTransparent(getImage("../data/two_gun_bulletA.png"));
		bulletB = ImageTransparent.makeTransparent(getImage("../data/two_gun_bulletB.png"));
	}
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(currentImage, getLocation().x, getLocation().y, null);		
	}

}
