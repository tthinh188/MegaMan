public class Ceiling extends MapObject{
	
	public Ceiling(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("data/ceiling.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
