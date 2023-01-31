package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.lang.Math;
public class Customer implements GameObject {
    public Vector2 position;
    public int type;
    public Sprite customSprite;
    public ArrayList<String> stack ;
    public Boolean happy = false;
    Texture img;

    public Customer(Texture img)
    {   
        this.img = img;
        type = (int) Math.round( Math.random() );
        stack = new ArrayList<String>();
        customSprite = new  Sprite(img);
        customSprite.setScale(1);
        position = new Vector2(10,10);
        
    }
    public void Update(float delta){}

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
    public void setHappy(){
        happy = true;
    }

    public void Dispose(){
        img.dispose();
    }


    public void addItem(String item){
        stack.add(item);
    }
    public void bin(){
        stack.clear();
    }
    
}
