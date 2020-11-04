
public class FlatRock extends MapObject{
	public FlatRock(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("data/rock_flat.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
