package com.zngames.skymag;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zngames.assets.Assets;
import com.zngames.screens.GameScreen;
import com.zngames.viewport.VirtualViewport;

public class SkyMagGame extends Game {
	public static String LOG = "MyGdxGame";
	
	public OrthographicCamera cam;
	public SpriteBatch batch;
	
	private VirtualViewport vvp;
	private FPSLogger log;
	private MInput input;
	
	@Override
	public void create() {		
		log = new FPSLogger();
		vvp = new VirtualViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		cam = new OrthographicCamera(vvp.world_x,vvp.world_y);
		cam.position.set(vvp.world_x/2f,vvp.world_y/2f,0f);
		
		batch = new SpriteBatch();
		
		input = new MInput(this,true);
		
		Gdx.input.setInputProcessor(input);
		Gdx.input.setCatchBackKey(true);
		
		Assets.load();
				
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		Assets.dispose();
	}

	@Override
	public void render() {		
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
	
	public VirtualViewport getVvp(){
		return vvp;
	}
	
	public void setVvp(VirtualViewport vvp){
		this.vvp = vvp;
	}

	public MInput getInput(){
		return input;
	}
}
