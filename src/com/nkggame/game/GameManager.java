package com.nkggame.game;

import java.util.ArrayList;

import com.nkggame.engine.AbstractGame;
import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;

public class GameManager extends AbstractGame {

//	private Image image;
//	private Image image2;
//	private Image background;
//	private Image image3;
//	private SoundClip clip;
//	private Light light;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public GameManager() {
//		image = new Image("/test.png");
//		image2 = new Image("/test2.png");
//		background = new Image("/test3.png");
//		image3 = new Image("/test4.png");
//		image3.setLightBlock(Light.FULL);
//		image2.setAlpha(true);
//		image3.setAlpha(true);
//		clip = new SoundClip("/audio/monkiflip.wav");
//		clip.setVolume(0f);
//		light = new Light(1000, 0xff00ffff);
		objects.add(new Map(0, 0, 1280, 720));
	}

	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColor(-1);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
//		gc.getRenderer().setAmbientColor(-1);
//		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
//
//		}
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if(objects.get(i).isDead()) {
				objects.remove(i);
				i--;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
//		r.drawImage(background, 0, 0);
//		r.drawLight(light, 0, 0);
//		r.setzDepth(0);
//		r.drawImage(image2, 0, 0);
//		r.drawImage(image, 0, 0);
//		r.drawImage(image3, 150, 100);
//		r.drawImage(image3, 100, 100);
//		r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		
		for(GameObject obj : objects) {
			obj.render(gc, r);
		}
	}

	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setWidth(1280);
		gc.setHeight(720);
		gc.setScale(1f);
		gc.start();
	}
}
