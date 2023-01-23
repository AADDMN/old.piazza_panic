package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fridge implements GameObject{
    public Vector2 position;
    public Sprite sprite;

    Texture img;

    public Fridge(Texture img)
    {
	this.img = img;
        sprite = new Sprite(img);
        sprite.setScale(1);
        position = new Vector2(Gdx.graphics.getWidth()/2,sprite.getScaleY()*sprite.getHeight()/2);
    }

    public Fridge(Texture img, Vector2 position)
    {
        this.img = img;
	sprite = new Sprite(img);
	sprite.setScale(1);
	this.position = position;
    }

    public void Update(float delta) {};

    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x,position.y);
        sprite.draw(batch);
    }

    public void Dispose()
    {
	img.dispose();
    }
    
}
