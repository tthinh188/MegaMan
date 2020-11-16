import java.awt.Graphics2D;
import java.awt.Image;

public class Bear extends Boss{
	private Image currentImage, standA, standB, standLeftA, standLeftB;
	private MegaMan player;
	private int animationTime = 0;
	private int delayTime = 150;
	private int random;
	private Type type;

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
		return delayTime;
	}

	@Override
	public Type attack() {
		if (delayTime == 0) {
//			random = (int) (Math.random()* 4);
			random = 1;
			if (random == 0) {
				delayTime = 400;
				type = Type.ICE;
				this.shoot2();
				return type;
			}
			if (random == 1) {
				delayTime = 300;
				type = Type.FIRE;
				this.shoot1();
				return type;
			}
		}
		else {
			delayTime--;
			if(delayTime == 250 && type == Type.ICE) {
				this.shoot2();
				return type;
			}
			
			if(delayTime == 100 && type == Type.ICE) {
				this.shoot2();
				return type;
			}
			
			if(delayTime == 280 && type == Type.FIRE) {
				return type;
			}
			if(delayTime == 260 && type == Type.FIRE) {
				return type;
			}
			if(delayTime == 240 && type == Type.FIRE) {
				return type;
			}
			if(delayTime == 220 && type == Type.FIRE) {
				return type;
			}
			if(delayTime == 200 && type == Type.FIRE) {
				return type;
			}
			
		}
		return null;
	}

	@Override
	public void paint(Graphics2D g) {
		if (visible)
	        g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}

}
