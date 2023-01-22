package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

interface GameObject {
	public void Update(float delta);
	public void Draw(SpriteBatch batch);
}
