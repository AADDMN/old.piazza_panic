package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;

public class Main extends Game {
	private int width, height;

	public SpriteBatch batch;
	public OrthographicCamera camera;
    public Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		viewport = new FitViewport(1920, 1080, camera);

	}
	@Override public void resize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		viewport.update(width, height);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
