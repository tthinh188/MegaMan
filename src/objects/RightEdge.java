package objects;

import utilities.*;

public class RightEdge extends MapObject{
	public RightEdge(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("../data/right_edge.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
