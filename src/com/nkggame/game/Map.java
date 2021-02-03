package com.nkggame.game;

import java.awt.event.KeyEvent;

import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;

public class Map extends GameObject {

	private boolean[] isStar;
	private int starCount;
	private int scale = 1;

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
		starCount = 0;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					// isStar[x + y * width] = Math.abs(rng(y << 16 | x) % 256) < 32;
					isStar[x + y * width] = Math.abs(rng(y << 16 | x)) % 101 < ((10) * Math.pow(Math.E,
							-((x - width / 2) * (x - width / 2) + (y - height / 2) * (y - height / 2)) / (10000)))
							- 0.0000001;
					if (isStar[x + y * width]) {
						starCount++;
					}
				}
			}
		}

		if (gc.getInput().isKey(KeyEvent.VK_W)) {
			posY += 10;
		}
		if (gc.getInput().isKey(KeyEvent.VK_A)) {
			posX += 10;
		}
		if (gc.getInput().isKey(KeyEvent.VK_S)) {
			posY -= 10;
		}
		if (gc.getInput().isKey(KeyEvent.VK_D)) {
			posX -= 10;
		}

//		if (gc.getInput().getScroll() != 0) {
//			int mouseX = gc.getInput().getMouseX() - gc.getWidth() / 2;
//			int mouseY = gc.getInput().getMouseY() - gc.getHeight() / 2;
//
////			int mouseOverX = mouseX - (int) posX - width / 2;
////			int mouseOverY = mouseY - (int) posY - height / 2;
//
//			posX = posX - (mouseX) / scale;
//			posY = posY - (mouseY) / scale;
//
//			scale -= gc.getInput().getScroll();
//			if (scale <= 0) {
//				scale = 1;
//			}
//
//		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (isStar[x + y * width]) {
					r.setPixel((x + (int) posX) * scale, (y + (int) posY) * scale, -1);
				} else {
					r.setPixel((x + (int) posX) * scale, (y + (int) posY) * scale, 0xff000000);
				}
			}
		}

		r.drawText("Starcount: " + starCount, 0, 11, 0xffff00ff);
		r.drawText("X:" + (gc.getInput().getMouseX() - gc.getWidth() / 2) + " Y: "
				+ (gc.getInput().getMouseY() - gc.getHeight() / 2), 0, 22, 0xffffff00);

	}

	public int rng(int seed) {
		seed += 0xe120fc15;
		Long temp = (long) seed * 0x4a39b70d;
		int m1 = (int) ((temp >> 32) ^ temp);
		temp = (long) m1 * 0x12fad5c9;
		int m2 = (int) ((temp >> 32) ^ temp);
		return m2;
	}

}
