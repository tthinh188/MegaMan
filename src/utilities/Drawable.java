package utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class Drawable {
	public enum Direction {UP, LEFT, DOWN, RIGHT}
	private int x, y, width, height;
	
	public void setX( int z ) { x = z; }
	public void setY( int z ) { y = z; }
	public void setWidth( int z ) { width = z; }
	public void setHeight( int z ) { height = z;}
	
	public int width() { return width; }
	public int height() { return height; }
	
	public abstract void paint( Graphics2D g );

	public void move( Direction d, int speed ) {
		switch (d) {
		case UP: y -= speed; break;
		case LEFT: x -= speed; break;
		case DOWN: y += speed; break;
		case RIGHT: x += speed; break;
		}
	}
	
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
	
	public Image getImage(String filename) {
	        URL url = getClass().getResource(filename);
	        ImageIcon icon = new ImageIcon( url );
	        return icon.getImage();
	}
	
    public Point getLocation(){
    	return new Point(x,y);
    }
    
}
