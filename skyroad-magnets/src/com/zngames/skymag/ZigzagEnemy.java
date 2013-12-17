package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public class ZigzagEnemy extends Enemy {

	int direction = 1;
	int acceleration = 1;
	Vector2 velocity = new Vector2(0,0);
	
	public ZigzagEnemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public ZigzagEnemy(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}
	
	public ZigzagEnemy(float width, float height){
		this(new Vector2(World.getRightBorderXCoordinate(), SkyMagGame.getHeight()), width, height);
	}
	
	public ZigzagEnemy(float y){
		this(new Vector2(World.getRightBorderXCoordinate(), y), SkyMagGame.getWidth()/18, SkyMagGame.getWidth()/18);
	}
	
	public ZigzagEnemy(){
		this(new Vector2(World.getRightBorderXCoordinate(), SkyMagGame.getHeight()), SkyMagGame.getWidth()/18, SkyMagGame.getWidth()/18);
	}
	
	public void update(World world, float delta){
		velocity.add((new Vector2(direction*acceleration*delta/50,0)));
		//if(!((direction == 1) ^ (position.x <= World.getMiddleBorderXCoordinate()))){
		if((direction == 1 && position.x <= World.getMiddleBorderXCoordinate()) || (direction == -1 && position.x >= World.getMiddleBorderXCoordinate())){
			acceleration *= -1;
		}
		if(position.x <= World.getLeftBorderXCoordinate() || position.x >= World.getRightBorderXCoordinate() || velocity.x <= 0){
			direction *= -1;
		}
	}
	
	public boolean shouldStopExisting(World world){
		return position.y + height <= 0;
	}
	
	public void actOn(Ship ship){
		// TODO
	}
	
}
