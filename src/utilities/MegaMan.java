package utilities;

import enemies.*;
import bullets.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import javax.sound.sampled.Clip;

public class MegaMan extends Drawable{
	private Image megaStand, megaStandLeft, standShoot, standShootLeft;
	private Image megaShootA, megaShootB, megaShootC, megaShootD, megaShootE;
	private Image megaShootLeftA, megaShootLeftB, megaShootLeftC, megaShootLeftD, megaShootLeftE;
	private Image megaMoveRightA, megaMoveRightB, megaMoveRightC, megaMoveRightD, megaMoveRightE;
	private Image megaMoveLeftA, megaMoveLeftB, megaMoveLeftC, megaMoveLeftD, megaMoveLeftE;
	private Image jump, jumpLeft, jumpShoot, jumpShootLeft;
	private Image hurt, hurtLeft;
	private Image ducking, duckingLeft;
	private Image climb, climbLeft, climbShoot, climbShootLeft;
	private boolean v = true;
	private Clip shootSound, hurtSound;
	private int lives, health, hspeed = 5, vspeed = 5;
	private int invulnerableTime = 0;
	private int animationTime = 7;
	private boolean playerIsStanding = true;
	private Image currentImage;
	
	public MegaMan() {
		super();
		generateImage();
    	setX(350);
    	setY(350);
		lives = 2;
		health = 15;
		shootSound  = getSound("../data/shoot.wav");
		hurtSound = getSound("../data/megamanhurt.wav");
		currentImage = megaStand;
	}
		
	public int getHspeed() {
		return hspeed;
	}

	public void setHspeed(int hspeed) {
		this.hspeed = hspeed;
	}

	public int getVspeed() {
		return vspeed;
	}

	public void setVspeed(int vspeed) {
		this.vspeed = vspeed;
	}

	public int live() {
		return this.lives;
	}
	
	public void setLive(int lives) {
		this.lives = lives;
	}
	
	public int health() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getTag() {
		return this.getClass().getSimpleName();
	}
	
	public void toggleRightImage() {
		animationTime++;
		if(animationTime == 8) {
			if ( currentImage == megaMoveRightA )
				currentImage = megaMoveRightB;
			else if( currentImage == megaMoveRightB)
				currentImage = megaMoveRightC;
			else if( currentImage == megaMoveRightC)
				currentImage = megaMoveRightD;
			else if( currentImage == megaMoveRightD)
				currentImage = megaMoveRightE;
			else {
				currentImage = megaMoveRightA;
			}
			animationTime = 0;
		}
	}
	
	public void toggleLeftImage() {
		animationTime++;
		if(animationTime == 8) {
			if (currentImage == megaMoveLeftA )
				currentImage = megaMoveLeftB;
			else if (currentImage == megaMoveLeftB )
				currentImage = megaMoveLeftC;
			else if (currentImage == megaMoveLeftC )
				currentImage = megaMoveLeftD;
			else if (currentImage == megaMoveLeftD )
				currentImage = megaMoveLeftE;
			else
				currentImage = megaMoveLeftA;
			animationTime = 0;
		}
	}
	
	public void toggleRightShoot() {
		animationTime++;
		if(animationTime == 8) {
			if ( currentImage == megaShootA)
				currentImage = megaShootB;
			else if( currentImage == megaShootB)
				currentImage = megaShootC;
			else if( currentImage == megaShootC)
				currentImage = megaShootD;
			else if( currentImage == megaShootD)
				currentImage = megaShootE;
			else {
				currentImage = megaShootA;
			}
			animationTime = 0;
		}
	}
	
	public void toggleLeftShoot() {
		animationTime++;
		if (animationTime == 8) {
			if ( currentImage == megaShootLeftA )
				currentImage = megaShootLeftB;
			else if( currentImage == megaShootLeftB)
				currentImage = megaShootLeftC;
			else if( currentImage == megaShootLeftC)
				currentImage = megaShootLeftD;
			else if( currentImage == megaShootLeftD)
				currentImage = megaShootLeftE;
			else {
				currentImage = megaShootLeftA;
			}
			animationTime = 0;
		}
	}
	
	public void jump(boolean isRight) {
		if (invulnerableTime < 50) {
			if (isRight) {
				currentImage = jump;
			}
			else {
				currentImage = jumpLeft;
			}
		}
	}

	private void generateImage() {
		setWidth(getImage("../data/mega_stand.png").getWidth(null));
		setHeight(getImage("../data/mega_stand.png").getHeight(null));
		
		megaStand = ImageTransparent.makeTransparent(getImage("../data/mega_stand.png"));
		megaStandLeft = ImageTransparent.makeTransparent(getImage("../data/mega_stand_left.png"));
		
		megaMoveRightA = ImageTransparent.makeTransparent(getImage("../data/mega_moving_rightA.png"));
		megaMoveRightB = ImageTransparent.makeTransparent(getImage("../data/mega_moving_rightB.png"));
		megaMoveRightC = ImageTransparent.makeTransparent(getImage("../data/mega_moving_rightC.png"));
		megaMoveRightD = ImageTransparent.makeTransparent(getImage("../data/mega_moving_rightD.png"));
		megaMoveRightE = ImageTransparent.makeTransparent(getImage("../data/mega_moving_rightE.png"));
		
		megaMoveLeftA = ImageTransparent.makeTransparent(getImage("../data/mega_moving_leftA.png"));
		megaMoveLeftB = ImageTransparent.makeTransparent(getImage("../data/mega_moving_leftB.png"));
		megaMoveLeftC = ImageTransparent.makeTransparent(getImage("../data/mega_moving_leftC.png"));
		megaMoveLeftD = ImageTransparent.makeTransparent(getImage("../data/mega_moving_leftD.png"));
		megaMoveLeftE = ImageTransparent.makeTransparent(getImage("../data/mega_moving_leftE.png"));
		
		megaShootA = ImageTransparent.makeTransparent(getImage("../data/mega_shootA.png"));
		megaShootB = ImageTransparent.makeTransparent(getImage("../data/mega_shootB.png"));
		megaShootC = ImageTransparent.makeTransparent(getImage("../data/mega_shootC.png"));
		megaShootD = ImageTransparent.makeTransparent(getImage("../data/mega_shootD.png"));
		megaShootE = ImageTransparent.makeTransparent(getImage("../data/mega_shootE.png"));

		megaShootLeftA = ImageTransparent.makeTransparent(getImage("../data/mega_shoot_leftA.png"));
		megaShootLeftB = ImageTransparent.makeTransparent(getImage("../data/mega_shoot_leftB.png"));
		megaShootLeftC = ImageTransparent.makeTransparent(getImage("../data/mega_shoot_leftC.png"));
		megaShootLeftD = ImageTransparent.makeTransparent(getImage("../data/mega_shoot_leftD.png"));
		megaShootLeftE = ImageTransparent.makeTransparent(getImage("../data/mega_shoot_leftE.png"));
		
		jump = ImageTransparent.makeTransparent(getImage("../data/jump.png"));
		jumpLeft = ImageTransparent.makeTransparent(getImage("../data/jump_left.png"));
		
		jumpShoot = ImageTransparent.makeTransparent(getImage("../data/jump_shoot.png"));
		jumpShootLeft = ImageTransparent.makeTransparent(getImage("../data/jump_shoot_left.png"));
		
		standShoot = ImageTransparent.makeTransparent(getImage("../data/mega_stand_shoot.png"));
		standShootLeft = ImageTransparent.makeTransparent(getImage("../data/mega_stand_shoot_left.png"));
		
		hurt = ImageTransparent.makeTransparent(getImage("../data/hurt.png"));
		hurtLeft = ImageTransparent.makeTransparent(getImage("../data/hurt_left.png"));
		
		climb = ImageTransparent.makeTransparent(getImage("../data/climb.png"));
		climbLeft = ImageTransparent.makeTransparent(getImage("../data/climb_left.png"));
		
		climbShoot = ImageTransparent.makeTransparent(getImage("../data/climb_shoot.png"));
		climbShootLeft = ImageTransparent.makeTransparent(getImage("../data/climb_shoot_left.png"));
		
		ducking = ImageTransparent.makeTransparent(getImage("../data/mega_ducking.png"));
		duckingLeft = ImageTransparent.makeTransparent(getImage("../data/mega_ducking_left.png"));
	}
	
	public void climb(PlayerBullet gb, boolean isRight) {
		if (invulnerableTime < 50) {
			if(gb == null) {
				if (isRight) {
					currentImage = climb;
				}
				else {
					currentImage = climbLeft;
				}
			}
			else {
				if (isRight) {
					currentImage = climbShoot;
				}
				else {
					currentImage = climbShootLeft;
				}
			}
		}
	}
	
	public void ducking(boolean isRight) {
		if(playerIsStanding) {
			setY(this.getLocation().y + 18);
			playerIsStanding = false;
		}
		setWidth(getImage("../data/mega_ducking.png").getWidth(null));
		setHeight(getImage("../data/mega_ducking.png").getHeight(null));
		if (isRight) {
			currentImage = ducking;
		} else {
			currentImage = duckingLeft;
		}
	}
	
	public void standImage(boolean isRight) {
		if(!playerIsStanding) {
			setY(this.getLocation().y - 18);
			playerIsStanding = true;
		}
		setWidth(getImage("../data/mega_stand.png").getWidth(null));
		setHeight(getImage("../data/mega_stand.png").getHeight(null));
		if (isRight) {
			currentImage = megaStand;
		} else {
			currentImage = megaStandLeft;
		}
	}
	
	public void standShootImage(boolean isRight) {
		if(!playerIsStanding) {
			setY(this.getLocation().y - 18);
			playerIsStanding = true;
		}
		if (isRight) {
			currentImage = standShoot;
		} else {
			currentImage = standShootLeft;
		}
	}
	
	public void jumpshootImage(boolean isRight) {
		if (invulnerableTime < 50) {
			if(isRight) {
				currentImage = jumpShoot;
			}
			else {
				currentImage = jumpShootLeft;
			}
		}
	}
	
	public void shoot() {
		shootSound.setFramePosition(0);
		shootSound.start();
	}
	
	public int getInvulnerableTime() {
		return invulnerableTime;
	}
	
	public void setInvulnerableTime(int invulnerableTime) {
		this.invulnerableTime = invulnerableTime;
	}
	
	//player got hit
	public boolean makeHit(List<Bullet> bullets, List<Robot> robots, Boss boss, List<Bullet> bossBullets) {
		if(invulnerableTime ==0) {
			// got hit by bullets
			for(Bullet b: bullets) {
				if (b != null &&
						this.getLocation().x               	 <= b.getLocation().x +b.width()  && 
						this.getLocation().x + this.width()	 >= b.getLocation().x 	   &&
						this.getLocation().y				 <= b.getLocation().y +b.height() &&
						this.getLocation().y + this.height() >= b.getLocation().y ) {
					invulnerableTime = 100;
					
					if(invulnerableTime == 100) {
						health -= b.power();
						hurtSound.setFramePosition(0);
						hurtSound.start();
					}
					
					if(health <= 0) {
						if (lives != 0) {
							lives -=1;
							health = 15;
						}
					}
					return true;
				}
			}
			// got hit by touching enemies
			for (Robot r: robots) {
				if (r != null &&
						this.getLocation().x               	 <= r.getLocation().x + r.width()  && 
						this.getLocation().x + this.width()	 >= r.getLocation().x 	   &&
						this.getLocation().y				 <= r.getLocation().y + r.height() &&
						this.getLocation().y + this.height() >= r.getLocation().y ) {
					invulnerableTime = 100;
					
					if(invulnerableTime == 100) {
						health -= 1;
						hurtSound.setFramePosition(0);
						hurtSound.start();
					}
					
					if (health ==0) {
						if (lives != 0) {
							lives -=1;
							health = 15;
						}
					}
					return true;
				}
			}
			
			// got hit by touching boss
			if (boss != null &&
					this.getLocation().x               	 <= boss.getLocation().x + boss.width()  && 
					this.getLocation().x + this.width()	 >= boss.getLocation().x 	   &&
					this.getLocation().y				 <= boss.getLocation().y + boss.height() &&
					this.getLocation().y + this.height() >= boss.getLocation().y ) {
				invulnerableTime = 100;
				if(invulnerableTime == 100) {
					health -= 1;
					hurtSound.setFramePosition(0);
					hurtSound.start();
				}
				if(health <= 0) {
					if(lives != 0) {
						lives -=1;
						health = 15;
					}
					
				}
				return true;
			}
		}
		
		// got hit by boss bullets
		for(Bullet b: bossBullets) {
			if (b != null &&
					this.getLocation().x               	 <= b.getLocation().x +b.width()  && 
					this.getLocation().x + this.width()	 >= b.getLocation().x 	   &&
					this.getLocation().y				 <= b.getLocation().y +b.height() &&
					this.getLocation().y + this.height() >= b.getLocation().y ) {
				invulnerableTime = 100;
				if(invulnerableTime == 100) {
					health -= b.power();
					hurtSound.setFramePosition(0);
					hurtSound.start();
				}
				if(health <= 0) {
					if(lives != 0) {
						lives -=1;
						health = 15;
					}
				}
				return true;
			}
		}
		return false;	
	}
	
	// hurt animation
	public void hurt(boolean isRight) {
		if(invulnerableTime > 50) {
			if (isRight) {
				currentImage = hurt;
			} else {
				currentImage = hurtLeft;
			}
		}
	}
	
	public void flashing() {
		if (v)
			this.v = false;
		else
			this.v = true;
	}
	
	@Override
	public void paint(Graphics2D g) {
		if (v)
			g.drawImage(currentImage, getLocation().x, getLocation().y, null);
	}

}
