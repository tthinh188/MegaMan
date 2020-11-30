package objects;

import utilities.*;

public class Gate extends MapObject{
	
	public Gate(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("../data/gate.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
		visible = false;
	}
}
