package com.nkggame.game;

import java.awt.event.KeyEvent;

import com.nkggame.engine.AbstractGame;
import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;
import com.nkggame.engine.audio.SoundClip;
import com.nkggame.engine.gfx.Image;
import com.nkggame.engine.gfx.Light;

public class GameManager extends AbstractGame {

	private Image image;
	private Image image2;
	private Image background;
	private Image image3;
	private SoundClip clip;
	private Light light;
	
	public GameManager() {
		image = new Image("/test.png");
		image2 = new Image("/test2.png");
		background = new Image("/test3.png");
		image3 = new Image("/test4.png");
		image3.setLightBlock(Light.FULL);
		image2.setAlpha(true);
		image3.setAlpha(true);
		clip = new SoundClip("/audio/monkiflip.wav");
		clip.setVolume(0f);
		light = new Light(100, 0xff00ffff);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			clip.play();
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(background, 0, 0);
		//r.drawLight(light, 0, 0);
		r.setzDepth(0);
		r.drawImage(image2, 0, 0);
		r.drawImage(image, 0, 0);
		r.drawImage(image3, 150, 100);
		r.drawImage(image3, 100, 100);
		r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}

	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
}
