package com.nkggame.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;
import com.nkggame.engine.gfx.ImageTile;

public class Map extends GameObject {

	private ArrayList<StarSystem> starSystems;
	private int starCount;
	private boolean isChanged;
	private int scale;
	private int spriteSize;
	private float cursorX = 0, cursorY = 0;
	private boolean isCursorSet = false;
	private ImageTile stars;

	public Map(float posX, float posY, int width, int height) {
		this.tag = "map";
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		scale = 1;
		spriteSize = 5;
		starSystems = new ArrayList<StarSystem>();
		starCount = 0;
		isChanged = true;
		stars = new ImageTile("/stars.png", 32, 32);
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (!isCursorSet) {
			cursorX = gc.getWidth() / 2 + posX;
			cursorY = gc.getHeight() / 2 + posY;
			isCursorSet = true;
		}

		if (isChanged) {
			starSystems.clear();
			starCount = 0;
			int sectorX = gc.getWidth() / scale;
			int sectorY = gc.getHeight() / scale;
			for (int y = 0; y < sectorY; y++) {
				for (int x = 0; x < sectorX; x++) {
					StarSystem star = new StarSystem(x + (int) posX, y + (int) posY);
					if (!star.isDead()) {
						starSystems.add(star);
						starCount++;
					}
				}
			}
			isChanged = false;
		}

		if (gc.getInput().isKey(KeyEvent.VK_W)) {
			posY -= 5 / scale + 1;
			cursorY -= 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_A)) {
			posX -= 5 / scale + 1;
			cursorX -= 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_S)) {
			posY += 5 / scale + 1;
			cursorY += 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_D)) {
			posX += 5 / scale + 1;
			cursorX += 5 / scale + 1;
			isChanged = true;
		}

		if (gc.getInput().getScroll() != 0) {

			if (gc.getInput().getScroll() < 0 && scale < 32) {
				posX += (gc.getWidth() / 2) / scale;
				posY += (gc.getHeight() / 2) / scale;
				scale = scale * 2;
				spriteSize--;
				posX -= (gc.getWidth() / 2) / scale;
				posY -= (gc.getHeight() / 2) / scale;
			} else if (gc.getInput().getScroll() > 0 && scale > 1) {
				posX -= (gc.getWidth() / 2) / scale;
				posY -= (gc.getHeight() / 2) / scale;
				scale = scale / 2;
				spriteSize++;
				if (scale == 8) {
					posY--;
				}
			}
			isChanged = true;
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {

		for (int i = 0; i < starSystems.size(); i++) {
			StarSystem star = starSystems.get(i);
			r.drawImageTile(stars, (int) ((star.getPosX() - posX) * scale), (int) ((star.getPosY() - posY) * scale),
					spriteSize, 0);
		}

		if (scale >= 16) {
			r.drawRect((int) gc.getWidth() / 2, gc.getHeight() / 2 - 8, scale, scale, 0xff00ff00);
		} else {
			r.drawRect((int) gc.getWidth() / 2, gc.getHeight() / 2, scale, scale, 0xff00ff00);
		}

		StarSystem starOnCursor = new StarSystem((int) cursorX, (int) cursorY);
		if (!starOnCursor.isDead()) {
			r.drawText("star", 0, 55, 0xffffff00);
		}

		r.drawText("Starcount: " + starCount, 0, 11, 0xffff00ff);
		r.drawText("X:" + ((int) cursorX) + " Y: " + ((int) cursorY), 0, 22, 0xffffff00);
		r.drawText(width + " x " + height, 0, 33, 0xffff00ff);
		r.drawText("Scale: " + scale, 0, 44, 0xffff00ff);

	}

}
