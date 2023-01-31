package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    final Main game;
    enum Screen{
        GAME_OVER,MAIN_GAME;
    }
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    BitmapFont font;
    Screen currentScreen = Screen.MAIN_GAME;

	SpriteBatch batch;
	Texture img;
	Texture img2;
	Texture backGroundImage;
	Texture imgFridge;
	Texture imgWin;

	Texture imgStack;
	Texture imgChop;
	Texture imgBowl;
	Texture imgCust;
	Texture imgGrill;
	Texture imgPress;
	Texture imgPlate;
	Chef chef1;
	Chef chef2;
	Chef[] chefs = new Chef[2];
	Customer[] customers = new Customer[5];
	int numCust = 5;
	int turn = 0;
	Station fridge;
	Station chop;
	Station bowl;
	Station grill;
	Station press;
	Station plate;
	Rectangle stack;
	Skin skin;
	Float time = (float) 0;
	int[] custSpace = { 0, 150, 350, 550, 700 };
	int happyCount = 0;

    public GameScreen(Main game){
        this.game = game;
        font = new BitmapFont();

		//Using skin to change the image of the stations when interacted with

		skin = new Skin();
		skin.add("chopDefault", new Texture("chop.png"));
		skin.add("chopLettuce", new Texture("chopLettuce.png"));
		skin.add("chopTomato", new Texture("chopTomato.png"));
		skin.add("chopOnion", new Texture("chopOnion.png"));

		skin.add("pressDefault", new Texture("press.png"));
		skin.add("pressPatty", new Texture("pressPatty.png"));

		skin.add("bowlDefault", new Texture("bowl.png"));
		skin.add("bowlL", new Texture("lettuceBowl.png"));
		skin.add("bowlO", new Texture("onionBowl.png"));
		skin.add("bowlT", new Texture("tomatoBowl.png"));
		skin.add("bowlTL", new Texture("LettuceTomatoBowl.png"));
		skin.add("bowlTLO", new Texture("saladBowl.png"));
		skin.add("bowlTO", new Texture("tomatoOnionBowl.png"));
		skin.add("bowlOL", new Texture("LettuceOnionBowl.png"));

		skin.add("plateDefault", new Texture("plate.png"));
		skin.add("plateB", new Texture("plateB.png"));
		skin.add("plateP", new Texture("plateP.png"));
		skin.add("plateC", new Texture("plateC.png"));
		skin.add("plateBP", new Texture("plateBP.png"));
		skin.add("plateBC", new Texture("plateBC.png"));
		skin.add("platePC", new Texture("platePC.png"));
		skin.add("plateBPC", new Texture("plateBPC.png"));

		skin.add("grillDefault", new Texture("grill.png"));
		skin.add("grillPatty", new Texture("grillPatty.png"));
		skin.add("grillCooked", new Texture("grillCooked.png"));

		skin.add("burgerCust", new Texture("burgerCust.png"));
		skin.add("saladCust", new Texture("saladCust.png"));
		skin.add("customer", new Texture("customer.png"));

		//Giving all the textures the image they relate to
		img = new Texture("one.png");
		img2 = new Texture("two.png");
		imgWin = new Texture("win.png");
		backGroundImage = new Texture("background.png");

		imgStack = new Texture("empty.png");
		imgFridge = new Texture("fridge.png");
		imgChop = new Texture("chop.png");
		imgBowl = new Texture("bowl.png");

		imgCust = new Texture("customer.png");
		imgGrill = new Texture("grill.png");
		imgPress = new Texture("press.png");
		imgPlate = new Texture("plate.png");
		chef1 = new Chef(img);
		chef2 = new Chef(img2);
		chef1.turn();

		chefs[0] = chef1;
		chefs[1] = chef2; 


		fridge = new Station(imgFridge);
		chop = new Station(imgChop);
		bowl = new Station(imgBowl);
		grill = new Station(imgGrill);
		press = new Station(imgPress);
		plate = new Station(imgPlate);

		//Positioning all the stations
		chop.position.x = 1450;
		chop.position.y = 50;
		bowl.position.x = 1200;
		bowl.position.y = 50;
		grill.position.x = 1200;
		grill.position.y = 960;
		press.position.x = 1450;
		press.position.y = 960;
		plate.position.x = 950;
		plate.position.y = 960;
        fridge.position.x = 1750;
		fridge.position.y = 540;

		//Creating the customers at incrementing 5 second delays
		float delay1 = 5;
		float delay2 = 10;
		float delay3 = 15;
		float delay4 = 20;

			Timer.schedule(new Task() {
				@Override
				public void run() {
					customers[1] = new Customer(imgCust);
			customers[1].position.y = custSpace[1];
				}
			}, delay1);

			Timer.schedule(new Task() {
				@Override
				public void run() {
					customers[2] = new Customer(imgCust);
			customers[2].position.y = custSpace[2];
				}
			}, delay2);

			Timer.schedule(new Task() {
				@Override
				public void run() {
					customers[3] = new Customer(imgCust);
			customers[3].position.y = custSpace[3];
				}
			}, delay3);

			Timer.schedule(new Task() {
				@Override
				public void run() {
					customers[4] = new Customer(imgCust);
			customers[4].position.y = custSpace[4];
				}
			}, delay4);
		
		
			customers[0] = new Customer(imgCust);
			customers[0].position.y = custSpace[0];
			

    }

    public void render(float delta){

		for(int a= 0; a<2 ; a++){
			if (chefs[a].position.x < 0){
				chefs[a].position.x = 0;
			}
		}
		for(int b= 0; b<2 ; b++){
			if (chefs[b].position.x > 1800){
				chefs[b].position.x = 1800;
			}
		}
		for(int c= 0; c<2 ; c++){
			if (chefs[c].position.y < 0){
				chefs[c].position.y = 0;
			}
		}for(int d= 0; d<2 ; d++){
			if (chefs[d].position.y > 960){
				chefs[d].position.y = 960;
			}
		}


	//Switch to end screen if conditions are met
        if(currentScreen == Screen.GAME_OVER){
            Gdx.gl.glClearColor(.25f, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.batch.begin();
			font.getData().setScale(4);

			game.batch.draw(imgWin, 600, 250);
            font.draw(game.batch,"Time = " + time.toString(), Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .15f);
			if (Gdx.input.isKeyPressed(Input.Keys.R)) {
				game.setScreen(new GameScreen(game));
			}
            game.batch.end();
        }
        if (currentScreen == Screen.MAIN_GAME){
        ScreenUtils.clear(181, 101, 29, 1);
		game.camera.update();

		game.batch.setProjectionMatrix(game.camera.combined);

game.batch.begin();
time +=Gdx.graphics.getDeltaTime();

for(int i = 0; i<objects.size(); i++){
    GameObject currentObject = objects.get(i);
    currentObject.Update(delta);
    currentObject.Draw(game.batch);

}
game.batch.draw(backGroundImage, 0, 0);

		
		fridge.Draw(game.batch);
		chop.Draw(game.batch);
		bowl.Draw(game.batch);
		grill.Draw(game.batch);
		press.Draw(game.batch);
		plate.Draw(game.batch);
        chef1.Draw(game.batch);
		chef2.Draw(game.batch);
		game.batch.draw(imgStack, 1700, 900);

		//Switch active chef
		if(Gdx.input.isKeyPressed(Keys.NUM_1)){
			chef1.turn();
			chef2.unTurn();
			turn = 0;
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_2)){
			chef2.turn();
			chef1.unTurn();
			turn = 1;
		}
		for (int i = 0; i < customers.length; i++) {
			if(customers[i] == null){
				break;
			}
			if(customers[i].happy == false){
			customers[i].Draw(game.batch);
			
			}
			if (customers[i].type == 0) {
				customers[i].customSprite = skin.getSprite("saladCust");
			} else {
				customers[i].customSprite = skin.getSprite("burgerCust");

			}
		}
		if(customers.length == 5){
			happyCount = 0;
			for(int x = 0;x<5;x++){
				if(customers[x] == null){
					break;
				}
				if(customers[x].happy ==true){
					happyCount ++;
					
				}
				if (happyCount == 5){
					currentScreen = Screen.GAME_OVER;

				}
			}
		}
//Checks if customers are given correct item and removes them if so
		 for(int j = 0;j<customers.length;j++){
			if(customers[j] == null){
				break;
			}
			if (customers[j].happy == false){
		 if (customers[j].type == 0){
		 if(chefs[turn].sprite.getBoundingRectangle().overlaps(customers[j].customSprite.getBoundingRectangle())
		 & Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "fullBowl"){
		 customers[j].addItem("fullBowl");
		 customers[j].happy = true;
		 chefs[turn].bin();
		 }
		 }
		 if (customers[j].type == 1){
		 if(chefs[turn].sprite.getBoundingRectangle().overlaps(customers[j].customSprite.getBoundingRectangle())
		 & Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Burger"){
		 customers[j].addItem("Burger");
		 customers[j].happy = true;
		 chefs[turn].bin();
		 }
		 }}
		}

		
		
		

	//Allows chef's to take items from fridge	

		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.L)) {
			chefs[turn].addItem("Lettuce");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.C)) {
			chefs[turn].addItem("Cheese");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.T)) {
			chefs[turn].addItem("Tomato");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.O)) {
			chefs[turn].addItem("Onion");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.M)) {
			chefs[turn].addItem("Mince");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(fridge.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.B)) {
			chefs[turn].addItem("Bread");
		}
		if (Gdx.input.isKeyPressed(Keys.E)) {
			chefs[turn].bin();
		}

		//changes image of item in inventory
		if (chefs[turn].get() == null) {
			imgStack = new Texture("empty.png");
		}
		if (chefs[turn].get() == "Onion") {
			imgStack = new Texture("onion.png");
		}
		if (chefs[turn].get() == "Tomato") {
			imgStack = new Texture("tomato.png");
		}
		if (chefs[turn].get() == "Lettuce") {
			imgStack = new Texture("lettuce.png");
		}
		if (chefs[turn].get() == "Cheese") {
			imgStack = new Texture("cheese.png");
		}
		if (chefs[turn].get() == "Mince") {
			imgStack = new Texture("mince.png");
		}
		if (chefs[turn].get() == "Patty") {
			imgStack = new Texture("patty.png");
		}
		if (chefs[turn].get() == "cookedPatty") {
			grill.bin();
			imgStack = new Texture("cookedPatty.png");
		}
		if (chefs[turn].get() == "Bread") {
			imgStack = new Texture("bread.png");
		}
		if (chefs[turn].get() == "cutLettuce") {
			imgStack = new Texture("cutLettuce.png");
		}
		if (chefs[turn].get() == "cutTomato") {
			imgStack = new Texture("cutTomato.png");
		}
		if (chefs[turn].get() == "cutOnion") {
			imgStack = new Texture("cutOnion.png");
		}
		if (chefs[turn].get() == "Burger") {
			imgStack = new Texture("burger.png");
		}
		if (chop.get() == "Lettuce") {
			chop.stationSprite = skin.getSprite("chopLettuce");
		}
		if (chop.get() == "Onion") {
			chop.stationSprite = skin.getSprite("chopOnion");
		}
		if (chop.get() == "Tomato") {
			chop.stationSprite = skin.getSprite("chopTomato");
		}
		if (press.get() == "Patty") {
			press.stationSprite = skin.getSprite("pressPatty");
		}

		if (press.get() == null) {
			press.stationSprite = skin.getSprite("pressDefault");
		}

		if (grill.get() == "Patty") {
			grill.stationSprite = skin.getSprite("grillPatty");
			float delay = 3;
			//wait 5 seconds then changes the raw patty into a cooked patty
			Timer.schedule(new Task() {
				@Override
				public void run() {
					grill.bin();
					grill.addItem("cookedPatty");
				}
			}, delay);

		}
		if (grill.get() == "cookedPatty") {
			grill.stationSprite = skin.getSprite("grillCooked");
		}
		if (grill.get() == null) {
			grill.stationSprite = skin.getSprite("grillDefault");
		}

		if (chefs[turn].get() == "fullBowl") {
			imgStack = new Texture("saladBowl.png");
		}
		if (chop.get() == null) {
			chop.stationSprite = skin.getSprite("chopDefault");
		}

		if (bowl.stack.contains("cutTomato")) {
			bowl.stationSprite = skin.getSprite("bowlT");
		}
		if (bowl.stack.contains("cutOnion")) {
			bowl.stationSprite = skin.getSprite("bowlO");
		}
		if (bowl.stack.contains("cutLettuce")) {
			bowl.stationSprite = skin.getSprite("bowlL");
		}
		if (bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion")) {
			bowl.stationSprite = skin.getSprite("bowlOL");
		}
		if (bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutTomato")) {
			bowl.stationSprite = skin.getSprite("bowlTL");
		}
		if (bowl.stack.contains("cutTomato") & bowl.stack.contains("cutOnion")) {
			bowl.stationSprite = skin.getSprite("bowlTO");
		}
		if (bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion") & bowl.stack.contains("cutTomato")) {
			bowl.stationSprite = skin.getSprite("bowlTLO");
		}
		if (bowl.get() == null) {
			bowl.stationSprite = skin.getSprite("bowlDefault");
		}

		if (bowl.stack.contains("cutLettuce") & bowl.stack.contains("cutOnion") & bowl.stack.contains("cutTomato")
				& chefs[turn].sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER)) {
			bowl.bin();
			chefs[turn].addItem("fullBowl");
		}

		if (plate.stack.contains("Cheese")) {
			plate.stationSprite = skin.getSprite("plateC");
		}
		if (plate.stack.contains("Bread")) {
			plate.stationSprite = skin.getSprite("plateB");
		}
		if (plate.stack.contains("cookedPatty")) {
			plate.stationSprite = skin.getSprite("plateP");
		}
		if (plate.stack.contains("cookedPatty") & plate.stack.contains("Bread")) {
			plate.stationSprite = skin.getSprite("plateBP");
		}
		if (plate.stack.contains("cookedPatty") & plate.stack.contains("Cheese")) {
			plate.stationSprite = skin.getSprite("platePC");
		}
		if (plate.stack.contains("Cheese") & plate.stack.contains("Bread")) {
			plate.stationSprite = skin.getSprite("plateBC");
		}
		if (plate.stack.contains("cookedPatty") & plate.stack.contains("Bread") & plate.stack.contains("Cheese")) {
			plate.stationSprite = skin.getSprite("plateBPC");
		}
		if (plate.get() == null) {
			plate.stationSprite = skin.getSprite("plateDefault");
		}

		if (plate.stack.contains("Bread") & plate.stack.contains("cookedPatty") & plate.stack.contains("Cheese")
				& chefs[turn].sprite.getBoundingRectangle().overlaps(plate.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER)) {
			plate.bin();
			chefs[turn].addItem("Burger");
		}
		//chef interacts with chopping stations to chop items
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER) & chop.get() == "Lettuce") {
			chefs[turn].addItem("cutLettuce");
			chop.bin();

		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Lettuce") {
			chefs[turn].bin();
			chop.addItem("Lettuce");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER) & chop.get() == "Onion") {
			chefs[turn].addItem("cutOnion");
			chop.bin();

		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Onion") {
			chefs[turn].bin();
			chop.addItem("Onion");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER) & chop.get() == "Tomato") {
			chefs[turn].addItem("cutTomato");
			chop.bin();

		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(chop.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Tomato") {
			chefs[turn].bin();
			chop.addItem("Tomato");
		}

		if (chefs[turn].sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "cutTomato") {
			chefs[turn].bin();
			bowl.addItem("cutTomato");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "cutLettuce") {
			chefs[turn].bin();
			bowl.addItem("cutLettuce");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "cutOnion") {
			chefs[turn].bin();
			bowl.addItem("cutOnion");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(bowl.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.E)) {
			bowl.bin();
		}

		if (chefs[turn].sprite.getBoundingRectangle().overlaps(press.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Mince") {
			chefs[turn].bin();
			press.addItem("Patty");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(press.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER) & press.get() == "Patty") {
			chefs[turn].addItem("Patty");
			press.bin();

		}

		if (chefs[turn].sprite.getBoundingRectangle().overlaps(grill.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Patty") {
			chefs[turn].bin();
			grill.addItem("Patty");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(grill.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.ENTER) & grill.get() == "cookedPatty") {
			grill.bin();
			chefs[turn].addItem("cookedPatty");

		}

		if (chefs[turn].sprite.getBoundingRectangle().overlaps(plate.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "cookedPatty") {
			chefs[turn].bin();
			plate.addItem("cookedPatty");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(plate.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Bread") {
			chefs[turn].bin();
			plate.addItem("Bread");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(plate.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.SPACE) & chefs[turn].get() == "Cheese") {
			chefs[turn].bin();
			plate.addItem("Cheese");
		}
		if (chefs[turn].sprite.getBoundingRectangle().overlaps(plate.stationSprite.getBoundingRectangle())
				& Gdx.input.isKeyPressed(Keys.E)) {
			plate.bin();
		}
        game.batch.end();
    }
    }
    @Override public void show(){}
    @Override public void resize(int height, int width){}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    
    @Override
    public void dispose(){
        for(int i = 0; i<objects.size();i++){
            GameObject currentObject = objects.get(i);
            currentObject.Dispose();
        }
        objects.clear();
    }

}
