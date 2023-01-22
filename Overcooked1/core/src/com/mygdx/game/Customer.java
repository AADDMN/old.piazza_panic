package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Customer {
    public Vector2 position;
    public Sprite customSprite;
    public ArrayList<String> stack ;



    public Customer(Texture img)
    {
        stack = new ArrayList<String>();
        customSprite = new  Sprite(img);
        customSprite.setScale(1);
        position = new Vector2(10,10);
        
    }

    public void Draw(SpriteBatch batch)
    {
        customSprite.setPosition(position.x,position.y);
        customSprite.draw(batch);
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
    
}
