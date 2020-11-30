package enemies;

import utilities.*;
import bullets.PlayerBullet;

import javax.sound.sampled.Clip;


public abstract class Robot extends Drawable {
	private Clip explodedSound = getSound("../data/exploded_sound.wav");
	private Clip shootSound1 = getSound("../data/rshoot1.wav");
	private Clip shootSound2 = getSound("../data/rshoot2.wav");

	public boolean visible = true;
	protected int health = 6;
	private int dx = 70;
	private int dy = 20;
	private Direction horizontal = Direction.LEFT, vertical = Direction.DOWN;
	
	public String getTag() {
		return getClass().getSimpleName();
	}
	
	public boolean hasDied() { return health <= 0; }
	
	public void disapear() {
		explodedSound.setFramePosition(0);
		explodedSound.start();
	}
	
	public int health() {
		return this.health;
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void move() {
		this.move(horizontal, 5);
		if(horizontal == Direction.LEFT) {
			dx -=5;
		}
		else {
			dx +=5;
		}
		if(dx <= -100) {
			horizontal = Direction.RIGHT;
		}
		else if(dx >= 100) {
			horizontal = Direction.LEFT;
		}
		if(this.getClass().equals(RedRobot.class) || this.getClass().equals(Wing.class)) {
			this.move(vertical, 5);
			if(vertical == Direction.UP) {
				dy -= 5;
			}
			else {
				dy += 5;
			}
			if(dy >= 20) {
				vertical = Direction.UP;
			}
			else if (dy <= -20) {
				vertical = Direction.DOWN;
			}
		}
	}
	
	public abstract void toggleImage();
	
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
	
	public void shoot1() {
		shootSound1.setFramePosition(0);
		shootSound1.start();
	}
	public void shoot2() {
		shootSound2.setFramePosition(0);
		shootSound2.start();
	}
	public void flashing() {
		if (visible) {
			visible = false;
		}
		else {
			visible = true;
		}
	}
}
