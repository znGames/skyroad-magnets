package com.zngames.skymag;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity {

	public Enemy(TextureRegion region) {
		super(region);
		// TODO Auto-generated constructor stub
	}

	/*public Enemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public Enemy(float x, float y, float width, float height){
		super(x, y, width, height);
	}
	
	public abstract void update(World world, float delta);
	
	public abstract boolean shouldStopExisting(World world);
	
	public abstract void actOn(Ship ship);*/
	
}
