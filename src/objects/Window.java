package objects;

import utilities.*;

public class Window extends MapObject{
	public Window(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("../data/window.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
