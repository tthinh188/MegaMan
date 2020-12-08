package enemies;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class Wing extends Robot{
	private Image wingA, wingB, currentImage;
	
	public Wing(Point p) {
		super();
		setX(p.x);
		setY(p.y);
		setWidth(getImage("../data/wingA.png").getWidth(null));
		setHeight(getImage("../data/wingA.png").getHeight(null));
		wingA = ImageTransparent.makeTransparent(getImage("../data/wingA.png"));
		wingB = ImageTransparent.makeTransparent(getImage("../data/wingB.png"));
		currentImage = wingA;
	}

	public void toggleImage() {
		if (currentImage == wingA) {
			currentImage = wingB;
		}
		else {
			currentImage = wingA;
		}
	}
	
	@Override
	public void paint(Graphics2D g) {
		if (isVisible())
	        g.drawImage(currentImage, getLocation().x, getLocation().y, null);		
	}

}
