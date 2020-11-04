
public class Wall extends MapObject{
	public Wall(int x, int y) {
		super();
		setX(x);
		setY(y);
		setImage(getImage("data/wall.png"));
		setHeight(image().getHeight(null));
		setWidth(image().getWidth(null));
		ImageTransparent.makeTransparent(image());
	}
}
