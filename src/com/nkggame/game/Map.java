package com.nkggame.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;
import com.nkggame.engine.gfx.ImageTile;

/**
 * 
 * @author Nathan Geater
 */

public class Map extends GameObject {

	private ArrayList<StarSystem> starSystems;
	private StarSystem selectedStarSystem;
	private int starCount;
	private boolean isChanged;
	private int scale;
	private int spriteSize;
	private float cursorX, cursorY;
	private boolean isCursorSet;
	private boolean useStarSprites;
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
		cursorX = 0;
		cursorY = 0;
		selectedStarSystem = null;
		isCursorSet = false;
		stars = new ImageTile("/stars.png", 32, 32);
		useStarSprites = true;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (!isCursorSet) {
			posX -= gc.getWidth() / 2;
			posY -= gc.getHeight() / 2;
			cursorX = gc.getWidth() / 2 + posX;
			cursorY = gc.getHeight() / 2 + posY;
			for (int i = 0; i < 5; i++) {
				posX += (gc.getWidth() / 2) / scale;
				posY += (gc.getHeight() / 2) / scale;
				scale = scale * 2;
				spriteSize--;
				posX -= (gc.getWidth() / 2) / scale;
				posY -= (gc.getHeight() / 2) / scale;
			}
			selectedStarSystem = new StarSystem((int) cursorX, (int) cursorY);
			isCursorSet = true;
		}

		if (gc.getInput().isKeyDown(KeyEvent.VK_ENTER)) {
			if (!gc.getInput().isTypingNumpad()) {
				gc.getInput().setQueryNumpad("");
				gc.getInput().setTypingNumpad(true);
			} else {
				gc.getInput().setTypingNumpad(false);
				String fullQ = gc.getInput().getQueryNumpad();
				fullQ = fullQ.strip();
				if (!fullQ.isBlank()) {
					int numSpaces = 0;
					for (int i = 0; i < fullQ.length(); i++) {
						if (fullQ.charAt(i) == ' ') {
							numSpaces++;
						}
						if (fullQ.charAt(i) == '/' || fullQ.charAt(i) == '*' || fullQ.charAt(i) == '+'
								|| fullQ.charAt(i) == '.') {
							numSpaces += 2;
						}
					}
					if (numSpaces == 1) {
						int coordX = Integer.parseInt(fullQ.substring(0, fullQ.indexOf(' ')));
						int coordY = Integer.parseInt(fullQ.substring(fullQ.indexOf(' ') + 1));
						posY += coordY - cursorY;
						cursorY = coordY;
						posX += coordX - cursorX;
						cursorX = coordX;
						isChanged = true;
					}
				}
			}
		}

		if (gc.getInput().isKey(KeyEvent.VK_W) && !gc.getInput().isTyping()) {
			posY -= 5 / scale + 1;
			cursorY -= 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_A) && !gc.getInput().isTyping()) {
			posX -= 5 / scale + 1;
			cursorX -= 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_S) && !gc.getInput().isTyping()) {
			posY += 5 / scale + 1;
			cursorY += 5 / scale + 1;
			isChanged = true;
		}
		if (gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isTyping()) {
			posX += 5 / scale + 1;
			cursorX += 5 / scale + 1;
			isChanged = true;
		}

		if (gc.getInput().isButtonDown(1)) {
			posX += (gc.getInput().getMouseX() / scale) - gc.getWidth() / scale / 2;
			posY += (gc.getInput().getMouseY() / scale) - gc.getHeight() / scale / 2;
			cursorX += (gc.getInput().getMouseX() / scale) - gc.getWidth() / scale / 2;
			cursorY += (gc.getInput().getMouseY() / scale) - gc.getHeight() / scale / 2;
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

		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE) && !gc.getInput().isTypingNumpad()) {
			if (selectedStarSystem.isStar()) {
				if (!selectedStarSystem.isViewing()) {
					selectedStarSystem.setViewing(true);
				} else {
					selectedStarSystem.setViewing(false);
				}
			}
		}

		if (isChanged) {
			starSystems.clear();
			starCount = 0;
			int sectorX = gc.getWidth() / scale;
			int sectorY = gc.getHeight() / scale;
			for (int y = 0; y < sectorY; y++) {
				for (int x = 0; x < sectorX; x++) {
					StarSystem starS = new StarSystem(x + (int) posX, y + (int) posY);
					if (starS.isStar()) {
						starSystems.add(starS);
						starCount++;
					}
				}
			}
			selectedStarSystem = new StarSystem((int) cursorX, (int) cursorY);
			isChanged = false;
		}
	}

	public int countEveryStarSystem() {
		int countOfAll = 0;
		for (int y = -height / 2; y < height / 2; y++) {
			for (int x = -width / 2; x < width / 2; x++) {
				StarSystem starS = new StarSystem(x, y);
				if (starS.isStar()) {
					countOfAll++;
				}
			}
		}
		return countOfAll;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {

		for (int i = 0; i < starSystems.size(); i++) {
			StarSystem starS = starSystems.get(i);
			if (scale != 1) {
				if (useStarSprites && scale >= 8) {
					r.drawImageTile(stars, (int) ((starS.getPosX() - posX) * scale),
							(int) ((starS.getPosY() - posY) * scale), spriteSize, starS.getMainStar().getType());
				} else {
					r.drawFillOval((int) ((starS.getPosX() - posX) * scale), (int) ((starS.getPosY() - posY) * scale),
							scale, scale, starS.getMainStar().getColor());
				}
			} else {
				r.setPixel((int) ((starS.getPosX() - posX) * scale), (int) ((starS.getPosY() - posY) * scale), -1);
			}

		}

		if (scale >= 16) {
			r.drawRect((int) gc.getWidth() / 2, gc.getHeight() / 2 - 8, scale, scale, 0xff00ff00);
		} else {
			r.drawRect((int) gc.getWidth() / 2, gc.getHeight() / 2, scale, scale, 0xff00ff00);
		}

		if (selectedStarSystem.isStar()) {
			if (selectedStarSystem.isViewing()) {
				selectedStarSystem.generate();
				viewStar(gc, r);
			}
			r.drawText(selectedStarSystem.getMainStar().getTypeString() + "-Type Star", 0, 55, 0xffffff00);
		}

		r.drawText("Starcount: " + starCount, 0, 11, 0xffff00ff);
		r.drawText("X:" + ((int) cursorX) + " Y: " + ((int) cursorY), 0, 22, 0xffffff00);
		r.drawText(width + " x " + height, 0, 33, 0xffff00ff);
		r.drawText("Scale: " + scale, 0, 44, 0xffff00ff);

		if (gc.getInput().isTypingNumpad()) {
			r.drawText("Go to: " + gc.getInput().getQueryNumpad(), 0, 66, 0xff00ff00);
		}

	}

	private void viewStar(GameContainer gc, Renderer r) {
		r.drawFillRect(0, gc.getHeight() / 4, gc.getWidth(), gc.getHeight() / 2, 0xff000022);
		int starSize = (int) (selectedStarSystem.getMainStar().getSize() * 750);
		r.drawFillOval(Math.min(-starSize / 2, -starSize + 200), gc.getHeight() / 2 - starSize / 2, starSize, starSize,
				selectedStarSystem.getMainStar().getColor());
		int distance = 240;
		for (int i = 0; i < selectedStarSystem.getMainStar().getSatellites().length; i++) {
			int planetSize = (int) selectedStarSystem.getMainStar().getSatellites()[i].getSize();
			if (selectedStarSystem.getMainStar().getSatellites()[i].isRinged()) {
				int ringSize = selectedStarSystem.getMainStar().getSatellites()[i].getRingSize();
				distance += ringSize / 2 - planetSize / 2;
			}
			r.drawFillOval(distance, gc.getHeight() / 2 - planetSize / 2, planetSize, planetSize,
					selectedStarSystem.getMainStar().getSatellites()[i].getColor());
			int moonDistance = planetSize / 2 + 5;
			for (int j = 0; j < selectedStarSystem.getMainStar().getSatellites()[i].getSatellites().length; j++) {
				int moonSize = (int) selectedStarSystem.getMainStar().getSatellites()[i].getSatellites()[j].getSize();
				r.drawFillOval(distance - moonSize / 2 + planetSize / 2, gc.getHeight() / 2 + moonDistance, moonSize, moonSize,
						selectedStarSystem.getMainStar().getSatellites()[i].getSatellites()[j].getColor());
				moonDistance += moonSize + 5;
			}
			if (selectedStarSystem.getMainStar().getSatellites()[i].isRinged()) {
				int ringSize = selectedStarSystem.getMainStar().getSatellites()[i].getRingSize();
				r.drawFillOval(distance - ringSize / 2 + planetSize / 2, gc.getHeight() / 2 - planetSize / 10, ringSize, planetSize / 5, -1);
				distance += ringSize / 2 - planetSize / 2;
			}
			distance += planetSize + 40;
		}
	}

}
