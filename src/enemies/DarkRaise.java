package enemies;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;

public class DarkRaise extends Boss{
	private Image currentImage, standA, standB, standLeftA, standLeftB;
	private Image slideA, slideB, slideLeftA, slideLeftB;
	private Image fireA, fireB, fireLeftA, fireLeftB;
	private MegaMan player;
	private int delayTime = 150;
	private int animationTime = 0;
	private int random;
	private boolean isFiring = false;
	private Type type;
	
	public DarkRaise(int x, int y, MegaMan player) {
		setX(x);
		setY(y);
		this.player = player;
		setWidth(getImage("../data/boss_left.png").getWidth(null));
		setHeight(getImage("../data/boss_left.png").getHeight(null));
		generateImage();
	}
	
	@Override
	public void toggleImage() {
		// right side to the player
		if (this.getLocation().x > player.getLocation().x) {
			if(!isFiring) {
				if(currentImage == standLeftA)
					currentImage = standLeftB;
				else
					currentImage = standLeftA;
			}
			else {
				if(currentImage == fireLeftA)
					currentImage = fireLeftB;
				else
					currentImage = fireLeftA;
			}
		}
		// left side to the player
		else {
			if(!isFiring) {
				if(currentImage == standA)
					currentImage = standB;
				else
					currentImage = standA;
			}
			else {
				if(currentImage == fireA)
					currentImage = fireB;
				else
					currentImage = fireA;
			}
		}
	}

	private void generateImage() {
		standA = ImageTransparent.makeTransparent(getImage("../data/boss.png"));
		standB = ImageTransparent.makeTransparent(getImage("../data/bossB.png"));
		standLeftA = ImageTransparent.makeTransparent(getImage("../data/boss_left.png"));
		standLeftB = ImageTransparent.makeTransparent(getImage("../data/boss_leftB.png"));
		slideA = ImageTransparent.makeTransparent(getImage("../data/boss_slideA.png"));
		slideB = ImageTransparent.makeTransparent(getImage("../data/boss_slideB.png"));
		slideLeftA = ImageTransparent.makeTransparent(getImage("../data/boss_slide_leftA.png"));
		slideLeftB = ImageTransparent.makeTransparent(getImage("../data/boss_slide_leftB.png"));
		fireA = ImageTransparent.makeTransparent(getImage("../data/boss_fireA.png"));
		fireB = ImageTransparent.makeTransparent(getImage("../data/boss_fireB.png"));
		fireLeftA = ImageTransparent.makeTransparent(getImage("../data/boss_fire_leftA.png"));
		fireLeftB = ImageTransparent.makeTransparent(getImage("../data/boss_fire_leftB.png"));
		currentImage = standLeftA;
	}
	
	@Override
	public void paint(Graphics2D g) {
		if (visible)
        g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}

	public void slidingImage(Direction d) {
		animationTime++;
		if(animationTime == 8) {
			if(d == Direction.RIGHT) {
				if(currentImage == slideA) {
					currentImage = slideB;
				} else {
					currentImage = slideA;
				}
			}
			else {
				if(currentImage == slideLeftA) {
					currentImage = slideLeftB;
				} else {
					currentImage = slideLeftA;
				}
			}
			animationTime = 0;
		}
	}
	
	// randomly attack player.
	// return attack type to the panel.
	public Type attack() {
		if (delayTime == 0) {
			isFiring = false;
			random = (int) (Math.random()* 4); 
			if (random == 0) {
				this.shoot1();
				type = Type.WING;
				delayTime = 200;
				isFiring = true;
				toggleImage();
				return type;
			}
			else if (random == 1) {
				type = Type.MISSILE;
				this.gun();
				delayTime = 400;
				isFiring = true;
				toggleImage();
				return type;
			}
			else if(random == 2){
				type = Type.SLIDE;
				this.shoot2();
				setY(this.getLocation().y + 99);
				setWidth(getImage("../data/boss_slide_leftA.png").getWidth(null));
				setHeight(getImage("../data/boss_slide_leftA.png").getHeight(null));
				delayTime = 400;
				return type;
			}
			else {
				type = Type.COMBO;
				delayTime = 500;
				isFiring = true;
				toggleImage();
				return type;
			}
		}
		else {
			delayTime--;
			if (delayTime == 100) {
				isFiring = false;
			}
			if(delayTime == 200 && type == Type.MISSILE) {
				this.gun();
				return type;
			}
			if(delayTime == 400 && type == Type.COMBO) {
				return type;
			}
			if(delayTime == 300 && type == Type.COMBO) {
				return type;
			}
			if(delayTime == 200 && type == Type.COMBO) {
				return type;
			}
			if(delayTime == 100 && type == Type.COMBO) {
				return type;
			}
			
			if(delayTime == 1 && type == Type.SLIDE) {
				this.shoot1();
				setY(this.getLocation().y - 99);
				setWidth(getImage("../data/boss_left.png").getWidth(null));
				setHeight(getImage("../data/boss_left.png").getHeight(null));
				return type;
			}
		
		}
		return null;
	}
	
	// check if be able to attack.
	public int getDelayTime() {
		return delayTime;
	}
}
