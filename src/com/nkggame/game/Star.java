package com.nkggame.game;

/**
 * 
 * @author Nathan Geater
 *
 */
public class Star extends Body {
	private int type;
	private String typeString;

	public Star(int random) {
		double typeRoll = Math.abs(random % 10000000) / 1e7;
		if (typeRoll < 0.0000003) {
			type = 0;
			temperature = 30000 + random % 26000;
			size = 6.6 + random % 101 / 100;
			typeString = "O";
			color = 0xffbbbbff;
		} else if (typeRoll < 0.0013000) {
			type = 1;
			temperature = 10000 + random % 20000;
			size = 1.8 + random % 49 / 10;
			typeString = "B";
			color = 0xfff1d3ff;
		} else if (typeRoll < 0.0073000) {
			type = 2;
			temperature = 7500 + random % 2500;
			size = 1.4 + random % 8 / 10;
			typeString = "A";
			color = 0xffffd9ff;
		} else if (typeRoll < 0.0373000) {
			type = 3;
			temperature = 6000 + random % 1500;
			size = 1.0 + random % 5 / 10;
			typeString = "F";
			color = 0xfffffffc;
		} else if (typeRoll < 0.1145000) {
			type = 4;
			temperature = 5200 + random % 800;
			size = 0.8 + random % 3 / 10;
			typeString = "G";
			color = 0xffffffa5;
		} else if (typeRoll < 0.2355000) {
			type = 5;
			temperature = 3700 + random % 1500;
			size = 0.5 + random % 4 / 10;
			typeString = "K";
			color = 0xfffff360;
		} else {
			type = 6;
			temperature = 2400 + random % 1300;
			size = 0.1 + random % 5 / 10;
			typeString = "M";
			color = 0xffffebcb;
		}

	}

	public void generate(int random) {
		satellites = new Body[Math.abs(random % (11 - type))];
		for (int i = 0; i < satellites.length; i++) {
			satellites[i] = new Planet(random + (i + 1) * (i + 1) * random);
		}
	}
	
	public int getType() {
		return type;
	}

	public String getTypeString() {
		return typeString;
	}

}
