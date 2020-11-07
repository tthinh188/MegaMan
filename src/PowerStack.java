import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class PowerStack extends Drawable{
	private Image currentImage;
	private Image stackA, stackB, stackLeftA, stackLeftB;
	private int animation = 0;
	private MegaMan player;
	private boolean visible = false;
	public PowerStack(Point p, MegaMan player) {
		super();
		setX(p.x);
		setY(p.y);
		stackA = ImageTransparent.makeTransparent(getImage("data/stackA.png"));
		stackB = ImageTransparent.makeTransparent(getImage("data/stackB.png"));
		stackLeftA = ImageTransparent.makeTransparent(getImage("data/stack_leftA.png"));
		stackLeftB = ImageTransparent.makeTransparent(getImage("data/stack_leftB.png"));
		this.player = player;
//		currentImage = stackA;
	}
	
	public void toggleImage(boolean isRight) {
		if (animation == 10) {
			if (isRight) {
				
				if (currentImage == stackA) {
					currentImage = stackB;
				}
				else {
					currentImage = stackA;
				}
			}
			else {
				if (currentImage == stackLeftA) {
					currentImage = stackLeftB;
				}
				else {
					currentImage = stackLeftA;
				}
			}
			animation = 0;
		}
		else {
			animation++;
		}
		
	}
	
	@Override
	public void paint(Graphics2D g) {
		if(visible)
			g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisible() {
		return visible;
	}
}
