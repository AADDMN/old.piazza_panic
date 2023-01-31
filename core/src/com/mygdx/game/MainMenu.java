package com.mygdx.game;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class MainMenu implements Screen {
	final Main game;

	private BitmapFont font;
	private int width, height;
	private Texture imgStart;
	private Viewport viewport;
    private Camera camera;
	public MainMenu(Main game) {
		camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(camera);
		this.game = game;
		this.font = new BitmapFont();
		imgStart = new Texture("menu (1).png");
		
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		
		
		
		camera.update();
		game.batch.begin();
		game.batch.draw(imgStart, 0, 0,viewport.getWorldWidth(),viewport.getWorldHeight());


		game.batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new GameScreen(game));
		}

	}

	@Override public void show() {}
	
	@Override public void resize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		viewport.update(width, height);

	}
	
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	
	@Override
	public void dispose () {
	}
	
}

