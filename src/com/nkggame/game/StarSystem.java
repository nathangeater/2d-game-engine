package com.nkggame.game;

import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;

/**
 * 
 * @author Nathan Geater
 */

public class StarSystem extends GameObject {
	
	private Star mainStar;
	
	public StarSystem(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		int random = Math.abs(rng((posX & 0xffff) << 16 | (posY & 0xffff)));
		boolean isStar = random % 101 < ((10) * Math.pow(Math.E,
				-((posX - width / 2) * (posX - width / 2) + (posY - height / 2) * (posY - height / 2)) / (10000000)))
				- 0.0000000000000000000000001;
		if (!isStar) {
			setDead(true);
			return;
		}
		mainStar = new Star(random);
	}

	@Override
	public void update(GameContainer gc, float dt) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {

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

}
