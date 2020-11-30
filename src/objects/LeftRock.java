package objects;

import utilities.*;

public class LeftRock extends MapObject{
	public LeftRock(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("../data/rock_left.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
