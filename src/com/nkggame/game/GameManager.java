package com.nkggame.game;

import java.util.ArrayList;

import com.nkggame.engine.AbstractGame;
import com.nkggame.engine.GameContainer;
import com.nkggame.engine.Renderer;

public class GameManager extends AbstractGame {

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public GameManager() {
		objects.add(new Map(0, 0, 5000, 5000));
	}

	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColor(-1);
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
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
