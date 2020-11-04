import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class TwoGun extends Robot{
	private Image image;

	public TwoGun(Point p) {
		super();
		setX(p.x);
		setY(p.y);
		setWidth(getImage("data/two_gun.png").getWidth(null));
		setHeight(getImage("data/two_gun.png").getHeight(null));
		image = ImageTransparent.makeTransparent(getImage("data/two_gun.png"));
	}
	@Override
	public void toggleImage() {
		// no animation
	}
	
	@Override
	public void paint(Graphics2D g) {
		if (visible)
	        g.drawImage(image, getLocation().x, getLocation().y, null);	
	}
}