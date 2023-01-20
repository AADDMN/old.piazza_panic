package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fridge {
    public Vector2 position;
    public Sprite sprite;
    public Fridge(Texture img)
    {
        sprite = new  Sprite(img);
        sprite.setScale(1);
        position = new Vector2(Gdx.graphics.getWidth()/2,sprite.getScaleY()*sprite.getHeight()/2);
    }

    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x,position.y);
        sprite.draw(batch);
    }
    
}
