package utilities;

import java.awt.Graphics2D;
import java.awt.Image;

public class BackGround extends Drawable{
	Image i; 

	public BackGround() {
		super();
		i = getImage("../data/planet.jpg");
	}
	@Override
	public void paint(Graphics2D g) {
        g.drawImage(i, getLocation().x, getLocation().y, null);
	}
}
