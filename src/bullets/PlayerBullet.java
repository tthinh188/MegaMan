package bullets;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class PlayerBullet extends Drawable {
	private Image image;
	private Direction direction;
	private int speed, power;
	
	public PlayerBullet(Point p, Direction d, int power) {
		super();
		setX(p.x);
		setY(p.y);
		direction = d;
		speed = 8;
		
		this.power = 1 + power/ 30 ;
		
		switch (this.power) {
		case 1:
			image = getImage("../data/player_bullet1.png");
			setHeight(image.getHeight(null));
			setWidth(image.getWidth(null));
			image = ImageTransparent.makeTransparent(image);
			break;
		case 2:
			image = getImage("../data/player_bullet2.png");
			setHeight(image.getHeight(null));
			setWidth(image.getWidth(null));
			image = ImageTransparent.makeTransparent(image);
			break;
		case 3:
			image = getImage("../data/player_bullet3.png");
			setHeight(image.getHeight(null));
			setWidth(image.getWidth(null));
			image = ImageTransparent.makeTransparent(image);
			break;
		default:
			image = getImage("../data/player_bullet4.png");
			setHeight(image.getHeight(null));
			setWidth(image.getWidth(null));
			image = ImageTransparent.makeTransparent(image);
			break;
//		if(this.power == 1) {
//			image = getImage("../data/player_bullet1.png");
//			setHeight(image.getHeight(null));
//			setWidth(image.getWidth(null));
//			image = ImageTransparent.makeTransparent(image);
//		}
//		else if(this.power == 2) {
//			image = getImage("../data/player_bullet2.png");
//			setHeight(image.getHeight(null));
//			setWidth(image.getWidth(null));
//			image = ImageTransparent.makeTransparent(image);
//		}
//		else if (this.power == 3) {
//			
//		}
//		else {
//			
//		}
		}
	}
	
	public void move() {
		this.move(direction, speed);
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public void setReverse() {
		switch (power) {
			case 1: 
				break;
			case 2:
				image = getImage("../data/player_bullet2_reverse.png");
				image = ImageTransparent.makeTransparent(image);
				break;
			case 3:
				image = getImage("../data/player_bullet3_reverse.png");
				image = ImageTransparent.makeTransparent(image);
				break;
			default:
				image = getImage("../data/player_bullet4_reverse.png");
				image = ImageTransparent.makeTransparent(image);
				break;
		}
			
		
	}
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(image, getLocation().x, getLocation().y, null);
	}

	public int power() {
		return power;
	}

	public Direction getDirection() {
		return direction;
	}
	
	
}
