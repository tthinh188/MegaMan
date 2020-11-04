import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TileMap {
	private ArrayList<MapObject> map = new ArrayList<MapObject>();

	public ArrayList<MapObject> getMap() {
		return map;
	}

	public void generateMap(int level) {
		if (level ==1) {
			for (int i = 0; i < 37; i++) {
				map.add(new Ceiling(i*91, 31));
			}
			for (int i =2; i < 18; i++) {
				map.add(new Wall(1547, i *30));
			}
			for(int i =0; i < 46; i++) {
				map.add(new FlatRock(i*74,0));
				map.add(new FlatRock(74*i,1395));
				map.add(new FlatRock(74*i,1426));
				map.add(new FlatRock(74*i,1457));
				map.add(new FlatRock(74*i,1488));
				map.add(new FlatRock(74*i,1519));
			}
			for (int i = 0; i <50; i++) {
				map.add(new FlatRock(0,i*31));
				map.add(new FlatRock(-74,i*31));
				map.add(new FlatRock(-148,i*31));
				map.add(new FlatRock(-222,i*31));
				map.add(new FlatRock(-296,i*31));
				
				map.add(new FlatRock(3404,i*31));
				map.add(new FlatRock(3478,i*31));
				map.add(new FlatRock(3552,i*31));
				map.add(new FlatRock(3626,i*31));
				map.add(new FlatRock(3700,i*31));
				map.add(new FlatRock(3774,i*31));
				map.add(new FlatRock(3848,i*31));
			}
			
			for (int i =2; i< 45; i++) {
				map.add(new LeftRock(3330	,i*31));
			}
			for(int i = 1; i < 16; i++) {
				map.add(new RightRock(74,(i*31)));
			}
			
			for (int i =11; i < 14; i++) {
				map.add(new Ceiling(i*91, 660));
				map.add(new Ceiling((i-2)*91, 1100));
			}
			for(int i = 0; i <10; i++) {
				map.add(new FlatRock(74*i,713));
				map.add(new FlatRock(74*i,682));
				map.add(new FlatRock(74*i,651));
				map.add(new FlatRock(74*i,620));
				map.add(new FlatRock(74*i,589));
				map.add(new FlatRock(74*i,558));
				map.add(new FlatRock(74*i,527));
				map.add(new FlatRock(74*i,496));
				
				map.add(new FlatRock(74*i,1023));
				map.add(new FlatRock(74*i,1054));
				map.add(new FlatRock(74*i,1085));
				
				map.add(new FlatRock(74*(i+17),713));
				map.add(new FlatRock(74*(i+17),682));
				map.add(new FlatRock(74*(i+17),651));
				map.add(new FlatRock(74*(i+17),620));
				map.add(new FlatRock(74*(i+17),589));
				map.add(new FlatRock(74*(i+17),558));
				map.add(new FlatRock(74*(i+17),527));
				map.add(new FlatRock(74*(i+17),496));
				
				map.add(new FlatRock(74*(i+17),1364));
				map.add(new FlatRock(74*(i+17),1333));
				map.add(new FlatRock(74*(i+17),1302));
				map.add(new FlatRock(74*(i+17),1271));
				map.add(new FlatRock(74*(i+17),1240));
				map.add(new FlatRock(74*(i+17),1209));
				map.add(new FlatRock(74*(i+17),1178));
				map.add(new FlatRock(74*(i+17),1147));
			}
			
			map.add(new Gate(2456,558));
			map.add(new Gate(2396,558));
			map.add(new Gate(2336,558));
			
			for (int i =0; i < 11; i++) {
				map.add(new FlatRock((i+34)* 74, 558));
				map.add(new FlatRock((i+31)* 74, 1116));
			}
			for(int i =0; i < 8; i++) {
				map.add(new RightRock(1995, (i+16)*31));
				map.add(new LeftRock(1239, (i+16)*31));
				map.add(new LeftRock(1239, (i+37)*31));
				map.add(new RightRock(1995, (i+37)*31));
			}
			
			for (int i = 0; i <35; i++) {
				map.add(new LeftRock(2274,(i+2)*31));
			}
			map.add(new LeftEdge(560,436));
			map.add(new RightEdge(650,436));
			map.add(new FlatRock(605,469));
			
			map.add(new LeftEdge(560,963));
			map.add(new RightEdge(650,963));
			map.add(new FlatRock(605,996));
			
			map.add(new FlatRock(3330,31));	
		}
		
		
		//TODO create level 2 map
		else if (level ==2) {
			
		}
		
		
	}
	
	public void generateEnemy(List<Robot> robots, MegaMan player, int level) {
		if	(level == 1) {
			robots.add(new RedRobot(new Point(1400,350), player));
			robots.add(new RedRobot(new Point(1750,350), player));
			robots.add(new RedRobot(new Point(2700,1220), player));
			robots.add(new RedRobot(new Point(2750,980), player));

			robots.add(new BlueRobot(new Point(100,930), player));
			robots.add(new BlueRobot(new Point(1300,1060), player));
			robots.add(new BlueRobot(new Point(1800,1060), player));
			robots.add(new BlueRobot(new Point(2400,1310), player));
			robots.add(new BlueRobot(new Point(2400,1030), player));
			robots.add(new BlueRobot(new Point(100,1300), player));
			
			robots.add(new Wing(new Point(800,580)));
			robots.add(new Wing(new Point(3000,1250)));
			robots.add(new Wing(new Point(550,860)));

			robots.add(new TwoGun(new Point(1800,60)));
			robots.add(new TwoGun(new Point(2550,1145)));	
		}
		else if (level ==2) {
//			robots.add(new RedRobot(new Point(500,350), player));
		}
	}
	
	public Boss generateBoss(int level, MegaMan player) {
		if (level == 1) {
			return new DarkRaise(3200,395, player); 
		}
		else {
			// TODO create boss Level2
			return new DarkRaise(170,1230, player);

		}
	}
}
