
public class RightRock extends MapObject{
	public RightRock(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("data/rock_right.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
