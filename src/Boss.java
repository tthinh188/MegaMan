import javax.sound.sampled.Clip;

public abstract class Boss extends Drawable{
	private Clip explodedSound = getSound("data/exploded_sound.wav");
	private Clip shootSound1 = getSound("data/rshoot1.wav");
	private Clip shootSound2 = getSound("data/rshoot2.wav");
	protected boolean visible = true;
	protected int health = 1;
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
	
	public boolean makeHit(GreenBullet gb) {
		if (gb != null &&
				this.getLocation().x               	 <= gb.getLocation().x +gb.width()  && 
				this.getLocation().x + this.width()	 >= gb.getLocation().x 	   &&
				this.getLocation().y				 <= gb.getLocation().y +gb.height() &&
				this.getLocation().y + this.height() >= gb.getLocation().y ) {
			
			health = health - gb.power();	
			gb.setPower(0);
			return true;
		}
		else
			return false;
	}
	
	public abstract DarkRaise.Type attack();
	
	protected void flashing() {
		if (visible) {
			visible = false;
		}
		else {
			visible = true;
		}
	}

	
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	protected void move(int level) {
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
}
	