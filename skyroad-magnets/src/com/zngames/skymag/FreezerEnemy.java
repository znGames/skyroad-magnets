package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public class FreezerEnemy extends Enemy {

	private float timeSinceCreation = 0;
	private boolean isFiring = false;
	public final float speed = 30;
	public final static float timeUntilFire = 5;
	
	public FreezerEnemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public FreezerEnemy(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}
	
	public FreezerEnemy(float x, float y){
		this(new Vector2(x, y), SkyMagGame.getWidth()/24, SkyMagGame.getWidth()/24);
	}
	
	public void update(World world, float delta){
		timeSinceCreation += delta;
		
		if(timeSinceCreation >= timeUntilFire){
			isFiring = true;
			return;
		}
		
		if(world.getShip().getY() < position.y){
			position.y -= speed*delta;
		} else if(world.getShip().getY() > position.y) {
			position.y += speed*delta;
		}

	}
	
	public boolean shouldStopExisting(World world){
		return timeSinceCreation >= timeUntilFire + 2;
	}
	
	public void actOn(Ship ship){
		//if(isFiring && )
		ship.changeVelocity(0, 0);
	}
	
	public boolean isFiring(){
		return isFiring;
	}
}
