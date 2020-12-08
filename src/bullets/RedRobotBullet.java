package bullets;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class RedRobotBullet extends Bullet {
	private Image currentImage, bulletA, bulletB, bulletC, leftA, leftB, leftC;
	private Direction direction;
	private int speed, power;
	
	public RedRobotBullet(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/purple_weaponA.png").getHeight(null));
		setWidth(getImage("../data/purple_weaponA.png").getWidth(null));
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
	
	private void generateImage(){
		bulletA = ImageTransparent.makeTransparent(getImage("../data/purple_weaponA.png"));
		bulletB = ImageTransparent.makeTransparent(getImage("../data/purple_weaponB.png"));
		bulletC = ImageTransparent.makeTransparent(getImage("../data/purple_weaponB.png"));
		leftA = ImageTransparent.makeTransparent(getImage("../data/purple_weapon_leftA.png"));
		leftB = ImageTransparent.makeTransparent(getImage("../data/purple_weapon_leftB.png"));
		leftC = ImageTransparent.makeTransparent(getImage("../data/purple_weapon_leftC.png"));
	}
	
	public void toggleImage(Drawable.Direction d) {
		if (d == Drawable.Direction.RIGHT) {
			if(currentImage == bulletA) 		{currentImage = bulletB; }
			else if (currentImage == bulletB) 	{currentImage = bulletC; }
			else								{currentImage = bulletA; }
		} else {
			if(currentImage == leftA) 		{currentImage = leftB; }
			else if (currentImage == leftB) 	{currentImage = leftC; }
			else								{currentImage = leftA; }
		}
	}
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(currentImage, getLocation().x, getLocation().y, null);		
	}
}
