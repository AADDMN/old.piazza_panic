package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;


public class GameScreen implements Screen {
	final Main game;
	
	Texture img;
	Texture imgFridge;
	Chef chef1;
	Fridge fridge;
	
	public GameScreen(Main game) {
		this.game = game;

		img = new Texture("one.png");
		imgFridge = new Texture("fridge.png");
		chef1 = new Chef(img);
		fridge = new Fridge(imgFridge);
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(181, 101, 29, 1);
		
		game.batch.begin();
		
		chef1.Draw(game.batch, delta);
		fridge.Draw(game.batch, delta);
		
		game.batch.end();
	}

	@Override public void show() {}
	@Override public void resize(int height, int width) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	
	@Override
	public void dispose () {
		img.dispose();
		imgFridge.dispose();
	}
}
