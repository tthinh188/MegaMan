package utilities;

import objects.*;
import enemies.*;

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
		
		
		else if (level ==2) {
			
			map.add(new Gate(145,1400));
			map.add(new Gate(205,1400));
			map.add(new Gate(265,1400));
			map.add(new Gate(325,1400));
			map.add(new Gate(385,1400));

			for (int i = 0; i < 29; i++) {
				map.add(new Ceiling(i*91, 31));
			}
			for(int i = 1; i < 72; i++) {
				map.add(new RightRock(74,(i*31)));
				map.add(new FlatRock(0,i*31));
				map.add(new LeftRock(2639,(i*31)));
				map.add(new FlatRock(2721,(i)*31));
				map.add(new FlatRock(2795,(i)*31));
				map.add(new FlatRock(2869,(i)*31));
				map.add(new FlatRock(2943,(i)*31));
				map.add(new FlatRock(3017,(i)*31));
				map.add(new FlatRock(3091,(i)*31));
				map.add(new RightRock(3165,(i)*31));
			}
			
			for(int i = 0; i < 44 ; i++) {
				map.add(new FlatRock(i*74,0));
			}
			
			for(int i = 50; i < 66; i++) {
				map.add(new FlatRock(1184,(i)*31));
				map.add(new FlatRock(1258,(i)*31));
				map.add(new FlatRock(1332,(i)*31));
				map.add(new FlatRock(1406,(i)*31));
				map.add(new FlatRock(1480,(i)*31));
				map.add(new FlatRock(1554,(i)*31));
				map.add(new FlatRock(1628,(i)*31));

			}
			
			for(int i = 0; i < 23; i++) {
				map.add(new FlatRock(74*i,2232));
				map.add(new FlatRock(74*i,2201));
				map.add(new FlatRock(74*i,2170));
				map.add(new FlatRock(74*i,2139));
				map.add(new FlatRock(74*i,2108));
				map.add(new FlatRock(74*i,2077));
				map.add(new FlatRock(74*i,2046));

			}
			
			
			for(int i = 6; i < 36 ; i++) {
				map.add(new FlatRock(74*i,1395));
				map.add(new FlatRock(74*i,1426));
				map.add(new FlatRock(74*i,1457));
				map.add(new FlatRock(74*i,1488));
				map.add(new FlatRock(74*i,1519));
			}

			for(int i =0; i < 33; i++) {
				map.add(new FlatRock(i*74,651));
				map.add(new FlatRock(i*74,682));
				map.add(new FlatRock(i*74,713));
				map.add(new FlatRock(i*74,734));
				map.add(new FlatRock(i*74,765));
				map.add(new FlatRock(i*74,796));
				map.add(new FlatRock(i*74,827));
				map.add(new FlatRock(i*74,858));
				map.add(new FlatRock(i*74,889));
			}
			for(int i =0; i < 8; i++) {
				map.add(new LeftRock(800, (i*31) + 403));
				map.add(new FlatRock((i*74) + 882, 403));
				map.add(new FlatRock((i*74) + 882, 434));
			}
			
			for(int i = 23; i < 36 ; i++) {
				map.add(new FlatRock(74*i,1550));
				map.add(new FlatRock(74*i,1581));
				map.add(new FlatRock(74*i,1612));
				map.add(new FlatRock(74*i,1643));
			}

			map.add(new LeftEdge(1300,1335));
			map.add(new RightEdge(1390,1335));
			map.add(new FlatRock(1345,1368));
		}
		
		
	}
	
	public void generateEnemy(List<Robot> robots, MegaMan player, int level) {
		if	(level == 1) {
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
			robots.add(new BlueRobot(new Point(1000,560), player));
			robots.add(new BlueRobot(new Point(2300,560), player));
			robots.add(new BlueRobot(new Point(2100,1300), player));

			robots.add(new RedRobot(new Point(1350,530), player));
			robots.add(new RedRobot(new Point(1250,290), player));
			robots.add(new RedRobot(new Point(1950,1270), player));
			robots.add(new RedRobot(new Point(1220,1270), player));

			robots.add(new Wing(new Point(1700,530)));
			robots.add(new Wing(new Point(2100,530)));
			robots.add(new Wing(new Point(1730,1270)));
			robots.add(new Wing(new Point(1470,1270)));

		}
	}
	
	public Boss generateBoss(int level, MegaMan player) {
		if (level == 1) {
			return new DarkRaise(3200,395, player); 
		}
		else {
			return new Bear(950,1870, player);

		}
	}
}
