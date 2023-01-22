package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Station extends Rectangle{
    public Vector2 position;
    public Sprite stationSprite;
    public ArrayList<String> stack ;



    public Station(Texture img)
    {
        stack = new ArrayList<String>();
        stationSprite = new  Sprite(img);
        stationSprite.setScale(1);
        position = new Vector2(Gdx.graphics.getWidth()/2,stationSprite.getScaleY()*stationSprite.getHeight()/2);
        
    }

    public void Draw(SpriteBatch batch)
    {
        stationSprite.setPosition(position.x,position.y);
        stationSprite.draw(batch);
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
