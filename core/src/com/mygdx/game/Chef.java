package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chef implements GameObject {
    public Vector2 position;
    public Sprite sprite;
    public float speed = 250;

    Texture img;

    public ArrayList<String> stack ;

    public Chef(Texture img)
    {
        stack = new ArrayList<String>();
	    this.img = img;
        sprite = new Sprite(img);
        sprite.setScale(1);
        position = new Vector2(Gdx.graphics.getWidth()/2,sprite.getScaleY()*sprite.getHeight()/2);

    } 
    
    public void Update(float deltaTime)
    {
        if(Gdx.input.isKeyPressed(Keys.A)) position.x -= deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.D)) position.x += deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.W)) position.y += deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.S)) position.y -= deltaTime * speed;

	float spriteWidthOffset = (sprite.getWidth() * sprite.getScaleX()) / 2;
	float spriteHeightOffset = (sprite.getHeight() * sprite.getScaleY()) / 2;

        if (position.x <= 0) {
		position.x = 0;
	}
	
	if (position.x + spriteWidthOffset >= Gdx.graphics.getWidth()) {
		position.x = Gdx.graphics.getWidth() - spriteWidthOffset;
	}

        if (position.y <= 0) {
		position.y = 0;
	}

	if (position.y + spriteHeightOffset >= Gdx.graphics.getHeight()) {
		position.y = Gdx.graphics.getHeight() - spriteHeightOffset;
	}
    }

    public void Draw(SpriteBatch batch)
    {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public String get(){
        if(stack.isEmpty()){
           return null;
        }
        return stack.get(stack.size() -1);

    }

    public void addItem(String item){
        stack.add(item);
    }

    public void bin(){
        stack.clear();
    }
    
    public void Dispose()
    {
	img.dispose();
    }
}
