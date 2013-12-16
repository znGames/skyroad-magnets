package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public class ZigzagEnemy extends Enemy {

	public ZigzagEnemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public ZigzagEnemy(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}
	
	public ZigzagEnemy(float width, float height){
		this(new Vector2(World.getRightBorderXCoordinate(), SkyMagGame.getHeight()), width, height);
	}
	
	public void update(World world, float delta){
		// TODO
	}
	
	public boolean shouldStopExisting(World world){
		return position.y + height <= 0;
	}
	
	public void actOn(Ship ship){
		// TODO
	}
	
}
