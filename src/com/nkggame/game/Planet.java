package com.nkggame.game;

/**
 * 
 * @author Nathan Geater
 *
 */
public class Planet extends Body {
	
	public Planet(int random) {
		ringSize = 0;
		isRinged = false;
		size = Math.abs(random % 101) + 10;
		color = Math.abs(random % (0x00aaaaaa)) + 0xff000000;
		satellites = new Body[Math.abs(random % ((int) size / 20 + 1))];
		for (int i = 0; i < satellites.length; i++) {
			satellites[i] = new Moon(random + (i + 1) * (i + 1) * random, (int) size / 5);
		}
		if (size > 30 && random % 6 == 0) {
			isRinged = true;
			ringSize = (int) (Math.abs(random % size * 3) + size + 20);
		}
	}
}
