import java.awt.Point;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	public static void save(Panel panel, MegaMan player, ArrayList<MapObject> map, ArrayList<Robot> robots, Boss boss) {
		String info = "";
		try {
			FileOutputStream file = new FileOutputStream("src/game_data/game_data.txt");
			OutputStreamWriter writer = new OutputStreamWriter(file);
			info += String.format("%d, %s", panel.getLevel(), String.valueOf(panel.getState())) + System.lineSeparator();
			info += String.format("%s, %d, %d", player.getTag(), player.health(), player.live()) + System.lineSeparator();
			for (MapObject mo: map) {
				if(mo.getTag().equals("Gate")) {
					info += String.format("%s, %d, %d, %s", mo.getTag(),mo.getLocation().x, mo.getLocation().y, String.valueOf(mo.visible) ) + System.lineSeparator();
				}
				else {
					info += String.format("%s, %d, %d", mo.getTag(),mo.getLocation().x, mo.getLocation().y )+ System.lineSeparator();
				}
			}
			
			for (Robot r: robots) {
				info += String.format("%s, %d, %d, %d, %d, %d", r.getTag(), r.getLocation().x, r.getLocation().y, r.health(), r.getDx(), r.getDy()) + System.lineSeparator();
			}
			if (boss != null) {
				info += String.format("%s, %d, %d, %d, %d", boss.getTag(), boss.getLocation().x, boss.getLocation().y, boss.health(), boss.getDx());
			}
			writer.write(info);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Boss load(Panel panel, MegaMan player, ArrayList<MapObject> map, ArrayList<Robot> robots, Boss boss) {
		try {
			FileReader file = new FileReader("src/game_data/game_data.txt");
			Scanner scanner = new Scanner(file);
			String levelInfo = scanner.nextLine();
			String[] token = levelInfo.split(", ");
			panel.setLevel(Integer.valueOf(token[0]));
			if(token[1].equals("true")) {
				panel.setState(true);
			}
			else {
				panel.setState(false);
			}
						
			while(scanner.hasNextLine()) {
				String info = scanner.nextLine();
				token = info.split(", ");
				if(token[0].equals("MegaMan")) {
					player.setHealth(Integer.valueOf(token[1]));
					player.setLive(Integer.valueOf(token[2]));
				}
				else if (token[0].equals("Ceiling")) {
					map.add(new Ceiling(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("FlatRock")) {
					map.add(new FlatRock(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("LeftEdge")) {
					map.add(new LeftEdge(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("RightEdge")) {
					map.add(new RightEdge(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("RightRock")) {
					map.add(new RightRock(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("LeftRock")) {
					map.add(new LeftRock(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("Wall")) {
					map.add(new Wall(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
				}
				else if (token[0].equals("Gate")) {
					Gate gate = new Gate(Integer.valueOf(token[1]),Integer.valueOf(token[2]));
					if(token[3].equals("true")) {
						System.out.println(token[3]);
						gate.visible = true;
					}
					map.add(gate);
				}
				else if(token[0].equals("RedRobot")) {
					RedRobot r = new RedRobot(new Point(Integer.valueOf(token[1]),Integer.valueOf(token[2])), player);
					r.setHealth(Integer.valueOf(token[3]));
					r.setDx(Integer.valueOf(token[4]));
					r.setDy(Integer.valueOf(token[5]));
					robots.add(r);
				}
				
				else if(token[0].equals("Wing")) {
					Wing r = new Wing(new Point(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
					r.setHealth(Integer.valueOf(token[3]));
					r.setDx(Integer.valueOf(token[4]));
					robots.add(r);
				}
				
				else if(token[0].equals("BlueRobot")) {
					BlueRobot r = new BlueRobot(new Point(Integer.valueOf(token[1]),Integer.valueOf(token[2])), player);
					r.setHealth(Integer.valueOf(token[3]));
					robots.add(r);
				}
				
				else if(token[0].equals("TwoGun")) {
					TwoGun r = new TwoGun(new Point(Integer.valueOf(token[1]),Integer.valueOf(token[2])));
					r.setHealth(Integer.valueOf(token[3]));
					robots.add(r);
				}
				
				else if(token[0].equals("DarkRaise")) {
					if(boss == null) {
						boss = new DarkRaise(350,350,player);
					}
					boss.setX(Integer.valueOf(token[1]));
					boss.setY(Integer.valueOf(token[2]));
					boss.setHealth(Integer.valueOf(token[3]));
					boss.setDx(Integer.valueOf(token[4]));
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boss;
	}
}
