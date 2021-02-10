package com.nkggame.game;

/**
 * 
 * @author Nathan Geater
 */

public class StarSystem {

	private Star mainStar;
	private boolean isViewing;
	private int random;
	private int posX;
	private int posY;
	private boolean isStar;

	public StarSystem(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		random = Math.abs(rng((posX & 0xffff) << 16 | (posY & 0xffff)));
		isStar = Math.abs(random % 101) < ((10) * Math.pow(Math.E, -((posX) * (posX) + (posY) * (posY)) / (10000000)))
				- 0.0000000000000000000000001;
		if (!isStar) {
			return;
		}
		mainStar = new Star(random);
		isViewing = false;
	}

	public void generate() {
		mainStar.generate(random);
	}
	
	private int rng(int seed) {
		seed += 0xe120fc15;
		Long temp = (long) seed * 0x4a39b70d;
		int m1 = (int) ((temp >> 32) ^ temp);
		temp = (long) m1 * 0x12fad5c9;
		int m2 = (int) ((temp >> 32) ^ temp);
		return m2;
	}

	public Star getMainStar() {
		return mainStar;
	}

	public boolean isViewing() {
		return isViewing;
	}

	public void setViewing(boolean isViewing) {
		this.isViewing = isViewing;
	}

	public boolean isStar() {
		return isStar;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
