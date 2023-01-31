package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;

public class Main extends Game {
	
	public SpriteBatch batch;
	public OrthographicCamera camera;
    public Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		//viewport = new FitViewport(100, 480, camera);

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
