package bullets;

import utilities.*;

public abstract class Bullet extends Drawable {	

	public abstract void toggleImage(Direction d);
	public abstract void move();
	public abstract int power();
	public abstract Direction direction();
	
}
