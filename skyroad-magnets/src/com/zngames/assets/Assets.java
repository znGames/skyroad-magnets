package com.zngames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	private static TextureAtlas atlas;
	
	/*public static TextureRegion ship;
	public static TextureRegion coin;
	public static TextureRegion key;
	public static TextureRegion magnet;
	public static TextureRegion hole;*/
	public static TextureRegion square;
	public static TextureRegion circle;
	public static TextureRegion borderedSquare;
	
	public static void load(){
		String dpi = SettingsManager.getDpi();
		
		atlas = new TextureAtlas(Gdx.files.internal("images/"+dpi+"-atlas/"+dpi+"-atlas.txt"),Gdx.files.internal("images/"+dpi+"-atlas/"));
	
		/*ship = atlas.findRegion("ship");
		coin = atlas.findRegion("coin");
		key = atlas.findRegion("key");
		magnet = atlas.findRegion("magnet");
		hole = atlas.findRegion("hole");*/
		square = atlas.findRegion("square");
		circle = atlas.findRegion("circle");
		borderedSquare = atlas.findRegion("borderedSquare");
	}
	
	public static void dispose(){
		atlas.dispose();
	}
}
