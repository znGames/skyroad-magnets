package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity {

	public Enemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public Enemy(float x, float y, float width, float height){
		super(x, y, width, height);
	}
	
	public abstract void update(World world, float delta);
	
	public abstract boolean shouldStopExisting(World world);
	
	public abstract void actOn(Ship ship);
	
}
