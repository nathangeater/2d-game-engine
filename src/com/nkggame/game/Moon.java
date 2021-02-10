package com.nkggame.game;

/**
 * 
 * @author Nathan Geater
 *
 */
public class Moon extends Body {
	public Moon(int random, int maxSize) {
		size = Math.abs(random % maxSize) + 10;
		color = Math.abs(random % (0x00aaaaaa)) + 0xff000000;
	}
}
