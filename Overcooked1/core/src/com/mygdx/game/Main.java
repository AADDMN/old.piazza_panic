package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture imgFridge;
	Chef chef1;
	Fridge fridge;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("one.png");
		imgFridge = new Texture("fridge.png");
		chef1 = new Chef(img);
		fridge = new Fridge(imgFridge);
	}

	@Override
	public void render () {
		ScreenUtils.clear(181, 101, 29, 1);
		batch.begin();
		chef1.Draw(batch);
		fridge.Draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
