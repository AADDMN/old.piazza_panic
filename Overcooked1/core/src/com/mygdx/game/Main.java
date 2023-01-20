package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Chef chef1;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("one.png");
		chef1 = new Chef(img);
	}

	@Override
	public void render () {
		ScreenUtils.clear(181, 101, 29, 1);
		batch.begin();
		chef1.Draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
