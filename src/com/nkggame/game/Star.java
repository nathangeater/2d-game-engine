package com.nkggame.game;

public class Star {
	private int type;
	private int color;
	private String typeString;
	private int temperature;
	private double size;
	
	public Star(int random) {
		double typeRoll = random % 10000000 / 1e7;
		if (typeRoll < 0.0000003) {
			type = 0;
			temperature = 30000 + random % 26000;
			typeString = "O";
			color = 0xffbbbbff;
		} else if (typeRoll < 0.0013000) {
			type = 1;
			temperature = 10000 + random % 20000;
			typeString = "B";
			color = 0xfff1d3ff;
		} else if (typeRoll < 0.0073000) {
			type = 2;
			temperature = 7500 + random % 2500;
			typeString = "A";
			color = 0xffffd9ff;
		} else if (typeRoll < 0.0373000) {
			type = 3;
			temperature = 6000 + random % 1500;
			typeString = "F";
			color = 0xfffffffc;
		} else if (typeRoll < 0.1145000) {
			type = 4;
			temperature = 5200 + random % 800;
			typeString = "G";
			color = 0xffffffa5;
		} else if (typeRoll < 0.2355000) {
			type = 5;
			temperature = 3700 + random % 1500;
			typeString = "K";
			color = 0xfffff360;
		} else {
			type = 6;
			temperature = 2400 + random % 1300;
			typeString = "M";
			color = 0xffffebcb;
		}
		
	}

	public int getType() {
		return type;
	}

	public int getTemperature() {
		return temperature;
	}

	public double getSize() {
		return size;
	}

	public String getTypeString() {
		return typeString;
	}

	public int getColor() {
		return color;
	}
	
}
