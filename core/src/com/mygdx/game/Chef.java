package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chef implements GameObject{
    public Vector2 position;
    public Sprite sprite;
    public float speed = 500;
    public ArrayList<String> stack ;
    int turn = 0;
    Texture img;
    public Chef(Texture img)
    {
        stack = new ArrayList<String>();
        this.img = img;
        sprite = new  Sprite(img);
        sprite.setScale(1);
        position = new Vector2(1700,200);

    } 
    public void Update(float deltaTime)
    {   
        if(turn == 1){
        if(Gdx.input.isKeyPressed(Keys.A)) position.x -= deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.D)) position.x += deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.W)) position.y += deltaTime * speed;
        if(Gdx.input.isKeyPressed(Keys.S)) position.y -= deltaTime * speed;

        if(position.x - (sprite.getWidth()*sprite.getScaleX()/2)<=0) position.x = (sprite.getWidth()*sprite.getScaleX()/2);
        if(position.x + (sprite.getWidth()*sprite.getScaleX()/2)>=Gdx.graphics.getWidth()) position.x = Gdx.graphics.getWidth()-(sprite.getWidth()*sprite.getScaleX()/2);
        if(position.y - (sprite.getHeight()*sprite.getScaleY()/2)<=0) position.y = (sprite.getHeight()*sprite.getScaleY()/2);        
        if(position.y + (sprite.getHeight()*sprite.getScaleY()/2)>=Gdx.graphics.getHeight()) position.y = Gdx.graphics.getHeight()-(sprite.getHeight()*sprite.getScaleY()/2);
        }
        

        
    }
    public void turn(){
        turn = 1;

    }
    public void unTurn(){
        turn = 0;

    }
    public void Draw(SpriteBatch batch)
    {
        Update(Gdx.graphics.getDeltaTime());
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
    public void Dispose(){
        img.dispose();
    }
}