package enemies;

import utilities.*;
import bullets.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class Fire extends Bullet{
	private Image img, fireA, fireB, fireC, fireD;
	private Direction direction = Direction.LEFT;
	private int speed = 15, power = 3;
	private int waitTime = 5;
	private int animationTime = 1;
	
	public Fire(Point p) {
		super();
		setX(p.x);
		setY(p.y);
		setHeight(getImage("../data/fireA.png").getHeight(null));
		setWidth(getImage("../data/fireA.png").getWidth(null));
		generateImage();
		img = fireA;
	}
	
	@Override
	public void toggleImage(Direction d) {
		if (animationTime == 0) {
			if (img == fireA) {
				img = fireB;
			}
			else if (img == fireB) {
				img = fireC;
			}
			else if (img == fireC) {
				img = fireD;
			}
			else {
				img = fireA;
			}
			animationTime = 1;
		}
		else {
			animationTime--;
		}
		
	}
	@Override
	public void move() {
		if(waitTime > 0) {
			waitTime --;
		}
		else {
			this.move(direction, speed);
			waitTime = 5;
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
	
	private void generateImage() {
		fireA = ImageTransparent.makeTransparent(getImage("../data/fireA.png"));
		fireB = ImageTransparent.makeTransparent(getImage("../data/fireB.png"));
		fireC = ImageTransparent.makeTransparent(getImage("../data/fireC.png"));
		fireD = ImageTransparent.makeTransparent(getImage("../data/fireD.png"));
	}
}
