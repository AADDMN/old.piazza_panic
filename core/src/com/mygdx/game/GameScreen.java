package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen implements Screen {
	final Main game;
	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();

	Skin skin;

	Texture img;
	Texture imgFridge;
	Texture imgStack;
	Texture imgChop;
	Texture imgBowl;
	Texture imgCust;

	Station fridge;
	Station chop;
	Station bowl;

	Chef chef1;

	Rectangle stack;

	Customer[] customers = new Customer[1];
	int numCust = 1;

	public GameScreen(Main game) {
		this.game = game;

		Texture img = new Texture("one.png");
		Texture imgFridge = new Texture("fridge.png");

		chef1 = new Chef(img);

		objects.add(chef1);

		skin = new Skin();
        skin.add("chopDefault", new Texture("chop.jpg"));
        skin.add("chopLettuce", new Texture("chopLettuce.png"));
		skin.add("chopTomato", new Texture("chopTomato.png"));
        skin.add("chopOnion", new Texture("chopOnion.png"));

		skin.add("bowlDefault", new Texture("bowl.png"));
        skin.add("bowlL", new Texture("bowlL.png"));
		skin.add("bowlO", new Texture("bowlO.png"));
        skin.add("bowlT", new Texture("bowlT.png"));
		skin.add("bowlTL", new Texture("bowlTL.png"));
		skin.add("bowlTLO", new Texture("bowlTLO.png"));
        skin.add("bowlTO", new Texture("bowlTO.png"));
        skin.add("bowlOL", new Texture("bowlOL.png"));

		imgStack = new Texture("empty.png");
		imgChop = new Texture("chop.jpg");
		imgBowl = new Texture("bowl.png");
		imgCust = new Texture("customer.png");

		fridge = new Station(imgFridge);
		chop = new Station(imgChop);
		bowl = new Station(imgBowl);

		chop.position.x= 900;
		chop.position.y= 400;
		bowl.position.x= 200;
		bowl.position.y= 400;

		int i = 0;
		for(int y = 0;y<numCust;y++){
			customers[i] = new Customer(imgCust);
			i++;
		}
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(181, 101, 29, 1);
		
		game.batch.begin();
		
		for (int i = 0; i < objects.size(); i++){
			GameObject currentObject = objects.get(i);

			currentObject.Update(delta);
			currentObject.Draw(game.batch);
		}

		fridge.Draw(game.batch);
		chop.Draw(game.batch);
		bowl.Draw(game.batch);

		game.batch.draw(imgStack,700,300);

		for(int i = 0;i<customers.length;i++){
			customers[i].Draw(game.batch);
		}


		if(chef1.sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys.L)){
			chef1.addItem("Lettuce");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys.C)){
			chef1.addItem("Cheese");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys.T)){
			chef1.addItem("Tomato");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys.O)){
			chef1.addItem("Onion");
		}
		if(Gdx.input.isKeyPressed(Keys.E)){
			chef1.bin();
		}


		if(chef1.get() == null) {
			imgStack = new Texture("empty.png");
		}
		if(chef1.get() == "Onion") {
			imgStack = new Texture("onion.png");
		}
		if(chef1.get() == "Tomato") {
			imgStack = new Texture("tomato.png");
		}
		if(chef1.get() == "Lettuce") {
			imgStack = new Texture("lettuce.png");
		}
		if(chef1.get() == "Cheese") {
			imgStack = new Texture("cheese.png");
		}
		if(chef1.get() == "cutLettuce") {
			imgStack = new Texture("cutLettuce.png");
		}
		if(chef1.get() == "cutTomato") {
			imgStack = new Texture("cutTomato.png");
		}
		if(chef1.get() == "cutOnion") {
			imgStack = new Texture("cutOnion.png");
		}
		if(chop.get() == "Lettuce") {
			chop.stationSprite = skin.getSprite("chopLettuce");
		}
		if(chop.get() == "Onion") {
			chop.stationSprite = skin.getSprite("chopOnion");
		}
		if(chop.get() == "Tomato") {
			chop.stationSprite = skin.getSprite("chopTomato");
		}
		if(chef1.get() == "fullBowl") {
			imgStack = new Texture("bowlTLO.png");
		}
		if(chop.get() == null) {
			chop.stationSprite = skin.getSprite("chopDefault");
		}

		if(bowl.stack.contains("cutTomato")){
			bowl.stationSprite = skin.getSprite("bowlT");
		}
		if(bowl.stack.contains("cutOnion")){
			bowl.stationSprite = skin.getSprite("bowlO");
		}
		if(bowl.stack.contains("cutLettuce")){
			bowl.stationSprite = skin.getSprite("bowlL");
		}
		if(bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion")){
			bowl.stationSprite = skin.getSprite("bowlOL");
		}
		if(bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutTomato")){
			bowl.stationSprite = skin.getSprite("bowlTL");
		}
		if(bowl.stack.contains("cutTomato") & bowl.stack.contains("cutOnion")){
			bowl.stationSprite = skin.getSprite("bowlTO");
		}
		if(bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion")& bowl.stack.contains("cutTomato")){
			bowl.stationSprite = skin.getSprite("bowlTLO");
		}
		if(bowl.get() == null){
			bowl.stationSprite = skin.getSprite("bowlDefault");
		}

		if(bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion")& bowl.stack.contains("cutTomato") &chef1.sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. ENTER)){
			bowl.bin();
			chef1.addItem("fullBowl");
		}


		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. ENTER) & chop.get() == "Lettuce"){
			chef1.addItem("cutLettuce");
			chop.bin();
			
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "Lettuce"){
			chef1.bin();
			chop.addItem("Lettuce");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. ENTER) & chop.get() == "Onion"){
			chef1.addItem("cutOnion");
			chop.bin();
			
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "Onion"){
			chef1.bin();
			chop.addItem("Onion");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. ENTER) & chop.get() == "Tomato"){
			chef1.addItem("cutTomato");
			chop.bin();
			
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "Tomato"){
			chef1.bin();
			chop.addItem("Tomato");
		}
		
		if(chef1.sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "cutTomato"){
			chef1.bin();
			bowl.addItem("cutTomato");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "cutLettuce"){
			chef1.bin();
			bowl.addItem("cutLettuce");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. SPACE) & chef1.get() == "cutOnion"){
			chef1.bin();
			bowl.addItem("cutOnion");
		}
		if(chef1.sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle()) & Gdx.input.isKeyPressed(Keys. E) ){
			bowl.bin();
		}
		
		game.batch.end();
	}

	@Override public void show() {}
	@Override public void resize(int height, int width) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	
	@Override
	public void dispose () {
		for (int i = 0; i < objects.size(); i++) {
			GameObject currentObject = objects.get(i);

			currentObject.Dispose();
		}
		objects.clear();
	}
}
