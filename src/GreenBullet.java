import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class GreenBullet extends Drawable {
	private Image image;
	private Direction direction;
	private int speed, power;
	
	public GreenBullet(Point p, Direction d) {
		super();
		setX(p.x);
		setY(p.y);
		image = getImage("data/green_bullet.png");
		setHeight(image.getHeight(null));
		setWidth(image.getWidth(null));
		image = ImageTransparent.makeTransparent(image);
		direction = d;
		speed = 8;
		power = 1;
	}
	
	public void move() {
		this.move(direction, speed);
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public void setReverse() {
		image = getImage("data/green_bullet_reverse.png");
		image = ImageTransparent.makeTransparent(image);
	}
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(image, getLocation().x, getLocation().y, null);
	}

	public int power() {
		return power;
	}

	public Direction getDirection() {
		return direction;
	}
	
	
}
