import java.awt.Graphics2D;
import java.awt.Image;


public class Bear extends Boss{
	private Image currentImage, standA, standB, standLeftA, standLeftB;
	private MegaMan player;
	private int delayTime = 0;
	private int animationTime = 0;

	public Bear(int x, int y, MegaMan player) {
		setX(x);
		setY(y);
		this.player = player;
		setWidth(getImage("data/bearA.png").getWidth(null));
		setHeight(getImage("data/bearA.png").getHeight(null));
		generateImage();

	}
	@Override
	public void slidingImage(Direction d) { }

	private void generateImage() {
		standA = ImageTransparent.makeTransparent(getImage("data/bearA.png"));
		standB = ImageTransparent.makeTransparent(getImage("data/bearB.png"));
		standLeftA = ImageTransparent.makeTransparent(getImage("data/bear_leftA.png"));
		standLeftB = ImageTransparent.makeTransparent(getImage("data/bear_leftB.png"));
	}
	
	@Override
	public void toggleImage() {
		// right side to the player
		if (this.getLocation().x > player.getLocation().x) {
			if(currentImage == standLeftA)
				currentImage = standLeftB;
			else
				currentImage = standLeftA;
		}
		// left side to the player
		else {
			if(currentImage == standA)
				currentImage = standB;
			else
				currentImage = standA;
		}		
	}

	@Override
	public int getDelayTime() {
		return 0;
	}

	@Override
	public Type attack() {
		return null;
	}

	@Override
	public void paint(Graphics2D g) {
		if (visible)
	        g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}

}
