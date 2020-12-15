package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;

import enemies.*;
import bullets.*;
import utilities.*;
import objects.*;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	private BackGround bg = new BackGround();
	private MegaMan player;
	private Boss boss;
	private Timer t;
	private int level = 1;
	private boolean bossState = false;
	private boolean levelUp = false;
	private boolean isRight= true, gameOver = false, winGame = false;
	private boolean onGround = false, gravity = true, canJump = true, playerHasJump = false;
	private boolean playerIsMovingLeft, playerIsMovingRight, playerIsJumping, playerIsDucking,
	playerIsShooting;
	private boolean canMoveLeft = true, canMoveRight = true;
	private boolean bossIsSliding = false;
	private ArrayList<MapObject> map = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<Bullet> bossBullets = new ArrayList<>();
	private RedRobotBullet rrb; private WingBullet wb;
	private PlayerBullet playerBullet;
	private BlueRobotBullet brb;
	private TwoGunBullet lg, rg;
	private Clip music = getSound("../data/bgmusic.wav");
	private boolean isMuted = false;
	private int distanceTravel = 0, fallDistance = 0, jump, animationPace = 0, attackPace = 0;
	
	private int power = 0;
	private boolean fire = false;
	private int levelUpDelay = 100;
	
	private ArrayList<Robot> robots = new ArrayList<>();
	private TileMap tileMap = new TileMap();
	
	private static final String ARIAL = "Arial";
	private String defeat = "";
	
	public Panel() {
		setFocusable(true);
		
		// initial set up.
		player = new MegaMan(this);
		tileMap.generateMap(level);
		tileMap.generateEnemy(robots, player, level);
		map = tileMap.getMap();
		boss = tileMap.generateBoss(level, player);

		// decrease volume of music.
		FloatControl gainControl = 
			    (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-15.0f);
		
		addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT && !playerIsDucking) {
					playerIsMovingLeft = true;
					isRight = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT && !playerIsDucking) {
					playerIsMovingRight = true;
					isRight = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					if(!playerHasJump) {
						playerIsJumping = true;
						playerHasJump = true;
					}
					else {
						playerIsJumping = false;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN && !playerIsMovingRight 
						&& !playerIsMovingLeft && !playerIsJumping) {		
					playerIsDucking = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					playerIsShooting = true;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					playerIsMovingLeft = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerIsMovingRight = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					playerIsJumping = false;
					playerHasJump = false;
				}

				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					playerIsDucking = false; 
				}
				
				// release space bar will shoot base on power stack.
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					playerIsShooting = false;
					if(playerBullet == null) {
						int y = 0;
						if (power < 30) {
							y = player.getLocation().y + 30;
						}
						else if (power < 60) {
							y = player.getLocation().y + 15;
						}
						else {
							y = player.getLocation().y ;
						}
						if (isRight) {
							playerBullet = new PlayerBullet(new Point(player.getLocation().x + player.width()/2,
								y), Drawable.Direction.RIGHT, power);
						}
						else {
							playerBullet = new PlayerBullet(new Point(player.getLocation().x + player.width()/2,
									y), Drawable.Direction.LEFT, power);
							playerBullet.setReverse();
						}
						player.shoot();
						power = 0;
					}
				}
			}
		});
	
		t = new Timer( 10, e -> {
			if(!music.isRunning() && ! isMuted) {
				music.setFramePosition(0);
				music.start();
			}
			alwaysActions();
			repaint();
			
		});
		t.start();

	}

	// painting objects to the screen.
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; 
		
	    bg.paint(g2);

		if(!map.isEmpty()) {
			for (Drawable go: map) {
				go.paint(g2);
			}
		}
		
	    if(player !=null) {
	    	player.paint(g2);
	    }
		
	    if(boss != null) {
	    	boss.paint(g2);
	    }
		if(robots != null) {
			for (Robot r: robots) {
				r.paint(g2);
			}
		}
	
		if(playerBullet != null) {
			playerBullet.paint(g2);
		}
		
		if(rrb != null) {
			rrb.paint(g2);
		}
		
		if(wb != null) {
			wb.paint(g2);
		}
		
		if(brb != null) {
			brb.paint(g2);
		}
		
		if(lg != null) {
			lg.paint(g2);
		}
		
		if(rg != null) {
			rg.paint(g2);
		}
		if(!bossBullets.isEmpty()) {
			for(Bullet bb: bossBullets) {
				bb.paint(g2);
			}
		}
		
		// displaying informations.
		g.setFont( new Font(ARIAL, Font.PLAIN, 18 ));
	    g.setColor(Color.WHITE);
	    g.drawString("Live: " + player.live(), 20, 30);
	    
		g.setColor(Color.RED);
	    g.drawRect(19,79,26,151);
		g.setColor(Color.GREEN);
	    g.fillRect(20, 230 - player.health()*10 , 25, player.health()*10);
		
	    g.fillRect(55, 680, power * 10, 20);
	    g.setColor(Color.WHITE);
	    g.drawString("Power", 0, 695);
	    
	    g.setFont( new Font(ARIAL, Font.PLAIN, 40 ));
	    g.setColor(Color.GREEN);
	    g.drawString(defeat, 220, 400);

	    // entering the boss battle show up boss's health.
		if (boss != null && (boss.getLocation().x - player.getLocation().x) < 850 && (boss.getLocation().x - player.getLocation().x) > - 830 && boss.getLocation().y > 50 && boss.getLocation().y < 900) {
			g.setColor(Color.RED);
		    g.drawRect(899,79,26,151);
			g.setColor(Color.GREEN);
			
			int health = boss.health() * 5 / 2;
			g.fillRect(900, 230 - health , 25, health);
		}
	
	    if(gameOver) {
	    	robots.clear();
	    	g.setFont( new Font(ARIAL, Font.PLAIN, 80 ));
		    g.setColor(Color.GREEN);
		    g.drawString("Game Over", 320, 400);
		    pauseGame(true);
	    }
	    
	    if(winGame) {
	    	robots.clear();
	    	g.setFont( new Font(ARIAL, Font.PLAIN, 80 ));
		    g.setColor(Color.GREEN);
		    g.drawString("You Win", 320, 400);
		    
		    if(levelUpDelay == 0) {
			    pauseGame(true);
		    }
		    else {
		    	levelUpDelay--;
		    }
	    }
	}
	
	private void alwaysActions() {
		gameOver = checkGameOver();
		canMoveLeft = checkCanMoveLeft();
		canMoveRight = checkCanMoveRight();
		// moving left
		if(playerIsMovingLeft && !levelUp){
			if(!canMoveLeft) {
				player.setVspeed(0);
			}
			else if (player.getInvulnerableTime() <50) {
				player.setVspeed(5);
			}
			player.move(Drawable.Direction.LEFT, player.getVspeed());
			distanceTravel -= player.getVspeed();
			if(jump == 0)
				gravity =true;
			if (onGround)
				if(playerBullet != null)
					player.toggleLeftShoot();
				else 
					player.toggleLeftImage();
		}
		
		// moving right
		if(playerIsMovingRight && !levelUp){
			if(!canMoveRight) {
				player.setVspeed(0);
			}
			else if (player.getInvulnerableTime() <50){
				player.setVspeed(5);
			}
			
			player.move(Drawable.Direction.RIGHT, player.getVspeed());
			distanceTravel += player.getVspeed();
			
			if(jump == 0)
				gravity = true;
			if (onGround)
				if(playerBullet != null) 
					player.toggleRightShoot();
				else
					player.toggleRightImage();
		}
		
		// gravity
		if (!onGround) {
			if(playerBullet != null) {
				player.jumpshootImage(isRight);
			}
			else {
				player.jump(isRight);
			}
			player.move(Drawable.Direction.DOWN, player.getHspeed());
			fallDistance += player.getHspeed();
		}
		
		// player is stacking power
		if(playerIsShooting && playerBullet == null && power < 90) {
			power++;
		}
		
		// check if player is shooting and show animation.
		if (playerBullet != null) {
			playerBullet.move();
			if ((playerBullet.getLocation().x + playerBullet.width()) < 0 || playerBullet.getLocation().x > 1048) {
				playerBullet = null;
			}
			if(!playerIsMovingRight && !playerIsMovingLeft && onGround) {
				player.standShootImage(isRight);
			}
		}  
		else {
			if(!playerIsMovingRight && !playerIsMovingLeft && onGround) {
				player.standImage(isRight);
			}
		}
		
		// player bullet causes damage.
		if(playerBullet != null && !robots.isEmpty()) {
			for (Robot r: robots) {
				if(r != null && r.makeHit(playerBullet)) {
						r.flashing();
					if(r.hasDied()) {
						if(!isMuted()) {
							r.disapear();
						}
						robots.remove(r);
						break;
					}
				}
			}			
		}
		
		removingBullet();
		
		// allow player to jump
		if(gravity) {
			onGround = checkOnGround();
		}
		
		enemyAnimation();
		AIEnemy();	
		camera();
		
		// hit box for boss
		if (boss != null && (boss.getLocation().x - player.getLocation().x) < 800 && (boss.getLocation().x - player.getLocation().x) > - 830 && boss.getLocation().y > 50 && boss.getLocation().y < 900) {
			//player bullet cause damage
			if (playerBullet != null && boss != null && boss.makeHit(playerBullet)) {
				boss.flashing();
				if(boss.hasDied()) {
					if(!isMuted()) {
						boss.disapear();
					}
					boss = null;
				}
			}
			bossState = true;
			bossAttacking();
		}		
		
		playerBeingAttack();
		
		if(playerIsDucking && !levelUp) {
			player.ducking(isRight);
		}
		
		// jumping
		if(playerIsJumping && canJump && !levelUp){
			jump = 15;
			gravity = false;
		}
	
		checkCanJump();
		
		// animation and jump
		if (jump > 0) {
			canJump = false;
			if(playerBullet != null) {
				player.jumpshootImage(isRight);
			}
			else {
				player.jump(isRight);
			}
			
			player.move(Drawable.Direction.UP, player.getHspeed() * 2);
			fallDistance -= player.getHspeed() * 2;
			jump --;
			
			if(jump ==0) {
				gravity = true;
			}			
		}
		
		// allow player to climb the wall
		if(playerIsJumping && !canMoveLeft || playerIsJumping && !canMoveRight) {
			canJump = true;
		}
		
		// climbing animation
		if(jump != 0 && (!canMoveLeft || !canMoveRight)) {
			player.climb(playerBullet, isRight);
		}
		
		// Defeat boss move up to the next level.
		if (boss == null && level < 2) {
			levelUp = true;
			defeat = "Congratulation! Go to next level";
		}
		
		if (boss == null && level == 2) {
			winGame = true;
		}
		
		if (levelUp) {
			if(levelUpDelay == 0) {
				level++;
				map.clear();
				robots.clear();
				bossBullets.clear();
				tileMap.generateMap(level);
				tileMap.generateEnemy(robots, player, level);
				map = tileMap.getMap();
				boss = tileMap.generateBoss(level, player);
				bossState = false;
				levelUp = false;
				levelUpDelay = 100;
				defeat = "";
			}	else {
				levelUpDelay--;
			}
		}
	} 
	
	private void enemyAnimation() {
		animationPace ++;
		
		// make slowly enemy Animation
		if(animationPace  == 30) {
			for (Robot r: robots) {
				if (r != null) {
					r.setVisible(true);
					r.toggleImage();
				}
			}
			if(boss != null) {
				boss.setVisible(true);
				if(!bossIsSliding) {
					boss.toggleImage();
				}
			}
			if (rrb != null) {
				rrb.toggleImage(rrb.direction());
			}
			if (brb != null) {
				brb.toggleImage(brb.direction());
			}
			if(lg != null) {
				lg.toggleImage(lg.direction());
			}
			if(rg != null) {
				rg.toggleImage(rg.direction());
			}
			if(!bossBullets.isEmpty()) {
				for(Bullet bb: bossBullets) {
					bb.toggleImage(bb.direction());
				}
			}
			animationPace  = 0;
			robotAttack();
		}
	}
	
	// pause game
	public void pauseGame(boolean state) {
		if (state) {
			t.stop();
		}
		else
			t.start();
	}
	
	//save game
	public void save() {
		FileIO.save(this, player, map, robots, boss);
	}
	
	//load game
	public void load() {
		t.start();	
		map.clear();
		robots.clear();
		bossBullets.clear();
		boss = null;
		gameOver = false;
		gravity = true;
		bossIsSliding = false;
		player.setY(player.getLocation().y - 20);
		fallDistance -= 20;
		boss = FileIO.load(this, player, map, robots, boss); //Boss will always exist or win game	
	}
	
	//check if collide at left side
	private boolean checkCanMoveLeft() {
		for (Drawable dr: map) {
			if(dr.getLocation().y >= player.getLocation().y &&
					dr.getLocation().y <= (player.getLocation().y +player.height()) &&
				(dr.getLocation().y + dr.height()) <= (player.getLocation().y + player.height()) &&
				(dr.getLocation().y +dr.height()) >= player.getLocation().y
				&& ( player.getLocation().x - (dr.getLocation().x + dr.width())) > -2 
				&& ( player.getLocation().x - (dr.getLocation().x + dr.width())) <= 6) {
					return false;
			}
		}
		return true;
	}
	
	//check if collide at right side
	private boolean checkCanMoveRight() {
		for (Drawable dr: map) {
			if(dr.getLocation().y >= player.getLocation().y &&
					dr.getLocation().y <= (player.getLocation().y + player.height() + 5) &&
				(dr.getLocation().y + dr.height()) <= (player.getLocation().y + player.height() + 5) &&
				(dr.getLocation().y +dr.height()) >= player.getLocation().y
				&& ( (player.getLocation().x + player.width() - 5) - dr.getLocation().x) <= 6  
				&& ( (player.getLocation().x + player.width() - 5) - dr.getLocation().x) > -2) {
				return false;
			}
		}
		return true;
	}
	
	// enemies move and attack.
	private void AIEnemy() {
		attackPace++;
		//slow down the animations
		if(attackPace == 10) {
			for (Robot r: robots) {
				if (r != null &&!r.getClass().equals(BlueRobot.class)) {
						r.move();
				}
			}
			attackPace  = 0;
			robotAttack();
		}
	}
	
	// Focus the player
	private void camera () {
		
		//player moving right
		if (distanceTravel > 0) {
			for (Drawable dr: map) {
				dr.move(Drawable.Direction.LEFT, 5);
			}
			for (Robot r: robots) {
				r.move(Drawable.Direction.LEFT, 5);
			}
			if(boss!= null) {
				boss.move(Drawable.Direction.LEFT, 5);
			}	
			if (playerBullet != null && playerBullet.getDirection() == Drawable.Direction.LEFT) {
				playerBullet.move(playerBullet.getDirection(), 5);
			}
			if (rrb != null && rrb.direction() == Drawable.Direction.LEFT) {
				rrb.move(rrb.direction(), 5);
			}
			if (wb != null && wb.direction() == Drawable.Direction.LEFT) {
				wb.move(wb.direction(), 5);
			}
			if (brb != null && brb.direction() == Drawable.Direction.LEFT) {
				brb.move(brb.direction(), 5);
			}
			if (rg != null) {
				rg.move(Drawable.Direction.LEFT, 5);
			}
			if (lg != null) {
				lg.move(Drawable.Direction.LEFT, 5);
			}
			if (!bossBullets.isEmpty()){
				for (Bullet bb: bossBullets) {
					if (bb != null && bb.direction() == Drawable.Direction.LEFT) {
						bb.move(bb.direction(), 5);	
					}
					if(bb != null && bb.direction() == Drawable.Direction.UP || bb.direction() == Drawable.Direction.DOWN) {
						bb.move(Drawable.Direction.LEFT, 5);	
					}
				}
			}
			player.move(Drawable.Direction.LEFT, 5);
			distanceTravel -=5;
		}
		
		// player Moving Left
		if (distanceTravel < 0) {
			for (Drawable dr: map) {
				dr.move(Drawable.Direction.RIGHT, 5);
			}
			
			for (Robot r: robots) {
				r.move(Drawable.Direction.RIGHT, 5);
			}
			
			if(boss!= null) {
				boss.move(Drawable.Direction.RIGHT, 5);
			}
			
			if (playerBullet != null && playerBullet.getDirection() == Drawable.Direction.RIGHT) {
				playerBullet.move(playerBullet.getDirection(), 5);
			}
			if (wb != null&& wb.direction() == Drawable.Direction.RIGHT) {
				wb.move(wb.direction(), 5);
			}
			
			if (rrb != null && rrb.direction() == Drawable.Direction.RIGHT) {
				rrb.move(rrb.direction(), 5);
			}
	
			if (brb != null && brb.direction() == Drawable.Direction.RIGHT) {
				brb.move(brb.direction(), 5);
			}
			if (rg != null) {
				rg.move(rg.direction(), 5);
				}
			if (lg != null) {
				lg.move(Drawable.Direction.RIGHT, 5);
			}
			if (!bossBullets.isEmpty()){
				for (Bullet bb: bossBullets) {
					if (bb != null && bb.direction() == Drawable.Direction.RIGHT) {
						bb.move(bb.direction(), 5);	
					}
					if(bb != null && bb.direction() == Drawable.Direction.UP || bb.direction() == Drawable.Direction.DOWN) {
						bb.move(Drawable.Direction.RIGHT, 5);	
					}
				}
			}
			player.move(Drawable.Direction.RIGHT, 5);
			distanceTravel +=5;
		}
		
		//player falling down
		if (fallDistance > 0) {
			for (Drawable dr: map) {
				dr.move(Drawable.Direction.UP, 5);
			}
			for (Robot r: robots) {
				r.move(Drawable.Direction.UP, 5);
			}
			if(boss!= null) {
				boss.move(Drawable.Direction.UP, 5);
			}
			if (playerBullet != null) {
				playerBullet.move(Drawable.Direction.UP,5);
			}
			if (wb != null) {
				wb.move(Drawable.Direction.UP,5);
			}
			if (brb != null) {
				brb.move(Drawable.Direction.UP,5);
			}
			if (rrb != null) {
				rrb.move(Drawable.Direction.UP,5);
			}
			if(rg != null) {
				rg.move(Drawable.Direction.UP,5);
			}
			if(lg != null) {
				lg.move(Drawable.Direction.UP,5);
			}
			if (!bossBullets.isEmpty()){
				for (Bullet bb: bossBullets) {
						bb.move(Drawable.Direction.UP, 5);	
				}
			}
			player.move(Drawable.Direction.UP, 5);
			fallDistance -= 5;
		}
		
		//player jumping
		if(fallDistance < 0) {
			for (Drawable dr: map) {
				dr.move(Drawable.Direction.DOWN, 5);
			}
			
			for (Robot r: robots) {
				r.move(Drawable.Direction.DOWN, 5);
			}
			if(boss!= null) {
				boss.move(Drawable.Direction.DOWN, 5);
			}
			if (playerBullet != null) {
				playerBullet.move(Drawable.Direction.DOWN,5);
			}
			if (wb != null) {
				wb.move(Drawable.Direction.DOWN, 5);
			}
			if (rrb != null) {
				rrb.move(Drawable.Direction.DOWN, 5);
			}
			if (brb != null) {
				brb.move(Drawable.Direction.DOWN, 5);
			}
			if(rg != null) {
				rg.move(Drawable.Direction.DOWN, 5);
			}
			if(lg != null) {
				lg.move(Drawable.Direction.DOWN, 5);
			}
			if (!bossBullets.isEmpty()){
				for (Bullet bb: bossBullets) {
					bb.move(Drawable.Direction.DOWN, 5);	
				}
			}
			player.move(Drawable.Direction.DOWN, 5);
			fallDistance +=5;
		}
	}

	// check if player is on ground and disable gravity
	private boolean checkOnGround() {
		for (MapObject dr: map) {
			if(dr.getClass().equals(Gate.class) && bossState) {
				dr.visible = true;
			}
			if (dr.visible && ((player.getLocation().y + player.height()) - dr.getLocation().y <= 10)
					&& (((player.getLocation().y + player.height()) - dr.getLocation().y) > 0)
					&& ( dr.getLocation().x <= player.getLocation().x + (2*player.width()/3)) && (player.getLocation().x < dr.getLocation().x + dr.width())) {
				gravity = false;
				canJump = true;
				return true;
			}
		}
		return false;
	}
	
	// remove bullets out of screen.
	private void removingBullet() {
		if(rrb != null) {
			rrb.move();
			if((rrb.getLocation().x + rrb.width()) < 0 || rrb.getLocation().x > 1048) {
				rrb = null;
			}
		}
		if(wb != null) {
			wb.move();
			if((wb.getLocation().x + wb.width()) < 0 || wb.getLocation().x > 1048) {
				wb = null;
			}
		}
		
		if(brb != null) {
			brb.move();
			if((brb.getLocation().x + brb.width()) < 0 || brb.getLocation().x > 1048) {
				brb = null;
			}
		}
		
		if(lg != null) {
			lg.move();
			if((lg.getLocation().x + lg.width()) < 0 || lg.getLocation().x > 1048 || lg.getLocation().y >768) {
				lg = null;
			}
		}
		
		if(rg != null) {
			rg.move();
			if((rg.getLocation().x + rg.width()) < 0 || rg.getLocation().x > 1048 || rg.getLocation().y >768) {
				rg = null;
			}
		}
		
		if(!bossBullets.isEmpty()) {
			for (Bullet bb: bossBullets) {
				bb.move();
				if (bb.getLocation().x + bb.width() < 0 || bb.getLocation().x > 1048) {
					bossBullets.remove(bb);
					break;
				}
			}
		}			
	}
	
	// collect bullet to make hit
	private void getBullet() {
		bullets = new ArrayList<>();
		bullets.add(rrb);
		bullets.add(brb);
		bullets.add(wb);
		bullets.add(lg);
	 	bullets.add(rg);
	}
	
	// what happen when player got hit
	private void playerBeingAttack() {
		if(player.getInvulnerableTime() == 0) {
			getBullet();
			player.makeHit(bullets, robots, boss, bossBullets);
			bullets.clear();
		}
		else { // player is frozen and becomes invulnerable after getting hit
			player.setInvulnerableTime(player.getInvulnerableTime()-1);
			if (player.getInvulnerableTime() == 99) {
				player.setHspeed(0);
				player.setVspeed(0);
			}
			else if (player.getInvulnerableTime() == 50) {
				player.setHspeed(5);
				player.setVspeed(5);
			}
			player.flashing();
			player.hurt(isRight);
		}
	}
	
	private boolean checkGameOver() {
		return player.health() <= 0 && player.live() == 0;
	}
	
	private void checkCanJump() {
		for (MapObject dr: map) {
			if ((dr.visible) &&(player.getLocation().y - (dr.getLocation().y + dr.height())<= 10)
					&& ((player.getLocation().y - (dr.getLocation().y + dr.height())) > 0)
					&& ( dr.getLocation().x <= player.getLocation().x + player.width()/2) && (player.getLocation().x < dr.getLocation().x + dr.width())) {
				jump = 0;
				gravity = true;
				break;
			}
		}
	}
	
	// Enemies attack player
	private void robotAttack() { 
		int chance = (int) (Math.random() * robots.size());
		for (int i =0; i < robots.size(); i++) {
			// player on the left Side
			if(robots.get(chance).getLocation().x - player.getLocation().x > 0 &&
					robots.get(chance).getLocation().x - player.getLocation().x < 700) {
				if(robots.get(chance).getClass().equals(RedRobot.class)) {
					if(rrb == null) {
						rrb = new RedRobotBullet(
								new Point(robots.get(chance).getLocation().x, robots.get(chance).getLocation().y + robots.get(chance).height()/3), Drawable.Direction.LEFT);
						if(!isMuted()) {
							robots.get(chance).shoot2();
						}
					}
				}
				else if (robots.get(chance).getClass().equals(Wing.class)) {
					if(wb == null) {
						wb = new WingBullet(
								new Point(robots.get(chance).getLocation().x, robots.get(chance).getLocation().y + robots.get(chance).height()/2), Drawable.Direction.LEFT);
						if(!isMuted()) {
							robots.get(chance).shoot1();
						}
					}
				}
				else if (robots.get(chance).getClass().equals(BlueRobot.class)) {
					if(brb == null) {
						brb = new BlueRobotBullet(
								new Point(robots.get(chance).getLocation().x, robots.get(chance).getLocation().y + robots.get(chance).height()/3), Drawable.Direction.LEFT);
						if(!isMuted()) {
							robots.get(chance).shoot2();
						}
					}
				}
				else if(robots.get(chance).getClass().equals(TwoGun.class) && lg == null && rg == null) {
					lg = new TwoGunBullet(new Point(robots.get(chance).getLocation().x, robots.get(chance).getLocation().y + robots.get(chance).height()), Drawable.Direction.LEFT);
					rg = new TwoGunBullet(new Point(robots.get(chance).getLocation().x + robots.get(chance).width(), robots.get(chance).getLocation().y + robots.get(chance).height()), Drawable.Direction.RIGHT);
					if(!isMuted()) {
						robots.get(chance).shoot1();
					}
				}
			}
			
			// player on the right side
			if(robots.get(chance).getLocation().x - player.getLocation().x < 0 &&
					robots.get(chance).getLocation().x - player.getLocation().x > -700) {
				if(robots.get(chance).getClass().equals(RedRobot.class)) {
					if(rrb == null) {
						rrb = new RedRobotBullet(
								new Point(robots.get(chance).getLocation().x + robots.get(chance).width()/2, robots.get(chance).getLocation().y + robots.get(chance).height()/3), Drawable.Direction.RIGHT);
					}
				}
				else if (robots.get(chance).getClass().equals(Wing.class)) {
					if(wb == null) {
						wb = new WingBullet(
								new Point(robots.get(chance).getLocation().x + robots.get(chance).width()/2, robots.get(chance).getLocation().y + robots.get(chance).height()/2), Drawable.Direction.RIGHT);
					}
				}
				else if (robots.get(chance).getClass().equals(BlueRobot.class)) {
					if(brb == null) {
						brb = new BlueRobotBullet(
								new Point(robots.get(chance).getLocation().x + robots.get(chance).width()/2, robots.get(chance).getLocation().y + robots.get(chance).height()/3), Drawable.Direction.RIGHT);
					}
				}
				else if(robots.get(chance).getClass().equals(TwoGun.class) && lg == null && rg == null) {
					lg = new TwoGunBullet(new Point(robots.get(chance).getLocation().x, robots.get(chance).getLocation().y + robots.get(chance).height()), Drawable.Direction.LEFT);
					rg = new TwoGunBullet(new Point(robots.get(chance).getLocation().x + robots.get(chance).width(), robots.get(chance).getLocation().y + robots.get(chance).height()), Drawable.Direction.RIGHT);
				}
			}
		}
	}
	
	// receive attack type from boss classes and perform it here.
	private void bossAttacking() {	
		robots.clear();
		if (boss != null && (boss.getLocation().x - player.getLocation().x) < 800 && (boss.getLocation().x - player.getLocation().x) > -800 && boss.getLocation().y > 50 && boss.getLocation().y < 900) {
			Boss.Type type = boss.attack();
			if(type == Boss.Type.MISSILE) {
				if (boss.getDelayTime() == 200) {
					bossBullets.add(new Missile(new Point(0,boss.getLocation().y -300), Drawable.Direction.RIGHT));
					bossBullets.add(new Missile(new Point(0,boss.getLocation().y -150), Drawable.Direction.RIGHT));
					bossBullets.add(new Missile(new Point(0,boss.getLocation().y + 150), Drawable.Direction.RIGHT));
					bossBullets.add(new Missile(new Point(0,boss.getLocation().y + 300), Drawable.Direction.RIGHT));
				}
				else if(boss.getDelayTime() == 400){
					bossBullets.add(new Missile(new Point(1000,boss.getLocation().y -300), Drawable.Direction.LEFT));
					bossBullets.add(new Missile(new Point(1000,boss.getLocation().y -150), Drawable.Direction.LEFT));
					bossBullets.add(new Missile(new Point(1000,boss.getLocation().y + 150), Drawable.Direction.LEFT));
					bossBullets.add(new Missile(new Point(1000,boss.getLocation().y + 300), Drawable.Direction.LEFT));
				}
				if(!isMuted) {
					boss.gun();
				}
			}
			else if (type == Boss.Type.WING) {
				bossBullets.add(new WingBullet(new Point(0,boss.getLocation().y), Drawable.Direction.RIGHT));
				bossBullets.add(new WingBullet(new Point(1000,boss.getLocation().y - 100), Drawable.Direction.LEFT));
				bossBullets.add(new WingBullet(new Point(1000,boss.getLocation().y + 100), Drawable.Direction.LEFT));
				bossBullets.add(new WingBullet(new Point((player.getLocation().x + boss.getLocation().x)/2,0), Drawable.Direction.DOWN));
				bossBullets.add(new WingBullet(new Point((player.getLocation().x + boss.getLocation().x)/2,768), Drawable.Direction.UP));
				if(!isMuted) {
					boss.shoot1();
				}
			}
			else if (type == Boss.Type.ICE) {
				bossBullets.add(new IceBerg(new Point(player.getLocation().x,0)));
				if(!isMuted) {
					boss.ice();
				}
			}
			
			else if (type == Boss.Type.FIRE) {
				if(!fire) {
					bossBullets.clear();
					fire = true;
					if(!isMuted) {
						boss.burning();
					}
				}
				if (boss.getDelayTime() == 1){
					fire = false;
				}
				else {
					if(bossBullets.isEmpty()) {
						bossBullets.add(new Fire(new Point(boss.getLocation().x,boss.getLocation().y + 120)));
					}
					else {
						bossBullets.add(new Fire(new Point(bossBullets.get(bossBullets.size()-1).getLocation().x + 40,boss.getLocation().y + 120)));
					}	
				}
			}
			
			else if (type == Boss.Type.PURPLE) {
				bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 20), Drawable.Direction.LEFT));
				bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 70), Drawable.Direction.LEFT));
				bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 120), Drawable.Direction.LEFT));
				if(!isMuted) {
					boss.shoot2();
				}
			}
			
			else if (type == Boss.Type.COMBO) {
				if (boss.getDelayTime() == 500) {
					bossBullets.add(new WingBullet(new Point(1048,boss.getLocation().y + 20), Drawable.Direction.LEFT));
					bossBullets.add(new WingBullet(new Point(1048,boss.getLocation().y + 70), Drawable.Direction.LEFT));
					bossBullets.add(new WingBullet(new Point(1048,boss.getLocation().y + 120), Drawable.Direction.LEFT));
				}
				else if (boss.getDelayTime() == 400) {
					bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 20), Drawable.Direction.LEFT));
					bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 70), Drawable.Direction.LEFT));
					bossBullets.add(new RedRobotBullet(new Point(1048,boss.getLocation().y + 120), Drawable.Direction.LEFT));
				}
				else if (boss.getDelayTime() == 300) {
					bossBullets.add(new BlueRobotBullet(new Point(1048,boss.getLocation().y + 20), Drawable.Direction.LEFT));
					bossBullets.add(new BlueRobotBullet(new Point(1048,boss.getLocation().y + 70), Drawable.Direction.LEFT));
					bossBullets.add(new BlueRobotBullet(new Point(1048,boss.getLocation().y + 120), Drawable.Direction.LEFT));
				}
				else if (boss.getDelayTime() == 200) {
					bossBullets.add(new Missile(new Point(1048,boss.getLocation().y + 20), Drawable.Direction.LEFT));
					bossBullets.add(new Missile(new Point(1048,boss.getLocation().y + 70), Drawable.Direction.LEFT));
					bossBullets.add(new Missile(new Point(1048,boss.getLocation().y + 120), Drawable.Direction.LEFT));
				}
				else if (boss.getDelayTime() == 100) {
					bossBullets.add(new TwoGunBullet(new Point(0,0), Drawable.Direction.RIGHT));
					bossBullets.add(new TwoGunBullet(new Point(0,50), Drawable.Direction.RIGHT));
					bossBullets.add(new TwoGunBullet(new Point(1048,0), Drawable.Direction.LEFT));
					bossBullets.add(new TwoGunBullet(new Point(1048,50), Drawable.Direction.LEFT));
				}			
				if(!isMuted) {
					boss.shoot1();
				}
			}
			else if (type == Boss.Type.SLIDE)	{
				if(boss.getDelayTime() == 400) {
					bossIsSliding = true;
					if(!isMuted) {
						boss.shoot2();
					}
				}
				
				else if(boss.getDelayTime() == 1) {
					bossIsSliding = false;
				}
			}
			if(bossIsSliding) {
				boss.move(level);
				boss.slidingImage(boss.direction());
			}
		}
	}
	
	// getter and setter level for save/load function
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	// getter and setter bossState for save/load function
	public void setState(boolean bossState) {
		this.bossState = bossState;
	}
	
	public boolean getState() {
		return bossState;
	}
	
	// stop music when user is using the menu
	public void volumeOff() {
		isMuted = true;
		music.stop();
	}
	
	public void volumeOn() {
		isMuted = false;
		music.start();
	}
	
	public boolean isMuted() {
		return isMuted;
	}
	
	// get Background music
	public Clip getSound(String filename) {
        Clip clip = null;
        try {
            InputStream in = getClass().getResourceAsStream(filename);
            InputStream buf = new BufferedInputStream(in);
            AudioInputStream stream = AudioSystem.getAudioInputStream(buf);
            clip = AudioSystem.getClip();
            clip.open(stream);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }
}
