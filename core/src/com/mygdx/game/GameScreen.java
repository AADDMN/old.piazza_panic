package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import java.util.ArrayList;

public class GameScreen implements Screen {
	final Main game;
	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public GameScreen(Main game) {
		this.game = game;

		Texture img = new Texture("one.png");
		Texture imgFridge = new Texture("fridge.png");
		
		objects.add(new Chef(img));
		objects.add(new Fridge(imgFridge));
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(181, 101, 29, 1);
		
		game.batch.begin();
		
		for (int i = 0; i < objects.size(); i++){
			GameObject currentObject = objects.get(i);

			currentObject.Update(delta);
			currentObject.Draw(game.batch);
		}

		game.batch.end();
	}

	@Override public void show() {}
	@Override public void resize(int height, int width) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	
	@Override
	public void dispose () {
		for (int i = 0; i < objects.size(); i++) {
			GameObject currentObject = objects.get(i);

			currentObject.Dispose();
		}
		objects.clear();
	}
}
