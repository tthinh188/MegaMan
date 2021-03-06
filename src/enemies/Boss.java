package enemies;

import utilities.*;
import bullets.*;

import javax.sound.sampled.Clip;

public abstract class Boss extends Drawable{
	public enum Type {SLIDE, MISSILE, WING, COMBO, ICE, FIRE, PURPLE};

	private Clip explodedSound = getSound("../data/exploded_sound.wav");
	private Clip shootSound1 = getSound("../data/rshoot1.wav");
	private Clip shootSound2 = getSound("../data/rshoot2.wav");
	private Clip burning = getSound("../data/burning.wav");
	private Clip gun = getSound("../data/gun.wav");
	private Clip ice = getSound("../data/ice.wav");

	private boolean visible = true;
	protected int health = 60;
	private int dx = 0;
	private Direction d = Direction.LEFT;
	
	public String getTag() {
		return getClass().getSimpleName();
	}
	
	public void disapear() {
		explodedSound.setFramePosition(0);
		explodedSound.start();
	}
	
	public int health() {
		return this.health;
	}
	
	public void shoot1() {
		shootSound1.setFramePosition(0);
		shootSound1.start();
	}
	public void shoot2() {
		shootSound2.setFramePosition(0);
		shootSound2.start();
	}
	
	public void burning() {
		burning.setFramePosition(0);
		burning.start();
	}
	
	public void ice() {
		ice.setFramePosition(0);
		ice.start();
	}
	
	public void gun() {
		gun.setFramePosition(0);
		gun.start();
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean hasDied() { return health <= 0; }
	
	public Direction direction() {
		return d;
	}
	
	public abstract void slidingImage(Direction d);
	
	public abstract void toggleImage();
	public abstract int getDelayTime();
	
	public boolean makeHit(PlayerBullet playerBullet) {
		if (playerBullet != null &&
				this.getLocation().x               	 <= playerBullet.getLocation().x + playerBullet.width()  && 
				this.getLocation().x + this.width()	 >= playerBullet.getLocation().x 	   &&
				this.getLocation().y				 <= playerBullet.getLocation().y + playerBullet.height() &&
				this.getLocation().y + this.height() >= playerBullet.getLocation().y ) {
			
			health = health - playerBullet.power();	
			playerBullet.setPower(0);
			return true;
		}
		else
			return false;
	}
	
	public abstract Type attack();
	
	public void flashing() {
		if (isVisible()) {
			setVisible(false);
		}
		else {
			setVisible(true);
		}
	}

	
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void move(int level) {
		if (level == 1) {
			this.move(d, 7);
			if(d == Direction.LEFT) {
				dx -= 7;
			}
			else {
				dx += 7;
			}
			if(dx >= 0) {
				d = Direction.LEFT;
			}
			else if(dx <= -680) {
				d = Direction.RIGHT;
			}	
		}
		else {
			this.move(d, 7);
			if(d == Direction.RIGHT) {
				dx += 7;
			}
			else {
				dx -= 7;
			}
			if(dx <= 0) {
				d = Direction.RIGHT;
			}
			else if(dx >= 680) {
				d = Direction.LEFT;
			}	
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
	