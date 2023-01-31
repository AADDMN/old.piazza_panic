package com.mygdx.game;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class MainMenu implements Screen {
	final Main game;

	private BitmapFont font;
	private int width, height;
	private Texture imgStart;

	public MainMenu(Main game) {
		this.game = game;
		this.font = new BitmapFont();
		imgStart = new Texture("menu.png");

	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		
		game.batch.begin();
		game.batch.draw(imgStart, 0, 0);


		game.batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new GameScreen(game));
		}

	}

	@Override public void show() {}
	
	@Override public void resize(int height, int width) 
	{
		this.width = width;
		this.height = height;
	}
	
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	
	@Override
	public void dispose () {
	}
}
