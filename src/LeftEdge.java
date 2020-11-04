
public class LeftEdge extends MapObject{
	public LeftEdge(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("data/left_edge.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
