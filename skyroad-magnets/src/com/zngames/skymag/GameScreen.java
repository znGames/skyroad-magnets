package com.zngames.skymag;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	SkyMagGame game;
	World world;
	WorldRenderer renderer;
	
	public GameScreen(SkyMagGame game){
		this.game = game;
		world = new World(game);
		renderer = new WorldRenderer(world);
	}
	
	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		//world.dispose();
		renderer.dispose();
	}

}
