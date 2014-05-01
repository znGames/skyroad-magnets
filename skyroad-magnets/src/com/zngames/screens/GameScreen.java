package com.zngames.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zngames.assets.Assets;
import com.zngames.skymag.Hole;
import com.zngames.skymag.MInput.Event;
import com.zngames.skymag.MInput.TouchEvent;
import com.zngames.skymag.Magnet;
import com.zngames.skymag.Ship;
import com.zngames.skymag.SkyMagGame;

public class GameScreen implements Screen {
	
	private SkyMagGame game;
	private Array<Hole> holes;
	private Ship ship;
	private Magnet leftMagnet;
	private Magnet rightMagnet;
	private Sprite background;
	
	public GameScreen(SkyMagGame game){
		this.game = game;
		
		float magnetSize = 0.8f;
		
		holes = new Array<Hole>();
		leftMagnet = new Magnet(new Vector2(1, game.getVvp().world_y/2f - magnetSize/2f), magnetSize, magnetSize);
		rightMagnet = new Magnet(new Vector2(game.getVvp().world_x - 1 - magnetSize, game.getVvp().world_y/2f - magnetSize/2f), magnetSize, magnetSize);
		
		background = new Sprite(Assets.square);
		background.setColor(1, 0.54f, 0, 1);
		background.setSize(game.getVvp().world_x/2f, game.getVvp().world_y);
		background.setPosition(game.getVvp().world_x/2f - background.getWidth()/2f,0);
		
		holes.add(new Hole(background.getX(),game.getVvp().world_y/2f,1.7f,true));
		
		ship = new Ship(0.7f,0.7f);
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		present(delta);
	}
	
	private void updateEvents(){
		List<Event> events = game.getInput().getEvents();
		int len = events.size();
		
		for(int i = 0 ; i < len ; i++){
			Event event = events.get(i);
			if(event.type == Event.TOUCH_DOWN){
				onTouchDown(((TouchEvent)event).x,((TouchEvent)event).y);
			}
			else if(event.type == Event.TOUCH_UP){
				onTouchUp(((TouchEvent)event).x,((TouchEvent)event).y);
			}
			else if(event.type == Event.TOUCH_DRAGGED){
				onTouchDragged(((TouchEvent)event).x,((TouchEvent)event).y);
			}
		}
	}
	
	private void onTouchDown(float x,float y){
		
	}
	
	private void onTouchUp(float x,float y){
		
	}
	
	private void onTouchDragged(float x,float y){
		
	}
	
	private void updateGame(float delta){
		ship.update(delta);
		
		for(int i = 0 ; i < holes.size ; i++){
			holes.get(i).update(delta);
		}
	}
	
	private void update(float delta){
		updateEvents();
		updateGame(delta);
	}
	
	private void present(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		game.cam.update();
		
		game.batch.setProjectionMatrix(game.cam.combined);
		game.batch.begin();
		
		background.draw(game.batch);
		
		for(int i = 0 ; i < holes.size ; i++){
			holes.get(i).draw(game.batch);
		}
		
		leftMagnet.draw(game.batch);
		rightMagnet.draw(game.batch);
		ship.draw(game.batch);
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
}
