package objects;

import utilities.*;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class MapObject extends Drawable {
	private String tag = this.getClass().getSimpleName();
	private Image image;
	public boolean visible = true;
	
	public MapObject() {
		super();
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setImage(Image i) {
		image = i;
	}
	
	public Image image() {
		return image;
	}

	@Override
	public void paint(Graphics2D g) {
		if(visible) 
			g.drawImage(image, getLocation().x, getLocation().y, null);
	}
}
