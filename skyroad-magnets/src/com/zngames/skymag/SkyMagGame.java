package com.zngames.skymag;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkyMagGame extends Game {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private TextureRegion region;
	private FPSLogger log;

	
	@Override
	public void create() {		
		/*float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);*/
		
		//batch = new SpriteBatch();
		log = new FPSLogger();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		//batch.dispose();
		//texture.dispose();
		super.dispose();
	}

	@Override
	public void render() {		
		/*Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();*/
		
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		//region = new TextureRegion(texture, 20, 20, 50, 50);
		//sprite = new Sprite(texture, 20, 20, 50, 50);
		//sprite.setPosition(100, 10);
		//sprite.setColor(0, 0, 1, 1);

		//batch.begin();
		//batch.setColor(1, 0, 0, 1);
		//batch.draw(texture, 10, 10);
		//batch.setColor(0, 1, 0, 1);
		//batch.draw(region, 50, 10);
		//sprite.draw(batch);
		//batch.end();
		
		super.render();
		log.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
