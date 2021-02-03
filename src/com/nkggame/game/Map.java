package com.nkggame.game;

import java.awt.event.KeyEvent;

import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;

public class Map extends GameObject {

	private boolean[] isStar;

	public Map(int posX, int posY, int width, int height) {
		this.tag = "map";
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		isStar = new boolean[width * height];
		for (int i = 0; i < isStar.length; i++) {
			isStar[i] = false;
		}
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					isStar[x + y * width] = Math.abs(rng(y << 16 | x) % 256) < 32;
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (isStar[x + y * width]) {
					r.setPixel(x, y, -1);
				} else {
					r.setPixel(x, y, 0xff000000);
				}
			}
		}
	}
	
	public int rng(int seed) {
		seed += 0xe120fc15;
		Long temp = (long)seed * 0x4a39b70d;
		int m1 = (int)((temp >> 32) ^ temp);
		temp = (long)m1 * 0x12fad5c9;
		int m2 = (int)((temp >> 32) ^ temp);
		return m2;
	}

}
