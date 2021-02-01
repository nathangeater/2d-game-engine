package com.nkggame.game;

import java.awt.event.KeyEvent;

import com.nkggame.engine.AbstractGame;
import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;
import com.nkggame.engine.audio.SoundClip;
import com.nkggame.engine.gfx.Image;

public class GameManager extends AbstractGame {

	private Image image;
	private SoundClip clip;
	
	public GameManager() {
		image = new Image("/test.png");
		clip = new SoundClip("/audio/monkiflip.wav");
		clip.setVolume(0f);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			clip.play();
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}

	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
}
